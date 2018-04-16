package XYXCompiler.ASTNode.Expression.Suffix;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class Self_Increasing extends Expression{
    public Expression body;

    public Self_Increasing(){
        body = null;
    }

    public Self_Increasing(Expression b){
        body = b;
    }

    public void print(){
        System.out.println("++ Expression!");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
