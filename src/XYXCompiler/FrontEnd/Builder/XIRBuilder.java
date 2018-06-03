package XYXCompiler.FrontEnd.Builder;

import XYXCompiler.FrontEnd.ASTNode.ASTRoot;
import XYXCompiler.FrontEnd.ASTNode.Declaration.*;
import XYXCompiler.FrontEnd.ASTNode.Expression.Binary_Expression;
import XYXCompiler.FrontEnd.ASTNode.Expression.Expression;
import XYXCompiler.FrontEnd.ASTNode.Expression.Primitive.*;
import XYXCompiler.FrontEnd.ASTNode.Expression.Suffix.*;
import XYXCompiler.FrontEnd.ASTNode.Expression.Unary_Expression;
import XYXCompiler.FrontEnd.ASTNode.Node;
import XYXCompiler.FrontEnd.ASTNode.Statement.*;
import XYXCompiler.FrontEnd.ASTNode.Type.*;
import XYXCompiler.Tools.TypeTable.Class_info;
import XYXCompiler.Tools.TypeTable.TypeTable;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst.*;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst.CmpOp;
import XYXCompiler.XIR.Instruction.Arithmatic.UnaryOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.UnaryOp_Inst.unaryop;
import XYXCompiler.XIR.Instruction.Control.CJump_Inst;
import XYXCompiler.XIR.Instruction.Control.Jump_Inst;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Functional.Return_Inst;
import XYXCompiler.XIR.Instruction.Memory.Alloc_Inst;
import XYXCompiler.XIR.Instruction.Memory.Load_Inst;
import XYXCompiler.XIR.Instruction.Memory.Move_Inst;
import XYXCompiler.XIR.Instruction.Memory.Store_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Register.GlobalVar;
import XYXCompiler.XIR.Operand.Static.Immediate;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.StringLiteral;
import XYXCompiler.XIR.Tools.BuiltinFunctionInserter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static XYXCompiler.BackEnd.X86_64.X86Registers.rax;
import static XYXCompiler.XIR.Tools.ConstVal.intsize;

public class XIRBuilder implements ASTVisitor {
    private BuiltinFunctionInserter inserter;
    public XIRRoot Root = new XIRRoot();
    public ASTRoot astRoot = null;
    private BasicBlock curBlk = null;
    private Function curFunc = null;
    private BasicBlock preLoopUpdate;
    private BasicBlock preLoopAfter;
    private BasicBlock CurLoopUpdate;
    private BasicBlock CurLoopAfter;
    private VirtualReg THISPOINTER = null;
    private Class_info curClassInfo = null;
    public Map<String, Function> FuncMap = new HashMap<>();
    public List<Array_Type> NewDimList = new LinkedList<>();
    public TypeTable typeTable;
    private boolean curIfAddr = false;
    private int Blknum = 0;
    private int Loopnum = 0;
    private int addrcnt = 0;


    public XIRBuilder(TypeTable typeTable) {
        this.typeTable = typeTable;
        inserter = new BuiltinFunctionInserter(Root, this);
    }

    @Override
    public void visit(ASTRoot node) {
        astRoot = node;
        inserter = new BuiltinFunctionInserter(Root, this);
        inserter.Insert();
        Collect_Functions(node);
        for(Declaration X: node.declarations){
            if(X instanceof Class_Declaration)
                Collect_Functions(X);
        }

        Allocate_GlobalVar(node);

        for(Declaration X: node.declarations){
            if(X instanceof Function_Declaration || X instanceof Class_Declaration)
                VISIT(X);
        }
    }

    public void VISIT(Node node){
        if(node != null)
            node.accept(this);
    }

    private boolean ifLogical(Node node){
        if(node instanceof Binary_Expression)
            if(((Binary_Expression) node).op == Binary_Expression.BinaryOP.LogicalAnd ||
                    ((Binary_Expression) node).op == Binary_Expression.BinaryOP.LogicalOr)
                return true;
        return false;
    }

    private void Construct_ShortCircuit(Binary_Expression node){
        // For Logical Expression
        // Construct the CFG from <node> to <node.ifTrue> and <node.ifFalse>
        if(node.op == Binary_Expression.BinaryOP.LogicalAnd){
            node.lhs.ifFalse = node.ifFalse;
            node.lhs.ifTrue = new BasicBlock(curFunc, "And_Lhs_IfTrue" + Blknum++);
            VISIT(node.lhs);
            curBlk = node.lhs.ifTrue;
        }else if(node.op == Binary_Expression.BinaryOP.LogicalOr){
            node.lhs.ifTrue = node.ifTrue;
            node.lhs.ifFalse = new BasicBlock(curFunc,"Or_Lhs_IfFalse" + Blknum++);
            VISIT(node.lhs);
            curBlk = node.lhs.ifFalse;
        }
        node.rhs.ifFalse = node.ifFalse;
        node.rhs.ifTrue = node.ifTrue;
        VISIT(node.rhs);
    }

    private void MergeCircuit(Binary_Expression node){
        // Merge the Short Circuit BBs for Expr Value only
        BasicBlock MergeBlk = new BasicBlock(curFunc, "Merge_Binary" + Blknum++);
        node.datasrc = new VirtualReg("merge");

        if(node.ifFalse.Exit != null) node.ifFalse.Exit.remove();
        if(node.ifTrue.Exit != null) node.ifTrue.Exit.remove();

        curBlk = node.ifTrue;
        curBlk.add(new Move_Inst(curBlk, node.datasrc, new Immediate(1)));
        curBlk.Close_J(MergeBlk);

        curBlk = node.ifFalse;
        curBlk.add(new Move_Inst(curBlk, node.datasrc, new Immediate(0)));
        curBlk.Close_J(MergeBlk);

        //node.ifTrue.Close_J(MergeBlk);
        //node.ifFalse.Close_J(MergeBlk);

        curBlk = MergeBlk;//Warning: the curBB has been changed
    }

