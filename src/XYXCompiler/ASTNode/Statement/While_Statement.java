package XYXCompiler.ASTNode.Statement;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class While_Statement extends Statement
{
    public Expression condition;
    public Statement body;

    public While_Statement() {}

    public While_Statement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
