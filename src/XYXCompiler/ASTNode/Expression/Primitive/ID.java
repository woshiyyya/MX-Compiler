package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Type.*;
import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Tools.Symbol;

public class ID extends Expression {
    public String name;

    public ID(){
        name = null;
    }

    public ID(String name){
        this.name = name;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
