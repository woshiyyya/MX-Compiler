package XYXCompiler.ASTNode.Statement;

import XYXCompiler.Builder.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class Compound_Statement extends Statement{
    public List<Statement> stmts;

    public Compound_Statement() {
        stmts = new LinkedList<Statement>();
    }

    public Compound_Statement(List<Statement> stmts) {
        this.stmts = stmts;
    }

    public Compound_Statement(Statement stmt) {
        stmts = new LinkedList<>();
        stmts.add(stmt);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
