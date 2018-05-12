package XYXCompiler.FrontEnd.ASTNode.Expression.Suffix;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Self_Increasing extends Expression{
    public Expression body;

    public Self_Increasing(){
        body = null;
        LValue = false;
    }

    public void print(){
        System.out.println("++ Expression!");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
