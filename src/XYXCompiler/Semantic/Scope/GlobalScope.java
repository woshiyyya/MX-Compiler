package XYXCompiler.Semantic.Scope;

import XYXCompiler.ASTNode.Declaration.Function_Declaration;
import XYXCompiler.ASTNode.Declaration.Variable_Declaration;
import XYXCompiler.ASTNode.Node;
import XYXCompiler.ASTNode.Type.Base_Type;
import XYXCompiler.ASTNode.Type.Int_Type;
import XYXCompiler.ASTNode.Type.String_Type;
import XYXCompiler.ASTNode.Type.Void_Type;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GlobalScope extends LocalScope {
    public LocalScope StringScope;
    public LocalScope ArrayScope;

    public GlobalScope() {
        father = null;
        StringScope = new LocalScope();
        ArrayScope = new LocalScope();
        AddBulitinMethod();
    }

    public void AddBulitinMethod(){
        List<Variable_Declaration> param0 = new LinkedList<>();
        List<Variable_Declaration> param1 = new LinkedList<>();     //(string)
        List<Variable_Declaration> param2 = new LinkedList<>();     //(int)
        List<Variable_Declaration> param3 = new LinkedList<>();     //(int, int)
        param1.add(new Variable_Declaration(new String_Type(),"str"));
        param2.add(new Variable_Declaration(new Int_Type(), "int"));
        param3.add(new Variable_Declaration(new Int_Type(), "int"));
        param3.add(new Variable_Declaration(new Int_Type(), "int"));

        //Builtin Functions
        Function_Declaration print = new Function_Declaration(new Void_Type(),"print",param1,null);
        Function_Declaration println = new Function_Declaration(new Void_Type(),"println",param1,null);
        Function_Declaration getString = new Function_Declaration(new String_Type(),"getString",param0,null);
        Function_Declaration getInt = new Function_Declaration(new Int_Type(),"getInt",param0,null);
        Function_Declaration toString = new Function_Declaration(new String_Type(),"toString",param2,null);

        //String Builtin Functions
        Function_Declaration length = new Function_Declaration(new Int_Type(),"length",param0,null);
        Function_Declaration subString = new Function_Declaration(new String_Type(),"substring",param3,null);
        Function_Declaration parseInt = new Function_Declaration(new Int_Type(),"parseInt",param0,null);
        Function_Declaration ord = new Function_Declaration(new Int_Type(),"ord",param2,null);

        //Array Builtin Function
        Function_Declaration size = new Function_Declaration(new Int_Type(), "size",param0,null);

        put(print);
        put(println);
        put(getString);
        put(getInt);
        put(toString);

        StringScope.put(length);
        StringScope.put(subString);
        StringScope.put(parseInt);
        StringScope.put(ord);

        ArrayScope.put(size);
    }

}