    private void Construct_Assign(Binary_Expression node){
        if(ifLogical(node.rhs)){
            node.rhs.ifTrue = new BasicBlock(curFunc, "logical_iftrue");
            node.rhs.ifFalse = new BasicBlock(curFunc, "logical_iffalse");
        }
        boolean backup = curIfAddr;
        enterAddr(IfNeedMem(node.lhs));
        VISIT(node.lhs);
        exitAddr(backup);

        VISIT(node.rhs);
        if(ifLogical(node.rhs))
            MergeCircuit((Binary_Expression) (node.rhs));

        if(IfNeedMem(node.lhs) || node.lhs.baseaddr instanceof GlobalVar){
            curBlk.add(new Store_Inst(curBlk, node.rhs.datasrc, intsize, node.lhs.baseaddr, node.lhs.offset));
        }else{
            curBlk.add(new Move_Inst(curBlk, node.lhs.datasrc, node.rhs.datasrc));
        }
    }

    private Function getFunction(String name){
        return FuncMap.get(name);
    }

    private void Handle_StringBuiltinFunction(Binary_Expression node){
        VirtualReg reg = new VirtualReg("StrOp");
        Function func = null;
        switch (node.op) {
            case Equal:         func = getFunction("string.eq");break;
            case Plus:          func = getFunction("string.add");break;
            case Less:          func = getFunction("string.s");break;
            case Greater:       func = getFunction("string.g");break;
            case LessEqual:     func = getFunction("string.le");break;
            case GreaterEqual:  func = getFunction("string.ge");break;
        }
        VISIT(node.lhs);
        VISIT(node.rhs);
        Call_Inst Inst = new Call_Inst(curBlk, func, reg);
        Inst.ArgLocs.add(node.lhs.datasrc);
        Inst.ArgLocs.add(node.rhs.datasrc);
        node.datasrc = reg;
        curBlk.add(Inst);
        curBlk.add(new Move_Inst(curBlk, reg, rax));
    }

    private void Construct_Comparation(Binary_Expression node){
        if(node.lhs.type instanceof String_Type){
            Handle_StringBuiltinFunction(node);
            if(hasBranch(node))
                curBlk.Close_B(node.datasrc,new Immediate(0),CmpOp.Z,node.ifTrue,node.ifFalse);
            return;
        }

        //specifically for while/for condition expr with compare operator
        RelationOp_Inst inst = new RelationOp_Inst(curBlk, new VirtualReg(null));
        switch (node.op) {
            case Equal:         inst.op = CmpOp.EQ; break;
            case NotEqual:      inst.op = CmpOp.NE; break;
            case LessEqual:     inst.op = CmpOp.LE; break;
            case GreaterEqual:  inst.op = CmpOp.GE; break;
            case Less:          inst.op = CmpOp.LS; break;
            case Greater:       inst.op = CmpOp.GT; break;
        }

        VISIT(node.lhs);
        VISIT(node.rhs);

        if(hasBranch(node)){
            //for this kind of Branch, we have to write "cmp L_operand R_operand; jle Label"
            curBlk.Close_B(node.lhs.datasrc, node.rhs.datasrc, inst.op, node.ifTrue, node.ifFalse);
        }else{
            inst.L_operand = node.lhs.datasrc;
            inst.R_operand = node.rhs.datasrc;
            curBlk.add(inst);
            node.datasrc = inst.dest;
        }
    }

    private void Construct_Arithmatic(Binary_Expression node){
        if(node.lhs.type instanceof String_Type || node.rhs.type instanceof String_Type){
            Handle_StringBuiltinFunction(node);
            return;
        }

        BinaryOp_Inst inst = new BinaryOp_Inst(curBlk);
        switch(node.op){
            case Mul:           inst.op = binaryop.Mul; break;
            case Div:           inst.op = binaryop.Div; break;
            case Mod:           inst.op = binaryop.Mod; break;
            case Plus:          inst.op = binaryop.Add; break;
            case Minus:         inst.op = binaryop.Sub; break;
            case LeftShift:     inst.op = binaryop.Lsh; break;
            case RightShift:    inst.op = binaryop.Rsh; break;
            case BitAnd:        inst.op = binaryop.And; break;
            case BitOr:         inst.op = binaryop.Or;  break;
            case BitXor:        inst.op = binaryop.Xor; break;
        }
        VISIT(node.lhs);
        VISIT(node.rhs);
        inst.L_operand = node.lhs.datasrc;
        inst.R_operand = node.rhs.datasrc;

        //idiv cannot support imm
        if(inst.R_operand instanceof Immediate && (inst.op == binaryop.Mod||inst.op == binaryop.Div)){
            VirtualReg reg = new VirtualReg(null);
            curBlk.add(new Move_Inst(curBlk, reg, inst.R_operand));
            inst.R_operand = reg;
        }
        node.datasrc = inst.dest = new VirtualReg(null);
        curBlk.add(inst);
    }

