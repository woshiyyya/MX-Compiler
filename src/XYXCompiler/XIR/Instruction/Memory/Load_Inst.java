package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Load_Inst extends Memory {
    public Register dest;
    public DataSrc addr;
    public DataSrc offset;
    public int size;

    public Load_Inst(BasicBlock BB_Scope, Register dest, DataSrc addr, DataSrc offset, int size) {
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
        if(offset instanceof VirtualReg)
            this.Used.add((VirtualReg) offset);

        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(addr != null && addr.equals(VReg)) addr = PReg;
        if(offset != null && offset.equals(VReg)) offset = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {
        if(dest instanceof VirtualReg) dest = Reg;
    }
}
