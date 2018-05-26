package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Instruction.Memory.Load_Inst;
import XYXCompiler.XIR.Instruction.Memory.Store_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.Immediate;

import java.util.*;

public class OnTheFly_Allocator {
    List<FrameSlice> slices = new LinkedList<>();
    Map<VirtualReg, FrameSlice> VRegSliceMap = new HashMap<>();
    List<PhysicalReg> AvailableRegs;
    X86Registers x86Registers;
    XIRRoot root;

    public OnTheFly_Allocator(XIRRoot root,X86Registers x86Registers) {
        this.root = root;
        this.x86Registers = x86Registers;
        AvailableRegs = new LinkedList<>(x86Registers.GeneralRegs);
    }

    public void Handle_Call_Params(Function caller, Call_Inst inst){
        int id = 0;
        int Argnum = inst.ArgLocs.size();
        List<PhysicalReg> paramRags = x86Registers.FuncParamRegs;

        // Now we just care about the first 6, others will be pushed in NASMTranslator
        for(int i = 0;i < 6; i++){
            if(i == Argnum) break;
            DataSrc X = inst.ArgLocs.get(i);
            if(X instanceof VirtualReg)
                X = VRegSliceMap.get(X);
            inst.prepend(new Load_Inst(inst.BB_Scope, paramRags.get(id++), X,null,8));
        }
    }


    public void Allocate(){
        for(Function func: root.Functions.values()){
            func.Initialize_FrameInfo();
            VRegSliceMap.clear();
            VRegSliceMap.putAll(func.ArgSliceMap);
            for(BasicBlock Blk: func.ReverseOrder){
                for(Instruction inst = Blk.Entry; inst != null; inst = inst.next){
                    System.out.println(inst.getClass().getSimpleName());
                    int id = 0;
                    if(inst instanceof Call_Inst){
                        Handle_Call_Params(func, (Call_Inst) inst);
                    }else{
                        //Handle Operand Regs
                        for(VirtualReg VReg: inst.Used){
                            PhysicalReg PReg = AvailableRegs.get(id++);
                            func.usedPregs.add(PReg);
                            inst.Reset_OperandRegs(VReg, PReg);
                            System.out.println("[" + VReg.Num + "->" + PReg.name + "]");

                            if(VRegSliceMap.get(VReg) == null){
                                FrameSlice sliceX = new FrameSlice(func, VReg.Name);
                                VRegSliceMap.put(VReg, sliceX);
                                func.frameSlice.add(sliceX);
                                inst.prepend(new Load_Inst(Blk, PReg, sliceX, new Immediate(0), 8));
                            }
                        }


                        //Handle Dest Reg
                        if(inst.Def != null){
                            PhysicalReg PReg = AvailableRegs.get(id++);
                            func.usedPregs.add(PReg);
                            inst.Reset_DestRegs(PReg);
                            System.out.println("[" + inst.Def.Num + "->" + PReg.name + "]");
                            if(VRegSliceMap.get(inst.Def) == null){
                                FrameSlice sliceX = new FrameSlice(func, inst.Def.Name);
                                VRegSliceMap.put(inst.Def, sliceX);
                                func.frameSlice.add(sliceX);
                                inst.append(new Store_Inst(Blk, sliceX, null, 0, PReg,8));
                                inst = inst.next;
                            }
                        }
                    }
                }
            }
        }
    }
}
