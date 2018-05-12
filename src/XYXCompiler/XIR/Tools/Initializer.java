package XYXCompiler.XIR.Tools;

import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.Instruction.Memory.Move_Inst;
import XYXCompiler.XIR.Operand.Static.StaticData;
import XYXCompiler.XIR.Operand.Register.Register;

import java.util.HashMap;
import java.util.Map;

public class Initializer {
    //this is a class for logging global variable declarations
    public Map<String, StaticData> StaticData = new HashMap<>();

    public void put(String name, StaticData data){
        StaticData.put(name, data);
    }
}
