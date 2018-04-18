package XYXCompiler.ASTNode;

import XYXCompiler.ASTNode.Declaration.Declaration;
import XYXCompiler.ASTNode.Type.TypeTable;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Semantic.Scope.GlobalScope;

import java.util.LinkedList;
import java.util.List;

public class ASTRoot extends Node {
    public List<Declaration> declarations;
    public GlobalScope globalScope;

    public ASTRoot() {
        this.declarations = new LinkedList<Declaration>();
        globalScope = new GlobalScope();
    }

    public ASTRoot(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