    private void enterLoop(BasicBlock Update, BasicBlock After){
        preLoopAfter = CurLoopAfter;
        preLoopUpdate = CurLoopUpdate;
        CurLoopAfter = After;
        CurLoopUpdate = Update;
    }

    private  void exitLoop(){
        CurLoopUpdate = preLoopUpdate;
        CurLoopAfter = preLoopAfter;
    }

    private void enterAddr(boolean Ifaddr){
        curIfAddr = Ifaddr;
    }

    private void exitAddr(boolean backup){
        curIfAddr = backup;
    }

    private boolean hasBranch(Expression node){
        return node.ifFalse != null;
    }

    private boolean IfNeedMem(Node node){
        return (node instanceof Accessing || node instanceof Indexing)
                || (THISPOINTER != null && node instanceof ID && curClassInfo.membernameList.contains(((ID) node).name));
    }

    private void StoreFuncMap(String name, Function func){
        Root.Functions.put(name, func);
        FuncMap.put(name, func);
    }

    private void Collect_Functions(Node node){
        List<Declaration> declarations;
        boolean inClass = false;
        if(node instanceof ASTRoot){
            declarations = ((ASTRoot) node).declarations;
        }else{
            declarations = ((Class_Declaration) node).Members;
            inClass = true;
        }

        for(Declaration X: declarations){
            if(X instanceof Function_Declaration){
                Function func = new Function();
                func.name = X.name;
                func.inClass = inClass;
                func.func_info = ((Function_Declaration)X).functype;
                func.retsize = ((Function_Declaration)X).returntype instanceof Void_Type ? 0 : 8;
                StoreFuncMap(func.name, func);
            }else if(X instanceof Construct_Function){
                Function func = new Function();
                func.name = X.name;
                func.inClass = inClass;
                StoreFuncMap(func.name, func);
            }
        }
    }

    private void Initialize_GlobalVar(ASTRoot node){
        for(Declaration X : node.declarations){
            if(X instanceof Global_Variable_Declaration)
                VISIT(X);
        }
    }

    private void Allocate_GlobalVar(ASTRoot root) {
        for (Declaration X : root.declarations) {
            if (X instanceof Global_Variable_Declaration){
                Global_Variable_Declaration node = (Global_Variable_Declaration) X;
                GlobalVar space;
                if(node.RHS != null){
                    space = new GlobalVar("_" + node.name, node.size);
                    Root.StaticSpace.add(space);
                }else{
                    space  = new GlobalVar("@" + node.name, node.size);
                    Root.ReservedSpace.add(space);
                }
                node.dataSrc = space;
            }
        }
    }

    @Override
    public void visit(Global_Variable_Declaration node) {
        VISIT(node.RHS);
        if(node.RHS != null){
            //curBlk.add(new Move_Inst(curBlk, node.dataSrc, node.RHS.datasrc));
            curBlk.add(new Store_Inst(curBlk, node.RHS.datasrc,8,node.dataSrc,0));
        }
    }

    @Override
    public void visit(Class_Declaration node) {
        curClassInfo = typeTable.ClassInfoTable.get(node.name);
        Collect_Functions(node);
        for(Declaration X: node.Members){
            if(X instanceof Function_Declaration || X instanceof Construct_Function)
                VISIT(X);
        }
        curClassInfo = null;
    }

    @Override
    public void visit(Construct_Function node) {
        curFunc = FuncMap.get(node.name);
        //Warning: without adding parameters
        THISPOINTER = new VirtualReg("this");

        curFunc.ArgRegs.add(THISPOINTER);
        curBlk = curFunc.StartBB;

        VISIT(node.body);

        curBlk.add(new Return_Inst(curBlk, THISPOINTER));
        curFunc.EndBB = curBlk;
        THISPOINTER = null;
    }

//-----Function Part---------------------------------------------------------
    @Override
    public void visit(Function_Declaration node) {
        curFunc = FuncMap.get(node.name);

        // A.func(this, param_1, ..., param_n)
        if(curFunc.inClass){
            THISPOINTER = new VirtualReg("__THIS__");
            curFunc.ArgRegs.add(THISPOINTER);
        }

        for(Variable_Declaration X : node.params){
            VISIT(X);
            curFunc.ArgRegs.add((VirtualReg) X.reg);
        }
        curBlk = curFunc.StartBB;

        if(node.name.equals("main"))
            Initialize_GlobalVar(astRoot);

        VISIT(node.body);

        //Merge multiple return block

        if(!(curBlk.Exit instanceof Return_Inst)){  //Must be void type function
            curBlk.ret = new Return_Inst(curBlk, new Immediate(0));
            curBlk.add(curBlk.ret);
            curFunc.RetBlks.add(curBlk);
        }


        switch (curFunc.RetBlks.size()){
            /*
            case 0:
                //Must not be void type function
                curBlk.add(new Return_Inst(curBlk, new Immediate(0)));
                curFunc.EndBB = curBlk;
                curBlk.If_closed = true;
                break;
            */
            case 1:
                curFunc.EndBB = curFunc.RetBlks.get(0);
                break;
            default:{ //create and merge All return BB
                curFunc.EndBB = new BasicBlock(curFunc,"Merged_Return" + Blknum++);
                curFunc.EndBB.add(new Return_Inst(curFunc.EndBB, rax));
                for(BasicBlock X: curFunc.RetBlks){
                    X.ret.prepend(new Move_Inst(X, rax, X.ret.retval));
                    X.ret.remove();
                    X.If_closed = false;
                    X.Close_J(curFunc.EndBB);
                }
            }
        }

        THISPOINTER = null;
    }

