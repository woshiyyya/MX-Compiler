package XYXCompiler.ASTNode.Expression.Primitive;


import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.String_Type;
import XYXCompiler.Builder.ASTVisitor;

public class STRING extends Expression {
    public String value;
    public STRING() {
        type = new String_Type();
        value = null;
    }

    public STRING(String value) {
        type = new String_Type();
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
