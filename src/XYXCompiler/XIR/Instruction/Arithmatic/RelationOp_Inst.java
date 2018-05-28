package XYXCompiler.XIR.Instruction.Arithmatic;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.HashMap;
import java.util.Map;

public class RelationOp_Inst extends Arithmatic {
    public enum CmpOp{
        LS, LE, GT, GE, EQ, NE, Z
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
    public void Print() {
        System.out.println("\t" + op.name() + " " + dest.getString() + " " + L_operand.getString() + " " + R_operand.getString());
    }

    @Override
    public void LLPrint() {
        Map<CmpOp, String> rename = new HashMap<>();
        rename.put(CmpOp.LS, "slt");
        rename.put(CmpOp.LE, "sle");
        rename.put(CmpOp.GT, "sgt");
        rename.put(CmpOp.GE, "sge");
        rename.put(CmpOp.EQ, "seq");
        rename.put(CmpOp.NE, "sne");
        System.out.println("\t" + dest.getString() + " = "+ rename.get(op) + " " + L_operand.getString() + " " + R_operand.getString());
    }
}
