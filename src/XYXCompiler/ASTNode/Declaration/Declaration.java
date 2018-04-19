package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Node;
import XYXCompiler.Builder.ASTVisitor;

public class Declaration extends Node {
    public String name;
    public Decl_type decl_type;
    public enum Decl_type{
        FUNCTION, CLASS, VARIABLE
    }
}
