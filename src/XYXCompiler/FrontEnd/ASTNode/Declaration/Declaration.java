package XYXCompiler.FrontEnd.ASTNode.Declaration;

import XYXCompiler.FrontEnd.ASTNode.Node;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class Declaration extends Node {
    public String name;
    public Decl_type decl_type;
    public enum Decl_type{
        FUNCTION, CLASS, VARIABLE
    }
}
