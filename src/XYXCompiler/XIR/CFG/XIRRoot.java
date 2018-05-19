package XYXCompiler.XIR.CFG;

import XYXCompiler.XIR.Operand.Static.Literal;
import XYXCompiler.XIR.Operand.Static.StaticData;

import java.util.*;

public class XIRRoot {
    public Map<String, Function> Functions = new HashMap<>();
    public List<StaticData> StaticSpace = new LinkedList<>();
    public Map<String, Literal> LiteralDataPool = new LinkedHashMap<>();
}
