package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class Alloc_Inst extends Memory {
    public Register dest; // the memory address
    public int size;

    public Alloc_Inst(BasicBlock BB_Scope, Register dest, int size) {
        super(BB_Scope);
        this.dest = dest;
        this.size = size;
    }
}
