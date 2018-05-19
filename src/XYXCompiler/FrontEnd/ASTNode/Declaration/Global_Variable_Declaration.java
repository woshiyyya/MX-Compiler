package XYXCompiler.FrontEnd.ASTNode.Declaration;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.XIR.Operand.DataSrc;

public class Global_Variable_Declaration extends Declaration{
    public Base_Type type;
    public Expression RHS;
    public int size;
    public DataSrc dataSrc;

    public Global_Variable_Declaration() {
        decl_type = Decl_type.VARIABLE;
        this.type = null;
        this.name = null;
        this.RHS = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public int getSize(){
        return size;
    }
}
