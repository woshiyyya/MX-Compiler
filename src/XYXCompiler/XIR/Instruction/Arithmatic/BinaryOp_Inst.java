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

    @Override
    public void Reset_Frameslice(FrameSlice slice, PhysicalReg Reg) {
        if(L_operand != null && L_operand == slice) L_operand = Reg;
        if(R_operand != null && R_operand == slice) R_operand = Reg;
    }

    @Override
    public void Reset_DestFrameSlice(PhysicalReg Reg) {
        if(dest != null) dest = Reg;
    }

    @Override
    public void Print() {
        System.out.println("\t" + op.name() + "\t" + dest.getString() + " " + L_operand.getString() + " " + R_operand.getString());
    }

    @Override
    public void LLPrint() {
        Map<binaryop, String> rename = new HashMap<>();
        rename.put(binaryop.Add, "add");
        rename.put(binaryop.Sub, "sub");
        rename.put(binaryop.Mul, "mul");
        rename.put(binaryop.Div, "div");
        rename.put(binaryop.Mod, "rem");
        rename.put(binaryop.Lsh, "lsh");
        rename.put(binaryop.Rsh, "rsh");
        rename.put(binaryop.And, "and");
        rename.put(binaryop.Or, "or");
        rename.put(binaryop.Xor, "xor");
        System.out.println("\t" + dest.getString() + " = " + rename.get(op) + " " + L_operand.getString() + " " + R_operand.getString());
    }

    public void accept(XIRVisitor visitor){
        visitor.visit(this);
    }
}
