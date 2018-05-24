package XYXCompiler.FrontEnd.ASTNode.Type;

import XYXCompiler.Tools.*;

import java.util.LinkedList;
import java.util.List;

import static XYXCompiler.XIR.Tools.ConstVal.intsize;

public class Class_Type extends Base_Type {
    public String name;

    public Class_Type(String name){
        this.name = name;
        this.type = Type.Class;
    }

    public void print(){
        System.out.println("Classtype ");
    }
    public String toString(){
        return name;
    }


}
