package XYXCompiler.BackEnd;


import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Control.CJump_Inst;
import XYXCompiler.XIR.Instruction.Control.Jump_Inst;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.HashSet;
import java.util.Set;

public class LivenessAnalyser {

    public void Initialize(XIRRoot root){
        for(Function func: root.Functions.values()){
            Set<VirtualReg> In = new HashSet<>();
            Set<VirtualReg> Out = new HashSet<>();
            boolean Converge;
            do{
                Converge = true;
                for(BasicBlock X: func.ReverseOrder){
                    for(Instruction inst = X.Exit; inst != null; inst = inst.prev){
                        In.clear();
                        Out.clear();
                        In.addAll(inst.Live_in);
                        Out.addAll(inst.Live_out);

                        if(!inst.ifupdated)
                            inst.Update_UseDef();
                        Update_InOut(inst);

                        if(!In.equals(inst.Live_in) || !Out.equals(inst.Live_out))
                            Converge = false;
                    }
                }
            }while(!Converge);
        }
    }

    private void Update_InOut(Instruction inst){
        if(inst instanceof CJump_Inst){
            inst.Live_out.addAll(((CJump_Inst) inst).ifTrue.Entry.Live_in);
            inst.Live_out.addAll(((CJump_Inst) inst).ifFalse.Entry.Live_in);
        }else if(inst instanceof Jump_Inst){
            inst.Live_out.addAll(((Jump_Inst) inst).target.Entry.Live_in);
        }else{
            if(inst.next != null)
                inst.Live_out.addAll(inst.next.Live_in);
        }
            inst.Live_in.addAll(inst.Used);
            inst.Live_in.addAll(inst.Live_out);
            inst.Live_in.remove(inst.Def);
    }


}
