package XYXCompiler.BackEnd;

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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static XYXCompiler.BackEnd.X86_64.X86Registers.rax;

public class ASMPrinter implements XIRVisitor{
        StringBuilder ASM = new StringBuilder();
        private Map<BasicBlock, String> BBNameMap = new HashMap<>();
        private Function curFunc;
        private BasicBlock AdjacentBlk;
        private int BBNum = 0;
        private int BlkId = 0;

        private static String getAssembly(String Inst) {
            return "\t" + Inst + "\n";
        }

        private static String getAssembly(String Inst, String Operand) {
            return "\t" + Inst + " \t" + Operand + "\n";
        }

        private static String getAssembly(String Inst, String Operand1, String Operand2) {
            if(Inst.equals("mov") && Operand1.equals(Operand2))
                return "";
            return "\t" + Inst + " \t" + Operand1 + ", " + Operand2 + "\n";
        }

        private static String getAssembly(String Inst, String Operand1, int Operand2) {
            return "\t" + Inst + " \t" + Operand1 + ", " + Operand2 + "\n";
        }

        private void Print_ExternReferences(){
            ASM.append("extern puts\nextern getchar\nextern putchar\nextern sprintf\n" +
                    "extern __stack_chk_fail\nextern malloc\nextern printf\n" +
                    "extern strlen\nextern memcpy\nextern scanf\n");
        }

        private void Print_GlobalLabels(XIRRoot node){
            for (GlobalVar X : node.StaticSpace)
                ASM.append("global ").append(X.name).append("\n");
            for (Function X : node.Functions.values())
                ASM.append("global ").append(X.name).append("\n");
        }

        private void Print_ASMText(XIRRoot node){
            ASM.append("SECTION .text\n");
            for (Function X : node.Functions.values())
                visit(X);
        }

        private void Print_StaticData(XIRRoot node){
            ASM.append("SECTION .data    align = 8\n");
            for (GlobalVar X : node.StaticSpace)
                ASM.append(X.name).append(":\n\t dq 000000000000000AH\n");

            for(StringLiteral X: node.LiteralDataPool){
                String data = X.toInt();
                ASM.append("\tdq ").append(X.length).append("\n");
                ASM.append(X.label).append(":\n");
                ASM.append("\tdb ").append(data).append("\n");
            }
            ASM.append("intbuffer:\n\tdq 0\nformat1:\n\tdb\"%lld\",0\nformat2:\n\tdb\"%s\",0\n\n");

            ASM.append("SECTION .bss \nstringbuffer:\n\tresb 256\n");
            for (GlobalVar X : node.ReservedSpace)
                ASM.append(X.name).append(":\n\tresq\t1\n");
        }

        private void Print_BuiltinFunctions()throws IOException {
            FileReader reader = new FileReader("src/XYXCompiler/Tools/BuiltinFunction.asm");
            BufferedReader br = new BufferedReader(reader);
            br.lines().forEach(x -> ASM.append(x).append("\n"));
        }

        @Override
        public void visit(XIRRoot node) {
            try {
                Print_ExternReferences();
                Print_GlobalLabels(node);
                Print_ASMText(node);
                Print_BuiltinFunctions();
                Print_StaticData(node);
            }catch (Exception ex){
                System.err.println("Code Generation Error!");
            }
            System.out.println(ASM);
        }

        @Override
        public void visit(Function node) {
            curFunc = node;
            ASM.append(node.name).append(":\n");
            ASM.append(getAssembly("push","rbp"));
            ASM.append(getAssembly("mov","rbp","rsp"));

            for(BasicBlock X: node.PreOrder) {
                visit(X);
                this.BlkId++;
            }
            this.BlkId = 0;
        }

        @Override
        public void visit(BasicBlock node) {
            ASM.append(getBBLabel(node)).append(":\n");
            for(Instruction inst = node.Entry; inst != null; inst = inst.next){
                inst.accept(this);
            }
        }

