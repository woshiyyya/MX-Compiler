package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Instruction.Instruction;

public class Control extends Instruction {
    public Control(BasicBlock BB_Scope) {
        super(BB_Scope);
    }
}