    @Override
    public void visit(Return node) {
        if(node.returnvalue == null){
            curBlk.Close_R(new Return_Inst(curBlk, new Immediate(0)));
        }else{
            if(ifLogical(node.returnvalue)){
                node.returnvalue.ifTrue = new BasicBlock(curFunc, "retval_iftrue");
                node.returnvalue.ifFalse = new BasicBlock(curFunc, "retval_iffalse");
                VISIT(node.returnvalue); //Warning: it must call ShortCircuit Fnuction
                MergeCircuit((Binary_Expression)(node.returnvalue));
            }else
                VISIT(node.returnvalue);
            curBlk.Close_R(new Return_Inst(curBlk, node.returnvalue.datasrc));
        }
    }

//-----VarDecl Part---------------------------------------------------------

    @Override
    public void visit(Variable_Declaration node) {
        if(curFunc != null){
            VirtualReg reg = new VirtualReg(node.name);
            node.reg = reg;
            curFunc.VirtualRegMap.put(node.name, reg);
        }
    }


    @Override
    public void visit(Variable_Declaration_Statement node) {
        VirtualReg reg = new VirtualReg(node.name);
        node.reg = reg;
        if(node.RHS != null){
            boolean logical = ifLogical(node.RHS);
            if(logical){
                node.RHS.ifTrue = new BasicBlock(curFunc, "Vardecl_RHS_Iftrue");
                node.RHS.ifFalse = new BasicBlock(curFunc, "Vardecl_RHS_Iffalse");
            }

            VISIT(node.RHS);

            if(logical)
                MergeCircuit((Binary_Expression) node.RHS);
            curBlk.add(new Move_Inst(curBlk, reg, node.RHS.datasrc));
        }else{
            curBlk.add(new Move_Inst(curBlk, reg, new Immediate(0)));
        }
    }

//----Control Part--------------------------------------------------------------------------------

    @Override
    public void visit(For_Statement node) {
        Loopnum++;
        Blknum += 4;
        BasicBlock ForCond   = new BasicBlock(curFunc, "ForCond" + Loopnum);
        BasicBlock ForBody   = new BasicBlock(curFunc, "ForBody" + Loopnum);
        BasicBlock ForUpdate = new BasicBlock(curFunc, "ForUpdate" + Loopnum);
        BasicBlock ForAfter  = new BasicBlock(curFunc, "ForAfter" + Loopnum);

        enterLoop(ForUpdate, ForAfter);
        /*
            (init)
            jmp -> _Cond

        _ForCond:
            cmp c, 0
            jz _After

        _ForBody:
            (Loop body)

        _ForUpdate:
            (update)
            jmp -> _Cond

         _ForAfter:

         */

        //construct init and jump to condition
        VISIT(node.init);
        if(node.condition != null)
            curBlk.Close_J(ForCond);
        else
            curBlk.Close_J(ForBody);

        //construct condition block
        curBlk = ForCond;
        if(node.condition != null){
            node.condition.ifTrue = ForBody;
            node.condition.ifFalse = ForAfter;
            VISIT(node.condition);
        }else
            curBlk.add(new Jump_Inst(curBlk, ForBody)); //Always true if nothing


        //construct body block
        curBlk = ForBody;
        VISIT(node.body);
        curBlk.Close_J(ForUpdate);

        //construct update block
        curBlk = ForUpdate;
        VISIT(node.update);
        if(node.condition != null)
            ForUpdate.Close_J(ForCond);
        else
            ForUpdate.Close_J(ForBody);

        //shift to after block
        exitLoop();
        curBlk = ForAfter;
    }

    @Override
    public void visit(While_Statement node) {
        Loopnum++;
        Blknum += 3;
        BasicBlock WhileCond = new BasicBlock(curFunc, "WhileCond" + Loopnum);
        BasicBlock WhileBody = new BasicBlock(curFunc, "WhileBody" + Loopnum);
        BasicBlock WhileAfter = new BasicBlock(curFunc, "WhileAfter" + Loopnum);

        /*
            jmp -> _WhileCond

        _WhileCond:
            cmp c, 0
            jz _WhileAfter

        _WhileBody:
            (Loop body)
            jmp -> _WhileCond

        _WhileAfter:

         */

        enterLoop(WhileCond, WhileAfter);

        curBlk.Close_J(WhileCond);

        curBlk = WhileCond;
        node.condition.ifTrue = WhileBody;
        node.condition.ifFalse = WhileAfter;
        VISIT(node.condition); //WhileCond has Closed by ShortCircuit

        curBlk = WhileBody;
        VISIT(node.body);
        curBlk.Close_J(WhileCond);

        curBlk = WhileAfter;
        exitLoop();
    }

    @Override
    public void visit(Break node) {
        curBlk.Close_J(CurLoopAfter);
    }

    @Override
    public void visit(Continue node) {
        curBlk.Close_J(CurLoopUpdate);
    }

//----Statement Part----------------------------------------------------------
    @Override
    public void visit(Compound_Statement node) {
        for(Statement X: node.stmts)
            VISIT(X);
    }

