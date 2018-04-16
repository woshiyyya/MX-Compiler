package XYXCompiler.ASTNode.Expression.Primitive;


import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

public class STRING extends Expression {
    String value;
    public STRING() {
        value = null;
    }

    public STRING(String value) {
        this.value = value;
    }

    public void print(int d) {
        System.out.println("StringLiteral " + value);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
