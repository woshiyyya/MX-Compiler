package XYXCompiler.FrontEnd.ASTNode.Type;

import java.util.List;

public class Func_Type extends Base_Type {
    public String name;
    public Base_Type returntype;
    public List<Base_Type> params_type;
    public List<String> params_name;

    public Func_Type(String name, Base_Type returntype, List<Base_Type> params_type, List<String> params_name) {
        this.name = name;
        this.type = Type.Function;
        this.returntype = returntype;
        this.params_type = params_type;
        this.params_name = params_name;
    }
}
