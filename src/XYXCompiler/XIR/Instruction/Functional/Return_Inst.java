package XYXCompiler.XIR.Instruction.Functional;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Return_Inst extends Functional {
    public DataSrc retval;

    public Return_Inst(BasicBlock BB_Scope, DataSrc retval) {
        super(BB_Scope);
        this.retval = retval;
    }

    @Override
    public void Update_UseDef() {
        if(retval instanceof VirtualReg)
            this.Used.add((VirtualReg) retval);
        this.ifupdated = true;
    }
}
