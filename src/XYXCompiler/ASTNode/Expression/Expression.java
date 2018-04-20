package XYXCompiler.ASTNode.Expression;

import XYXCompiler.ASTNode.Node;
import XYXCompiler.ASTNode.Type.*;
import XYXCompiler.Tools.Position;

public class Expression extends Node {
    public Base_Type type;
    public Boolean LValue;

    public void setType(Base_Type type){
        this.type = type;
    }
}
