package XYXCompiler.ASTNode.Type;

public class Bool_Type extends Base_Type {
    public void print(){
        System.out.println("Booltype ");
    }

    public Bool_Type(){
        type = Type.Bool;
    }

    public String toString(){
        return type.toString();
    }
}
