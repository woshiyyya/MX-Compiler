package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.Allocator.GraphColoring.IFG_Node;
import XYXCompiler.BackEnd.X86_64.FrameInfo;
import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Functional.Return_Inst;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Instruction.Memory.*;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.Immediate;
import javafx.geometry.Pos;
import org.antlr.v4.runtime.BailErrorStrategy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static XYXCompiler.BackEnd.X86_64.X86Registers.*;
import static java.lang.Integer.min;

public class FrameTranslator {
    public XIRRoot xirRoot;
    public Map<Function, FrameInfo> InfoMap = new HashMap<>();
    private Function curFunc;

    /*
        # MyFunction2(10, 5, 2);
        Notice: Everything is 8 Byte ,push as well

        <---->             (caller-saved [optional])    rpb + Pos_CallerSaved
        |    | [rbp + ?]   (kth argument)               rbp + Pos_LastParam
        |    | [rbp + 10H]  (7th argument)
        | RA | [rbp + 08H]  (return address)             rpb + Pos_RetAddr
        | FP | [rbp]       (old ebp value)               rpb
        |    | [rbp - 08H]  (1st argument #rdi)          rpb - Pos_FirstParam
        |    | [rbp - 10H]  (2nd argument #rsi)
        |    | [rbp - 18H]  (3rd argument #rdx)
        |    | [rbp - 20H]  (4th argument #rcx)
        |    | [rbp - 28H]  (5th argument #r8)
        |    | [rbp - 30H]  (6th argument #r9)
        |    | [rbp - 38H]  (Callee-Saved Start)
        :    :
        |    | [rbp - ?H]   (Local Vars)                 rbp - Pos_LocalVar
        :    :
        |    | []           (Caller-Saved Start)
        |    | []         (call)
        |    | [rbp - X]  (esp - the current stack pointer. The use of push / pop is valid now)
    */

    public FrameTranslator(XIRRoot xirRoot) {
        this.xirRoot = xirRoot;
    }

    private void processFunctionFrame(Function func){
        FrameInfo Info = new FrameInfo(func);
        Info.updatePreservedRegs();
        InfoMap.put(func, Info);
        int Argnum = func.ArgRegs.size();
        int ReducedArgnum = (Argnum > 6) ? 6 : Argnum;
        Info.RetAddr                = 8;
        Info.LastParam              = Info.RetAddr              + 8 * (Argnum - ReducedArgnum);
        Info.FirstParam             = -8;
        Info.CalleeSavedStart       = Info.FirstParam           - 8 * (ReducedArgnum);
        Info.LocalVariableStart     = Info.CalleeSavedStart     - 8 * Info.UsedCalleeSavedReg.size();
        Info.CallerSavedStart       = Info.LocalVariableStart   - 8 * func.frameSlice.size();
        //Info.Totalsize              = (Info.CallerSavedStart    - 8 * Info.UsedCallerSavedReg.size())*(-1);
        Info.Totalsize              = (Info.CallerSavedStart     -8 * func.usedPregs.size())*-1;

        Info.updateLocalVariablePosition();
        Info.updateParameterPosition();
    }

    private void Prologue(Function func){
        FrameInfo Info = InfoMap.get(func);
        BasicBlock curBB = func.StartBB;
        Instruction Entry = curBB.Entry;

        Entry.prepend(new BinaryOp_Inst(curBB, rsp, rsp, new Immediate(Info.Totalsize),BinaryOp_Inst.binaryop.Sub));

        //StoreParametersRegs(Info, curBB, Entry);  //On-the-fly!
        MoveParametersRegs(Info, curBB, Entry);     //Graph Coloring!
        //StoreCalleeSavedRegs(func, curBB, Entry);
    }


    private void Epilogue(Function func){
        FrameInfo Info = InfoMap.get(func);
        BasicBlock curBB = func.EndBB;
        Instruction Exit = curBB.Exit;

        //ReloadCalleeSavedRegs(func, curBB, Exit);

        Exit.prepend(new BinaryOp_Inst(curBB, rsp, rsp, new Immediate(Info.Totalsize),BinaryOp_Inst.binaryop.Add));
        Exit.prepend(new Pop(curBB, rbp));
    }



