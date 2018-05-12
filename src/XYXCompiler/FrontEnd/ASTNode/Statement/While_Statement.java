package XYXCompiler.FrontEnd.ASTNode.Statement;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.XIR.Tools.Condition;

public class While_Statement extends Statement
{
    public Expression condition;
    public Statement body;
    public Condition Cond;

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
