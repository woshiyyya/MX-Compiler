package XYXCompiler.BackEnd.Allocator.GraphColoring;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.FrontEnd.ASTNode.Node;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Instruction.Memory.Load_Inst;
import XYXCompiler.XIR.Instruction.Memory.Store_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.*;

import static XYXCompiler.BackEnd.X86_64.X86Registers.GeneralRegs;
import static XYXCompiler.BackEnd.X86_64.X86Registers.rax;
import static XYXCompiler.BackEnd.X86_64.X86Registers.rdi;

public class GraphColoringAllocator {
    private XIRRoot xirRoot;
    private Function curFunc;
    private Stack<IFG_Node> Stack = new Stack<>();
    private Set<VirtualReg> WaitList = new HashSet<>();
    private Map<VirtualReg, IFG_Node> NodeMap = new HashMap<>();

    private int MaxPRegs = 9;
    private Set<PhysicalReg> UsedPregs = new HashSet<>();


    /*
        Live_span:
                    R1:               |------------------------|
                                     Def                     Last_usage
                    R2:    |------------------------|
                          Def         ^            Last_usage
                                      ^
                                 Current Inst

            R1 & R2 Interference if R2 in Inst.Liveout
    */

    public GraphColoringAllocator(XIRRoot xirRoot) {
        this.xirRoot = xirRoot;
    }

    private IFG_Node Add_Node(VirtualReg Vreg){
        IFG_Node node = NodeMap.get(Vreg);
        if(node == null){
            node = new IFG_Node(Vreg);
            NodeMap.put(Vreg, node);
        }
        return node;
    }

    private void Construct_InterferenceGraph(Function func){
        NodeMap.clear();
        for(VirtualReg args: func.ArgRegs)
            Add_Node(args);

        for(BasicBlock BB: func.PostOrder){
            for(Instruction inst = BB.Entry; inst != null; inst = inst.next){
                if(inst.Def == null) continue;
                IFG_Node CurNode = Add_Node(inst.Def);
                for(VirtualReg VReg: inst.Live_out){
                    IFG_Node NewNode = Add_Node(VReg);
                    CurNode.Add_Neighbor(NewNode);
                    NewNode.Add_Neighbor(CurNode);
                }
            }
        }
    }

    private void Delete(IFG_Node cur_node){
        NodeMap.remove(cur_node.VReg);
        WaitList.remove(cur_node.VReg);
        Stack.push(cur_node);
        cur_node.deleted = true;

        for(VirtualReg X: cur_node.NeighborVregs){
            IFG_Node node = NodeMap.get(X);
            if(node != null){
                node.degree--;
                if(node.degree < MaxPRegs)
                    WaitList.add(node.VReg);
            }
        }
    }

    private void Initialize(Function function){
        Construct_InterferenceGraph(function);
        //initialize waiting list
        for(IFG_Node node: NodeMap.values())
            if(node.degree < MaxPRegs)
                WaitList.add(node.VReg);
    }

    private void Coloring(Function func){
        Initialize(func);
        while(!NodeMap.isEmpty()){
            while(!WaitList.isEmpty()){
                IFG_Node node = NodeMap.get(WaitList.iterator().next());
                Delete(node);
            }
            if(!NodeMap.isEmpty()){
                IFG_Node node = NodeMap.get(NodeMap.keySet().iterator().next());
                Delete(node);
            }
        }

        while(!Stack.isEmpty()){
            //Re-put the node
            IFG_Node node = Stack.pop();
            NodeMap.put(node.VReg, node);
            node.deleted = false;

            //Collect Regs that already been used in neighbors
            for(IFG_Node X: node.NeighborNodes){
                if(!X.deleted && X.PReg instanceof PhysicalReg)
                    UsedPregs.add((PhysicalReg) X.PReg);
            }

            //Try to allocate available register
            for(int i = 0;i < MaxPRegs;i++){
                PhysicalReg reg = GeneralRegs.get(i);
                if(!UsedPregs.contains(reg)) {
                    node.PReg = reg;
                    break;
                }
            }

            //degree > k, Spilling!
            if(node.PReg == null){
                node.PReg = new FrameSlice(curFunc, node.VReg.Name);
                curFunc.frameSlice.add((FrameSlice) node.PReg);
            }

            UsedPregs.clear();
        }
    }

    private void  Handle_FunctionCall(Call_Inst call){
        for(int i = 0;i < call.ArgLocs.size(); i++){
            DataSrc dataSrc = call.ArgLocs.get(i);
            IFG_Node node = NodeMap.get(dataSrc);
            if(node != null)
                call.ArgLocs.set(i, node.PReg);
        }
    }

    private void Transform_Instructions(Function function){
        //special use!
        function.usedPregs.add(rax);
        function.usedPregs.add(rdi);

        for (BasicBlock curBB: function.PostOrder){
            for(Instruction inst = curBB.Entry; inst != null; inst = inst.next){
                if(inst instanceof Call_Inst){
                    Handle_FunctionCall((Call_Inst) inst);
                }else{
                    boolean UseTransfer = false;

                    if(inst.Def != null){
                        DataSrc dataSrc = NodeMap.get(inst.Def).PReg;
                        if(dataSrc instanceof PhysicalReg){
                            curFunc.usedPregs.add((PhysicalReg) dataSrc);
                            inst.Reset_DestRegs((PhysicalReg) dataSrc);
                        }else if(dataSrc instanceof FrameSlice){
                            inst.Reset_DestRegs(rax);
                            inst.append(new Store_Inst(curBB, dataSrc,8, rax,0));
                        }
                    }

                    for(VirtualReg Vreg: inst.Used){
                        DataSrc dataSrc = NodeMap.get(Vreg).PReg;
                        if(dataSrc instanceof PhysicalReg){
                            curFunc.usedPregs.add((PhysicalReg) dataSrc);
                            inst.Reset_OperandRegs(Vreg, (PhysicalReg) dataSrc);
                        }else if(dataSrc instanceof FrameSlice){
                            inst.prepend(new Load_Inst(curBB, UseTransfer? rdi : rax, dataSrc,0,8));
                            UseTransfer = true;
                        }
                    }
                }
            }
        }
    }

    private void Logging_Parameters_Info(){
        for(VirtualReg arg: curFunc.ArgRegs){
            curFunc.ArgSrcs.add(NodeMap.get(arg).PReg);
        }
    }

    public void Allocate(){
        for(Function func: xirRoot.Functions.values()){
            curFunc = func;
            Coloring(func);
            Logging_Parameters_Info();
            Transform_Instructions(func);
        }
    }

}
