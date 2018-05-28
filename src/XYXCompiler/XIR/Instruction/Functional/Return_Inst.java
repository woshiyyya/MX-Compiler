package XYXCompiler.XIR.Instruction.Functional;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
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

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(retval.equals(VReg)) retval = PReg;
        else System.out.println("No such ret Reg!");
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {}

    @Override
    public void Print() {
        System.out.println("\tReturn\t" + retval.getString());
    }

    @Override
    public void LLPrint() {
        String ans = "\tret " + (retval == null ? "" :retval.getString());
        System.out.println(ans);
    }
}
