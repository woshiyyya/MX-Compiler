package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.Semantic.Symbol.Symbol;

import java.util.LinkedList;
import java.util.List;

public class Function_Declaration extends Declaration {
    public Base_Type returntype;
    public Symbol symbol;
    public List<Variable_Declaration> params;
    public List<Base_Type> paramstype;
    public Compound_Statement body;

    public Function_Declaration() {
        decl_type = Decl_type.FUNCTION;
        symbol = null;
        returntype = null;
        name = null;
        params = new LinkedList<>();
        paramstype = new LinkedList<>();
        body = null;
    }

    public Function_Declaration(Base_Type returntype, String name, List<Variable_Declaration> params, Compound_Statement body) {
        decl_type = Decl_type.FUNCTION;
        this.returntype = returntype;
        this.name = name;
        this.params = params;
        this.body = body;
    }

    public void updateType(){
        for(Variable_Declaration X: params){
            paramstype.add(X.type);
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
