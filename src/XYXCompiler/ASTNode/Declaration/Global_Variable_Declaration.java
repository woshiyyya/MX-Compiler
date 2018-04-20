package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;

public class Global_Variable_Declaration extends Declaration{
    public Base_Type type;
    public Expression RHS;

    public Global_Variable_Declaration() {
        decl_type = Decl_type.VARIABLE;
        this.type = null;
        this.name = null;
        this.RHS = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
