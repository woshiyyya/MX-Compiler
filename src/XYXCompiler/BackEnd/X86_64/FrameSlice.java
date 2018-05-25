package XYXCompiler.BackEnd.X86_64;

import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.Operand.DataSrc;

public class FrameSlice extends DataSrc {
    public Function func;
    public String name;

    public FrameSlice(Function func, String name) {
        this.func = func;
        this.name = name;
    }

}
