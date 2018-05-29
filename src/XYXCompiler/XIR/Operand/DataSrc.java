package XYXCompiler.XIR.Operand;

import XYXCompiler.BackEnd.XIRVisitor;
import XYXCompiler.XIR.Operand.Register.GlobalVar;
import XYXCompiler.XIR.Operand.Register.PhysicalReg;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.Immediate;

public class DataSrc {
    public String getString(){
        if(this instanceof VirtualReg){
            if(((VirtualReg) this).Name != null)
                return "$" + ((VirtualReg) this).Name;
            else
                return "$V" + ((VirtualReg) this).Num;
        }else if(this instanceof Immediate) {
            return "" + ((Immediate) this).value;
        }else if(this instanceof PhysicalReg) {
            return "$" + ((PhysicalReg) this).name;
        }else if(this instanceof GlobalVar){
            return "$" + ((GlobalVar) this).name;
        }else
            return "MEM";
    }

}