    @Override
    public void visit(Selection_Statement node) {
        BasicBlock thenbody = new BasicBlock(curFunc, "ThenBody" + Blknum++);
        BasicBlock afterIF = new BasicBlock(curFunc, "AfterIF" + Blknum++);
        BasicBlock elsebody = null;

        if(node.Else_body != null){
            elsebody = new BasicBlock(curFunc, "ElseBody" + Blknum++);
        }else
            elsebody = afterIF;

        node.condition.ifTrue = thenbody;
        node.condition.ifFalse = elsebody;
        VISIT(node.condition);

        curBlk = thenbody;
        VISIT(node.body);
        curBlk.Close_J(afterIF);

        if(node.Else_body != null){
            curBlk = elsebody;
            VISIT(node.Else_body);
            curBlk.Close_J(afterIF);
        }

        curBlk = afterIF;
    }

    @Override
    public void visit(Expression_Statement node) {
        VISIT(node.body);
    }

//-----Primary Part-----------------------------------------------------------
    @Override
    public void visit(ID node) {
        if(curClassInfo != null && curClassInfo.membernameList.contains(node.name)){
            //Need This.
            if(node.name.equals("this")){
                node.datasrc = THISPOINTER;
            }else{
                int offset = curClassInfo.getOffset(node.name);
                if(curIfAddr){
                    node.setAddr(THISPOINTER, offset);
                }else{
                    node.datasrc = new VirtualReg("this." + node.name);
                    curBlk.add(new Load_Inst(curBlk, (Register) (node.datasrc), THISPOINTER, offset, 8));
                }
            }
        }else{ //Not class Member
            Node Entity = node.entity;
            if(Entity instanceof Global_Variable_Declaration){
                VirtualReg reg = new VirtualReg(node.name);
                DataSrc Gvar = ((Global_Variable_Declaration) Entity).dataSrc;
                curBlk.add(new Load_Inst(curBlk, reg, Gvar,0,8));
                node.datasrc  =  reg;
                node.baseaddr = Gvar;
            }
            if(Entity instanceof Variable_Declaration)
                node.datasrc = ((Variable_Declaration) Entity).reg;
            if(Entity instanceof Variable_Declaration_Statement)
                node.datasrc = ((Variable_Declaration_Statement) Entity).reg;
        }
        if(hasBranch(node))
            curBlk.Close_B(node.datasrc, new Immediate(0), CmpOp.Z, node.ifTrue, node.ifFalse);
    }

    @Override
    public void visit(Unary_Expression node) {
        switch (node.op){
            case Not:
                //node.ifFalse = new BasicBlock(curFunc, "[Not]IfFalse" + Blknum++);
                //node.ifTrue = new BasicBlock(curFunc, "[Not]IfTrue" + Blknum++);
                VISIT(node.body);
                node.datasrc = new VirtualReg(null);
                curBlk.add(new UnaryOp_Inst(curBlk,
                        (VirtualReg)node.datasrc, unaryop.NOT, node.body.datasrc));
                if(hasBranch(node)){
                    curBlk.Close_B(node.datasrc,new Immediate(0), CmpOp.Z, node.ifTrue, node.ifFalse);
                }
                break;
            case Plus:
                VISIT(node.body);
                node.datasrc = node.body.datasrc;
                break;
            case Minus:
                VISIT(node.body);
                node.datasrc = new VirtualReg(null);
                if(node.body.datasrc instanceof Immediate){
                    ((Immediate) node.body.datasrc).value *= -1;
                    curBlk.add(new Move_Inst(curBlk, node.datasrc, node.body.datasrc));
                }else
                    curBlk.add(new UnaryOp_Inst(curBlk,
                        (VirtualReg)node.datasrc, unaryop.NEG, node.body.datasrc));
                break;
            case PlusPlus:
                Construct_PPMM(node, node.body, binaryop.Add);
                break;
            case MinusMinus:
                Construct_PPMM(node, node.body, binaryop.Sub);
                break;
            case Tilde:
                VISIT(node.body);
                node.datasrc = new VirtualReg(null);
                curBlk.add(new UnaryOp_Inst(curBlk,
                        (VirtualReg)node.datasrc, unaryop.NOT, node.body.datasrc));
                break;
        }
    }

    public void Construct_PPMM(Expression node, Expression body, binaryop op){
        boolean backup = curIfAddr;
        enterAddr(IfNeedMem(body));
        VISIT(body);
        exitAddr(backup);

        enterAddr(false);
        VISIT(body);
        exitAddr(backup);

        VirtualReg OriginalVal = null;
        if(!(node instanceof Unary_Expression)){ // a++;
            OriginalVal = new VirtualReg("backup");
            curBlk.add(new Move_Inst(curBlk, OriginalVal, body.datasrc));
        }

        VirtualReg dest = new VirtualReg(null);
        if(IfNeedMem(body) || body.baseaddr instanceof GlobalVar) {
            curBlk.add(new BinaryOp_Inst(curBlk, dest, body.datasrc, new Immediate(1), op));
            curBlk.add(new Store_Inst(curBlk, dest, 8, body.baseaddr, body.offset));
            if(OriginalVal == null)
                node.datasrc = dest;
            else
                node.datasrc = OriginalVal;
        }else{
            curBlk.add(new BinaryOp_Inst(curBlk, (Register) body.datasrc, body.datasrc, new Immediate(1), op));
            if(OriginalVal == null)
                node.datasrc = body.datasrc;
            else
                node.datasrc = OriginalVal;
        }
    }

