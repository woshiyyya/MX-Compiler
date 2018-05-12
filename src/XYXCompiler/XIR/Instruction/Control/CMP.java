package XYXCompiler.XIR.Instruction.Control;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.Memory.DataSrc;

import javax.xml.crypto.Data;

public class CMP extends Control {
    public DataSrc L_operand;
    public DataSrc R_operand;

    public CMP(BasicBlock BB_Scope) {
        super(BB_Scope);
    }

    public CMP(BasicBlock BB_Scope, DataSrc l_operand, DataSrc r_operand) {
        super(BB_Scope);
        L_operand = l_operand;
        R_operand = r_operand;
    }
}
