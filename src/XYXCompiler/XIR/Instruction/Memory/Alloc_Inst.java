package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Alloc_Inst extends Memory {
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
}
