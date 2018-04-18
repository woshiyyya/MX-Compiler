package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.Semantic.Symbol.Symbol;

import java.util.LinkedList;
import java.util.List;

public class Class_Declaration extends Declaration{
    public Symbol symbol;
    public List<Declaration> Members;

    public Class_Declaration() {
        decl_type = Decl_type.CLASS;
        Members = new LinkedList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
