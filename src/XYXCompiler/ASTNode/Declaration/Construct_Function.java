package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Statement.Compound_Statement;
import XYXCompiler.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Construct_Function extends Declaration {
    public List<Variable_Declaration> params;
    public Compound_Statement body;

    public Construct_Function() {
        decl_type = Decl_type.FUNCTION;
        params = new LinkedList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
