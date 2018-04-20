package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Null_Type;
import XYXCompiler.Builder.ASTVisitor;

public class Null extends Expression {

    public Null() {
        type = new Null_Type();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
