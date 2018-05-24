package XYXCompiler.BackEnd;

import XYXCompiler.XIR.CFG.XIRRoot;

public class CodeGenerator {
    LivenessAnalyser analyser = new LivenessAnalyser();
    XIRRoot root;

    public CodeGenerator(XIRRoot root) {
        this.root = root;
    }

    public void generate(){
        analyser.Initialize(root);
    }
}
