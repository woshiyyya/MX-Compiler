package XYXCompiler.XIR.Instruction;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Instruction {
    public Instruction prev = null;
    public Instruction next = null;
    public BasicBlock BB_Scope;
    public Set<VirtualReg> Live_in = new HashSet<>();
    public Set<VirtualReg> Live_out= new HashSet<>();
    public Set<VirtualReg> Used = new HashSet<>();
    public VirtualReg Def = null;
    public boolean ifupdated = false;

    public Instruction() {
    }

    public Instruction(BasicBlock BB_Scope) {
        this.BB_Scope = BB_Scope;
    }

    public void append(Instruction inst){
        if(next != null){
            next.prev = inst;
            inst.next = next;
        }else{
            BB_Scope.Exit = inst;
        }
        next = inst;
        inst.prev = this;
    }

    public void prepend(Instruction inst){
        if(prev != null){
            prev.next = inst;
            inst.prev = prev;
        }else{
            BB_Scope.Entry = inst;
        }
        prev = inst;
        inst.next = this;
    }

    public void remove(){
        if(prev == null)
            BB_Scope.Entry = next;
        if (next == null)
            BB_Scope.Exit = prev;

        if(prev != null)
            prev.next = next;
        if(next != null)
            next.prev = prev;
    }

    public abstract void Update_UseDef();
    public abstract void Reset_OperandRegs(VirtualReg VReg, PhysicalReg PReg);
    public abstract void Reset_DestRegs(PhysicalReg Reg);
    public abstract void Print();
    public abstract void LLPrint();

    public String Error(){
        return " is NUll----- in " + BB_Scope.getLabel();
    }
}
