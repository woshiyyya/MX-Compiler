package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import javax.xml.crypto.Data;

public class CJump_Inst extends Control{

    public DataSrc L_operand;
    public DataSrc R_operand;
    public RelationOp_Inst.CmpOp op;
    public BasicBlock ifTrue;
    public BasicBlock ifFalse;

    public CJump_Inst(BasicBlock BB_Scope, DataSrc l_operand, DataSrc r_operand, RelationOp_Inst.CmpOp op, BasicBlock ifTrue, BasicBlock ifFalse) {
        super(BB_Scope);
        L_operand = l_operand;
        R_operand = r_operand;
        this.op = op;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    @Override
    public void Update_UseDef() {
        if(L_operand instanceof VirtualReg)
            this.Used.add((VirtualReg) L_operand);
        if(R_operand instanceof VirtualReg)
            this.Used.add((VirtualReg) R_operand);

        this.ifupdated = true;
    }

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(L_operand.equals(VReg)) L_operand = PReg;
        if(R_operand.equals(VReg)) R_operand = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {}
}
