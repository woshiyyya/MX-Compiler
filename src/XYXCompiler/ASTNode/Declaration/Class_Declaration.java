package XYXCompiler.ASTNode.Declaration;

import XYXCompiler.Builder.ASTVisitor;


import java.util.LinkedList;
import java.util.List;

public class Class_Declaration extends Declaration{
    public List<Declaration> Members;

    public Class_Declaration() {
        decl_type = Decl_type.CLASS;
        Members = new LinkedList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
