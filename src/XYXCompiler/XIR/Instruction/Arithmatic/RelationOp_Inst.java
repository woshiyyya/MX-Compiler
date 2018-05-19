package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class RelationOp_Inst extends Arithmatic {
    public enum CmpOp{
        LS, LE, GT, GE, EQ, NE
    }

    public Register dest;
    public CmpOp op;
    public DataSrc L_operand;
    public DataSrc R_operand;

    public RelationOp_Inst(BasicBlock BB_Scope, Register dest) {
        super(BB_Scope);
        this.dest = dest;
    }

    public RelationOp_Inst(BasicBlock BB_Scope, Register dest, DataSrc l_operand, DataSrc r_operand, CmpOp op) {
        super(BB_Scope);
        this.dest = dest;
        this.op = op;
        L_operand = l_operand;
        R_operand = r_operand;
    }
}