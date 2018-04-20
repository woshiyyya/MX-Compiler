package XYXCompiler.ASTNode.Type;

public class Void_Type extends Base_Type {
    public void print(){
        System.out.println("Voidtype ");
    }

    public Void_Type(){
        type = Type.Void;
    }

    public String toString(){
        return type.toString();
    }
}
