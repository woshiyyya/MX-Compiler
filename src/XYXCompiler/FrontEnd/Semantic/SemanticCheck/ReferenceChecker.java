package XYXCompiler.FrontEnd.Semantic.SemanticCheck;

import XYXCompiler.FrontEnd.ASTNode.ASTRoot;
import XYXCompiler.FrontEnd.ASTNode.Declaration.*;
import XYXCompiler.FrontEnd.ASTNode.Expression.Binary_Expression;
import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Expression.Primitive.*;
import XYXCompiler.FrontEnd.ASTNode.Expression.Suffix.*;
import XYXCompiler.FrontEnd.ASTNode.Expression.Unary_Expression;
import XYXCompiler.FrontEnd.ASTNode.Node;
import XYXCompiler.FrontEnd.ASTNode.Statement.*;
import XYXCompiler.FrontEnd.ASTNode.Type.*;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.Tools.Exceptions.SemanticException;
import XYXCompiler.Tools.Exceptions.XYXException;

import java.util.Stack;

public class ReferenceChecker implements ASTVisitor {

    //  In this Reference Checker class, we will solve the following tasks:
    //
    //      1.Recursively determine whether an expression is L-value...
    //      2.Check the invalid assignment & function calls
    //      3.Check break/continue statement in Loops

    private Stack<Node> LoopStack = new Stack<>();
    private boolean inClass = false;

    private void VISIT(Node node){
        if(node != null){
            node.accept(this);
        }
    }

    private void AddError(String error){
        SemanticException.exceptions.add(new XYXException(error));
    }

    @Override
    public void visit(ASTRoot node) {
        for(Declaration X: node.declarations)
            VISIT(X);
    }



    @Override
    public void visit(Class_Declaration node) {
        for(Declaration X :node.Members)
            VISIT(X);
    }

    @Override
    public void visit(Construct_Function node) {
        VISIT(node.body);
    }

    @Override
    public void visit(Function_Declaration node) {
        VISIT(node.body);
    }

    @Override
    public void visit(Variable_Declaration node) {
        VISIT(node.RHS);
    }

    @Override
    public void visit(Global_Variable_Declaration node) {
        VISIT(node.RHS);
    }

    @Override
    public void visit(For_Statement node) {
        LoopStack.push(node);
        VISIT(node.init);
        VISIT(node.condition);
        VISIT(node.update);
        VISIT(node.body);
        LoopStack.pop();
    }

    @Override
    public void visit(While_Statement node) {
        VISIT(node.condition);
        LoopStack.push(node);
        VISIT(node.body);
        LoopStack.pop();
    }

    @Override
    public void visit(Compound_Statement node) {
        for(Statement X:node.stmts)
            VISIT(X);
    }

    @Override
    public void visit(Selection_Statement node) {
        VISIT(node.condition);
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
    }

    @Override
    public void visit(ID node) {

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
    public void visit(Newexpr node) {
        VISIT(node.type);
    }

    //If A Expression Chain is referable, then every sub Chain must be referable!
    @Override
    public void visit(Indexing node) {
        VISIT(node.name);
        VISIT(node.index);
        if(!node.name.LValue)
            AddError(node.getPosition() + "Indexing body is not L-Value!");

    }

    @Override
    public void visit(Accessing node) {
        VISIT(node.body);
        Base_Type bodytype = node.body.type;
        if(!(bodytype instanceof String_Type || bodytype instanceof Class_Type || bodytype instanceof Array_Type))
            AddError(node.body.getPosition() + "Accessing body is not Accessible!" + node.body.type.toString());
    }

    @Override
    public void visit(Class_Method node) {
        VISIT(node.body);
        Base_Type bodytype = node.body.type;
        if(!(bodytype instanceof String_Type || bodytype instanceof Class_Type || bodytype instanceof Array_Type))
            AddError(node.getPosition() + "Class_Method body is not Accessible!");
        if(node.returntype instanceof Class_Type || node.returntype instanceof String_Type)
            node.LValue = true;
    }

    @Override
    public void visit(Function_call node) {
        for(Expression X :node.params)
            VISIT(X);

        Func_Type funcType = ((Function_Declaration) node.Scope.find(node.name)).functype;
        if(funcType.returntype instanceof Class_Type || funcType.returntype instanceof String_Type)
                node.LValue = true;
        //if(!node.body.LValue)
        //    AddError(node.getPosition() + "Funccal body is not L-Value!");
    }

    @Override
    public void visit(Self_Decreasing node) {
        VISIT(node.body);
        if(!node.body.LValue)
            AddError(node.getPosition() + "Self-- body is not L-Value!");
    }

    @Override
    public void visit(Self_Increasing node) {
        VISIT(node.body);
        if(!node.body.LValue)
            AddError(node.getPosition() + "Self++ body is not L-Value!");
    }

    @Override
    public void visit(Unary_Expression node) {
        VISIT(node.body);
        if(node.op.equals(Unary_Expression.UnaryOP.MinusMinus)
                || node.op.equals(Unary_Expression.UnaryOP.PlusPlus)){
            if(!node.body.LValue)
                AddError(node.getPosition() + "Unary body is not L-Value!");
        }
    }

    @Override
    public void visit(Binary_Expression node) {
        VISIT(node.lhs);
        VISIT(node.rhs);
        if(node.op.equals(Binary_Expression.BinaryOP.Assign)){
            if(node.lhs instanceof Function_call || node.lhs instanceof Class_Method)
                AddError(node.getPosition() + "Function call are not Assignable!");
            if(!node.lhs.LValue)
                AddError(node.getPosition() + "Assign LHS body is not L-Value!");
        }
    }

    @Override
    public void visit(Break node) {
        if(LoopStack.isEmpty())
            AddError(node.getPosition() + "Break Statement not in Loop");
    }

    @Override
    public void visit(Return node) {

    }

    @Override
    public void visit(Continue node) {
        if(LoopStack.isEmpty())
            AddError(node.getPosition() + "Continue Statement not in Loop");
    }

    @Override
    public void visit(Array_Type node) {
        if(node.size != null && (node.basetype instanceof Array_Type && ((Array_Type) node.basetype).size == null))
            AddError(node.getPosition() + "Invalid Array Type");
        VISIT(node.size);
        VISIT(node.basetype);
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
