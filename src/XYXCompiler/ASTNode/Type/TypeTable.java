package XYXCompiler.ASTNode.Type;

import XYXCompiler.ASTNode.Expression.Primitive.Bool;

import java.util.LinkedHashMap;
import java.util.Map;

public class TypeTable {
    public Map<String, Base_Type> table;

    public static Int_Type int_type = new Int_Type();
    public static Bool_Type bool_type = new Bool_Type();
    public static Void_Type void_type = new Void_Type();

    public TypeTable() {
        table = new LinkedHashMap<>();
        table.put("int", int_type);
        table.put("bool", bool_type);
        table.put("void", void_type);
    }

    public boolean add(String name, Base_Type type){
        if(table.containsKey(name))
            return false;
        else {
            table.put(name, type);
            return true;
        }
    }

    public boolean find(String name){
        return table.containsKey(name);
    }
}
