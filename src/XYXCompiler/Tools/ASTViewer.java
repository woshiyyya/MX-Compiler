package XYXCompiler.Tools;

import XYXCompiler.ASTNode.ASTRoot;
import XYXCompiler.ASTNode.Declaration.*;
import XYXCompiler.ASTNode.Expression.Binary_Expression;
import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Expression.Primitive.*;
import XYXCompiler.ASTNode.Expression.Suffix.*;
import XYXCompiler.ASTNode.Expression.Unary_Expression;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;
import static java.lang.System.out;

public class ASTViewer implements ASTVisitor {

    private int ind;

    public ASTViewer() {
        ind = 0;
    }

    public void Tab(){
        for(int i = 0;i < ind;i++)
            System.out.print("\t");
    }

    @Override
    public void visit(ASTRoot node) {
        out.println(node.getClass().getSimpleName());
        for(Declaration X:node.declarations)
            X.accept(this);
    }

    @Override
    public void visit(Base_Type node) {
        out.print(node.getClass().getSimpleName() + " ");
    }

    @Override
    public void visit(Class_Declaration node) {
        ind++;
        Tab();
        out.println(node.name + " " + node.getClass().getSimpleName());
        for(Declaration X: node.Members)
            X.accept(this);
        ind--;
    }

    @Override
    public void visit(Construct_Function node) {
        ind++;
        Tab();
        out.println(node.name + " " + node.getClass().getSimpleName());
        node.body.accept(this);
        ind--;
    }

    @Override
    public void visit(Function_Declaration node) {
        ind++;
        Tab();
        visit(node.returntype);
        out.println(node.name + " " + node.getClass().getSimpleName());
        for (Variable_Declaration X : node.params)
            X.accept(this);
        node.body.accept(this);
        ind--;
    }

    @Override //params
    public void visit(Variable_Declaration node) {
        ind++;
        Tab();
        out.println(node.name + " ");
        if(node.RHS != null)
            node.RHS.accept(this);
        ind--;
    }

    @Override
    public void visit(Global_Variable_Declaration node) {
        ind++;
        Tab();
        out.println(node.name + " " +node.getClass().getSimpleName());
        ind--;
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
        out.print("StringConst");
    }

    @Override
    public void visit(Newexpr node) {
        out.print("new" + node.type.type);
    }

    @Override
    public void visit(Indexing node) {
        out.print("Indexing");
    }

    @Override
    public void visit(Accessing node) {
        out.print("Accessing");
    }

    @Override
    public void visit(Class_Method node) {
        out.print("Class method   "+ node.body.getClass().getSimpleName());
    }

    @Override
    public void visit(Function_call node) {
        out.print(node.name + "Func call");
    }

    @Override
    public void visit(Self_Decreasing node) {
        out.print("suffix --");
    }

    @Override
    public void visit(Self_Increasing node) {
        out.print("suffix ++");
    }

    @Override
    public void visit(Unary_Expression node) {
        out.print(node.op + " ");
    }

    @Override
    public void visit(Binary_Expression node) {
        out.print(node.op + " ");
    }

    @Override
    public void visit(Break node) {
        ind++;
        Tab();
        System.out.println("Break");
        ind--;
    }

    @Override
    public void visit(Return node) {
        ind++;
        Tab();
        System.out.print("Return: ");
        if(node.returnvalue != null)
            out.println(node.returnvalue.getClass().getSimpleName());
        out.println();
        ind--;
    }

    @Override
    public void visit(Continue node) {
        ind++;
        Tab();
        System.out.println("Continue");
        ind--;
    }

    @Override
    public void visit(For_Statement node) {
        ind++;
        Tab();
        out.print(node.getClass().getSimpleName() + ":  ");
        if(node.init != null)
            node.init.accept(this);
        if(node.condition != null)
            node.condition.accept(this);
        if(node.update != null)
            node.update.accept(this);
        out.println();
        node.body.accept(this);
        ind--;
    }

    @Override
    public void visit(While_Statement node) {
        ind++;
        Tab();
        out.print(node.getClass().getSimpleName() + ":  ");
        if(node.condition != null)
            node.condition.accept(this);
        out.println();
        if(node.body != null)
            node.body.accept(this);
        ind--;
    }

    @Override
    public void visit(Compound_Statement node) {
        for(Statement X:node.stmts){
            X.accept(this);
        }
    }

    @Override
    public void visit(Selection_Statement node) {
        ind++;
        Tab();
        out.print(node.getClass().getSimpleName() + ":  ");
        node.condition.accept(this);
        out.println();
        node.body.accept(this);
        if(node.Else_body != null)
            node.Else_body.accept(this);
        ind--;
    }

    @Override
    public void visit(Expression_Statement node) {
        ind++;
        Tab();
        if(node.body != null)
            node.body.accept(this);
        out.println();
        ind--;
    }

    @Override
    public void visit(Variable_Declaration_Statement node) {
        ind++;
        Tab();
        out.println(node.name + " " + node.type.getClass().getSimpleName());
        ind--;
    }

    @Override
    public void visit(Statement node) {

    }

    @Override
    public void visit(Expression node) {

    }
}
