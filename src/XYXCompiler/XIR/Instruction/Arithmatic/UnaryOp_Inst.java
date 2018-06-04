package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.HashMap;
import java.util.Map;

public class UnaryOp_Inst extends Arithmatic {

    public enum unaryop{
        NEG, NOT, Tilde
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

    @Override
    public void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg) {
        if(operand.equals(VReg)) operand = PReg;
    }

    @Override
    public void Reset_DestRegs(PhysicalReg Reg) {
        if(dest instanceof VirtualReg) dest = Reg;
    }

    @Override
    public void Reset_Frameslice(FrameSlice slice, PhysicalReg Reg) {
        if(operand != null && operand == slice) operand = Reg;
    }

    @Override
    public void Reset_DestFrameSlice(PhysicalReg Reg) {
        if(dest != null) dest = Reg;
    }

    @Override
    public void Print() {
        System.out.println("\t" + op.name() + " " + dest.getString() + " " + operand.getString());
    }

    @Override
    public void LLPrint() {
        Map<unaryop, String> rename = new HashMap<>();
        rename.put(unaryop.NEG, "neg");
        rename.put(unaryop.NOT, "not");
        System.out.println("\t" + dest.getString() + " = " + rename.get(op) + " "  + operand.getString());
    }

    public void accept(XIRVisitor visitor){
        visitor.visit(this);
    }
}
