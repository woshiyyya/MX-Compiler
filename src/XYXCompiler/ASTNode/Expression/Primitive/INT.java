package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.Builder.ASTVisitor;

import java.math.BigInteger;

public class INT extends Expression {
    BigInteger value= BigInteger.ZERO;

    public INT(){}
    public INT(String v){
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
