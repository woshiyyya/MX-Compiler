package XYXCompiler.ASTNode.Expression.Suffix;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Tools.Symbol;

import java.util.LinkedList;
import java.util.List;

public class Function_call extends Expression {
    public String name;
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
