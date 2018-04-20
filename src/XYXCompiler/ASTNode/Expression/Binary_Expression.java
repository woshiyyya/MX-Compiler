package XYXCompiler.ASTNode.Expression;

import XYXCompiler.Builder.ASTVisitor;

public class Binary_Expression extends Expression{
    public enum BinaryOP{
        Mul, Div, Mod, Plus, Minus, LeftShift, RightShift,
        Less, LessEqual, Greater, GreaterEqual, Equal, NotEqual,
        BitAnd, BitXor, BitOr, LogicalAnd, LogicalOr, Assign
    }

    public final BinaryOP op;
    public Expression lhs;
    public Expression rhs;

    public Binary_Expression(BinaryOP OP, Expression LHS, Expression RHS){
        this.op = OP;
        this.rhs = RHS;
        this.lhs = LHS;
    }

    public void print(){
        System.out.println("Binary Expression!");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
