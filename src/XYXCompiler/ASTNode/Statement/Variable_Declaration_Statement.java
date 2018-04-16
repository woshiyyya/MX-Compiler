package XYXCompiler.ASTNode.Statement;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Tools.Symbol;

public class Variable_Declaration_Statement extends Statement {
    public Base_Type type;
    public Symbol symbol;
    public String name;
    public Expression RHS;

    public Variable_Declaration_Statement() {}

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
