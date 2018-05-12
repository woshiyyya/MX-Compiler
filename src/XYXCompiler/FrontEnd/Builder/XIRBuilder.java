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
import XYXCompiler.FrontEnd.ASTNode.Type.Array_Type;
import XYXCompiler.FrontEnd.ASTNode.Type.Base_Type;
import XYXCompiler.FrontEnd.ASTNode.Type.Void_Type;
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst.*;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.UnaryOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.UnaryOp_Inst.unaryop;
import XYXCompiler.XIR.Instruction.Control.CMP;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Functional.Return_Inst;
import XYXCompiler.XIR.Instruction.Memory.Move_Inst;
import XYXCompiler.XIR.Instruction.Memory.Store_Inst;
import XYXCompiler.XIR.Operand.Memory.DataSrc;
import XYXCompiler.XIR.Operand.Memory.Immediate;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Static.IntLiteral;
import XYXCompiler.XIR.Operand.Static.Literal;
import XYXCompiler.XIR.Operand.Static.StaticData;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.StringLiteral;
import XYXCompiler.XIR.Tools.Initializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static XYXCompiler.XIR.Tools.ConstVal.intsize;

public class XIRBuilder implements ASTVisitor {
    public XIRRoot Root = new XIRRoot();
    public BasicBlock curBlk = null;
    public Function curFunc = null;
    public BasicBlock preLoopUpdate;
    public BasicBlock preLoopAfter;
    public BasicBlock CurLoopUpdate;
    public BasicBlock CurLoopAfter;
    public Map<String, Function> FuncMap = new HashMap<>();

    public XIRBuilder() {}

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
            node.lhs.ifTrue = new BasicBlock(curFunc);
            VISIT(node.lhs);

