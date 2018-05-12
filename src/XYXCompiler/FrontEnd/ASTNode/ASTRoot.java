package XYXCompiler.FrontEnd.ASTNode;

import XYXCompiler.FrontEnd.ASTNode.Declaration.Declaration;
import XYXCompiler.FrontEnd.ASTNode.Type.TypeTable;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.FrontEnd.Semantic.Scope.GlobalScope;

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
