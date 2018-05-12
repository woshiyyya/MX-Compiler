package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;

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

    public boolean isLogical(){
        return (op == binaryop.Or || op == binaryop.And);
    }
}