    private void TransformSlice(Function func, Instruction inst){
        FrameInfo Info = InfoMap.get(func);
        if(inst instanceof Load_Inst){
            Load_Inst Inst = (Load_Inst) inst;
            if(Inst.addr instanceof FrameSlice){
                Inst.offset = Info.FrameSliceOffset.get(Inst.addr);
                Inst.addr = rbp;
            }
        }else if(inst instanceof Store_Inst){
            Store_Inst Inst = (Store_Inst) inst;
            if(Inst.addr instanceof FrameSlice){
                Inst.offset = Info.FrameSliceOffset.get(Inst.addr);
                Inst.addr = rbp;
            }
        }
    }

    private void MoveParametersRegs(FrameInfo Info, BasicBlock curBB, Instruction Inst){
        Function func = Info.func;
        for(int i = 0; i < func.ArgSrcs.size(); i++){
            if(i == 6) break;
            DataSrc dataSrc = func.ArgSrcs.get(i);
            if(dataSrc instanceof PhysicalReg)
                Inst.prepend(new Move_Inst(curBB, dataSrc, FuncParamRegs.get(i)));
            else if(dataSrc instanceof FrameSlice){
                Inst.prepend(new Store_Inst(curBB, FuncParamRegs.get(i),8,
                        rbp, Info.FrameSliceOffset.get(dataSrc)));
            }
        }

        for(int i = 6;i < func.ArgRegs.size();i++){
            VirtualReg arg = Info.func.ArgRegs.get(i);
            FrameSlice slice = func.ArgSliceMap.get(arg);
            DataSrc dest = func.ArgSrcs.get(i);
            int offset = Info.FrameSliceOffset.get(slice);

            if(dest instanceof PhysicalReg){
                Inst.prepend(new Load_Inst(curBB, (PhysicalReg)dest, rbp, offset,8));
            }else if(dest instanceof FrameSlice){
                Inst.prepend(new Load_Inst(curBB, rdx, rbp, offset,8));
                Inst.prepend(new Store_Inst(curBB, rdx,8, rbp,Info.FrameSliceOffset.get(dest)));
            }
        }
    }

    private void StoreParametersRegs(FrameInfo Info, BasicBlock curBB, Instruction Inst){
        Function func = Info.func;
        for(int i = 0;i < min(func.ArgRegs.size(),6);i++){
            Inst.prepend(new Store_Inst(curBB, FuncParamRegs.get(i), 8, rbp, Info.FirstParam - 8*i));
        }
    }

    private void StoreCallerSavedRegs(FrameInfo Info, BasicBlock curBB, Instruction inst){
        for(int i = 0; i < Info.UsedCallerSavedReg.size(); i++){
            inst.prepend(new Store_Inst(curBB, Info.UsedCallerSavedReg.get(i),
                    8,rbp,Info.CallerSavedStart - i*8));
        }
    }

    private void ReloadCallerSavedRegs(FrameInfo Info, BasicBlock curBB, Instruction inst){
        for(int i = 0; i < Info.UsedCallerSavedReg.size(); i++){
            inst.append(new Load_Inst(curBB, Info.UsedCallerSavedReg.get(i),
                    rbp,Info.CallerSavedStart - i*8,8));
        }
    }

    private void StoreCalleeSavedRegs(Function func, BasicBlock curBB, Instruction Entry){
        FrameInfo Info = InfoMap.get(func);
        for(int i = 0; i < Info.UsedCalleeSavedReg.size(); i++){
            Entry.prepend(new Store_Inst(curBB, Info.UsedCalleeSavedReg.get(i),8,rbp,Info.CalleeSavedStart - 8*i));
        }
    }

