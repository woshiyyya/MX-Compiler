package XYXCompiler.Semantic.SymbolType.S_Variable_Type;

import XYXCompiler.Semantic.SymbolType.S_Type;
import XYXCompiler.Semantic.SymbolType.S_Type.Types;


public class S_Array_Type extends S_Variable_Type {

    public S_Variable_Type base_type;

    public S_Array_Type(S_Variable_Type base_type) {
        this.type = Types.ARRAY;
        this.base_type = base_type;
    }

    @Override
    public boolean Equal(S_Type other) {
        if(other.type == Types.ARRAY)
            return base_type.Equal(((S_Array_Type) other).base_type);
        else return false;
    }
}
