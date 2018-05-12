package XYXCompiler.XIR.Operand.Register;

public class VirtualReg extends Register {
    private static int cnt = 0;
    public int Num;
    public String Name;

    public VirtualReg(String name) {
        Num = cnt++;
        Name = name;
    }
}
