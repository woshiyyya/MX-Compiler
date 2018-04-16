package XYXCompiler.Builder;

import XYXCompiler.ASTNode.ASTRoot;
import XYXCompiler.ASTNode.Declaration.*;
import XYXCompiler.ASTNode.Expression.Binary_Expression;
import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Expression.Primitive.*;
import XYXCompiler.ASTNode.Expression.Suffix.*;
import XYXCompiler.ASTNode.Expression.Unary_Expression;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Parser.XYXParser;

public class ClassnameScanner implements ASTVisitor {
    @Override
    public void visit(ASTRoot node) {
        for(Declaration X:node.declarations){
            X.accept(this);
        }
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

    @Override
    public void visit(Class_Declaration node) {

    }

    @Override
    public void visit(Construct_Function node) {

    }

    @Override
    public void visit(Function_Declaration node) {

    }

    @Override
    public void visit(Variable_Declaration node) {

    }

    @Override
    public void visit(Global_Variable_Declaration node) {

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

    }

    @Override
    public void visit(Indexing node) {

    }

    @Override
    public void visit(Accessing node) {

    }

    @Override
    public void visit(Class_Method node) {

    }

    @Override
    public void visit(Function_call node) {

    }

    @Override
    public void visit(Self_Decreasing node) {

    }

    @Override
    public void visit(Self_Increasing node) {

    }

    @Override
    public void visit(Unary_Expression node) {

    }

    @Override
    public void visit(Binary_Expression node) {

    }

    @Override
    public void visit(Break node) {

    }

    @Override
    public void visit(Return node) {

    }

    @Override
    public void visit(Continue node) {

    }

    @Override
    public void visit(For_Statement node) {

    }

    @Override
    public void visit(While_Statement node) {

    }

    @Override
    public void visit(Compound_Statement node) {

    }

    @Override
    public void visit(Selection_Statement node) {

    }

    @Override
    public void visit(Expression_Statement node) {

    }

    @Override
    public void visit(Variable_Declaration_Statement node) {

    }
}
