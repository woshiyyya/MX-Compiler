package XYXCompiler.XIR.Instruction.Functional;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.LinkedList;
import java.util.List;

public class Call_Inst extends Functional {
    public Function function;
    public Register dest;
    public List<DataSrc> ArgLocs = new LinkedList<>();

    public Call_Inst(BasicBlock BB_Scope, Function function, Register dest) {
        super(BB_Scope);
        this.function = function;
        this.dest = dest;
    }

    public void Add_Args(DataSrc arg){
        ArgLocs.add(arg);
    }

    @Override
    public void Update_UseDef() {
        if(dest instanceof VirtualReg)
            this.Def = (VirtualReg) dest;
        for(DataSrc X: ArgLocs)
            if(X instanceof VirtualReg)
                this.Used.add((VirtualReg) X);

        this.ifupdated = true;
    }
}
