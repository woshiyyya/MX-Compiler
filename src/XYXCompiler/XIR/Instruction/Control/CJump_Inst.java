package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.Immediate;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void Print() {
        System.out.println("\tCMP\t" + L_operand.getString() + " " + R_operand.getString());
        System.out.println("\tCJump\t" + op.name() + " " + ifTrue.getLabel() + " " + ifFalse.getLabel());
    }

    @Override
    public void LLPrint() {
        Map<RelationOp_Inst.CmpOp, String> rename = new HashMap<>();
        rename.put(RelationOp_Inst.CmpOp.LS, "slt");
        rename.put(RelationOp_Inst.CmpOp.LE, "sle");
        rename.put(RelationOp_Inst.CmpOp.GT, "sgt");
        rename.put(RelationOp_Inst.CmpOp.GE, "sge");
        rename.put(RelationOp_Inst.CmpOp.EQ, "seq");
        rename.put(RelationOp_Inst.CmpOp.NE, "sne");
        String ans = "";
        if(op != RelationOp_Inst.CmpOp.Z){
            VirtualReg tem = new VirtualReg(null);
            ans += "\t" + tem.getString() + " = " + rename.get(op) + " " + L_operand.getString() + " " + R_operand.getString() + "\n";
            ans += "\tbr " + tem.getString() + " " + ifTrue.getLabel() + " " + ifFalse.getLabel();
        }else{
            ans += "\tbr " + L_operand.getString() + " " + ifTrue.getLabel() + " " + ifFalse.getLabel();
        }
        System.out.println(ans);
    }

    public void accept(XIRVisitor visitor){
        visitor.visit(this);
    }

}
