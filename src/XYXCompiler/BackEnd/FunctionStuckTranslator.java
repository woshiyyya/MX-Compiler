package XYXCompiler.BackEnd;

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
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.Immediate;
import javafx.geometry.Pos;
import org.antlr.v4.runtime.BailErrorStrategy;

import java.util.HashMap;
import java.util.Map;

import static XYXCompiler.BackEnd.X86_64.X86Registers.*;

public class FunctionStuckTranslator {
    public XIRRoot xirRoot;
    public Map<Function, FrameInfo> InfoMap = new HashMap<>();
    X86Registers x86Registers = new X86Registers();

    /*
        # MyFunction2(10, 5, 2);
        Notice: Everything is 8 Byte ,push as well

        <---->             (caller-saved [optional])    rpb + Pos_CallerSaved
        |    | [rbp + ?]   (kth argument)               rbp + Pos_LastParam
        |    | [rbp + 10H]  (7th argument)
        | RA | [rbp + 08H]  (return address)             rpb + Pos_RetAddr
        | FP | [rbp]       (old ebp value)              rpb
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

    public FunctionStuckTranslator(XIRRoot xirRoot) {
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
        Info.LocalVariableStart     = Info.CalleeSavedStart     - 8 * func.usedCalleeSavedregs.size();
        Info.CallerSavedStart       = Info.LocalVariableStart   - 8 * func.frameSlice.size();
        Info.Totalsize              = (Info.CallerSavedStart    - 8 * func.usedCallerSavedRegs.size())*(-1);

        Info.updateLocalVariablePosition();
        Info.updateParameterPosition();
    }

    private void Prologue(Function func){
        FrameInfo Info = InfoMap.get(func);
        BasicBlock curBB = func.StartBB;
        Instruction Entry = curBB.Entry;

        Entry.prepend(new BinaryOp_Inst(curBB, rsp, rsp, new Immediate(Info.Totalsize),BinaryOp_Inst.binaryop.Sub));

        StoreParametersRegs(Info,curBB,Entry);

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
    private void StoreParametersRegs(FrameInfo Info, BasicBlock curBB, Instruction Inst){
        Function func = Info.func;
        for(int i = 0;i < func.ArgRegs.size();i++){
            Inst.prepend(new Store_Inst(curBB, FuncParamRegs.get(i), 8, rbp, Info.FirstParam - 8*i));
        }
    }

    private void StoreCallerSavedRegs(FrameInfo Info, BasicBlock curBB, Instruction inst){
        for(int i = 0; i < Info.UsedCallerSavedReg.size(); i++){
            inst.prepend(new Store_Inst(curBB, Info.UsedCallerSavedReg.get(i),
                    8,rbp,Info.CallerSavedStart + i*8));
        }
    }

    private void ReloadCallerSavedRegs(FrameInfo Info, BasicBlock curBB, Instruction inst){
        for(int i = 0; i < Info.UsedCallerSavedReg.size(); i++){
            inst.append(new Load_Inst(curBB, Info.UsedCallerSavedReg.get(i),
                    rbp,Info.CallerSavedStart + i*8,8));
        }
    }

    private void StoreCalleeSavedRegs(Function func, BasicBlock curBB, Instruction Entry){
        FrameInfo Info = InfoMap.get(func);
        for(int i = 0; i < func.usedCalleeSavedregs.size(); i++){
            Entry.prepend(new Store_Inst(curBB, func.usedCalleeSavedregs.get(i),8,rbp,Info.CalleeSavedStart + 8*i));
        }
    }

    private void ReloadCalleeSavedRegs(Function func, BasicBlock curBB, Instruction Exit){
        FrameInfo Info = InfoMap.get(func);
        for(int i = 0; i < func.usedCalleeSavedregs.size(); i++){
            Exit.prepend(new Load_Inst(curBB, func.usedCalleeSavedregs.get(i),rbp,Info.CalleeSavedStart + 8*i,8));
        }
    }
    private void TransformCall(Function func, Instruction inst){
        FrameInfo Info = InfoMap.get(func);
        BasicBlock curBB = inst.BB_Scope;

        if(inst instanceof Call_Inst){
            Call_Inst Inst = (Call_Inst) inst;


            //StoreCallerSavedRegs(Info, curBB ,Inst);

            int paramnum = Inst.ArgLocs.size();
            for(int i = paramnum - 1; i >= 0; i--){
                DataSrc source = Inst.ArgLocs.get(i);
                if(i > 5){
                    if(source instanceof FrameSlice){
                        Inst.prepend(new Move_Inst(curBB, rax, source));
                        Inst.prepend(new Push(curBB, rax));
                    }else{
                        Inst.prepend(new Push(curBB, source));
                    }
                }else{
                    PhysicalReg reg = FuncParamRegs.get(i);
                    if(source instanceof FrameSlice)
                        Inst.prepend(new Load_Inst(curBB, reg, rbp, Info.FrameSliceOffset.get(source),8));
                    else
                        Inst.prepend(new Move_Inst(curBB, reg, source));
                }
            }


            //ReloadCallerSavedRegs(Info, curBB, Inst);

            //Pop stack
            for(int i = paramnum;i >= 6;i--){
                inst.append(new Pop(curBB, rax));
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
            processFunctionFrame(func);
            Prologue(func);
            Epilogue(func);
            for(BasicBlock X: func.PreOrder){
                for(Instruction inst = X.Entry;inst != null;inst = inst.next){
                    EliminateUselessCode(inst);
                    TransformCall(func, inst);
                    TransformSlice(func, inst);
                }
            }
        }

    }
}
