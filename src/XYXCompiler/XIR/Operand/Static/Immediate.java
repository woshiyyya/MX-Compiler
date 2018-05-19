package XYXCompiler.XIR.Operand.Static;

import XYXCompiler.XIR.Operand.DataSrc;

public class Immediate extends DataSrc {
    public int value;

    public Immediate(int value) {
        this.value = value;
    }
}
