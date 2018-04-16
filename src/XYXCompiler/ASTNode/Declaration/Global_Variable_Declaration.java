package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Tools.Symbol;

public class Global_Variable_Declaration extends Declaration{
    public Base_Type type;
    public Symbol symbol;
    public String name;
    public Expression RHS;

    public Global_Variable_Declaration() {
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
