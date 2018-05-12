package XYXCompiler.FrontEnd.ASTNode.Type;

import XYXCompiler.FrontEnd.ASTNode.Node;

public class Base_Type extends Node {
    public enum Type{
        Int, String, Void, Bool, Class, Function , Null, Array
    }

    public Type type;
    public int size;
}
