package XYXCompiler.FrontEnd.ASTNode;

import XYXCompiler.FrontEnd.Builder.ASTVisitor;
import XYXCompiler.FrontEnd.Semantic.Scope.LocalScope;
import XYXCompiler.Tools.Position;

public class Node {
    public LocalScope Scope;
    public Position position;
    public void accept(ASTVisitor visitor){};
    public void setScope(LocalScope scope){
        this.Scope = scope;
    }
    public void setPosition(int Line, int Col){position = new Position(Line, Col);}
    public String getPosition(){return position.toString();}
}
