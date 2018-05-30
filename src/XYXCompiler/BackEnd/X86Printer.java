package XYXCompiler.BackEnd;

import XYXCompiler.BackEnd.X86_64.FrameSlice;
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
import XYXCompiler.XIR.Instruction.Instruction;
import XYXCompiler.XIR.Instruction.Memory.*;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.GlobalVar;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.Immediate;
import XYXCompiler.XIR.Operand.Static.StringLiteral;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static XYXCompiler.BackEnd.X86_64.X86Registers.rax;

public class X86Printer implements XIRVisitor {

    private Map<BasicBlock, String> BBNameMap = new HashMap<>();

    public X86Printer(PrintStream stream) {
        if(stream != null)
            System.setOut(stream);
    }

    private int BBNum = 0;

    private String getBBLabel(BasicBlock BB){
        String name = BBNameMap.get(BB);
        if(name == null){
            name = BB.label + "." + (BBNum++);
            BBNameMap.put(BB, name);
        }
        return name;
    }

    private void Print_Extern(){
        String extern
                =   "extern puts\nextern getchar\nextern putchar\nextern sprintf\n" +
                    "extern __stack_chk_fail\nextern malloc\nextern printf\n" +
                    "extern strlen\nextern memcpy\nextern scanf\n";
        System.out.println(extern);
    }

    private void Print_Global(XIRRoot node){
        for (GlobalVar X : node.StaticSpace)
            System.out.println("global " + X.name);
        for (Function X : node.Functions.values())
            System.out.println("global " + X.name);
    }

    private void Print_Text(XIRRoot node){
        System.out.println("SECTION .text");
        for (Function X : node.Functions.values())
            visit(X);
    }

    private void Print_StaticData(XIRRoot node){
        System.out.println("SECTION .data    align = 8");
        for (GlobalVar X : node.StaticSpace) {
            System.out.println(X.name + ":\n\t dq 000000000000000AH");
        }

        for(StringLiteral X: node.LiteralDataPool){
            System.out.println(X.label);
            System.out.println("\tdq " + X.length);
            System.out.println("\tdb " + X.toInt());
        }
        System.out.println(
                "intbuffer:\n\tdq 0\n" +
                "format1:\n\tdb\"%lld\",0\n" +
                "format2:\n\tdb\"%s\",0\n\n" +
                "SECTION .bss\nstringbuffer:\n\tresb 256\n");
    }

