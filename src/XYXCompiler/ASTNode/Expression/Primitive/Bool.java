package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class Bool extends Expression {
    boolean value;

    public Bool(boolean v) {
        value = v;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
