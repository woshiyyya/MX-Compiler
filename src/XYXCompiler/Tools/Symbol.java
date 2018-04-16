package XYXCompiler.Tools;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.ASTNode.Type.Base_Type.Type;

public class Symbol extends Expression {
    public String name;
    public Base_Type type;

    public Symbol(String name) {
        this.name = name;
        this.type = null;
    }
    public void Print(){
        if(name == null)
            System.out.println("NULL Name");
        else
            System.out.println(name);
    }
    public void SetType(Base_Type type){
        this.type = type;
    }

    public boolean IsEqual(Symbol other){
        if(other.type.type != Type.Array){
            return other.type.type == type.type;
        }else{
            System.out.println("Array Has not been decided.....");
            return true;
        }
    }
}


