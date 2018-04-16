package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class Null extends Expression {

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
