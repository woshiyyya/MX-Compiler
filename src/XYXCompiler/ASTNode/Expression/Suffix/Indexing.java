package XYXCompiler.ASTNode.Expression.Suffix;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class Indexing extends Expression {
    public Expression name;
    public Expression index;

    public Indexing(){
        name = null;
        index = null;
    }

    public Indexing(Expression name, Expression index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
