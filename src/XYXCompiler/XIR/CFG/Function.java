package XYXCompiler.XIR.CFG;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.FrontEnd.ASTNode.Type.Func_Type;
import XYXCompiler.XIR.Instruction.Arithmatic.Arithmatic;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import javafx.geometry.Pos;

import java.util.*;

public class Function {
    public String name;
    public List<VirtualReg> ArgRegs = new LinkedList<>();
    public List<DataSrc> ArgSrcs = new LinkedList<>(); //transformed after allocation
    public List<BasicBlock> RetBlks = new LinkedList<>();
    public boolean isBuiltin = false;
    public int retsize = 0;

    //for CFG
    public Func_Type func_info;
    public Map<String, VirtualReg> VirtualRegMap = new HashMap<>();
    public BasicBlock StartBB;
    public BasicBlock EndBB = null;
    public boolean inClass = false;

    //for Liveness Analysis
    public List<BasicBlock> PreOrder = new LinkedList<>();
    public List<BasicBlock> PostOrder = new LinkedList<>();
    private Set<BasicBlock> visit = new HashSet<>();
    public Set<BasicBlock> ReverseOrder = new HashSet<>();

    //for StackFrame Allocator
    public Map<VirtualReg, FrameSlice> ArgSliceMap = new HashMap<>(); // Help params find their location in Frame
    public List<FrameSlice> frameSlice = new ArrayList<>();
    public Set<PhysicalReg> usedPregs = new HashSet<>();
    //public List<PhysicalReg> usedCallerSavedRegs = new LinkedList<>();
    //public List<PhysicalReg> usedCalleeSavedregs = new LinkedList<>();

    public Function() {
        StartBB = new BasicBlock(this,"Start_BB");
    }

    public void Initialize_FrameInfo(){
        // Warning: without consider param > 6
        for(int i = 0; i < ArgRegs.size(); i++){
            FrameSlice slice = new FrameSlice(this, "param" + i);
            frameSlice.add(slice);
            ArgSliceMap.put(ArgRegs.get(i), slice);
        }
    }

    private void Construct_PreOrder(BasicBlock blk){
        if(visit.contains(blk)) return;
        visit.add(blk);
        PreOrder.add(blk);
        for(BasicBlock X: blk.successor)
            Construct_PreOrder(X);
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

    // delete unreachable blocks
    public void Update_Info(){
        Construct_PreOrder(StartBB);
        visit.clear();
        Construct_PostOrder(StartBB);
        visit.clear();
        Construct_ReverseOrder(EndBB);
        visit.clear();
        for(int i = 0; i < PostOrder.size(); i++)
            if(!ReverseOrder.contains(PostOrder.get(i)))
                PostOrder.remove(PostOrder.get(i));
    }
}
