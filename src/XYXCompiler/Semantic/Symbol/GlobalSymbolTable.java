package XYXCompiler.Semantic.Symbol;

import XYXCompiler.ASTNode.Type.Base_Type;

import XYXCompiler.ASTNode.Type.Base_Type.Type;
import XYXCompiler.Tools.Exceptions.SemanticException;


import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalSymbolTable {
    public Map<String, Type> map;

    public GlobalSymbolTable() {
        this.map = new LinkedHashMap<>();
    }

    public void Add(String name, Type type){
        if(map.containsKey(name))
            System.out.println("_______________________EXCEPTION________________________");
        else
            map.put(name, type);
    }
}
