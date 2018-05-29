package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.Tools.IRViewer;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;

import java.io.IOException;
import java.io.PrintStream;

public class CodeGenerator {

    XIRRoot xirRoot;
    String LLfilename = null;
    public CodeGenerator(XIRRoot root) {
        this.xirRoot = root;
    }

    public void setLLfilename(String LLfilename) {
        this.LLfilename = LLfilename;
    }

    public void generate(boolean mod)throws IOException {
        for(Function func: xirRoot.Functions.values())
            func.Update_Info();

        X86Registers x86Registers = new X86Registers();
        x86Registers.Initialize();
        LivenessAnalyser analyser = new LivenessAnalyser();
        analyser.Initialize(xirRoot);
        IRViewer viewer = new IRViewer(xirRoot);
        //if(mod)
        //    viewer.View();
        //else
        //    viewer.LLView(LLfilename);
        OnTheFly_Allocator Allocator = new OnTheFly_Allocator(xirRoot, x86Registers);
        Allocator.Allocate();
        FunctionStuckTranslator Translator = new FunctionStuckTranslator(xirRoot);
        Translator.Transform();
        //Translator.Transform();
        //viewer.View();
        X86Printer printer = new X86Printer(null);
        printer.visit(xirRoot);
    }
}
