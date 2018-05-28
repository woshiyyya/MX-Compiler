package XYXCompiler.Tools;

import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.ASTNode.Type.Void_Type;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.io.*;

public class IRViewer {
    public XIRRoot root;

    public IRViewer(XIRRoot root) {
        this.root = root;
    }

    public void View(){
        for(Function func: root.Functions.values()){
            for(BasicBlock BB: func.PreOrder){
                System.out.println(BB.label);
                for(Instruction inst = BB.Entry; inst != null; inst = inst.next){
                    inst.Print();
                }
            }
        }
    }

    public void LLView(String name)throws IOException {
        String path = "C:\\Users\\lenovo\\Desktop\\MX-Compiler\\XYXCompiler\\src\\XYXCompiler\\testLL\\";
        PrintStream OUT = new PrintStream(path + name);
        System.setOut(OUT);

        for(Function func: root.Functions.values()){
            String funchead = (func.func_info.returntype instanceof Void_Type) ? "void " : "func ";
            funchead += func.name + " ";
            for(VirtualReg X: func.ArgRegs)
                funchead += X.getString() + " ";
            System.out.println(funchead + "{");

            for(BasicBlock BB: func.PreOrder){
                System.out.println(BB.getLabel() + ":");
                for(Instruction inst = BB.Entry; inst != null; inst = inst.next){
                    inst.LLPrint();
                }
                System.out.println();
            }
            System.out.println("}");
        }
    }
}
