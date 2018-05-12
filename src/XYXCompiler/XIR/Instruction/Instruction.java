package XYXCompiler.XIR.Instruction;

import XYXCompiler.XIR.CFG.BasicBlock;

public class Instruction {
    private Instruction prev = null;
    private Instruction next = null;
    public BasicBlock BB_Scope;

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
}
