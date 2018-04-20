package XYXCompiler.ASTNode.Type;


public class String_Type extends Base_Type {
    public void print(){
        System.out.println("Stringtype ");
    }

    public String_Type(){
        type = Type.String;
    }

    public String toString(){
        return type.toString();
    }
}
