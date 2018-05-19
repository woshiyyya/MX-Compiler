package XYXCompiler.FrontEnd.ASTNode.Expression.Suffix;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Indexing extends Expression {
    public Expression name;
    public Expression index;

    public Indexing(){
        name = null;
        index = null;
        LValue = true;
    }

    public Indexing(Expression name, Expression index) {
        this.name = name;
        this.index = index;
        LValue = true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
