package XYXCompiler.FrontEnd.ASTNode.Expression.Primitive;

import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Node;
import XYXCompiler.FrontEnd.Builder.ASTVisitor;

public class ID extends Expression {
    public String name;
    public Node entity = null;

    public ID(String name){
        this.name = name;
        LValue = true;
    }

    public void setEntity(Node entity){
        if(this.entity == null)
            this.entity = entity;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
