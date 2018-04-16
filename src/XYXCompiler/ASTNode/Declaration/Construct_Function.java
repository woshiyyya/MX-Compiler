package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Statement.Compound_Statement;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Tools.Symbol;

import java.util.LinkedList;
import java.util.List;

public class Construct_Function extends Declaration {
    public String name;
    public List<Expression> params;
    public Compound_Statement body;

    public Construct_Function() {
        params = new LinkedList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
