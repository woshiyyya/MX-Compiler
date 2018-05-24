package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Instruction.Instruction;

public abstract class Arithmatic extends Instruction {

    public Arithmatic(BasicBlock BB_Scope) {
        super(BB_Scope);
    }
}
