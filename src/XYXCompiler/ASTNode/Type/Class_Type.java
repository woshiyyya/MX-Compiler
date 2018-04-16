package XYXCompiler.ASTNode.Type;

import XYXCompiler.Tools.*;

public class Class_Type extends Base_Type {
    public Symbol name;

    public Class_Type(String name){
        this.name = new Symbol(name);
        this.type = Type.Class;
    }

    public void print(){
        System.out.println("Classtype ");
    }
}
