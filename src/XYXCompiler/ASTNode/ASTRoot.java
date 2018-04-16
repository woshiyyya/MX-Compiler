package XYXCompiler.ASTNode;

import XYXCompiler.ASTNode.Declaration.Declaration;
import XYXCompiler.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class ASTRoot extends Node {
    public List<Declaration> declarations;

    public ASTRoot() {
        this.declarations = new LinkedList<Declaration>();
    }

    public ASTRoot(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
