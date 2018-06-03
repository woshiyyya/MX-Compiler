package XYXCompiler.BackEnd.Allocator.GraphColoring;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Instruction.Memory.Load_Inst;
import XYXCompiler.XIR.Instruction.Memory.Move_Inst;
import XYXCompiler.XIR.Instruction.Memory.Store_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import javafx.print.PrintColor;

import java.util.*;

import static XYXCompiler.BackEnd.X86_64.X86Registers.*;

public class GraphColoringAllocator {
    private XIRRoot xirRoot;
    private Function curFunc;
    private Stack<IFG_Node> Stack = new Stack<>();
    private Stack<VirtualReg> WaitList = new Stack<>();
    private Map<VirtualReg, IFG_Node> NodeMap = new HashMap<>();
    private Set<VirtualReg> nodes = new HashSet<>();

    private int MaxPRegs = 5;
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

    private void UpdateNodeDegrees(){
        for(IFG_Node node: NodeMap.values())
            node.setDegree();
    }

    private void Construct_InterferenceGraph(Function func){
        NodeMap.clear();
        for(VirtualReg args: func.ArgRegs)
            Add_Node(args);

        for(int i = 0;i < func.ArgRegs.size();i++){
            for(int j = 0;j < func.ArgRegs.size();j++)
                if(i != j)
                    NodeMap.get(func.ArgRegs.get(i)).Add_Neighbor(NodeMap.get(func.ArgRegs.get(j)));
        }

        for(BasicBlock BB: func.PostOrder){
            for(Instruction inst = BB.Entry; inst != null; inst = inst.next){

                if(inst.Def == null) continue;
                IFG_Node CurNode = Add_Node(inst.Def);

                for(VirtualReg VReg: inst.Live_out){
                    IFG_Node NewNode = Add_Node(VReg);
                    if(NewNode != CurNode){
                        CurNode.Add_Neighbor(NewNode);
                        NewNode.Add_Neighbor(CurNode);
                    }
                }
            }
        }
        UpdateNodeDegrees();
    }

    private void Delete(IFG_Node cur_node){
        nodes.remove(cur_node.VReg);
        Stack.push(cur_node);
        cur_node.deleted = true;

        for(VirtualReg X: cur_node.NeighborVregs){
            IFG_Node node = NodeMap.get(X);
            if(node != null && !node.deleted){
                node.degree--;
                if(node.degree < MaxPRegs)
                    WaitList.push(node.VReg);
            }
        }
    }

    private void Initialize(Function function){
        Construct_InterferenceGraph(function);
        nodes.addAll(NodeMap.keySet());
        //initialize waiting list
        for(IFG_Node node: NodeMap.values())
            if(node.degree < MaxPRegs)
                WaitList.push(node.VReg);
    }


    private void Coloring(Function func){
        Initialize(func);
        while(!nodes.isEmpty()){
            while(!WaitList.isEmpty()){
                IFG_Node node = NodeMap.get(WaitList.pop());
                Delete(node);
            }
            if(!nodes.isEmpty()){
                IFG_Node node = NodeMap.get(nodes.iterator().next());
                Delete(node);
            }
        }

        while(!Stack.isEmpty()){
            //Re-put the node
            UsedPregs.clear();
            IFG_Node node = Stack.pop();
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
        //function.usedPregs.add(rax);
        //function.usedPregs.add(rdi);

        for (BasicBlock curBB: function.PostOrder){
            for(Instruction inst = curBB.Entry; inst != null; inst = inst.next){
                if(inst instanceof Call_Inst){
                    Handle_FunctionCall((Call_Inst) inst);
                }else{
                    boolean UseTransfer = false;

                    for(VirtualReg Vreg: inst.Used){
                        DataSrc dataSrc = NodeMap.get(Vreg).PReg;
                        if(dataSrc instanceof PhysicalReg){
                            curFunc.usedPregs.add((PhysicalReg) dataSrc);
                            inst.Reset_OperandRegs(Vreg, (PhysicalReg) dataSrc);
                        }else if(dataSrc instanceof FrameSlice){
                            PhysicalReg tem = UseTransfer? rbx : r15;
                            inst.prepend(new Load_Inst(curBB, tem, dataSrc,0,8));
                            inst.Reset_OperandRegs(Vreg, tem);
                            UseTransfer = true;
                        }
                    }

                    if(inst.Def != null){
                        DataSrc dataSrc = NodeMap.get(inst.Def).PReg;
                        if(dataSrc instanceof PhysicalReg){
                            curFunc.usedPregs.add((PhysicalReg) dataSrc);
                            inst.Reset_DestRegs((PhysicalReg) dataSrc);
                        }else if(dataSrc instanceof FrameSlice){
                            inst.Reset_DestRegs(r15);
                            inst.append(new Store_Inst(curBB, r15,8, dataSrc,0));
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

    private void PrintColorMap(){
        for(IFG_Node node: NodeMap.values()){
            System.err.println(node.VReg.Name + " : " + node.PReg.getString());
        }
    }

    public void Allocate(){
        for(Function func: xirRoot.Functions.values()){
            NodeMap.clear();
            curFunc = func;
            func.Initialize_FrameInfo();
            Coloring(func);
            Logging_Parameters_Info();
            Transform_Instructions(func);
            PrintColorMap();
        }
    }

}
