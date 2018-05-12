package XYXCompiler.XIR.CFG;

import XYXCompiler.FrontEnd.ASTNode.Type.Func_Type;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Function {
    public String name;
    public List<Register> ArgRegs = new LinkedList<>();
    public List<BasicBlock> RetBlks = new LinkedList<>();
    public Register ReturnReg;
    public int retsize;

    public Func_Type func_info;
    public Map<String, VirtualReg> VirtualRegMap = new HashMap<>();
    public BasicBlock StartBB;
    public BasicBlock EndBB = null;

    public Function() {
        StartBB = new BasicBlock(this);
    }
}
