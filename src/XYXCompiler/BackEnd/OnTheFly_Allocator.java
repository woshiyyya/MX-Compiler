package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.*;

public class OnTheFly_Allocator {
    List<FrameSlice> slices = new LinkedList<>();
    Map<VirtualReg, FrameSlice> VRegSliceMap = new HashMap<>();
    List<PhysicalReg> AvailableRegs;
    XIRRoot root;

    public OnTheFly_Allocator(XIRRoot root, Set<PhysicalReg> X86Registers) {
        this.root = root;
        AvailableRegs = new LinkedList<>(X86Registers);
    }

    private void Alloc_FrameSlice(Set<VirtualReg> used, Function func){
        if(!used.isEmpty()){
            for(VirtualReg X: used){
                if(VRegSliceMap.get(X) == null){
                    FrameSlice sliceX = new FrameSlice(func, X.Name);
                    VRegSliceMap.put(X, sliceX);
                }
            }
        }
    }

    public void Allocate(){
        for(Function X: root.Functions.values()){
            VRegSliceMap.putAll(X.VRegSliceMap);
            for(BasicBlock Y: X.ReverseOrder){
                for(Instruction inst = Y.Entry; inst != null; inst = inst.next){
                    Alloc_FrameSlice(inst.Used, X);
                    Fuckhere
                }
            }
        }
    }
}
