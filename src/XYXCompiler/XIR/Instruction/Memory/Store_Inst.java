package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class Store_Inst extends Memory {
    //store imm/reg to memory location
    public DataSrc source;
    public DataSrc addr;
    public int size;
    public int offset;

    public Store_Inst(BasicBlock BB_Scope, DataSrc source, int size, DataSrc addr, int offset) {
        super(BB_Scope);
        this.source = source;
        this.addr = addr;
        this.size = size;
        this.offset = offset;
    }
}
