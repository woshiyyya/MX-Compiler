package XYXCompiler.FrontEnd.ASTNode.Type;

import XYXCompiler.Tools.*;

import java.util.List;

import static XYXCompiler.XIR.Tools.ConstVal.intsize;

public class Class_Type extends Base_Type {
    public String name;
    public List<Base_Type> membertypeList;
    public List<String> membernameList;

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

    public int getOffset(String name){
        int cnt = 0;
        for(String X: membernameList){
            cnt++;
            if(name.equals(X))
                return cnt;
        }
        return -1;
    }

    public int getSize(){
        return membernameList.size() * intsize;
    }
}
