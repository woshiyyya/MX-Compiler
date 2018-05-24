package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

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

    @Override
    public void Update_UseDef() {
        if(dest instanceof VirtualReg)
            this.Def = (VirtualReg) dest;
        if(operand instanceof VirtualReg)
            this.Used.add((VirtualReg) operand);

        this.ifupdated = true;
    }
}