    @Override
    public void visit(Binary_Expression node) {
        switch(node.op){
            case Mul:
            case Div:
            case Mod:
            case Plus:
            case Minus:
            case LeftShift:
            case RightShift:
            case BitAnd:
            case BitOr:
            case BitXor:
                Construct_Arithmatic(node);
                break;
            case Less:
            case LessEqual:
            case Greater:
            case GreaterEqual:
            case Equal:
            case NotEqual:
                Construct_Comparation(node);
                break;
            case LogicalAnd:
            case LogicalOr:
                if(node.ifFalse == null){
                    node.ifTrue = new BasicBlock(curFunc, "logical_iftrue");
                    node.ifFalse = new BasicBlock(curFunc, "logical_iffalse");
                    Construct_ShortCircuit(node);
                    MergeCircuit(node);
                }else
                    Construct_ShortCircuit(node);
                break;
            case Assign:
                Construct_Assign(node);
        }
    }

    @Override
    public void visit(INT node) {
        node.datasrc = new Immediate(node.value);
    }

    @Override
    public void visit(Null node) {
        node.datasrc = new Immediate(0);
    }

    @Override
    public void visit(Bool node) {
        node.datasrc = new Immediate(node.value ? 1 : 0);
        if(hasBranch(node)) {
            if (node.value)
                curBlk.Close_J(node.ifTrue);
            else
                curBlk.Close_J(node.ifFalse);
        }
    }

    @Override
    public void visit(STRING node) {
        String val = node.value;
        StringLiteral literal = new StringLiteral(val.substring(1, val.length()-1));
        node.datasrc = literal;
        Root.LiteralDataPool.add(literal);
    }
//20
    private void Construct_Array(Register reg, int level){
        int dim = NewDimList.size();
        if(level == dim) return;
        Array_Type type = NewDimList.get(level);
        Expression index = type.size;
        if(level == 0){
            curBlk.add(new BinaryOp_Inst(curBlk, reg, index.datasrc, new Immediate(8), binaryop.Mul));
            curBlk.add(new BinaryOp_Inst(curBlk, reg, reg, new Immediate(8), binaryop.Add));
            curBlk.add(new Alloc_Inst(curBlk, reg, reg));
            curBlk.add(new Store_Inst(curBlk, index.datasrc,8, reg,0));
            Construct_Array(reg, level + 1);
        }else{
            Array_Type pretype = NewDimList.get(level - 1);
            Expression preindex = pretype.size;
            BasicBlock ForCond = new BasicBlock(curFunc,"for_cond");
            BasicBlock ForUpdate = new BasicBlock(curFunc,"for_update");
            BasicBlock ForBody = new BasicBlock(curFunc,"for_body");
            BasicBlock ForAfter = new BasicBlock(curFunc,"for_after");

            //init
            VirtualReg cnt = new VirtualReg("cnt");
            curBlk.add(new Move_Inst(curBlk, cnt, new Immediate(0)));
            curBlk.Close_J(ForCond);

            //cond
            curBlk = ForCond;
            curBlk.Close_B(cnt, preindex.datasrc, CmpOp.LS, ForBody, ForAfter);

            //body
            curBlk = ForBody;
            //pointer is where the newArray's addr located
            VirtualReg Pointer = new VirtualReg("pointer");
            curBlk.add(new BinaryOp_Inst(curBlk, Pointer, cnt, new Immediate(8),binaryop.Mul));
            curBlk.add(new BinaryOp_Inst(curBlk, Pointer, reg, Pointer, binaryop.Add));

            VirtualReg HeadAddr = new VirtualReg("headaddr");
            curBlk.add(new BinaryOp_Inst(curBlk, HeadAddr, index.datasrc, new Immediate(8),binaryop.Mul));
            curBlk.add(new BinaryOp_Inst(curBlk, HeadAddr, HeadAddr, new Immediate(8),binaryop.Add));
            curBlk.add(new Alloc_Inst(curBlk, HeadAddr, HeadAddr));
            curBlk.add(new Store_Inst(curBlk, index.datasrc,8, HeadAddr,0));
            curBlk.add(new Store_Inst(curBlk, HeadAddr,8,Pointer,8));

            Construct_Array(HeadAddr, level + 1);

            curBlk.Close_J(ForUpdate);

            //Update
            curBlk = ForUpdate;
            curBlk.add(new BinaryOp_Inst(curBlk, cnt, cnt, new Immediate(1), binaryop.Add));
            curBlk.Close_J(ForCond);
            //After
            curBlk = ForAfter;
        }
    }
/*
    private void Construct_Alloc(Register Headaddr, DataSrc nextdim){
        if(nextdim instanceof Immediate){
            curBlk.add(new Alloc_Inst(curBlk, Headaddr, new Immediate(((Immediate) nextdim).value*8)));
        }else{
            VirtualReg dimsize = new VirtualReg("dimsize");
            curBlk.add(new BinaryOp_Inst(curBlk, dimsize, nextdim, new Immediate(8), binaryop.Mul));
            curBlk.add(new Alloc_Inst(curBlk, Headaddr, dimsize));
        }
    }

    private void Construct_Forloop(DataSrc baseaddr, DataSrc thisdim, int level){
        //NewDimList.size() - level
        if(level == NewDimList.size())
            return;
        Array_Type next = NewDimList.get(level);
        DataSrc nextdim = next.size.datasrc;

        if(level == 0){
            Construct_Alloc((VirtualReg)baseaddr, nextdim);
            Construct_Forloop(baseaddr, nextdim,level + 1);
            return;
        }

        Loopnum++;
        Blknum += 4;
        BasicBlock ForCond   = new BasicBlock(curFunc, "Dim_ForCond" + Loopnum);
        BasicBlock ForBody   = new BasicBlock(curFunc, "Dim_ForBody" + Loopnum);
        BasicBlock ForUpdate = new BasicBlock(curFunc,"Dim_ForUpdate" + Loopnum);
        BasicBlock ForAfter  = new BasicBlock(curFunc, "Dim_ForAfter" + Loopnum);
        enterLoop(ForUpdate, ForAfter);

        //prepare for loop
        Register cnt = new VirtualReg("cnt" + Loopnum);
        Register Headaddr = new VirtualReg("Headaddr" + Loopnum);

        //----construct init block
        curBlk.add(new Move_Inst(curBlk, cnt, new Immediate(0)));
        curBlk.Close_J(ForCond);

        //------construct condition block
        curBlk = ForCond;
        curBlk.Close_B(cnt, thisdim, CmpOp.LS, ForBody, ForAfter);

        //------construct body block
        curBlk = ForBody;
        Construct_Alloc(Headaddr, nextdim);

        //fuck here, the first?
        VirtualReg PreciseAddr = new VirtualReg(null);
        curBlk.add(new BinaryOp_Inst(curBlk, PreciseAddr, cnt, new Immediate(8), binaryop.Mul));
        curBlk.add(new BinaryOp_Inst(curBlk, PreciseAddr, baseaddr, PreciseAddr, binaryop.Add));
        curBlk.add(new Store_Inst(curBlk,Headaddr,8, baseaddr, 0));

        if(level < NewDimList.size())
            Construct_Forloop(Headaddr, nextdim, level + 1);

        curBlk.Close_J(ForUpdate);

        //-------construct update block
        curBlk = ForUpdate;
        curBlk.add(new BinaryOp_Inst(curBlk, cnt, cnt, new Immediate(1), binaryop.Add));
        curBlk.Close_J(ForCond);

        //-------shift to after block
        exitLoop();
        curBlk = ForAfter;
    }
*/


