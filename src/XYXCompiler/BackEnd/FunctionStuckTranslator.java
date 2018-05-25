package XYXCompiler.BackEnd;

import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import javafx.geometry.Pos;

public class FunctionStuckTranslator {
    /*
        # MyFunction2(10, 5, 2);
        Notice: Everything is 8 Byte ,push as well

        |    | [rbp + ?]   (kth argument)               rbp + Pos_LastParam
        |    | [rbp + 10]  (7th argument)
        <---->             (caller-saved [optional])    rpb + Pos_CallerSaved
        | RA | [rbp + 08]  (return address)             rpb + Pos_RetAddr
        | FP | [rbp]       (old ebp value)              rpb
        |    | [rbp - 08]  (1st argument #rdi)          rpb - Pos_FirstParam
        |    | [rbp - 10]  (2nd argument #rsi)
        |    | [rbp - 18]  (3rd argument #rdx)
        |    | [rbp - 20]  (4th argument #rcx)
        |    | [rbp - 28]  (5th argument #r8)
        |    | [rbp - 30]  (6th argument #r9)
        |    | [rbp - 38]  (Local Vars)                 rbp - Pos_LocalVar
        :    :
        :    :
        |    | [rbp - ?]  (Local Vars)                  rbp - Pos_CalleeSaved
        |    | [rbp - X]  (esp - the current stack pointer. The use of push / pop is valid now)
    */

        public void processFunctionFrame(Function func){
            updatePreservedRegs(func);
            int Argnum = func.ArgRegs.size();
            int ReducedArgnum = (Argnum > 6) ? 6 : Argnum;
            func.Pos_RetAddr        = 8;
            func.Pos_CallerSaved    = 8                     + 8 * func.usedCallerSavedRegs.size();
            func.Pos_LastParam      = func.Pos_CallerSaved  + 8 * (Argnum - ReducedArgnum);
            func.Pos_FirstParam     = 8;
            func.Pos_LocalVar       = 8                     + 8 * (ReducedArgnum);
            func.Pos_CalleeSaved    = func.Pos_LocalVar     + 8 * func.frameSlice.size();
            func.totalFrameSize     = func.Pos_CalleeSaved  + 8 * func.usedCalleeSavedregs.size();
        }

        private void updatePreservedRegs(Function func){
            for(PhysicalReg X: func.usedPregs){
                if(X.CalleeSave)
                    func.usedCalleeSavedregs.add(X);
                else
                    func.usedCallerSavedRegs.add(X);
            }
        }

}
