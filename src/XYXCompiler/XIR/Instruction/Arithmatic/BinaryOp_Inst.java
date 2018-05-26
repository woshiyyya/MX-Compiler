package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

public class BinaryOp_Inst extends Arithmatic {
    public static enum binaryop{
        Add, Sub, Mul, Div, Mod, Lsh, Rsh, And, Or, Xor
    }

    public Register dest;
    public DataSrc L_operand;
    public DataSrc R_operand;
    public binaryop op;

    public BinaryOp_Inst(BasicBlock BB_Scope, Register dest, DataSrc l_operand, DataSrc r_operand, binaryop op) {
        super(BB_Scope);
        this.dest = dest;
        L_operand = l_operand;
        R_operand = r_operand;
        this.op = op;
    }

    public BinaryOp_Inst(BasicBlock BB_Scope){
        super(BB_Scope);
    }


    @Override
    public void Update_UseDef() {
        if(dest instanceof VirtualReg)
            this.Def = (VirtualReg) dest;
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
    public void Reset_DestRegs(PhysicalReg Reg) {
        if(dest instanceof VirtualReg) dest = Reg;
    }
}
