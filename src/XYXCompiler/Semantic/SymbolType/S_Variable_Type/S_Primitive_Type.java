package XYXCompiler.Semantic.SymbolType.S_Variable_Type;

import XYXCompiler.Semantic.SymbolType.S_Type;
import javafx.scene.shape.SVGPath;

public class S_Primitive_Type extends S_Variable_Type {

    public S_Primitive_Type(S_Type.Types t) {
        this.type = t;
    }

    public boolean Equal(S_Type other){
        return other.type == this.type;
    }
}
