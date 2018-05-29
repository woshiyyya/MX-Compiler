package XYXCompiler.FrontEnd.ASTNode.Declaration;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Statement.Compound_Statement;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Construct_Function extends Declaration {
    public List<Variable_Declaration> params = new LinkedList<>();
    public Compound_Statement body;

    public Construct_Function() {
        decl_type = Decl_type.FUNCTION;
    }

    public Construct_Function(String name) {
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
