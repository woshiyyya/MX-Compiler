package XYXCompiler.FrontEnd.ASTNode.Expression.Primitive;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Newexpr extends Expression {
    public Newexpr() {}

    public Newexpr(Base_Type type) {
        this.type = type;
        this.LValue = true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
