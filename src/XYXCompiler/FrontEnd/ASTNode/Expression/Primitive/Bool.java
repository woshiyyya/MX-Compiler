package XYXCompiler.FrontEnd.ASTNode.Expression.Primitive;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.ASTNode.Type.Bool_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Bool extends Expression {
    public boolean value;

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
