package XYXCompiler.BackEnd.X86_64;

import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class X86Registers {
    //Scratch Register
    public static PhysicalReg rax = new PhysicalReg("rax",true,false);
    public static PhysicalReg rdi = new PhysicalReg("rdi",true,false);
    public static PhysicalReg rsi = new PhysicalReg("rsi",true,false);
    public static PhysicalReg rdx = new PhysicalReg("rdx",true,false);
    public static PhysicalReg rcx = new PhysicalReg("rcx",true,false);
    public static PhysicalReg r8 = new PhysicalReg("r8", true,false);
    public static PhysicalReg r9 = new PhysicalReg("r9", true,false);
    public static PhysicalReg r10 = new PhysicalReg("r10",true,false);
    public static PhysicalReg r11 = new PhysicalReg("r11",true,false);

    //Preserved Register
    public static PhysicalReg rbx = new PhysicalReg("rbx",false,true);
    public static PhysicalReg rsp = new PhysicalReg("rsp",false,true);
    public static PhysicalReg rbp = new PhysicalReg("rbp",false,true);
    public static PhysicalReg r12 = new PhysicalReg("r12",false,true);
    public static PhysicalReg r13 = new PhysicalReg("r13",false,true);
    public static PhysicalReg r14 = new PhysicalReg("r14",false,true);
    public static PhysicalReg r15 = new PhysicalReg("r15",false,true);

    public Set<PhysicalReg> CallerSavedRegs = new HashSet<>();
    public Set<PhysicalReg> CalleeSavedRegs = new HashSet<>();
    public List<PhysicalReg> GeneralRegs = new LinkedList<>();
    public List<PhysicalReg> FuncParamRegs = new LinkedList<>();

    public X86Registers() {
        // 9
        CallerSavedRegs.add(rax);
        CallerSavedRegs.add(rdi);
        CallerSavedRegs.add(rsi);
        CallerSavedRegs.add(rdx);
        CallerSavedRegs.add(rcx);
        CallerSavedRegs.add(r8);
        CallerSavedRegs.add(r9);
        CallerSavedRegs.add(r10);
        CallerSavedRegs.add(r11);

        // 7
        CalleeSavedRegs.add(rbx);
        CalleeSavedRegs.add(rsp);
        CalleeSavedRegs.add(rbp);
        CalleeSavedRegs.add(r12);
        CalleeSavedRegs.add(r13);
        CalleeSavedRegs.add(r14);
        CalleeSavedRegs.add(r15);

        GeneralRegs.add(rax);
        GeneralRegs.add(rdi);
        GeneralRegs.add(rsi);
        GeneralRegs.add(rdx);
        GeneralRegs.add(rcx);
        GeneralRegs.add(r8);
        GeneralRegs.add(r9);
        GeneralRegs.add(r10);
        GeneralRegs.add(r11);
        GeneralRegs.add(rbx);
        GeneralRegs.add(r12);
        GeneralRegs.add(r13);
        GeneralRegs.add(r14);
        GeneralRegs.add(r15);

        FuncParamRegs.add(rdi);
        FuncParamRegs.add(rsi);
        FuncParamRegs.add(rdx);
        FuncParamRegs.add(rcx);
        FuncParamRegs.add(r8);
        FuncParamRegs.add(r9);
    }
}
