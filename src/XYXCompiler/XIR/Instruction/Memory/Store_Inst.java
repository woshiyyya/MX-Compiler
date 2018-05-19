package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;

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
}
