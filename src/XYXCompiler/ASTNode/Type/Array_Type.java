package XYXCompiler.ASTNode.Type;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Array_Type extends Base_Type {
    public Base_Type.Type type;
    public Base_Type basetype;
    public Expression size;

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
}
