package XYXCompiler.Tools.TypeTable;

import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;

import java.util.LinkedList;
import java.util.List;

import static XYXCompiler.XIR.Tools.ConstVal.intsize;

public class Class_info {
    public String name;
    public List<Base_Type> membertypeList = new LinkedList<>();
    public List<String> membernameList = new LinkedList<>();

    public Class_info(String name) {
        this.name = name;
    }

    public int getOffset(String name){
        int cnt = 0;
        for(String X: membernameList){
            cnt++;
            if(name.equals(X))
                return (cnt-1) * intsize;
        }
        return -1;
    }

    public int getSize(){
        return membernameList.size() * intsize;
    }

}
