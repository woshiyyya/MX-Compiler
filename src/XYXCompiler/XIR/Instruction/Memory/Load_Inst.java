package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Load_Inst extends Memory {
    public Register dest;
    public DataSrc addr;
    public int offset = 0;
    public int size;


    public Load_Inst(BasicBlock BB_Scope, Register dest, DataSrc addr, int offset, int size) {
        super(BB_Scope);
        this.dest = dest;
        this.addr = addr;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public void Update_UseDef() {
        if(dest instanceof VirtualReg)
            this.Def = (VirtualReg) dest;
        if(addr instanceof VirtualReg)
            this.Used.add((VirtualReg) addr);

        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(addr != null && addr.equals(VReg)) addr = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {
        if(dest instanceof VirtualReg) dest = Reg;
    }

    @Override
    public void Print() {
        String ans = "\tLoad\t" + dest.getString() + " [" + addr.getString() + " + " + offset + "]";
        System.out.println(ans);
    }

    @Override
    public void LLPrint() {
        String ans = "\t" + dest.getString() + " = load " + "8 " + addr.getString() +  " " + offset;
        System.out.println(ans);
    }
}
