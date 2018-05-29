package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Pop extends Instruction {
    public Register dest;

    public Pop(BasicBlock BB_Scope, Register dest) {
        super(BB_Scope);
        this.dest = dest;
    }

    @Override
    public void Update_UseDef() {

    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {

    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {

    }

    @Override
    public void Print() {

    }

    @Override
    public void LLPrint() {

    }

    @Override
    public void accept(XIRVisitor visitor) {
        visitor.visit(this);
    }
}
