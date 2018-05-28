package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.Immediate;

public class Store_Inst extends Memory {
    //store imm/reg to memory location
    public DataSrc source;
    public int datasize;
    public DataSrc addr;
    public int offset = 0;


    public Store_Inst(BasicBlock BB_Scope, DataSrc source, int datasize, DataSrc addr, int offset) {
        super(BB_Scope);
        this.source = source;
        this.datasize = datasize;
        this.addr = addr;
        this.offset = offset;
    }

    @Override
    public void Update_UseDef() {
        if(source instanceof VirtualReg)
            this.Used.add((VirtualReg) source);
        if(addr instanceof VirtualReg)
            this.Used.add((VirtualReg) addr);

        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(source != null && source.equals(VReg)) source = PReg;
        if(addr != null && addr.equals(VReg)) addr = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {}

    @Override
    public void Print() {
        String ans = "\tStore\t" + datasize+ " [" + addr.getString();
        if(offset != 0)
            ans += " + " + offset;
        ans += "] " + source.getString();
        System.out.println(ans);
    }

    @Override
    public void LLPrint() {
        String ans = "\tstore " + "8 " + addr.getString() + " " + source.getString() + " " + offset;
        System.out.println(ans);
    }
}
