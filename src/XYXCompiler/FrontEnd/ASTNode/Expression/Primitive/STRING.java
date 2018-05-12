package XYXCompiler.FrontEnd.ASTNode.Expression.Primitive;


import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.String_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

import java.util.List;

public class STRING extends Expression {
    public String value;
    public int size;

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
