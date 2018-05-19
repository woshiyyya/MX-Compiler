package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

public class UnaryOp_Inst extends Arithmatic {
    public enum unaryop{
        NEG, NOT
    }
    public Register dest;
    public unaryop op;
    public DataSrc operand;


    public UnaryOp_Inst(BasicBlock BB_Scope, Register dest, unaryop op, DataSrc operand) {
        super(BB_Scope);
        this.dest = dest;
        this.op = op;
        this.operand = operand;
    }
}
