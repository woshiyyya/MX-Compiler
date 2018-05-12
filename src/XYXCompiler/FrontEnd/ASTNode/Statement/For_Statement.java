package XYXCompiler.FrontEnd.ASTNode.Statement;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.XIR.Tools.Condition;

public class For_Statement extends Statement {
    public Expression init, condition, update;
    public Statement body;
    public Condition cond;


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
