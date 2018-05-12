package XYXCompiler.FrontEnd.ASTNode.Expression.Primitive;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Type.Int_Type;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

import java.math.BigInteger;

public class INT extends Expression {
    public int value = 0;
    public int size = 8;

    public INT(){
        type = new Int_Type();
    }
    public INT(String v){
        LValue = false;
        type = new Int_Type();
        value = Integer.parseInt(v);
    }

    public void print(){
        System.out.println("Integer " + value);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
