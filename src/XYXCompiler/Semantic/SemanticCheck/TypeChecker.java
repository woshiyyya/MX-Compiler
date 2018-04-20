package XYXCompiler.Semantic.SemanticCheck;

import XYXCompiler.ASTNode.ASTRoot;
import XYXCompiler.ASTNode.Declaration.*;
import XYXCompiler.ASTNode.Expression.Binary_Expression;
import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Expression.Primitive.*;
import XYXCompiler.ASTNode.Expression.Suffix.*;
import XYXCompiler.ASTNode.Expression.Unary_Expression;
import XYXCompiler.ASTNode.Node;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.ASTNode.Type.*;
import XYXCompiler.ASTNode.Type.Base_Type.Type;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Semantic.Scope.LocalScope;
import XYXCompiler.Tools.Exceptions.SemanticException;
import XYXCompiler.Tools.Exceptions.XYXException;
import XYXCompiler.ASTNode.Expression.Unary_Expression.UnaryOP;
import XYXCompiler.ASTNode.Expression.Binary_Expression.BinaryOP;

import java.util.List;

public class TypeChecker implements ASTVisitor {

    // When building AST, Funcdecl, VarDecl, ClassDecl have got their Types;
    // Also, Newexpr & Literal Value (Bool, Int, String, Void, null) got their Types;
    // 1. CHECK TYPE EQUAL:
    //      We have to determine the type of ID from the Linked Entity,
    //      and recursively determine the Type of every Expressions then check them
    // 2. DETERMINE L-VALUE:
    //      Left Value: variables && class members && array components

    protected TypeTable typeTable;

    public TypeChecker(TypeTable typeTable) {
        this.typeTable = typeTable;
    }

    private Base_Type returntype = null;
    public static Bool_Type BOOL = new Bool_Type();
    public static Int_Type INT = new Int_Type();
    public static String_Type STRING = new String_Type();
    public static Void_Type VOID = new Void_Type();
    public static Null_Type NULL = new Null_Type();

    private void Debug(String string){
        System.out.println(string);
    }

    public boolean EqualType(Expression A, Expression B){
        if(A.type instanceof Class_Type && B.type instanceof Null_Type) return true;
        if(A.type instanceof Array_Type && B.type instanceof Null_Type) return true;

        boolean Ans = false;
        Base_Type TA = A.type;
        Base_Type TB = B.type;

        if(TA.type == TB.type)
            Ans = true;
        if(Ans && TA.type == Type.Class)
            Ans = (((Class_Type) TA).name.equals(((Class_Type) TB).name));
        else if(Ans && TA.type == Type.Array)
            Ans = EqualArrayType((Array_Type)TA,(Array_Type)TB);
        return Ans;
    }

    public boolean EqualType(Expression A, Base_Type B){
        if(A.type instanceof Class_Type && B instanceof Null_Type) return true;
        if(A.type instanceof Array_Type && B instanceof Null_Type) return true;

        boolean Ans = false;
        Base_Type TA = A.type;
        if(TA == null)
            Debug("FUCK" + A.getPosition() + A.getClass() + " " + B.toString());
        if(TA.type == B.type)
            Ans = true;
        if(Ans && TA.type == Type.Class)
            Ans = (((Class_Type) TA).name.equals(((Class_Type) B).name));
        else if(Ans && TA.type == Type.Array)
            Ans = EqualArrayType((Array_Type)TA, (Array_Type) B);
        return Ans;
    }

    public boolean EqualType(Base_Type A, Base_Type B){
        if(A instanceof Class_Type && B instanceof Null_Type) return true;
        if(A instanceof Array_Type && B instanceof Null_Type) return true;

        boolean Ans = false;
        if(A.type == B.type)
            Ans = true;
        if(Ans && A.type == Type.Class)
            Ans = (((Class_Type) A).name.equals(((Class_Type) B).name));
        else if(Ans && A.type == Type.Array)
            Ans = EqualArrayType((Array_Type) A, (Array_Type) B);
        return Ans;
    }

    private Base_Type ResolveArrayBaseType(Array_Type A){
        if(A.basetype instanceof Array_Type)
            return ResolveArrayBaseType((Array_Type) A.basetype);
        else
            return A.basetype;
    }

    private boolean EqualArrayType(Array_Type A, Array_Type B){
        int dimA = 1, dimB = 1;
        Base_Type btA, btB;
        Array_Type tem = A;
        while(tem.basetype instanceof Array_Type){
            dimA++;
            tem = (Array_Type)A.basetype;
        }
        btA = tem.basetype;

        tem = B;
        while(tem.basetype instanceof Array_Type){
            dimB++;
            tem = (Array_Type)B.basetype;
        }
        btB = tem.basetype;

        return (dimA == dimB && EqualType(btA, btB));
    }

