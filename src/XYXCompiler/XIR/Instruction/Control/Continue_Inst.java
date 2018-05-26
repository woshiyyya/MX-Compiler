package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

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

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {}

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {}
}
