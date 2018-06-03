package XYXCompiler.BackEnd.X86_64;

import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class FrameSlice extends Register {
    public Function func;
    public String name;

    public FrameSlice(Function func, String name) {
        this.func = func;
        this.name = name;
    }

}
