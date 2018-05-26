package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.XIR.CFG.XIRRoot;

public class CodeGenerator {

    XIRRoot root;

    public CodeGenerator(XIRRoot root) {
        this.root = root;
    }

    public void generate(){
        LivenessAnalyser analyser = new LivenessAnalyser();
        analyser.Initialize(root);
        OnTheFly_Allocator Allocator = new OnTheFly_Allocator(root, new X86Registers());
        Allocator.Allocate();
    }
}
