package XYXCompiler.Semantic.Symbol;

import XYXCompiler.ASTNode.Node;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Symbol> LocalScope;
    private SymbolTable UpperScope;
    private boolean global;

    public SymbolTable() {
        LocalScope = new LinkedHashMap<>();
        UpperScope = null;
        global = false;
    }

    public Node get(Symbol s) {
        if(!LocalScope.containsKey(s))
            if(UpperScope == null)
                return null;
            else
                return UpperScope.get(s);
        else
            return LocalScope.get(s);
    }

    public boolean Insert(String name, Symbol symbol){
        if(LocalScope.containsKey(name))
            return false;
        else{
            LocalScope.put(name,symbol);
            return true;
        }
    }
}
