package XYXCompiler.FrontEnd.ASTNode.Expression.Suffix;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Expression.Primitive.ID;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Function_call extends Expression {
    public String name;
    public ID body;
    public List<Expression> params;

    public Function_call(){
        name = null;
        params = new LinkedList<>();
        LValue = false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
