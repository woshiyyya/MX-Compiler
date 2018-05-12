package XYXCompiler.FrontEnd.ASTNode.Statement;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class Variable_Declaration_Statement extends Statement {
    public Base_Type type;
    public String name;
    public Expression RHS;
    public Register reg;

    public Variable_Declaration_Statement() {}

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
