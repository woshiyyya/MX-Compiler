package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Move_Inst extends Memory {
    public DataSrc dest;
    public DataSrc Source;

    public Move_Inst(BasicBlock BB_Scope, DataSrc dest, DataSrc source) {
        super(BB_Scope);
        this.dest = dest;
        Source = source;
    }

    @Override
    public void Update_UseDef() {
        if(dest instanceof VirtualReg)
            this.Def = (VirtualReg) dest;
        if(Source instanceof VirtualReg)
            this.Used.add((VirtualReg) Source);

        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(Source.equals(VReg)) Source = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {
        if(dest instanceof VirtualReg) dest = Reg;
    }

    @Override
    public void Print() {
        if(dest == null)
            System.out.println("dest" + Error());
        if(Source == null)
            System.out.println("source" + Error());
        System.out.println("\tMove\t" + dest.getString() + " " + Source.getString());
    }

    @Override
    public void LLPrint() {
        if(dest == null)
            System.out.println("dest" + Error());
        if(Source == null)
            System.out.println("source" + Error());
        System.out.println("\t" + dest.getString() + " = move " + Source.getString());
    }
}
