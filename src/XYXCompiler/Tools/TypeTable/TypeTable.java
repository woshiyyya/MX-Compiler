package XYXCompiler.Tools.TypeTable;

import XYXCompiler.FrontEnd.ASTNode.Declaration.Class_Declaration;
import XYXCompiler.FrontEnd.ASTNode.Declaration.Declaration;
import XYXCompiler.FrontEnd.ASTNode.Declaration.Global_Variable_Declaration;
import XYXCompiler.FrontEnd.ASTNode.Declaration.Variable_Declaration;
import XYXCompiler.FrontEnd.ASTNode.Expression.Primitive.Bool;
import XYXCompiler.FrontEnd.ASTNode.Expression.Primitive.STRING;
import XYXCompiler.FrontEnd.ASTNode.Node;
import XYXCompiler.FrontEnd.ASTNode.Type.Class_Type;
import XYXCompiler.FrontEnd.Semantic.Scope.GlobalScope;
import XYXCompiler.FrontEnd.Semantic.Scope.LocalScope;
import XYXCompiler.Tools.Exceptions.SemanticException;
import XYXCompiler.Tools.Exceptions.XYXException;

import java.util.LinkedHashMap;
import java.util.Map;

public class TypeTable {
    public Map<String, Node> table;
    public Map<String, LocalScope> ClassScopeMap;
    public Map<String, STRING> StringLiteralTable;
    public Map<String, Class_info> ClassInfoTable;  //for IR


    public TypeTable() {
        table = new LinkedHashMap<>();
        ClassScopeMap = new LinkedHashMap<>();
        StringLiteralTable = new LinkedHashMap<>();
        ClassInfoTable = new LinkedHashMap<>();
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

    public void UpdateClassTypeMap(Class_Declaration node){
        Class_info type = new Class_info(node.name);
        for(Declaration X: node.Members){
            if(X instanceof Variable_Declaration){
                type.membernameList.add(X.name);
                type.membertypeList.add(((Variable_Declaration) X).type);
            }
        }
        ClassInfoTable.put(node.name, type);
    }

    public Class_info getInfo(String name){
        return ClassInfoTable.get(name);
    }
}
