package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Instruction.Instruction;

public class Memory extends Instruction {
    public Memory(BasicBlock BB_Scope) {
        super(BB_Scope);
    }
}
