package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class Move_Inst extends Memory {
    public DataSrc dest;
    public DataSrc Source;

    public Move_Inst(BasicBlock BB_Scope, DataSrc dest, DataSrc source) {
        super(BB_Scope);
        this.dest = dest;
        Source = source;
    }
}
