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
import XYXCompiler.ASTNode.Type.Array_Type;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.ASTNode.Type.Class_Type;
import XYXCompiler.ASTNode.Type.TypeTable;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Semantic.Scope.GlobalScope;
import XYXCompiler.Semantic.Scope.LocalScope;
import XYXCompiler.Tools.Exceptions.SemanticException;
import XYXCompiler.Tools.Exceptions.XYXException;
import org.antlr.v4.codegen.model.decl.Decl;

import java.util.List;
import java.util.Stack;

public class ScopeTreeBuilder implements ASTVisitor {

    public final Stack<LocalScope> scopeStack;
    public GlobalScope globalScope;
    public String currentClass;
    public TypeTable typeTable;

    public ScopeTreeBuilder() {
        typeTable = new TypeTable();
        scopeStack = new Stack<>();
        currentClass = null;
    }

    private void VISIT(Node node){
        if(node != null){
            node.setScope(scopeStack.peek());
            node.accept(this);
        }
    }

    public void CreateFuncScope(List<Variable_Declaration> params){ //创建新的Scope并将其压入栈
        LocalScope localScope = new LocalScope(scopeStack.peek());
        for(Variable_Declaration X: params){
            if(HaveType(X.type))
                localScope.put(X);
            else
                SemanticException.exceptions.add(new XYXException("Undefined Type! Param's Varname = " + X.name));
        }
        scopeStack.push(localScope);
    }

    public boolean HaveType(Base_Type Type) {       //针对变量声明，检测其类是否存在
        if(Type instanceof Class_Type)
            return typeTable.find(((Class_Type) Type).name);
        else if(Type instanceof Array_Type)
            return HaveType(((Array_Type) Type).basetype);
        else return true;
    }

    @Override
    public void visit(ASTRoot node) {
        globalScope = node.globalScope;
        scopeStack.push(globalScope);

        for(Declaration X: node.declarations){  //全局函数and全局Class支持前向引用，全局变量则不支持
            if(X instanceof Class_Declaration){
                typeTable.add(X.name, X); //添加到类型表中
                globalScope.put(X);
            }else if(X instanceof Function_Declaration){
                globalScope.put(X);
            }
        }

        for(Declaration X: node.declarations){
            VISIT(X);
        }

        scopeStack.pop();
    }


    @Override
    public void visit(Global_Variable_Declaration node) {
        if(!HaveType(node.type))
            SemanticException.exceptions.add(new XYXException("Undefined Type! Global Varname = " + node.name));

        LocalScope currentScope = scopeStack.peek();

        //给子表达式setScope，setEntity完再添加进scope里
        VISIT(node.RHS);
        currentScope.put(node);
    }

    @Override
    public void visit(Class_Declaration node) {
        LocalScope currentScope = new LocalScope(scopeStack.peek());  //进入Class，建立新域
        scopeStack.push(currentScope);
        currentClass = node.name;

        // 建立Class名与其定义域的映射
        typeTable.LinkClassScope(node.name, currentScope);

        //Class 内部函数支持前向引用
        for(Declaration X: node.Members){
            if(X instanceof Function_Declaration)
                currentScope.put(X);
        }

        // 给成员变量声明添加进Scope
        for(Declaration X: node.Members){       //未检查构造函数个数
            VISIT(X);   //TROUBLE~!!!  Solved at Line167
            if(!(X instanceof Function_Declaration)){
                System.out.println(X.name);
                currentScope.put(X);
            }
        }

        scopeStack.pop();
        currentClass = null;
    }

    @Override
    public void visit(Function_Declaration node) {
        if(!HaveType(node.returntype))
            SemanticException.exceptions.add(new XYXException("Undefined Return Type! Funcname = " + node.name));

        CreateFuncScope(node.params);
        node.setScope(scopeStack.peek());

        VISIT(node.body);

        scopeStack.pop();
    }

    @Override
    public void visit(Construct_Function node) {
        if (!node.params.isEmpty())
            SemanticException.exceptions.add(new XYXException("Construct Function requires no params!"));

        VISIT(node.body);
    }

    @Override
    public void visit(Compound_Statement node) {
        LocalScope currentScope = new LocalScope(scopeStack.peek());
        scopeStack.push(currentScope);

        for(Statement X: node.stmts){
            VISIT(X);
        }

        scopeStack.pop();
    }

    @Override
    public void visit(Variable_Declaration node) {
        if(!HaveType(node.type))
            SemanticException.exceptions.add(new XYXException("Undefined Type! name = " + node.name));

        VISIT(node.RHS);
        LocalScope currentScope = scopeStack.peek();
        // its ugly~~~~~~
        if(!(currentClass != null && currentScope.equals(typeTable.getScope(currentClass))))
            currentScope.put(node);
    }

    @Override
    public void visit(Variable_Declaration_Statement node) {
        if(!HaveType(node.type))
            SemanticException.exceptions.add(new XYXException("Undefined Type! name = " + node.name));

        VISIT(node.RHS);
        scopeStack.peek().put(node);
    }


    @Override
    public void visit(For_Statement node) {
        VISIT(node.init);
        VISIT(node.condition);
        VISIT(node.update);
        VISIT(node.body);
    }

    @Override
    public void visit(While_Statement node) {
        VISIT(node.condition);
        VISIT(node.body);
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
    public void visit(Return node) {
        VISIT(node.returnvalue);
    }

    @Override
    public void visit(Break node) {}

    @Override
    public void visit(Continue node) {}

    @Override
    public void visit(Unary_Expression node) {
        VISIT(node.body);
    }

    @Override
    public void visit(Binary_Expression node) {
        VISIT(node.lhs);
        VISIT(node.rhs);
    }

    @Override
    public void visit(Newexpr node) {
        if(node.type instanceof Array_Type){
            VISIT(((Array_Type) node.type).size);
            VISIT(node.type);
        }
    }

    @Override
    public void visit(Indexing node) {
        VISIT(node.index);
        VISIT(node.name);
    }

    @Override
    public void visit(Accessing node) {
        VISIT(node.body);
        if(node.body instanceof ID){
            if(((ID) node.body).name.equals("this") && currentClass == null){
                SemanticException.exceptions.add(new XYXException("keyword \"this\" can only be used in class declaration!"));
            }
        }
    }

    @Override
    public void visit(Class_Method node) {
        VISIT(node.body);
        for(Expression X: node.params){
            VISIT(X);
        }
    }

    @Override
    public void visit(Function_call node) {
        //手动建立函数名与函数声明的联系，否则Link到currentScope的同名变量
        try{
            node.body.setEntity(globalScope.find(node.name));
        }catch (Exception e){
            SemanticException.exceptions.add(new XYXException("Cannot Find ID Defination! name = " + node.name));
        }

        for(Expression X: node.params){
            VISIT(X);
        }
    }

    @Override
    public void visit(Self_Decreasing node) {
        VISIT(node.body);
    }

    @Override
    public void visit(Self_Increasing node) {
        VISIT(node.body);
    }

    @Override
    public void visit(ID node) {
        try{
            LocalScope currentScope = scopeStack.peek();
            node.setEntity(currentScope.find(node.name));
        }catch (Exception e){
            SemanticException.exceptions.add(new XYXException("Cannot Find ID Defination! name = " + node.name));
        }
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
        typeTable.StringLiteralTable.put(node.value, node);
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
