package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Semantic.Symbol.Symbol;

public class Global_Variable_Declaration extends Declaration{
    public Base_Type type;
    public Symbol symbol;
    public Expression RHS;

    public Global_Variable_Declaration() {
        decl_type = Decl_type.VARIABLE;
        this.type = null;
        this.symbol = null;
        this.name = null;
        this.RHS = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