    private void ReloadCalleeSavedRegs(Function func, BasicBlock curBB, Instruction Exit){
        FrameInfo Info = InfoMap.get(func);
        for(int i = 0; i < Info.UsedCalleeSavedReg.size(); i++){
            Exit.append(new Load_Inst(curBB, Info.UsedCalleeSavedReg.get(i),rbp,Info.CalleeSavedStart - 8*i,8));
        }
    }

    private void StoreBuiltinCalleeSavedRegs(BasicBlock curBB, Instruction Entry){
        //curFunction Info
        FrameInfo Info = InfoMap.get(curFunc);
        int offset = Info.UsedCallerSavedReg.size();
        // Save Additional regs upon caller-saved Slice!
        for(int i = 0; i < Info.UsedCalleeSavedReg.size(); i++){
            Entry.prepend(new Store_Inst(curBB, Info.UsedCalleeSavedReg.get(i),8,rbp,Info.CallerSavedStart - 8*(i+offset)));
        }
    }

    private void ReloadBuiltinCalleeSavedRegs(BasicBlock curBB, Instruction Exit){
        FrameInfo Info = InfoMap.get(curFunc);
        int offset = Info.UsedCallerSavedReg.size();
        // Save Additional regs upon caller-saved Slice!
        for(int i = 0; i < Info.UsedCalleeSavedReg.size(); i++){
            Exit.append(new Load_Inst(curBB, Info.UsedCalleeSavedReg.get(i),rbp,Info.CallerSavedStart - 8*(i + offset),8));
        }
    }

    private void StoreAllRegisters(BasicBlock curBB, Instruction inst){
        FrameInfo Info = InfoMap.get(curFunc);
        List<PhysicalReg> List = new LinkedList<>(curFunc.usedPregs);
        for(int i = 0;i < curFunc.usedPregs.size();i++){
            PhysicalReg reg = List.get(i);
            inst.prepend(new Store_Inst(curBB, reg, 8,rbp,Info.CallerSavedStart - 8*i));
        }
    }

    private void ReloadAllRegisters(BasicBlock curBB, Instruction inst){
        FrameInfo Info = InfoMap.get(curFunc);
        List<PhysicalReg> List = new LinkedList<>(curFunc.usedPregs);
        for(int i = 0;i < curFunc.usedPregs.size();i++){
            PhysicalReg reg = List.get(i);
            inst.append(new Load_Inst(curBB, reg, rbp,Info.CallerSavedStart - 8*i,8));
        }
    }

    //liveout - define

    private void StorePreservedRegisters(BasicBlock curBB, Instruction inst){
        FrameInfo Info = InfoMap.get(curFunc);
        int offset = 0;
        for(VirtualReg X: inst.Live_out){
            IFG_Node node = curFunc.ColorMap.get(X);
            if(node.PReg == inst.Def) continue;
            if(node.PReg instanceof PhysicalReg)
                inst.prepend(new Store_Inst(curBB, node.PReg, 8,rbp,Info.CallerSavedStart - 8*(offset++)));
        }
    }

    private void ReloadPreservedRegisters(BasicBlock curBB, Instruction inst){
        FrameInfo Info = InfoMap.get(curFunc);
        int offset = 0;
        for(VirtualReg X: inst.Live_out){
            IFG_Node node = curFunc.ColorMap.get(X);
            if(node.PReg == inst.Def) continue;
            if(node.PReg instanceof PhysicalReg)
                inst.append(new Load_Inst(curBB, (PhysicalReg)node.PReg, rbp,Info.CallerSavedStart - 8*(offset++),8));
        }
    }

    private void TransformAlloc(Function func, Alloc_Inst Inst){
        FrameInfo Info = InfoMap.get(func);
        BasicBlock curBB = Inst.BB_Scope;

        StorePreservedRegisters(curBB, Inst);

        Inst.append(new Move_Inst(curBB, Inst.dest, rax));

        ReloadPreservedRegisters(curBB, Inst);
    }

