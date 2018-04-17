package XYXCompiler.ASTNode.Type;

import XYXCompiler.ASTNode.Declaration.Variable_Declaration;
import XYXCompiler.Parser.XYXParser;

import java.util.LinkedList;
import java.util.List;

public class Func_Type extends Base_Type {
    public String name;
    public List<Base_Type> params_type;

    public Func_Type() {
        this.params_type = new LinkedList<>();
    }

    public void add_params(List<Variable_Declaration> params){
        for(Variable_Declaration X:params) {
            params_type.add(X.type);
        }
    }
}
