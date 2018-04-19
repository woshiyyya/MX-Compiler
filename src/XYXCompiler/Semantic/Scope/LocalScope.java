package XYXCompiler.Semantic.Scope;

import XYXCompiler.ASTNode.Declaration.Declaration;
import XYXCompiler.ASTNode.Node;
import XYXCompiler.ASTNode.Statement.Variable_Declaration_Statement;
import XYXCompiler.Tools.Exceptions.SemanticException;
import XYXCompiler.Tools.Exceptions.XYXException;
import org.antlr.v4.codegen.model.decl.Decl;

import java.util.LinkedHashMap;
import java.util.Map;

public class LocalScope {
    public Map<String, Node> Entity;
    public LocalScope father;

    public LocalScope() {
        Entity = new LinkedHashMap<>();
    }

    public LocalScope(LocalScope father){
        Entity = new LinkedHashMap<>();
        this.father = father;
    }

    public void put(Node node){
        String name = null;
        if(node instanceof Declaration)
            name = ((Declaration) node).name;
        else if(node instanceof Variable_Declaration_Statement)
            name = ((Variable_Declaration_Statement) node).name;

        if(Entity.containsKey(name)){
            SemanticException.exceptions.add(new XYXException("Duplicated Defination of: " + name + " " + node.getClass().getSimpleName()));
        }else{
            Entity.put(name, node);  //对任意Declaration类型Node，其Scope已被tag标注
            node.setScope(this);
        }
    }

    public Node find(String name){
        if(Entity.containsKey(name))
            return Entity.get(name);
        else
            return father.find(name);
    }

    public boolean contain(String name){
        if(Entity.containsKey(name))
            return true;
        else if (father != null)
            return father.contain(name);
        else return false;
    }
}