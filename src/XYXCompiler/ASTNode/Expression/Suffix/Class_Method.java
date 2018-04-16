package XYXCompiler.ASTNode.Expression.Suffix;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Class_Method extends Expression {
    public Expression body;
    public List<Expression> params;

    public Class_Method() {
        params = new LinkedList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