    private void AddError(String error){
        SemanticException.exceptions.add(new XYXException(error));
    }

    private Base_Type GetEntityType(Node node){
        if(node instanceof Variable_Declaration)
            return ((Variable_Declaration) node).type;
        else if(node instanceof Global_Variable_Declaration)
            return ((Global_Variable_Declaration) node).type;
        else if(node instanceof Variable_Declaration_Statement)
            return ((Variable_Declaration_Statement) node).type;
        else return null;
    }


    private void VISIT(Node node){
        if(node != null){
            node.accept(this);
        }
    }

    @Override
    public void visit(ASTRoot node) {
        for(Declaration X:node.declarations){
            VISIT(X);
        }
    }

    @Override
    public void visit(Variable_Declaration node) {
        VISIT(node.RHS);
        if(node.type instanceof Void_Type){
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Cannot define Void Variables!"));
        }
        if(node.RHS != null && !EqualType(node.type, node.RHS.type)){
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched Type! name = " + node.name));
        }
    }

    @Override
    public void visit(Global_Variable_Declaration node) {
        VISIT(node.RHS);
        if(node.type instanceof Void_Type){
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Cannot define Void Variables!"));
        }
        if(node.RHS != null && !EqualType(node.type, node.RHS.type)){
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched Type! name = " + node.name));
        }
    }

    @Override
    public void visit(Class_Declaration node) {
        for(Declaration X: node.Members){
            VISIT(X);
        }
    }

    @Override
    public void visit(Function_Declaration node) {
        returntype  = node.returntype;
        VISIT(node.body);
        returntype = null;
    }

    @Override
    public void visit(Construct_Function node) {
        VISIT(node.body);
    }

    @Override
    public void visit(Compound_Statement node) {
        for(Statement X: node.stmts)
            VISIT(X);
    }