    private void TransformCall(Function func, Instruction inst){
        if(inst instanceof Alloc_Inst){
            TransformAlloc(func, (Alloc_Inst)inst);
            return;
        }
        FrameInfo Info = InfoMap.get(func);
        BasicBlock curBB = inst.BB_Scope;

        if(inst instanceof Call_Inst){
            Call_Inst Inst = (Call_Inst) inst;

            //StoreAllRegisters(curBB, Inst);//brute force
            StorePreservedRegisters(curBB, Inst);


            int paramnum = Inst.ArgLocs.size();

            for(int i = paramnum - 1; i >= 0; i--){
                DataSrc source = Inst.ArgLocs.get(i);
                if(i > 5){
                    if(source instanceof FrameSlice){
                        Inst.prepend(new Load_Inst(curBB, rax, rbp, Info.FrameSliceOffset.get(source),8));
                        Inst.prepend(new Push(curBB, rax));
                    }else{
                        Inst.prepend(new Push(curBB, source));
                    }
                }else{
                    PhysicalReg reg = FuncParamRegs.get(i);
                    if(source instanceof FrameSlice)
                        Inst.prepend(new Load_Inst(curBB, reg, rbp, Info.FrameSliceOffset.get(source),8));
                    else{
                        Inst.prepend(new Move_Inst(curBB, reg, source));
                    }
                }
            }
/*

            Map<PhysicalReg,PhysicalReg> trans_Map = new HashMap<>();
            Map<PhysicalReg, Integer> OrderMap = new HashMap<>();
            List<PhysicalReg> Sources = new LinkedList<>();
            int id = 0;

            for(int i = paramnum - 1; i >= 0; i--){
                DataSrc source = Inst.ArgLocs.get(i);
                if(i > 5){
                    if(source instanceof FrameSlice){
                        Inst.prepend(new Load_Inst(curBB, rax, rbp, Info.FrameSliceOffset.get(source),8));
                        Inst.prepend(new Push(curBB, rax));
                    }else{
                        Inst.prepend(new Push(curBB, source));
                    }
                }else{
                    PhysicalReg reg = FuncParamRegs.get(i);
                    if(source instanceof FrameSlice)
                        Inst.prepend(new Load_Inst(curBB, reg, rbp, Info.FrameSliceOffset.get(source),8));
                    else{
                        if(source instanceof PhysicalReg && FuncParamRegs.contains(source)){
                            PhysicalReg transReg = IntermediateRegs.get(id++);
                            trans_Map.put((PhysicalReg)source, transReg);
                            OrderMap.put((PhysicalReg)source, i);
                            Sources.add((PhysicalReg)source);
                            Inst.prepend(new Move_Inst(curBB, transReg, source));
                            continue;
                        }
                        Inst.prepend(new Move_Inst(curBB, reg, source));
                    }
                }
            }

            for(int i = 0;i < Sources.size();i++){
                PhysicalReg Key = Sources.get(i);
                int order = OrderMap.get(Key);
                PhysicalReg Source = trans_Map.get(Key);
                PhysicalReg dest = FuncParamRegs.get(order);
                Inst.prepend(new Move_Inst(curBB, dest, Source));
            }
*/

            //ReloadAllRegisters(curBB, Inst);
            ReloadPreservedRegisters(curBB, Inst);

            //Pop stack
            for(int i = 0;i < paramnum - 6;i++){
                inst.next.append(new Pop(curBB, r15));
            }
        }
    }

    private void EliminateUselessCode(Instruction inst){
        if(inst instanceof Move_Inst)
            if(((Move_Inst) inst).dest == ((Move_Inst) inst).Source)
                inst.remove();
    }

    public void Transform(){
        for(Function func: xirRoot.Functions.values()){
            curFunc = func;
            processFunctionFrame(func);
            Prologue(func);
            Epilogue(func);
            for(BasicBlock X: func.PreOrder){
                for(Instruction inst = X.Entry; inst != null; inst = inst.next){
                    EliminateUselessCode(inst);
                    TransformCall(func, inst);
                    TransformSlice(func, inst);
                }
            }
        }
    }
}
