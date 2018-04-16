package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.ASTNode.Expression.*;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.Tools.Symbol;

import java.util.LinkedList;
import java.util.List;

public class Function_Declaration extends Declaration {
    public Base_Type returntype;
    public Symbol symbol;
    public String name;
    public List<Variable_Declaration> params;
    public Compound_Statement body;

    public Function_Declaration() {
        symbol = null;
        returntype = null;
        name = null;
        params = new LinkedList<>();
        body = null;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