    @Override
    public void visit(For_Statement node) {
        VISIT(node.init);
        VISIT(node.condition);
        if(node.condition!= null && !EqualType(node.condition, BOOL))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + " For Condition must be bool type!"));
        VISIT(node.update);
        VISIT(node.body);
    }

    @Override
    public void visit(While_Statement node) {
        VISIT(node.condition);
        if(node.condition == null)
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Condition Cannot be null!"));
        if(node.condition != null && !EqualType(node.condition, BOOL))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + " While Condition must be bool type!"));
        VISIT(node.body);
    }

    @Override
    public void visit(Selection_Statement node) {
        VISIT(node.condition);
        if(node.condition == null)
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Condition Cannot be null!"));
        if(node.condition != null && !EqualType(node.condition, BOOL))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + " IF Condition must be bool type!"));
        VISIT(node.body);
        VISIT(node.Else_body);
    }

    @Override
    public void visit(Expression_Statement node) {
        VISIT(node.body);
    }

    @Override
    public void visit(Variable_Declaration_Statement node) {
        VISIT(node.RHS);
        if(node.type instanceof Void_Type){
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Cannot define Void Variables!"));
        }
        if(node.RHS != null && !EqualType(node.type, node.RHS.type))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched Type name = " + node.name));
    }

    @Override
    public void visit(Break node) {}

    @Override
    public void visit(Continue node) {}

    @Override
    public void visit(Return node) {
        VISIT(node.returnvalue);
        if(node.returnvalue == null && returntype.type != Type.Void)
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched return value type!"));
        else if(node.returnvalue != null && !EqualType(returntype, node.returnvalue.type))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched return value type!"));
    }

    @Override
    public void visit(Unary_Expression node) {
        VISIT(node.body);
        if(node.op == UnaryOP.Not){
            if(EqualType(node.body, BOOL)){
                node.setType(BOOL);
            }else{
                SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unary Expression Unmatched Type!"));
            }
        }else{
            if(EqualType(node.body, INT)){
                node.setType(INT);
            }else{
                SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unary Expression Unmatched Type!"));
            }
        }
    }

    @Override
    public void visit(Binary_Expression node) {
        VISIT(node.rhs);
        VISIT(node.lhs);
        if(!EqualType(node.lhs, node.rhs))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Binary Expression unmatched type!"));

        Base_Type HSType = node.lhs.type;

        switch (node.op){
            case LogicalAnd:case LogicalOr:{
                node.setType(BOOL);
                if(!(HSType instanceof Bool_Type))
                    AddError(node.getPosition() + " Logical Operands must be bool type!");
                break;
            }
            case Less:case Greater:case LessEqual:case GreaterEqual:{
                node.setType(BOOL);
                if(!(HSType instanceof Int_Type || HSType instanceof String_Type))
                    AddError(node.getPosition() + "Comparation Operands must be Int or String!");
                break;
            }
            case Equal:case NotEqual:{
                node.setType(BOOL);
                break;
            }
            case Plus:{
                if(HSType instanceof Int_Type)
                    node.setType(INT);
                else if(HSType instanceof String_Type)
                    node.setType(STRING);
                else
                    AddError(node.getPosition() + "Add Operands can noly be Int or String!");
                break;
            }
            case Assign:{
                node.setType(HSType);
                break;
            }
            default:{
                if(!(HSType instanceof Int_Type))
                    AddError(node.getPosition() + "Arithmatic Operands must be Int!");
                node.setType(INT);
            }
        }
    }

    @Override
    public void visit(Self_Decreasing node) {
        VISIT(node.body);
        if(!EqualType(node.body , INT))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Self decreasing only support Int type~!"));
        node.setType(INT);
    }

    @Override
    public void visit(Self_Increasing node) {
        VISIT(node.body);
        if(!EqualType(node.body , INT))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Self Increasing only support Int type~!"));
        node.setType(INT);
    }

    @Override
    public void visit(Indexing node) {
        VISIT(node.name);
        if(node.name.type instanceof Array_Type){
            Array_Type bodytype = (Array_Type)node.name.type;
            node.setType(bodytype.basetype);
        }else
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Non-arraytype cannot be subscripted! name = " + node.name.toString()));

        VISIT(node.index);
        if(!EqualType(node.index, INT))
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Index must be Integer!"));
    }

    @Override
    public void visit(Accessing node) {
        VISIT(node.body);
        LocalScope classscope = null;
        Base_Type bodytype = node.body.type;

        if(bodytype instanceof Class_Type)
            classscope = typeTable.getScope(((Class_Type) bodytype).name);
        else if(bodytype instanceof Array_Type)
            classscope = typeTable.getScope("__ARRAY__");
        else if(bodytype instanceof String_Type)
            classscope = typeTable.getScope("__STRING__");

        if(classscope != null){
            Node member = classscope.FindinClass(node.components);
            if(member instanceof Variable_Declaration){
                node.setType(((Variable_Declaration) member).type);
            }else if(member instanceof Function_Declaration){
                node.setType(((Function_Declaration) member).functype);
            }
        }else
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Undefined Member!"));
    }

    @Override
    public void visit(Class_Method node) {
        VISIT(node.body);
        for(Expression X : node.params)
            VISIT(X);

        if(!(node.body.type instanceof Func_Type))
            SemanticException.exceptions.add(new XYXException("Body is not a Class Method!"));
        else{
            List<Base_Type> ParamTypes = ((Func_Type) node.body.type).params_type;
            if(ParamTypes.size() != node.params.size()){
                SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched ClassMethod parameters number!"));
            }else{
                for(int i = 0;i < ParamTypes.size();i++){
                    if(!EqualType(ParamTypes.get(i), node.params.get(i).type))
                        SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched ClassMethod " + i +"th params type!"));
                }
            }
        }

        Func_Type bodytype = (Func_Type)node.body.type;
        node.setType(bodytype.returntype);
    }

    @Override
    public void visit(Function_call node) {
        for(Expression X: node.params)
            VISIT(X);
        Func_Type funcType = ((Function_Declaration) node.Scope.find(node.name)).functype;
        List<Base_Type> ParamTypes = funcType.params_type;

        if(ParamTypes.size() != node.params.size())
            SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched Funccall parameters number!"));
        else{
            for(int i = 0;i < ParamTypes.size();i++){
                if(!EqualType(ParamTypes.get(i), node.params.get(i).type))
                    SemanticException.exceptions.add(new XYXException(node.getPosition() + "Unmatched Funccal " + i +"th params type!"));
            }
        }

        node.setType(funcType.returntype);
    }

    @Override
    public void visit(Newexpr node) {
        VISIT(node.type);
    }

    @Override
    public void visit(Array_Type node) {
        VISIT(node.size);
        VISIT(node.basetype);
    }

    @Override
    public void visit(ID node) {
        Node Entity = node.entity;
        node.setType(GetEntityType(Entity));
    }

    @Override
    public void visit(INT node) {

    }

    @Override
    public void visit(Null node) {

    }

    @Override
    public void visit(Bool node) {

    }

    @Override
    public void visit(STRING node) {

    }

    @Override
    public void visit(Base_Type node) {

    }

    @Override
    public void visit(Statement node) {

    }

    @Override
    public void visit(Expression node) {

    }
}
