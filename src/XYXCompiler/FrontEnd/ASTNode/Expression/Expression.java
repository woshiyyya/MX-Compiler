package XYXCompiler.FrontEnd.ASTNode.Expression;

import XYXCompiler.FrontEnd.ASTNode.Node;
import XYXCompiler.FrontEnd.ASTNode.Type.*;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.Operand.DataSrc;

public class Expression extends Node {
    public Base_Type type;
    public Boolean LValue;

    //Expression Value Location
    public DataSrc datasrc = null;

    //Actual Memory Location
    public DataSrc baseaddr = null;
    public DataSrc offset = null;

    //Short-Circuit Evaluation
    public BasicBlock ifTrue = null;
    public BasicBlock ifFalse = null;

    public void setType(Base_Type type){
        this.type = type;
    }
}