    private void Print_builtin()throws IOException {
        StringBuffer sb = new StringBuffer("");
        FileReader reader = new FileReader("src/XYXCompiler/Tools/BuiltinFunction.asm");
        BufferedReader br = new BufferedReader(reader);
        String str;
        while((str = br.readLine()) != null) {
            sb.append(str).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
        reader.close();
    }

    @Override
    public void visit(XIRRoot node) {
        Print_Extern();
        Print_Global(node);
        Print_Text(node);
        try {
            Print_builtin();
        }catch (Exception ex){
            System.err.println("Read file error!");
        }
        Print_StaticData(node);
    }

    @Override
    public void visit(Function node) {
        System.out.println(node.name + ":");
        System.out.println("\tpush \trbp");
        System.out.println("\tmov \trbp, rsp");
        for(BasicBlock X: node.PreOrder)
            visit(X);
    }

    @Override
    public void visit(BasicBlock node) {
        System.out.println(getBBLabel(node) + ":");
        for(Instruction inst = node.Entry; inst != null; inst = inst.next){
            inst.accept(this);
        }
    }

    @Override
    public void visit(BinaryOp_Inst node) {
        String asm = "\t";
        if(node.L_operand instanceof Immediate && node.R_operand instanceof Immediate){
            int Lval = ((Immediate) node.L_operand).value;
            int Rval = ((Immediate) node.R_operand).value;
            asm = asm + "mov \t" + visit(node.dest) + ", ";
            switch (node.op){
                case Add: asm = asm + (Lval + Rval); break;
                case Sub: asm = asm + (Lval - Rval); break;
                case Mul: asm = asm + (Lval * Rval); break;
                case Div: asm = asm + (Lval / Rval); break;
                case Mod: asm = asm + (Lval % Rval); break;
                case Lsh: asm = asm + (Lval << Rval); break;
                case Rsh: asm = asm + (Lval >> Rval); break;
                case And: asm = asm + (Lval & Rval);break;
                case Or:  asm = asm + (Lval | Rval);  break;
                case Xor: asm = asm + (Lval ^ Rval); break;
            }
        }else{
            switch (node.op){
                case Add: asm = asm + "add"; break;
                case Sub: asm = asm + "sub"; break;
                case Mul: asm = asm +"imul"; break;
                case Div: asm = asm + "div"; break;
                case Mod: asm = asm + "mod"; break;
                case Lsh: asm = asm + "shl"; break;
                case Rsh: asm = asm + "shr"; break;
                case And: asm = asm + "and";break;
                case Or:  asm = asm + "or";  break;
                case Xor: asm = asm + "xor"; break;
            }
            asm = asm + " \t" + visit(node.L_operand) + ", " + visit(node.R_operand) + "\n";
            asm = asm + "\tmov \t" + visit(node.dest) + ", " + visit(node.L_operand);
        }
        System.out.println(asm);
    }


    @Override
    public void visit(RelationOp_Inst node) {
        // R3 = R1 > R2
        // cmp  R1, R2
        // setg R3
        String asm = "\tcmp \t" + visit(node.L_operand) + ", " + visit(node.R_operand) + "\n";
        switch (node.op){
            case LS: asm = asm + "\tsetl" ; break;
            case LE: asm = asm + "\tsetle"; break;
            case GT: asm = asm + "\tsetg" ; break;
            case GE: asm = asm + "\tsetge"; break;
            case EQ: asm = asm + "\tsete" ; break;
            case NE: asm = asm + "\tsetne"; break;
        }
        asm = asm + " \t" + visit(node.dest);
        System.out.println(asm);
    }

    @Override
    public void visit(UnaryOp_Inst node) {
        // -a
        //  neg a
        //  mov R1 a
        String asm = "\t";
        if(node.op == UnaryOp_Inst.unaryop.NEG)
            asm = asm + "neg \t" + visit(node.operand) + "\n";
        else if(node.op == UnaryOp_Inst.unaryop.NOT)
            asm = asm + "not \t" + visit(node.operand) + "\n";
        else
            System.err.println("unary error");

        asm = asm + "\tmov \t" + visit(node.dest) + ", " + visit(node.operand);
        System.out.println(asm);
    }

    @Override
    public void visit(CJump_Inst node) {
        String asm = "\tcmp \t" + visit(node.L_operand) + ", " + visit(node.R_operand) + "\n";
        switch (node.op){
            case Z: asm = asm + "\tjz \t"  + getBBLabel(node.ifFalse); break;
            case EQ:asm = asm + "\tjne\t"  + getBBLabel(node.ifFalse);  break;
            case NE:asm = asm + "\tje \t"  + getBBLabel(node.ifFalse);  break;
            case LS:asm = asm + "\tjge\t"  + getBBLabel(node.ifFalse);  break;
            case LE:asm = asm + "\tjg \t"  + getBBLabel(node.ifFalse);  break;
            case GE:asm = asm + "\tjl \t"  + getBBLabel(node.ifFalse);  break;
            case GT:asm = asm + "\tjle\t"  + getBBLabel(node.ifFalse);  break;
        }
        System.out.println(asm);
    }

    @Override
    public void visit(Jump_Inst node) {
        System.out.println("\tjmp \t" + getBBLabel(node.target));
    }

    @Override
    public void visit(Call_Inst node) {
        System.out.println("\tcall \t" + node.function.name);
    }

    @Override
    public void visit(Return_Inst node) {
        if(node.retval != rax)
            System.out.println("\tmov \trax, " + visit(node.retval));
        System.out.println("\tret");
    }

    @Override
    public void visit(Alloc_Inst node) {
        //getCallersaved()
        System.out.println("\tmov \trdi, " + visit(node.size));
        System.out.println("\tcall malloc");
        System.out.println("\tmov \t" + visit(node.dest) + ", rax");
        //getCalleeSaved()
    }

    @Override
    public void visit(Load_Inst node) {
        String asm = "\tmov \t";
        asm = asm + visit(node.dest) + ", qword [" + visit(node.addr);
        if(node.offset < 0){
            asm = asm + node.offset + "]";
        }else{
            asm = asm + "+" + node.offset + "]";
        }
        System.out.println(asm);
    }

    @Override
    public void visit(Move_Inst node) {
        if(node.dest != node.Source)
            System.out.println("\tmov \t" + visit(node.dest) + ", " + visit(node.Source));
    }

    @Override
    public void visit(Store_Inst node) {

        String asm = "\tmov \tqword [" + visit(node.addr);
        if(node.offset < 0){
            asm = asm + node.offset + "]";
        }else{
            asm = asm + "+" + node.offset + "]";
        }
        asm = asm + ", " + visit(node.source);
        System.out.println(asm);
    }

    @Override
    public void visit(Push node) {
        System.out.println("\tpush \t" + visit(node.source));
    }

    @Override
    public void visit(Pop node) {
        System.out.println("\tpop \t" + visit(node.dest));
    }

    private String visit(DataSrc reg){
        if(reg instanceof PhysicalReg)
            return ((PhysicalReg)reg).name;
        else if(reg instanceof Immediate) {
            return "" + ((Immediate) reg).value;
        }else if(reg instanceof GlobalVar){
            return "qword [" + ((GlobalVar) reg).name + "]";
        }else if(reg instanceof StringLiteral){
            return ((StringLiteral) reg).label;
        }else
            assert false;
        return "FUCK!!!" + reg.getClass().getSimpleName() + ((VirtualReg)reg).Name;
    }
}