        @Override
        public void visit(BinaryOp_Inst node) {
            if(node.L_operand instanceof Immediate && node.R_operand instanceof Immediate){
                int Lval = ((Immediate) node.L_operand).value;
                int Rval = ((Immediate) node.R_operand).value;
                int Ans = 0;
                switch (node.op){
                    case Add: Ans = Lval +  Rval; break;
                    case Sub: Ans = Lval -  Rval; break;
                    case Mul: Ans = Lval *  Rval; break;
                    case Div: Ans = Lval /  Rval; break;
                    case Mod: Ans = Lval %  Rval; break;
                    case Lsh: Ans = Lval << Rval; break;
                    case Rsh: Ans = Lval >> Rval; break;
                    case And: Ans = Lval &  Rval; break;
                    case Xor: Ans = Lval ^  Rval; break;
                    case Or:  Ans = Lval |  Rval; break;
                }
                ASM.append(getAssembly("mov", get(node.dest), Ans));
            }else{
                if(node.op == BinaryOp_Inst.binaryop.Div){
                    ASM.append(getAssembly("mov","rdx",0));
                    ASM.append(getAssembly("mov","rax",get(node.L_operand)));
                    ASM.append(getAssembly("idiv",get(node.R_operand)));
                    ASM.append(getAssembly("mov",get(node.dest),"rax"));
                }else if(node.op == BinaryOp_Inst.binaryop.Mod){
                    ASM.append(getAssembly("mov","rdx",0));
                    ASM.append(getAssembly("mov","rax",get(node.L_operand)));
                    ASM.append(getAssembly("idiv",get(node.R_operand)));
                    ASM.append(getAssembly("mov",get(node.dest),"rdx"));
                }else if(node.op == BinaryOp_Inst.binaryop.Lsh){
                    ASM.append(getAssembly("mov","rcx",get(node.R_operand)));
                    ASM.append(getAssembly("sal",get(node.L_operand),"cl"));
                    ASM.append(getAssembly("mov",get(node.dest),get(node.L_operand)));
                }else if(node.op == BinaryOp_Inst.binaryop.Rsh){
                    ASM.append(getAssembly("mov","rcx",get(node.R_operand)));
                    ASM.append(getAssembly("sar",get(node.L_operand),"cl"));
                    ASM.append(getAssembly("mov",get(node.dest),get(node.L_operand)));
                }else{
                    String Inst = "";
                    switch (node.op){
                        case Add: Inst =  "add"; break;
                        case Sub: Inst =  "sub"; break;
                        case Mul: Inst = "imul"; break;
                        case And: Inst =  "and"; break;
                        case Xor: Inst =  "xor"; break;
                        case Or:  Inst =  "or";  break;
                    }
                    ASM.append(getAssembly("mov", get(node.dest), get(node.L_operand)));
                    ASM.append(getAssembly(Inst, get(node.dest), get(node.R_operand)));
                }
            }
        }

        @Override
        public void visit(RelationOp_Inst node) {
            ASM.append(getAssembly("cmp", get(node.L_operand), get(node.R_operand)));
            String Inst = null;
            switch (node.op){
                case LS: Inst = "setl" ; break;
                case LE: Inst = "setle"; break;
                case GT: Inst = "setg" ; break;
                case GE: Inst = "setge"; break;
                case EQ: Inst = "sete" ; break;
                case NE: Inst = "setne"; break;
            }
            ASM.append(getAssembly(Inst, get(node.dest) + "b"));
            ASM.append(getAssembly("movzx",get(node.dest),get(node.dest) + "b"));
        }

        @Override
        public void visit(UnaryOp_Inst node) {
            switch (node.op) {
                case NEG:
                    ASM.append(getAssembly("neg", get(node.operand)));
                    break;
                case NOT:
                    ASM.append(getAssembly("xor", get(node.operand),1));
                    break;
            }
            ASM.append(getAssembly("mov",get(node.dest), get(node.operand)));
        }

        private void ArrangeBlockOrder(CJump_Inst node){
            AdjacentBlk = (BlkId + 1 < curFunc.PreOrder.size()) ? curFunc.PreOrder.get(BlkId + 1) : null;
            if(node.ifTrue == AdjacentBlk){
                node.ifTrue = node.ifFalse;
                node.ifFalse = AdjacentBlk;
                switch (node.op){
                    case Z:  node.op = RelationOp_Inst.CmpOp.NZ; break;
                    case EQ: node.op = RelationOp_Inst.CmpOp.NE; break;
                    case NE: node.op = RelationOp_Inst.CmpOp.EQ; break;
                    case LE: node.op = RelationOp_Inst.CmpOp.GT; break;
                    case LS: node.op = RelationOp_Inst.CmpOp.GE; break;
                    case GE: node.op = RelationOp_Inst.CmpOp.LS; break;
                    case GT: node.op = RelationOp_Inst.CmpOp.LE; break;
                }
            }
        }

