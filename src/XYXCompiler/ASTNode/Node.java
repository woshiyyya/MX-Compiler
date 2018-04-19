package XYXCompiler.ASTNode;

import XYXCompiler.Builder.ASTVisitor;
import XYXCompiler.Semantic.Scope.LocalScope;

public class Node {
    public LocalScope Scope;
    public void accept(ASTVisitor visitor){};
    public void setScope(LocalScope scope){
        this.Scope = scope;
    } //将Node与其所在的Scope联系
}
