package XYXCompiler.BackEnd.Allocator.GraphColoring;

import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IFG_Node {
    public VirtualReg VReg = null;
    public DataSrc PReg = null;
    public int PRegId;
    public int degree = 0;
    public boolean deleted = false;
    public List<IFG_Node> NeighborNodes = new LinkedList<>();
    public List<VirtualReg> NeighborVregs = new LinkedList<>();

    public IFG_Node(VirtualReg VReg) {
        this.VReg = VReg;
    }

    public void Add_Neighbor(IFG_Node node){
        NeighborNodes.add(node);
        NeighborVregs.add(node.VReg);
        degree++;
    }
}