        @Override
        public void visit(CJump_Inst node) {
            if(node.L_operand instanceof Immediate){
                ASM.append(getAssembly("mov","rax",get(node.L_operand)));
                ASM.append(getAssembly("cmp","rax",get(node.R_operand)));
            }else
                ASM.append(getAssembly("cmp", get(node.L_operand), get(node.R_operand)));

            ArrangeBlockOrder(node);

            switch (node.op){
                case Z :ASM.append(getAssembly("jnz", getBBLabel(node.ifTrue)));  break;
                case NZ:ASM.append(getAssembly("jz ", getBBLabel(node.ifTrue)));  break;
                case EQ:ASM.append(getAssembly("je ", getBBLabel(node.ifTrue)));  break;
                case NE:ASM.append(getAssembly("jne", getBBLabel(node.ifTrue)));  break;
                case LS:ASM.append(getAssembly("jl ", getBBLabel(node.ifTrue)));  break;
                case GE:ASM.append(getAssembly("jge", getBBLabel(node.ifTrue)));  break;
                case LE:ASM.append(getAssembly("jle", getBBLabel(node.ifTrue)));  break;
                case GT:ASM.append(getAssembly("jg ", getBBLabel(node.ifTrue)));  break;
            }
        }

        @Override
        public void visit(Jump_Inst node) {
            ASM.append(getAssembly("jmp",getBBLabel(node.target)));
        }

        @Override
        public void visit(Call_Inst node) {
            ASM.append(getAssembly("call", node.function.name));
        }

        @Override
        public void visit(Return_Inst node) {
            if(node.retval instanceof GlobalVar)
                ASM.append(getAssembly("mov","rax","qword ["+get(node.retval)+"]"));
            else if(node.retval != rax && node.retval != null)
                ASM.append(getAssembly("mov","rax", get(node.retval)));
            ASM.append(getAssembly("ret"));
        }

        @Override
        public void visit(Alloc_Inst node) {
            //getCallersaved()
            ASM.append(getAssembly("mov","rdi",get(node.size)));
            ASM.append(getAssembly("call","malloc"));
            ASM.append(getAssembly("mov", get(node.dest),"rax"));
            //getCalleeSaved()
        }

        @Override
        public void visit(Load_Inst node) {
            String Source  = "qword [" + get(node.addr);
            if(node.offset < 0)
                Source += node.offset + "]";
            else
                Source += "+" + node.offset + "]";

            ASM.append(getAssembly("mov", get(node.dest), Source));
        }

        @Override
        public void visit(Move_Inst node) {
            ASM.append(getAssembly("mov", get(node.dest), get(node.Source)));
        }

        @Override
        public void visit(Store_Inst node) {
            String Dest =  "qword [" + get(node.addr);
            if(node.offset < 0){
                Dest += node.offset + "]";
            }else{
                Dest += "+" + node.offset + "]";
            }
            ASM.append(getAssembly("mov", Dest, get(node.source)));
        }

        @Override
        public void visit(Push node) {
            ASM.append(getAssembly("push", get(node.source)));
        }

        @Override
        public void visit(Pop node) {
            ASM.append(getAssembly("pop", get(node.dest)));
        }

        private String get(DataSrc Operand){
            if(Operand instanceof PhysicalReg)      return ((PhysicalReg) Operand).name;
            if(Operand instanceof Immediate)        return "" + ((Immediate) Operand).value;
            if(Operand instanceof GlobalVar)        return ((GlobalVar) Operand).name;
            if(Operand instanceof StringLiteral)    return ((StringLiteral) Operand).label;

            return "FUCK!!!!" + Operand.getClass().getSimpleName() + ((VirtualReg)Operand).Name;
        }

        private String getBBLabel(BasicBlock BB){
            String name = BBNameMap.get(BB);
            if(name == null){
                name = BB.label + "." + (BBNum++);
                BBNameMap.put(BB, name);
            }
            return name;
        }
    }
