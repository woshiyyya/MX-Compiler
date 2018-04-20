package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.Int_Type;
import XYXCompiler.Builder.ASTVisitor;

import java.math.BigInteger;

public class INT extends Expression {
    BigInteger value = BigInteger.ZERO;

    public INT(){
        type = new Int_Type();
    }
    public INT(String v){
        type = new Int_Type();
        value = new BigInteger(v);
    }

    public void print(){
        System.out.println("Integer " + value);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
