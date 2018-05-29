package XYXCompiler.XIR.CFG;

import XYXCompiler.XIR.Operand.Register.GlobalVar;
import XYXCompiler.XIR.Operand.Static.StringLiteral;

import java.util.*;

public class XIRRoot {
    public Map<String, Function> Functions = new HashMap<>();
    public List<GlobalVar> StaticSpace = new LinkedList<>();
    public List<StringLiteral> LiteralDataPool = new LinkedList<>();
}
