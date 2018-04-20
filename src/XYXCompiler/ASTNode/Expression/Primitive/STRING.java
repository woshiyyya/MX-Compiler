package XYXCompiler.ASTNode.Expression.Primitive;


import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.String_Type;
import XYXCompiler.Builder.ASTVisitor;

import java.util.List;

public class STRING extends Expression {
    public String value;

    public STRING(String value) {
        type = new String_Type();
        this.value = value;
        LValue = false;
    }

    public void print() {
        System.out.println("StringLiteral " + value);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
