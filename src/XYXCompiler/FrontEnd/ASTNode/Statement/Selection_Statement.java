package XYXCompiler.FrontEnd.ASTNode.Statement;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Selection_Statement extends Statement {
    public Expression condition = null;
    public Statement body = null;
    public Statement Else_body = null;

    public Selection_Statement() {}

    public Selection_Statement(Expression condition, Statement body, Statement else_body) {
        this.condition = condition;
        this.body = body;
        Else_body = else_body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
