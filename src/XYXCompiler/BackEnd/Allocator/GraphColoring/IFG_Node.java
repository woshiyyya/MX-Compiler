package XYXCompiler.BackEnd.Allocator.GraphColoring;

import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.*;

public class IFG_Node {
    public VirtualReg VReg = null;
    public DataSrc PReg = null;
    public int PRegId;
    public int degree = 0;
    public boolean deleted = false;
    public Set<IFG_Node> MoveRhs = new HashSet<>();
    public Set<IFG_Node> NeighborNodes = new HashSet<>();
    public Set<VirtualReg> NeighborVregs = new HashSet<>();

    public IFG_Node(VirtualReg VReg) {
        this.VReg = VReg;
    }

    public void Add_Neighbor(IFG_Node node){
        NeighborNodes.add(node);
        NeighborVregs.add(node.VReg);
    }

    public void setDegree(){
        degree = NeighborNodes.size();
    }
}
