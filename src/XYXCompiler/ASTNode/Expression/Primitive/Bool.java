package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.ASTNode.Type.Bool_Type;
import XYXCompiler.Builder.ASTVisitor;

public class Bool extends Expression {
    boolean value;

    public Bool(boolean v) {
        type = new Bool_Type();
        value = v;
        LValue = false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
