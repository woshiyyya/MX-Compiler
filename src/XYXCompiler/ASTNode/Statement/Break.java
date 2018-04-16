package XYXCompiler.ASTNode.Statement;

import XYXCompiler.Builder.ASTVisitor;

public class Break extends Statement {

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
