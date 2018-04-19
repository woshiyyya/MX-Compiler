package XYXCompiler.ASTNode.Type;

import XYXCompiler.ASTNode.Node;

public class Base_Type extends Node {
    public enum Type{
        Int, String, Void, Bool, Class, Function , Null, Array
    }

    public Type type;

}
