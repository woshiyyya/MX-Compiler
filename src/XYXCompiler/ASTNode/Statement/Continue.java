package XYXCompiler.ASTNode.Statement;

import XYXCompiler.Builder.ASTVisitor;

public class Continue extends Statement {
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
