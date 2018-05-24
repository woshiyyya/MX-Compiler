package XYXCompiler.XIR.CFG;

import XYXCompiler.FrontEnd.ASTNode.Type.Func_Type;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import javafx.geometry.Pos;

import java.util.*;

public class Function {
    public String name;
    public List<Register> ArgRegs = new LinkedList<>();
    public List<BasicBlock> RetBlks = new LinkedList<>();
    public Register ReturnReg;
    public int retsize;

    //for CFG
    public Func_Type func_info;
    public Map<String, VirtualReg> VirtualRegMap = new HashMap<>();
    public BasicBlock StartBB;
    public BasicBlock EndBB = null;

    //for Liveness Analysis
    public List<BasicBlock> PostOrder = new LinkedList<>();
    private Set<BasicBlock> visit = new HashSet<>();
    public Set<BasicBlock> ReverseOrder = new HashSet<>();

    public Function() {
        StartBB = new BasicBlock(this,"Start BB");
    }

    private void Construct_PostOrder(BasicBlock blk){
        if(visit.contains(blk)) return;
        visit.add(blk);
        for(BasicBlock X: blk.successor)
            Construct_PostOrder(X);
        PostOrder.add(blk);
    }

    private void Construct_ReverseOrder(BasicBlock blk){
        if(visit.contains(blk)) return;
        visit.add(blk);
        for(BasicBlock X: blk.predcessor)
            Construct_ReverseOrder(X);
        ReverseOrder.add(blk);
    }

    public void Update_Info(){
        Construct_PostOrder(StartBB);
        Construct_ReverseOrder(EndBB);
        for(int i = 0; i < PostOrder.size(); i++)
            if(!ReverseOrder.contains(PostOrder.get(i)))
                PostOrder.remove(PostOrder.get(i));
    }
}
