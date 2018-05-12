package XYXCompiler.FrontEnd.ASTNode.Declaration;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.XIR.Operand.Register.Register;

public class Variable_Declaration extends Declaration{
    public Base_Type type;
    public Expression RHS;
    public Register reg;
    public int size;

    public Variable_Declaration() {
        decl_type = Decl_type.VARIABLE;
    }

    public Variable_Declaration(Base_Type type, String name) {
        decl_type = Decl_type.VARIABLE;
        this.type = type;
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public int getSize(){
        return size;
    }
}
