package XYXCompiler.BackEnd.X86_64;

import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;

import java.util.*;

public class FrameInfo {
    public Function func;
    public int RetAddr;
    public int CallerSavedStart;
    public int LastParam;
    public int FirstParam;
    public int LocalVariableStart;
    public int CalleeSavedStart;
    public int Totalsize;
    public List<PhysicalReg> UsedCallerSavedReg = new LinkedList<>();
    public List<PhysicalReg> UsedCalleeSavedReg = new LinkedList<>();
    public Map<FrameSlice, Integer> FrameSliceOffset = new HashMap<>();

    public FrameInfo(Function func) {
        this.func = func;
    }

    public void updatePreservedRegs(){
        for(PhysicalReg X: func.usedPregs){
            if(X.CalleeSave)
                UsedCalleeSavedReg.add(X);
            else
                UsedCallerSavedReg.add(X);
        }
    }

    public void updateLocalVariablePosition(){
        for(int i  = 0; i < func.frameSlice.size(); i++){
            FrameSlice slice = func.frameSlice.get(i);
            FrameSliceOffset.put(slice, LocalVariableStart - i*8);
        }
    }

    public void updateParameterPosition(){
        int paramnum = func.ArgSliceMap.size();
        for(int i = 0;i < paramnum; i++){
            FrameSlice slice = func.ArgSliceMap.get(func.ArgRegs.get(i));
            if(i > 5){
                FrameSliceOffset.put(slice, LastParam + (paramnum - 1 - i)*8);
            }else{
                FrameSliceOffset.put(slice, FirstParam - i*8);
            }
        }
    }
}