    @Override
    public void visit(Array_Type node) {
        VISIT(node.size);
        VISIT(node.basetype);
        if(node.size == null)
            return;
        NewDimList.add(node);
    }

    @Override
    public void visit(Newexpr node){
        VirtualReg dest = new VirtualReg("new");
        if(node.type instanceof Array_Type){
            NewDimList.clear();
            visit((Array_Type) node.type);
            Construct_Array(dest, 0);
        }else if(node.type instanceof Class_Type){
            String classname = ((Class_Type) node.type).name;
            int size = typeTable.ClassInfoTable.get(classname).getSize();
            curBlk.add(new Alloc_Inst(curBlk,dest,new Immediate(size)));
            Call_Inst call = new Call_Inst(curBlk, FuncMap.get(classname), null);
            call.ArgLocs.add(dest);
            curBlk.add(call);
        }
        node.datasrc = dest;
    }

    @Override
    public void visit(Indexing node) {
        boolean backup = curIfAddr;
        enterAddr(false);
        VISIT(node.index);
        VISIT(node.name);
        exitAddr(backup);

        //if baseaddr is global
        VirtualReg reg = new VirtualReg("address" + addrcnt++);
        VirtualReg bodyAddr = (VirtualReg)(node.name.datasrc);

        //bodyAddr = (VirtualReg)(node.name.datasrc);
        curBlk.add(new BinaryOp_Inst(curBlk, reg, node.index.datasrc, new Immediate(8),binaryop.Mul));
        curBlk.add(new BinaryOp_Inst(curBlk, reg, bodyAddr, reg, binaryop.Add));


        if(curIfAddr){
            node.baseaddr = reg;
            node.offset = 8;
        }else{
            curBlk.add(new Load_Inst(curBlk, reg, reg,8,8));
            node.datasrc = reg;
        }

        if(hasBranch(node)){
            curBlk.Close_B(node.datasrc, new Immediate(0), CmpOp.Z, node.ifTrue, node.ifFalse);
        }
    }

    private boolean Handle_ThisAccessing(Accessing node){
        if(curClassInfo != null && node.body instanceof ID && ((ID) node.body).name.equals("this")){
            int offset = curClassInfo.getOffset(node.components);
            if(curIfAddr){
                node.baseaddr = THISPOINTER;
                node.offset = offset;
            }else{
                VirtualReg reg = new VirtualReg(null);
                curBlk.add(new Load_Inst(curBlk, reg, THISPOINTER, offset, intsize));
                node.datasrc = reg;
                if(hasBranch(node))
                    curBlk.Close_B(node.datasrc, new Immediate(0), CmpOp.Z, node.ifTrue, node.ifFalse);
            }
            return true;
        }
        return false;
    }

    @Override
    public void visit(Accessing node) {
        if(Handle_ThisAccessing(node))
            return;

        boolean backup = curIfAddr;
        enterAddr(false);
        VISIT(node.body);
        exitAddr(backup);

        Class_info class_info = typeTable.getInfo(((Class_Type)(node.body.type)).name);
        int offset = class_info.getOffset(node.components);

        if(curIfAddr){
            node.baseaddr = node.body.datasrc;
            node.offset = offset;
        }else{
            node.datasrc = new VirtualReg(null);
            curBlk.add(new Load_Inst(curBlk,(Register)(node.datasrc), node.body.datasrc, offset, intsize));
            if(hasBranch(node)){
                curBlk.Close_B(node.datasrc, new Immediate(0), CmpOp.Z, node.ifTrue, node.ifFalse);
            }
        }
    }

