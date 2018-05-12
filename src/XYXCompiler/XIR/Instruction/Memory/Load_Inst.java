package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class Load_Inst extends Memory {
    public Register dest;
    public DataSrc addr;
    public int offset;
    public int size;

    public Load_Inst(BasicBlock BB_Scope, Register dest, DataSrc addr, int offset, int size) {
        super(BB_Scope);
        this.dest = dest;
        this.addr = addr;
        this.offset = offset;
        this.size = size;
    }
}
