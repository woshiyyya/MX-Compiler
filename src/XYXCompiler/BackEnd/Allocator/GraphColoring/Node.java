package XYXCompiler.BackEnd.Allocator.GraphColoring;

import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.HashSet;
import java.util.Set;

public class Node {
    Set<VirtualReg> Neighbour = new HashSet<>();
    int degree;
    boolean deleted = false;
    Register Preg;
    Set<VirtualReg> Moves = new HashSet<>();
}
