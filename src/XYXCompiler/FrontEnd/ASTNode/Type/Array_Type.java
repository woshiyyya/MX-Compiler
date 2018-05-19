package XYXCompiler.FrontEnd.ASTNode.Type;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.XIR.Operand.DataSrc;

public class Array_Type extends Base_Type {
    public Base_Type.Type type;
    public Base_Type basetype;
    public Expression size = null;
    public DataSrc baseaddr; //memory addr only

    public Array_Type() {
        type = Type.Array;
        basetype = null;
        size = null;
    }

    public String toString(){
        return type.toString();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public Base_Type getBasetype(){
        if(!(basetype instanceof Array_Type))
            return basetype;
        else
            return ((Array_Type)this.basetype).getBasetype();
    }

    public int getSize(){
        return 8;
    }
}
