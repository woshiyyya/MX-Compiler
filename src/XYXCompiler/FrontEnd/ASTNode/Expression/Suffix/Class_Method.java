package XYXCompiler.FrontEnd.ASTNode.Expression.Suffix;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Expression.Primitive.ID;
import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Class_Method extends Expression {
    public Expression body;
    public String Func_Name;
    public List<Expression> params;
    public Base_Type returntype;

    public Class_Method() {
        params = new LinkedList<>();
        LValue = true;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