    private void HandleClassBuiltinFunction(Class_Method node){
        String name = node.Func_Name;
        Function func = FuncMap.get(name);
        VirtualReg reg = null;
        for(Expression X: node.params)
            VISIT(X);
        if(name.equals("size")){
            reg = new VirtualReg("size");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            call.ArgLocs.add(node.body.datasrc);
            curBlk.add(call);
        }else if(name.equals("parseInt")){
            reg = new VirtualReg("parseInt");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            call.ArgLocs.add(node.body.datasrc);
            curBlk.add(call);
        }else if(name.equals("length")){
            reg = new VirtualReg("length");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            call.ArgLocs.add(node.body.datasrc);
            curBlk.add(call);
        }else if(name.equals("ord")){
            reg = new VirtualReg("ord");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            call.ArgLocs.add(node.body.datasrc);
            call.ArgLocs.add(node.params.get(0).datasrc);
            curBlk.add(call);
        }else if(name.equals("substring")){
            reg = new VirtualReg("subString");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            call.ArgLocs.add(node.body.datasrc);
            call.ArgLocs.add(node.params.get(0).datasrc);
            call.ArgLocs.add(node.params.get(1).datasrc);
            curBlk.add(call);
        }
        node.datasrc = reg;
        if(reg != null)
            curBlk.add(new Move_Inst(curBlk, reg, rax));
        if(hasBranch(node))
            curBlk.Close_B(node.datasrc,new Immediate(0),CmpOp.Z,node.ifTrue,node.ifFalse);
    }

    private void HandleBuiltinFunction(Function_call node){
        String name = node.name;
        Function func = FuncMap.get(name);
        VirtualReg reg = null;
        for(Expression X: node.params)
            VISIT(X);
        if(name.equals("println")){
            Call_Inst call = new Call_Inst(curBlk, func, null);
            call.ArgLocs.add(node.params.get(0).datasrc);
            curBlk.add(call);
        }else if(name.equals("getInt")){
            reg = new VirtualReg("getint");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            curBlk.add(call);
        }else if(name.equals("getString")){
            reg = new VirtualReg("getString");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            curBlk.add(call);
        }else if(name.equals("print")){
            Call_Inst call = new Call_Inst(curBlk, func, null);
            call.ArgLocs.add(node.params.get(0).datasrc);
            curBlk.add(call);
        }else if(name.equals("toString")){
            reg = new VirtualReg("toStirng");
            Call_Inst call = new Call_Inst(curBlk, func, reg);
            call.ArgLocs.add(node.params.get(0).datasrc);
            curBlk.add(call);
        }

        node.datasrc = reg;
        if(reg != null)
            curBlk.add(new Move_Inst(curBlk, reg, rax));
        if(hasBranch(node))
            curBlk.Close_B(node.datasrc,new Immediate(0),CmpOp.Z,node.ifTrue,node.ifFalse);
    }

    @Override
    public void visit(Class_Method node) {
        boolean backup = curIfAddr;
        enterAddr(false);
        VISIT(node.body);
        for(Expression X: node.params)
            VISIT(X);
        exitAddr(backup);

        if(inserter.ifbuiltin(node.Func_Name)){
            HandleClassBuiltinFunction(node);
        }else{
            //WARNING: Class Method might have the same name as Function!!
            Function func = FuncMap.get(node.Func_Name);
            Register reg = (func.retsize == 0) ? null : new VirtualReg(func.name + "_ret");
            Call_Inst inst = new Call_Inst(curBlk, func, reg);
            node.datasrc = reg;
            curBlk.add(inst);

            inst.ArgLocs.add(node.body.datasrc); //ADD this ??
            for(Expression X: node.params)
                inst.ArgLocs.add(X.datasrc);

            if(reg != null)
                curBlk.add(new Move_Inst(curBlk, reg, rax));
            if(hasBranch(node))
                curBlk.Close_B(node.datasrc,new Immediate(0),CmpOp.Z,node.ifTrue,node.ifFalse);
        }
    }

    @Override
    public void visit(Function_call node) {
        VISIT(node.body);

        if(inserter.ifbuiltin(node.name))
            HandleBuiltinFunction(node);
        else{
            Function func = FuncMap.get(node.name);
            Register reg = (func.retsize == 0) ? null : new VirtualReg(func.name + "_ret");
            Call_Inst inst = new Call_Inst(curBlk, func, reg);
            node.datasrc = reg;


            if(THISPOINTER != null && curClassInfo.memberFuncnameList.contains(node.name))
                inst.ArgLocs.add(THISPOINTER);
            for(Expression X: node.params){
                VISIT(X);
                inst.ArgLocs.add(X.datasrc);
            }

            curBlk.add(inst);
            if(reg != null)
                curBlk.add(new Move_Inst(curBlk, reg, rax));
            if(hasBranch(node))
                curBlk.Close_B(node.datasrc,new Immediate(0),CmpOp.Z,node.ifTrue,node.ifFalse);
        }
    }

    @Override
    public void visit(Self_Decreasing node) {
        Construct_PPMM(node, node.body, binaryop.Sub);
    }

    @Override
    public void visit(Self_Increasing node) {
        Construct_PPMM(node, node.body, binaryop.Add);
    }

    @Override
    public void visit(Base_Type node) {

    }

    @Override
    public void visit(Statement node) {

    }

    @Override
    public void visit(Expression node) {

    }
}
