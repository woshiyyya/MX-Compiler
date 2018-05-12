package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Tools.Condition;

public class Branch_Inst extends Control {
    public DataSrc Cond;
    public BasicBlock ifTrue;
    public BasicBlock ifFalse;

    public Branch_Inst(BasicBlock BB_Scope, DataSrc cond, BasicBlock ifTrue, BasicBlock ifFalse) {
        super(BB_Scope);
        Cond = cond;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }
}
