package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;

public class Variable_Declaration extends Declaration{
    public Base_Type type;
    public Expression RHS;

    public Variable_Declaration() {
        decl_type = Decl_type.VARIABLE;
    }

    public Variable_Declaration(Base_Type type, String name) {
        decl_type = Decl_type.VARIABLE;
        this.type = type;
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
