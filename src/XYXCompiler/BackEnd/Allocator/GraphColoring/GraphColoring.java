package XYXCompiler.BackEnd.Allocator.GraphColoring;

import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Instruction.Memory.Move_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.*;

public class GraphColoring {
    private XIRRoot xirRoot;
    private int maxColor = 9;
    private Function curFunc;
    private Map<VirtualReg, Node> NodeMap = new HashMap<>();
    private Set<VirtualReg> Nodes = new HashSet<>();
    private Stack<VirtualReg> WaitingList = new Stack<>();
    private Set<PhysicalReg> UsedColors = new HashSet<>();

    public Node GetNode(VirtualReg Vreg){
        Node node = NodeMap.get(Vreg);
        if(node == null){
            node = new Node();
            NodeMap.put(Vreg, node);
        }
        return node;
    }

    private void Link(VirtualReg V1, VirtualReg V2){
        Node N1 = GetNode(V1);
        Node N2 = GetNode(V2);
        N1.Neighbour.add(V2);
        N2.Neighbour.add(V1);
    }

    private void Construct_InterferenceGraph(){
        for(VirtualReg arg: curFunc.ArgRegs){
            GetNode(arg);
        }

        for(BasicBlock BB: curFunc.ReverseOrder){
            for(Instruction inst = BB.Entry; inst!= null; inst = inst.next){
                if(inst.Def != null){
                    Node defNode = GetNode(inst.Def);
                    if(inst instanceof Move_Inst){
                        DataSrc RHS = ((Move_Inst) inst).Source;
                        if(RHS instanceof VirtualReg){
                            defNode.Moves.add((VirtualReg) RHS);
                            GetNode((VirtualReg) RHS).Moves.add(inst.Def);
                        }

                    }
                }

            }
        }
    }
}
