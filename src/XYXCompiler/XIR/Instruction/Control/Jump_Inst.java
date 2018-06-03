package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Jump_Inst extends Control {
    public BasicBlock target;

    public Jump_Inst(BasicBlock BB_Scope, BasicBlock target) {
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

    @Override
    public void Reset_Frameslice(FrameSlice slice, PhysicalReg Reg) {

    }

    @Override
    public void Reset_DestFrameSlice(PhysicalReg Reg) {

    }

    @Override
    public void Print() {
        System.out.println("\tJump\t" + target.getLabel());
    }

    @Override
    public void LLPrint() {
        System.out.println("\tjump " + target.getLabel());
    }

    public void accept(XIRVisitor visitor){
        visitor.visit(this);
    }
}
