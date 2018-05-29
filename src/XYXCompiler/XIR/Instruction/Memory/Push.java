package XYXCompiler.XIR.Instruction.Memory;

import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class Push extends Instruction {

    public DataSrc source;

    public Push(BasicBlock BB_Scope, DataSrc source) {
        super(BB_Scope);
        this.source = source;
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
