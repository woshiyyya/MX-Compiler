package XYXCompiler.FrontEnd.ASTNode.Declaration;

import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.ASTNode.Type.Func_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.FrontEnd.ASTNode.Statement.*;

import java.util.LinkedList;
import java.util.List;

public class Function_Declaration extends Declaration {
    public Base_Type returntype;
    public Func_Type functype;
    public List<Variable_Declaration> params;
    public List<Base_Type> paramstype;
    public Compound_Statement body;

    public Function_Declaration() {
        decl_type = Decl_type.FUNCTION;
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
        this.paramstype = new LinkedList<>();
        updateType();
    }

    public void updateType(){
        List<String> paramsname = new LinkedList<>();
        for(Variable_Declaration X: params){
            paramstype.add(X.type);
            paramsname.add(X.name);
        }
        functype = new Func_Type(name, returntype, paramstype, paramsname);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
