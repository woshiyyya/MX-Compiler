package XYXCompiler.ASTNode.Expression.Suffix;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Expression.Primitive.ID;
import XYXCompiler.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Function_call extends Expression {
    public String name;
    public ID body;
    public List<Expression> params;

    public Function_call(){
        name = null;
        params = new LinkedList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
