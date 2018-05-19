package XYXCompiler.XIR.Tools;

import XYXCompiler.XIR.Operand.DataSrc;

public class Condition {
    public DataSrc LHS;
    public DataSrc RHS;

    public Condition(DataSrc LHS, DataSrc RHS) {
        this.LHS = LHS;
        this.RHS = RHS;
    }
}
