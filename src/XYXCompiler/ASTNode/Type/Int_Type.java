package XYXCompiler.ASTNode.Type;

public class Int_Type extends Base_Type{
    public void print(){
        System.out.println("Inttype ");
    }

    public Int_Type(){
        type = Type.Int;
    }

    public String toString(){
        return type.toString();
    }
}
