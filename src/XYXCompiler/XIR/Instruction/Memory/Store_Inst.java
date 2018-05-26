package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Store_Inst extends Memory {
    //store imm/reg to memory location
    public DataSrc source;
    public int datasize;
    public DataSrc addr;
    public DataSrc offset;
    public int offsetsize;

    public Store_Inst(BasicBlock BB_Scope, DataSrc addr, DataSrc offset, int offsetsize, DataSrc source, int datasize) {
        super(BB_Scope);
        this.source = source;
        this.datasize = datasize;
        this.addr = addr;
        this.offset = offset;
        this.offsetsize = offsetsize;
    }

    @Override
    public void Update_UseDef() {
        if(source instanceof VirtualReg)
            this.Used.add((VirtualReg) source);
        if(addr instanceof VirtualReg)
            this.Used.add((VirtualReg) addr);
        if(offset instanceof VirtualReg)
            this.Used.add((VirtualReg) offset);

        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(source != null && source.equals(VReg)) source = PReg;
        if(addr != null && addr.equals(VReg)) addr = PReg;
        if(offset != null && offset.equals(VReg)) offset = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {}
}
