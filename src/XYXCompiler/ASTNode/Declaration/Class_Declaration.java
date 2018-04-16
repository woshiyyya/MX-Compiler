package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.Tools.Symbol;

import java.util.LinkedList;
import java.util.List;

public class Class_Declaration extends Declaration{
    public Symbol symbol;
    public String name;
    public Construct_Function CF = null;
    public List<Variable_Declaration_Statement> Var_Decls;
    public List<Function_Declaration> Func_Decls;

    public Class_Declaration() {
        Var_Decls = new LinkedList<>();
        Func_Decls = new LinkedList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
