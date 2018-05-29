package XYXCompiler.BackEnd;

import XYXCompiler.FrontEnd.ASTNode.Statement.Return;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.UnaryOp_Inst;
import XYXCompiler.XIR.Instruction.Control.CJump_Inst;
import XYXCompiler.XIR.Instruction.Control.Jump_Inst;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Functional.Return_Inst;
import XYXCompiler.XIR.Instruction.Memory.*;

public interface XIRVisitor {
    void visit(XIRRoot node);
    void visit(Function node);
    void visit(BasicBlock node);
    void visit(BinaryOp_Inst node);
    void visit(RelationOp_Inst node);
    void visit(UnaryOp_Inst node);
    void visit(CJump_Inst node);
    void visit(Jump_Inst node);
    void visit(Call_Inst node);
    void visit(Return_Inst node);
    void visit(Alloc_Inst node);
    void visit(Load_Inst node);
    void visit(Move_Inst node);
    void visit(Store_Inst node);
    void visit(Push node);
}
