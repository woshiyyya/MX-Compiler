package XYXCompiler.FrontEnd.ASTNode.Expression;

import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Unary_Expression extends Expression {
    public static enum UnaryOP{
        PlusPlus, MinusMinus, Tilde, Not, Plus, Minus
    }

    public final UnaryOP op;
    public Expression body;

    public Unary_Expression(UnaryOP OP, Expression Base_Expr){
        this.op = OP;
        this.body = Base_Expr;
        this.LValue = false;
    }

    public void print(){
        System.out.println("Unary Expression!");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public boolean ifLogical(){
        return op == UnaryOP.Not;
    }

}
