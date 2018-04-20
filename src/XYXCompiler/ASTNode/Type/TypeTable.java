package XYXCompiler.ASTNode.Type;

import XYXCompiler.ASTNode.Declaration.Global_Variable_Declaration;
import XYXCompiler.ASTNode.Expression.Primitive.Bool;
import XYXCompiler.ASTNode.Expression.Primitive.STRING;
import XYXCompiler.ASTNode.Node;
import XYXCompiler.Semantic.Scope.GlobalScope;
import XYXCompiler.Semantic.Scope.LocalScope;
import XYXCompiler.Tools.Exceptions.SemanticException;
import XYXCompiler.Tools.Exceptions.XYXException;

import java.util.LinkedHashMap;
import java.util.Map;

public class TypeTable {
    public Map<String, Node> table;
    public Map<String, LocalScope> ClassScopeMap;
    public Map<String, STRING> StringLiteralTable;


    public TypeTable() {
        table = new LinkedHashMap<>();
        ClassScopeMap = new LinkedHashMap<>();
        StringLiteralTable = new LinkedHashMap<>();
    }

    public boolean add(String name, Node type){
        if(table.containsKey(name)){
            SemanticException.exceptions.add(new XYXException("Duplicated Class Type"));
            return false;
        }else {
            table.put(name, type);
            return true;
        }
    }

    public void LinkClassScope(String name, LocalScope localScope){
        ClassScopeMap.put(name, localScope);
    }

    public boolean find(String name){
        return table.containsKey(name);
    }

    public LocalScope getScope(String classname){
        return ClassScopeMap.get(classname);
    }

    public void AddBuiltinClass(GlobalScope globalScope){
        ClassScopeMap.put("__STRING__", globalScope.StringScope);
        ClassScopeMap.put("__ARRAY__", globalScope.ArrayScope);
    }

    public void PrintTypeTable(){
        for(String X: table.keySet()){
            System.out.println(X);
        }
    }
}
