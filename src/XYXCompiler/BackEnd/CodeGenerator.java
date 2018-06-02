package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.Allocator.GraphColoring.GraphColoringAllocator;
import XYXCompiler.BackEnd.Allocator.OnTheFly_Allocator;
import XYXCompiler.BackEnd.X86_64.X86Registers;
import XYXCompiler.Tools.IRViewer;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;

public class CodeGenerator {

    private XIRRoot xirRoot;
    private String LLfilename = null;
    public CodeGenerator(XIRRoot root) {
        this.xirRoot = root;
    }

    public void setLLfilename(String LLfilename) {
        this.LLfilename = LLfilename;
    }

    public void generate(boolean mod){
        for(Function func: xirRoot.Functions.values())
            func.Update_Info();

        X86Registers x86Registers = new X86Registers();
        x86Registers.Initialize();

        LivenessAnalyser analyser = new LivenessAnalyser();
        analyser.Initialize(xirRoot);

        IRViewer viewer = new IRViewer(xirRoot);
        if(mod)
            viewer.View();
        //else
        //  viewer.LLView(LLfilename);

        //OnTheFly_Allocator Allocator = new OnTheFly_Allocator(xirRoot, x86Registers);
        //Allocator.Allocate();

        GraphColoringAllocator Allocator = new GraphColoringAllocator(xirRoot);
        Allocator.Allocate();

        FrameTranslator Translator = new FrameTranslator(xirRoot);
        Translator.Transform();


        if(!mod){
            //X86_Printer printer = new X86_Printer();
            ASMPrinter printer = new ASMPrinter();
            printer.visit(xirRoot);
        }
    }
}
