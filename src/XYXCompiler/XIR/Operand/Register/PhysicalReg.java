package XYXCompiler.XIR.Operand.Register;

public class PhysicalReg extends Register {
    public String name;
    public boolean CallerSave;
    public boolean CalleeSave;

    public PhysicalReg(String name, boolean callerSave, boolean calleeSave) {
        this.name = name;
        CallerSave = callerSave;
        CalleeSave = calleeSave;
    }
}
