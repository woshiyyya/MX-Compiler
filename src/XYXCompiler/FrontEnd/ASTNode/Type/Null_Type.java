package XYXCompiler.FrontEnd.ASTNode.Type;

public class Null_Type extends Base_Type {
    public Null_Type() {
        type = Type.Null;
    }
    public String toString(){
        return type.toString();
    }
}
