package XYXCompiler.ASTNode.Statement;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class For_Statement extends Statement {
    public Expression init, condition, update;
    public Statement body;


    public For_Statement() {}

    public For_Statement(Expression init, Expression condition, Expression update, Statement body) {
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
