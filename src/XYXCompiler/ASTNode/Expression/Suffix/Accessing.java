package XYXCompiler.ASTNode.Expression.Suffix;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Tools.Symbol;

import java.util.List;

public class Accessing extends Expression {
    public Expression body;
    public String components;

    public Accessing(){
        body = null;
        components = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
