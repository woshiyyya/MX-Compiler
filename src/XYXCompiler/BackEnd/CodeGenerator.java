package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.Tools.IRViewer;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;

import java.io.IOException;

public class CodeGenerator {

    XIRRoot root;
    String LLfilename = null;
    public CodeGenerator(XIRRoot root) {
        this.root = root;
    }

    public void setLLfilename(String LLfilename) {
        this.LLfilename = LLfilename;
    }

    public void generate()throws IOException {
        for(Function func: root.Functions.values())
            func.Update_Info();

        LivenessAnalyser analyser = new LivenessAnalyser();
        analyser.Initialize(root);
        IRViewer viewer = new IRViewer(root);
        //viewer.View();
        viewer.LLView(LLfilename);
        //OnTheFly_Allocator Allocator = new OnTheFly_Allocator(root, new X86Registers());
        //Allocator.Allocate();
    }
}
