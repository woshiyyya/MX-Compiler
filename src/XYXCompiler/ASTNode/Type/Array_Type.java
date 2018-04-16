package XYXCompiler.ASTNode.Type;

import XYXCompiler.ASTNode.Expression.Expression;

import java.util.LinkedList;
import java.util.List;

public class Array_Type extends Base_Type {
    public int dimension;
    public Base_Type array_basetype;
    public List<Expression> size;

    public Array_Type() {
        type = Type.Array;
        dimension = 0;
        size = new LinkedList<Expression>();
    }
}
