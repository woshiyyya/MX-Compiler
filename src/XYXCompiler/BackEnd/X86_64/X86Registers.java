package XYXCompiler.BackEnd.X86_64;

import XYXCompiler.XIR.Operand.Register.PhysicalReg;

import java.util.HashSet;
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
    public Set<PhysicalReg> FullRegs = new HashSet<>();
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

        FullRegs.addAll(CallerSavedRegs);
        FullRegs.addAll(CalleeSavedRegs);

    }
}
