package XYXCompiler.XIR.Operand.Register;

public class GlobalVar extends Register {
    public String name;
    public int size;

    public GlobalVar(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
