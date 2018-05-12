package XYXCompiler.FrontEnd.ASTNode.Expression.Primitive;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.Null_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Null extends Expression {

    public Null() {
        type = new Null_Type();
        LValue = false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
