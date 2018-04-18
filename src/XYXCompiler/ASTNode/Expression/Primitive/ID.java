package XYXCompiler.ASTNode.Expression.Primitive;

import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Node;
import XYXCompiler.Builder.ASTVisitor;

public class ID extends Expression {
    public String name;
    public Node entity = null;

    public ID(){
        name = null;
    }

    public ID(String name){
        this.name = name;
    }

    public void setEntity(Node entity){ //将ID与声明实体联系
        if(this.entity == null)
            this.entity = entity;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
