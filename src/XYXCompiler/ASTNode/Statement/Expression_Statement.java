package XYXCompiler.ASTNode.Statement;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class Expression_Statement extends Statement {
    public Expression body;

    public Expression_Statement() {
        body = null;
    }

    public Expression_Statement(Expression body) {
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
