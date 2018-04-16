package XYXCompiler.ASTNode.Statement;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class Return extends Statement {
    public Expression returnvalue;

    public Return() {
        returnvalue = null;
    }

    public Return(Expression returnvalue) {
        this.returnvalue = returnvalue;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
