package XYXCompiler.XIR.Operand.Static;

import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.Operand.DataSrc;

public class Immediate extends DataSrc {
    public int value;
    public String Str;
    public boolean ignore = false;

    public Immediate(int value) {
        this.value = value;
        this.Str = "" + value;
        this.ignore = (value>8e7);
    }

}
