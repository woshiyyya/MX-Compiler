package XYXCompiler.XIR.Instruction.Functional;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;

public class Return_Inst extends Functional {
    public DataSrc retval;

    public Return_Inst(BasicBlock BB_Scope, DataSrc retval) {
        super(BB_Scope);
        this.retval = retval;
    }
}
