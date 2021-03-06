package XYXCompiler.XIR.CFG;

import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst;
import XYXCompiler.XIR.Instruction.Control.CJump_Inst;
import XYXCompiler.XIR.Instruction.Control.Jump_Inst;
import XYXCompiler.XIR.Instruction.Functional.Return_Inst;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.DataSrc;

import java.util.LinkedList;
import java.util.List;

public class BasicBlock {
    public Function Func_Scope;
    public List<BasicBlock> predcessor = new LinkedList<>();
    public List<BasicBlock> successor = new LinkedList<>();
    public Instruction Entry = null;
    public Instruction Exit = null;
    public Return_Inst ret = null;
    public boolean If_closed = false;
    public int cnt;
    public String label;

    public BasicBlock(Function func_Scope) {
        Func_Scope = func_Scope;
    }

    public BasicBlock(Function func_Scope, String label) {
        Func_Scope = func_Scope;
        this.label = label;
    }

    public void add_Pred(BasicBlock Pred){
        predcessor.add(Pred);
    }

    public void add_Succ(BasicBlock Succ){
        successor.add(Succ);
    }

    public void add(Instruction Inst){
        if(Entry == null){
            Entry = Inst;
            Exit = Inst;
        }else{
            Exit.append(Inst);
        }
        cnt++;
    }
    public void Link(BasicBlock BB){
        BB.add_Pred(this);
        this.add_Succ(BB);
    }

    public void Close_R(Return_Inst ret){
        add(ret);
        this.ret = ret;
        Func_Scope.RetBlks.add(this);
        If_closed = true;
    }

    public void Close_J(BasicBlock end){
        if(If_closed)
            return;
        Jump_Inst jmp = new Jump_Inst(this, end);
        add(jmp);
        add_Succ(end);
        end.add_Pred(this);
        If_closed = true;
    }

    public void Close_B(DataSrc lhs, DataSrc rhs, RelationOp_Inst.CmpOp op, BasicBlock ifTrue, BasicBlock ifFalse){
        if(If_closed)
            return;
        CJump_Inst jmp = new CJump_Inst(this, lhs, rhs, op, ifTrue, ifFalse);
        add(jmp);
        add_Succ(ifTrue);
        add_Succ(ifFalse);
        ifTrue.add_Pred(this);
        ifFalse.add_Pred(this);
        If_closed = true;
    }

    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return "%" + this.label;
    }

}
