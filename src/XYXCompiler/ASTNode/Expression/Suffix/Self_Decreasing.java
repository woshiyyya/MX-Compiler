package XYXCompiler.ASTNode.Expression.Suffix;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class Self_Decreasing extends Expression{
    public Expression body;

    public Self_Decreasing(){
        body = null;
    }

    public Self_Decreasing(Expression b){
        body = b;
    }

    public void print(){
        System.out.println("-- Expression!");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        super.accept(visitor);
    }
}
