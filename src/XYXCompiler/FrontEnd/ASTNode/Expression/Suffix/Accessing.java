package XYXCompiler.FrontEnd.ASTNode.Expression.Suffix;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Accessing extends Expression {
    public Expression body;
    public String components;

    public Accessing(){
        body = null;
        components = null;
        LValue = true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
