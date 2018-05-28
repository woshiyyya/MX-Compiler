package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Alloc_Inst extends Memory {
    /*
        int* A = new int[10];

        mov     edi, 40
        call    _Znam
        mov     qword [rbp-8H], rax
    */
    public Register dest; // the memory address
    public DataSrc size;

    public Alloc_Inst(BasicBlock BB_Scope, Register dest, DataSrc size) {
        super(BB_Scope);
        this.dest = dest;
        this.size = size;
    }


    @Override
    public void Update_UseDef() {
        if(dest instanceof VirtualReg)
            this.Def = (VirtualReg) dest;
        if(size instanceof VirtualReg)
            this.Used.add((VirtualReg) size);

        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(size.equals(VReg)) size = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {
        if(dest instanceof VirtualReg) dest = Reg;
    }

    @Override
    public void Print() {
        System.out.println("\tAlloc\t" + dest.getString() + " " + size.getString());
    }

    @Override
    public void LLPrint() {
        System.out.println("\t" + dest.getString() + " = alloc " + size.getString());
    }
}
