package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.XIR.CFG.BasicBlock;

public class Continue_Inst extends Control {
    public BasicBlock target;

    public Continue_Inst(BasicBlock BB_Scope, BasicBlock target) {
        super(BB_Scope);
        this.target = target;
    }

    @Override
    public void Update_UseDef() {
        this.ifupdated = true;
    }
}
