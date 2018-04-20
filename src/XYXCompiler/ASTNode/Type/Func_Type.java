package XYXCompiler.ASTNode.Type;

import XYXCompiler.ASTNode.Declaration.Variable_Declaration;
import XYXCompiler.Parser.XYXParser;

import java.util.LinkedList;
import java.util.List;

public class Func_Type extends Base_Type {
    public String name;
    public Base_Type returntype;
    public List<Base_Type> params_type;

    public Func_Type(String name, Base_Type returntype, List<Base_Type> params_type) {
        this.name = name;
        this.type = Type.Function;
        this.returntype = returntype;
        this.params_type = params_type;
    }

    public void add_params(List<Variable_Declaration> params){
        for(Variable_Declaration X:params) {
            params_type.add(X.type);
        }
    }
}
