package XYXCompiler.FrontEnd.ASTNode.Statement;

import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Break extends Statement {

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
