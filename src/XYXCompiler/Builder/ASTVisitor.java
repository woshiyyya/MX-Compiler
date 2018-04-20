package XYXCompiler.Builder;

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

public interface ASTVisitor {

    void visit(ASTRoot node);
    void visit(Base_Type node);
    void visit(Statement node);
    void visit(Expression node);

    void visit(Class_Declaration node);
    void visit(Construct_Function node);
    void visit(Function_Declaration node);
    void visit(Variable_Declaration node);
    void visit(Global_Variable_Declaration node);


    void visit(ID node);
    void visit(INT node);
    void visit(Null node);
    void visit(Bool node);
    void visit(STRING node);
    void visit(Newexpr node);

    void visit(Indexing node);
    void visit(Accessing node);
    void visit(Class_Method node);
    void visit(Function_call node);
    void visit(Self_Decreasing node);
    void visit(Self_Increasing node);
    void visit(Unary_Expression node);
    void visit(Binary_Expression node);

    void visit(Break node);
    void visit(Return node);
    void visit(Continue node);
    void visit(For_Statement node);
    void visit(While_Statement node);
    void visit(Compound_Statement node);
    void visit(Selection_Statement node);
    void visit(Expression_Statement node);
    void visit(Variable_Declaration_Statement node);

    void visit(Array_Type node);

}
