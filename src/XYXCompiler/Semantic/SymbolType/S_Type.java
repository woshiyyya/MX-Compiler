package XYXCompiler.Semantic.SymbolType;

public abstract class S_Type {
    public enum Types{
        INT, BOOL, VOID, STRING, ARRAY, FUNCTION, CLASS
    }

    public Types type;

    public abstract boolean Equal(S_Type other);
}
