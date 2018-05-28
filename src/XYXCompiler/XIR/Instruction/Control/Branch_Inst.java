package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

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

    @Override
    public void Update_UseDef() {
        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {

    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {

    }

    @Override
    public void Print() {
        System.out.println("\tDrop");
    }

    @Override
    public void LLPrint() {

    }
}
