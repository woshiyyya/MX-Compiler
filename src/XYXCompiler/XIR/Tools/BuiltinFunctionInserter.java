package XYXCompiler.XIR.Tools;

import XYXCompiler.FrontEnd.Builder.XIRBuilder;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Operand.Register.VirtualReg;

import java.util.HashMap;
import java.util.Map;

public class BuiltinFunctionInserter {
    private XIRRoot xirRoot;
    private XIRBuilder builder;

    public BuiltinFunctionInserter(XIRRoot xirRoot, XIRBuilder builder) {
        this.xirRoot = xirRoot;
        this.builder = builder;
    }

    private Function CreateFunc(String OriginalName, String AssemblyName){
        Function func   = new Function();
        func.name       = AssemblyName;
        func.isBuiltin  = true;
        builder.FuncMap.put(OriginalName, func);
        return func;
    }

    private void addArgs(Function func, String Argname){
        func.ArgRegs.add(new VirtualReg(Argname));
    }

    public void Insert(){
        Function println = CreateFunc("println","println");
        addArgs(println, "strout");

        Function getString = CreateFunc("getString","getString");

        Function getInt = CreateFunc("getInt","getInt");

        Function parseInt = CreateFunc("parseInt", "string.parseInt");
        addArgs(parseInt, "this");

        Function strlen = CreateFunc("length","string.length");
        addArgs(strlen, "this");

        Function strord = CreateFunc("ord","string.ord");
        addArgs(strord, "this");
        addArgs(strord, "pos");

        Function substring = CreateFunc("substring","string.substring");
        addArgs(substring, "this");
        addArgs(substring, "left");
        addArgs(substring, "right");

        Function toString = CreateFunc("toString","toString");
        addArgs(toString, "i");

        Function arraySize = CreateFunc("size","array.size");
        addArgs(arraySize, "baseaddr");
    }

    public boolean ifbuiltin(String name){
        return builder.FuncMap.containsKey(name);
    }
}