            //Actually we just need to write "cmp datasrc 0; jz ifFalse"
            curBlk.Close_B(node.lhs.datasrc, node.lhs.ifTrue, node.ifFalse);
            curBlk = node.lhs.ifTrue;
        }else if(node.op == Binary_Expression.BinaryOP.LogicalOr){
            node.lhs.ifTrue = node.ifTrue;
            node.lhs.ifFalse = new BasicBlock(curFunc);
            VISIT(node.lhs);

            curBlk.Close_B(node.lhs.datasrc, node.ifTrue, node.lhs.ifFalse);
            curBlk = node.lhs.ifFalse;
        }
        node.rhs.ifFalse = node.ifFalse;
        node.rhs.ifTrue = node.ifTrue;
        VISIT(node.rhs);
    }

    private void MergeCircuit(Binary_Expression node, Expression lhs){
        // Merge the Short Circuit BBs for Expr Value only
        BasicBlock MergeBlk = new BasicBlock(curFunc);
        if(lhs instanceof Accessing || lhs instanceof Indexing){
            node.ifTrue.add(new Store_Inst(curBlk, new Immediate(1),intsize, lhs.baseaddr, lhs.offset));
            node.ifFalse.add(new Store_Inst(curBlk, new Immediate(0), intsize, lhs.baseaddr, lhs.offset));
        }else{
            node.datasrc = new VirtualReg(null);
            node.ifTrue.add(new Move_Inst(curBlk, node.datasrc, new Immediate(1)));
            node.ifFalse.add(new Move_Inst(curBlk, node.datasrc, new Immediate(0)));
        }
        node.ifTrue.Close_J(MergeBlk);
        node.ifFalse.Close_J(MergeBlk);
        curBlk = MergeBlk;//Warning: the curBB has been changed
    }

    private void Construct_Compare(Binary_Expression node){
        //specifically for while/for condition expr with compare operator
        RelationOp_Inst inst = new RelationOp_Inst(curBlk, new VirtualReg(null));
        switch (node.op) {
            case Equal:         inst.op = RelationOp_Inst.CmpOp.EQ; break;
            case NotEqual:      inst.op = RelationOp_Inst.CmpOp.NE; break;
            case LessEqual:     inst.op = RelationOp_Inst.CmpOp.LE; break;
            case GreaterEqual:  inst.op = RelationOp_Inst.CmpOp.GE; break;
            case Less:          inst.op = RelationOp_Inst.CmpOp.LS; break;
            case Greater:       inst.op = RelationOp_Inst.CmpOp.GT; break;
        }

        VISIT(node.lhs);
        VISIT(node.rhs);

        inst.L_operand = node.lhs.datasrc;
        inst.R_operand = node.rhs.datasrc;
        curBlk.add(inst);
        //for this kind of Branch, we have to write "cmp L_operand R_operand; jle Label"
        curBlk.Close_B(inst.dest, node.ifTrue, node.ifFalse);
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


    private void Collect_Functions(Node node){
        List<Declaration> declarations;
        if(node instanceof ASTRoot)
            declarations = ((ASTRoot) node).declarations;
        else
            declarations = ((Class_Declaration) node).Members;

        for(Declaration X: declarations){
            if(X instanceof Function_Declaration){
                Function func = new Function();
                func.name = X.name;
                func.func_info = ((Function_Declaration)X).functype;
                func.retsize = ((Function_Declaration)X).returntype.size;   //size: to be done
                FuncMap.put(func.name, func);
            }
        }
    }

    @Override
    public void visit(ASTRoot node) {
        Collect_Functions(node);
        for(Declaration X : node.declarations){
            if(X instanceof Global_Variable_Declaration)
                VISIT(X);
        }

        for(Declaration X: node.declarations){
            if(X instanceof Function_Declaration)
                VISIT(X);
        }
    }

    @Override
    public void visit(Class_Declaration node) {
        Collect_Functions(node);
        for(Declaration X: node.Members){
            if(X instanceof Function_Declaration)
                VISIT(X);
        }
    }

    @Override
    public void visit(Construct_Function node) {

    }

//-----Function Part---------------------------------------------------------
    @Override
    public void visit(Function_Declaration node) {
        curFunc = FuncMap.get(node.name);

        for(Variable_Declaration X : node.params){
            VISIT(X);
            curFunc.ArgRegs.add(X.reg);
        }

        Root.Functions.put(node.name, curFunc);
        curBlk = curFunc.StartBB;
        VISIT(node.body);

        //Merge multiple return block
        int returnInstsize = curFunc.EndBB.predcessor.size();
        if(returnInstsize == 1){
            curFunc.EndBB = curFunc.RetBlks.get(0);
        }
        else if(returnInstsize == 0){
            if(curFunc.func_info.returntype instanceof Void_Type){
                curBlk.add(new Return_Inst(curBlk, null));
            }else{
                curBlk.add(new Return_Inst(curBlk, new Immediate(0)));
            }
            curFunc.EndBB = curBlk;
            curBlk.If_closed = true;
        }
        else{
            //create end BB
            VirtualReg retReg = new VirtualReg("retval");
            curFunc.EndBB = new BasicBlock(curFunc);
            curFunc.EndBB.add(new Return_Inst(curFunc.EndBB, retReg));
            //merge All return BB
            for(BasicBlock X: curFunc.RetBlks){
                X.ret.prepend(new Move_Inst(X, retReg, X.ret.retval));
                X.ret.remove();
                X.Close_J(curFunc.EndBB);
            }
        }
        curFunc = null;
    }

    @Override
    public void visit(Return node) {
        if(node.returnvalue == null){
            curBlk.Close_R(new Return_Inst(curBlk, null));
        }else{
            if(ifLogical(node.returnvalue)){
                node.returnvalue.ifFalse = new BasicBlock(curFunc);
                node.returnvalue.ifTrue = new BasicBlock(curFunc);
                VISIT(node.returnvalue); //Warning: it must call ShortCircuit Fnuction
                MergeCircuit((Binary_Expression)(node.returnvalue),null);
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
    public void visit(Global_Variable_Declaration node) {
        //only considered the LHS is literal value
        Literal literal = null;
        StaticData space = new StaticData(node.name, node.size);
        if(node.RHS instanceof STRING){
            STRING rhs = (STRING) node.RHS;
            literal = new StringLiteral(rhs.value);
        }else if(node.RHS instanceof INT){
            INT rhs = (INT)node.RHS;
            literal = new IntLiteral(rhs.value);
        }

        if(literal != null)
            Root.LiteralDataPool.put(node.name, literal);
        Root.StaticSpace.add(space);
        node.dataSrc = space;
    }


    @Override
    public void visit(Variable_Declaration_Statement node) {
        VirtualReg reg = new VirtualReg(node.name);
        node.reg = reg;
        if(node.RHS != null){
            if(ifLogical(node.RHS)){
                node.RHS.ifTrue = new BasicBlock(curFunc);
                node.RHS.ifFalse = new BasicBlock(curFunc);
            }
            VISIT(node.RHS);
            curBlk.add(new Move_Inst(curBlk, reg, node.RHS.datasrc));
        }else{
            curBlk.add(new Move_Inst(curBlk, reg, new Immediate(0)));
        }
    }

//----Control Part--------------------------------------------------------------------------------

    @Override
    public void visit(For_Statement node) {
        BasicBlock ForCond   = new BasicBlock(curFunc);
        BasicBlock ForBody   = new BasicBlock(curFunc);
        BasicBlock ForUpdate = new BasicBlock(curFunc);
        BasicBlock ForAfter  = new BasicBlock(curFunc);

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
        curBlk.Close_J(ForCond);

        //construct condition block
        curBlk = ForCond;
        node.condition.ifTrue = ForBody;
        node.condition.ifFalse = ForAfter;
        VISIT(node.condition);

        //construct body block
        curBlk = ForBody;
        VISIT(node.body);
        curBlk.Close_J(ForUpdate);

        //construct update block
        curBlk = ForUpdate;
        VISIT(node.update);
        ForUpdate.Close_J(ForCond);

        //shift to after block
        exitLoop();
        curBlk = ForAfter;
    }

    @Override
    public void visit(While_Statement node) {
        BasicBlock WhileCond = new BasicBlock(curFunc);
        BasicBlock WhileBody = new BasicBlock(curFunc);
        BasicBlock WhileAfter = new BasicBlock(curFunc);

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
        WhileBody.Close_J(WhileCond);

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
        BasicBlock thenbody = new BasicBlock(curFunc);
        BasicBlock afterIF = new BasicBlock(curFunc);
        BasicBlock elsebody = null;
        if(node.Else_body != null)
            elsebody = new BasicBlock(curFunc);
        else
            elsebody = afterIF;

        node.condition.ifTrue = thenbody;
        node.condition.ifFalse = elsebody;
        VISIT(node.condition);

        curBlk = thenbody;
    }

    @Override
    public void visit(Expression_Statement node) {
        VISIT(node.body);
    }

//-----Primary Part-----------------------------------------------------------
    @Override
    public void visit(ID node) {
        Node Entity = node.entity;
        if(Entity instanceof Global_Variable_Declaration)
            node.datasrc = ((Global_Variable_Declaration) Entity).dataSrc;
        if(Entity instanceof Variable_Declaration)
            node.datasrc = ((Variable_Declaration) Entity).reg;
        if(Entity instanceof Variable_Declaration_Statement)
            node.datasrc = ((Variable_Declaration_Statement) Entity).reg;
        if(node.ifTrue != null){
            curBlk.Close_B(node.datasrc, node.ifTrue, node.ifFalse);
        }
    }

    @Override
    public void visit(Unary_Expression node) {
        switch (node.op){
            case Not:
                node.ifFalse = new BasicBlock(curFunc);
                node.ifTrue = new BasicBlock(curFunc);
                VISIT(node.body);
                break;
            case Plus:
                VISIT(node.body);
                node.datasrc = node.body.datasrc;
                break;
            case Minus:
                VISIT(node.body);
                node.datasrc = new VirtualReg(null);
                curBlk.add(new UnaryOp_Inst(curBlk,
                        (VirtualReg)node.datasrc, unaryop.NEG, node.body.datasrc));
                break;
            case PlusPlus:
                Construct_PPMM(node, node.body, binaryop.Add);
            case MinusMinus:
                Construct_PPMM(node, node.body, binaryop.Sub);
            case Tilde:
                VISIT(node.body);
                node.datasrc = new VirtualReg(null);
                curBlk.add(new UnaryOp_Inst(curBlk,
                        (VirtualReg)node.datasrc, unaryop.NOT, node.body.datasrc));
                break;
        }
    }

    public void Construct_PPMM(Expression node, Expression body, binaryop op){
        VISIT(body);
        IntLiteral RHS = new IntLiteral(1);
        DataSrc LHS = body.datasrc;
        //????? WTF Memory operation
        if(node instanceof Unary_Expression){
            // ++a
            curBlk.add(new BinaryOp_Inst(curBlk, (Register)(LHS), LHS, RHS, op));
            node.datasrc = LHS;
        }else{
            // a++
            Register backup = new VirtualReg(null);
            curBlk.add(new Move_Inst(curBlk, backup, LHS));
            curBlk.add(new BinaryOp_Inst(curBlk, (Register)(LHS), LHS, RHS, op));
            node.datasrc = backup;
        }

    }

    @Override
    public void visit(Binary_Expression node) {

    }

    @Override
    public void visit(INT node) {
        node.datasrc = new IntLiteral(node.value);
    }

    @Override
    public void visit(Null node) {
        node.datasrc = new IntLiteral(0);
    }

    @Override
    public void visit(Bool node) {
        node.datasrc = new IntLiteral(node.value ? 1 : 0);
    }

    @Override
    public void visit(STRING node) {
        node.datasrc = new StringLiteral(node.value);
    }

    @Override
    public void visit(Newexpr node) {
        VISIT(node.type);
        int size = node.type.size;
        // IM Not Sure what to Store !!!
    }

    @Override
    public void visit(Indexing node) {

    }

    @Override
    public void visit(Accessing node) {

    }

    @Override
    public void visit(Class_Method node) {
        VISIT(node.body);
        //WARNING: Class Method might have the same name as Function!!
        Function func = FuncMap.get(node.Func_Name);
        Register reg = new VirtualReg(null);
        Call_Inst inst = new Call_Inst(curBlk, func, reg);

        inst.ArgLocs.add(node.body.datasrc); //ADD this ??
        for(Expression X: node.params){
            VISIT(X);
            inst.ArgLocs.add(X.datasrc);
        }
        curBlk.add(inst);
        node.datasrc = reg;
    }

    @Override
    public void visit(Function_call node) {
        VISIT(node.body);
        Function func = FuncMap.get(node.name);
        Register reg = new VirtualReg(null);
        Call_Inst inst = new Call_Inst(curBlk, func, reg);

        for(Expression X: node.params){
            VISIT(X);
            inst.ArgLocs.add(X.datasrc);
        }
        curBlk.add(inst);
        node.datasrc = reg;
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
    public void visit(Array_Type node) {

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
