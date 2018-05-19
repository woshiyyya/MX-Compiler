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
import XYXCompiler.XIR.CFG.BasicBlock;
import XYXCompiler.XIR.CFG.Function;
import XYXCompiler.XIR.CFG.XIRRoot;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.BinaryOp_Inst.*;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.RelationOp_Inst.CmpOp;
import XYXCompiler.XIR.Instruction.Arithmatic.UnaryOp_Inst;
import XYXCompiler.XIR.Instruction.Arithmatic.UnaryOp_Inst.unaryop;
import XYXCompiler.XIR.Instruction.Control.CMP;
import XYXCompiler.XIR.Instruction.Functional.Call_Inst;
import XYXCompiler.XIR.Instruction.Functional.Return_Inst;
import XYXCompiler.XIR.Instruction.Memory.Alloc_Inst;
import XYXCompiler.XIR.Instruction.Memory.Load_Inst;
import XYXCompiler.XIR.Instruction.Memory.Move_Inst;
import XYXCompiler.XIR.Instruction.Memory.Store_Inst;
import XYXCompiler.XIR.Operand.DataSrc;
import XYXCompiler.XIR.Operand.Static.Immediate;
import XYXCompiler.XIR.Operand.Register.Register;
import XYXCompiler.XIR.Operand.Static.IntLiteral;
import XYXCompiler.XIR.Operand.Static.Literal;
import XYXCompiler.XIR.Operand.Static.StaticData;
import XYXCompiler.XIR.Operand.Register.VirtualReg;
import XYXCompiler.XIR.Operand.Static.StringLiteral;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
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
    public Boolean curNeedAddr = false;
    public Boolean preNeedAddr = false;
    public Register NewDest = null;
    public Map<String, Function> FuncMap = new HashMap<>();
    public List<Array_Type> NewDimList = new LinkedList<>();
    public TypeTable typeTable = null;

    private int PointerSize = 8;

    public XIRBuilder(TypeTable typeTable) {
        this.typeTable = typeTable;
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

    private void MergeCircuit(Binary_Expression node){
        // Merge the Short Circuit BBs for Expr Value only
        BasicBlock MergeBlk = new BasicBlock(curFunc);
        node.datasrc = new VirtualReg(null);

        node.ifTrue.add(new Move_Inst(curBlk, node.datasrc, new Immediate(1)));
        node.ifFalse.add(new Move_Inst(curBlk, node.datasrc, new Immediate(0)));

        node.ifTrue.Close_J(MergeBlk);
        node.ifFalse.Close_J(MergeBlk);
        curBlk = MergeBlk;//Warning: the curBB has been changed
    }

    private void Construct_Assign(Binary_Expression node){
        if(ifLogical(node.rhs)){
            node.rhs.ifTrue = new BasicBlock(curFunc);
            node.rhs.ifFalse = new BasicBlock(curFunc);
        }

        VISIT(node.lhs);
        VISIT(node.rhs);

        if(node.lhs instanceof Accessing || node.lhs instanceof Indexing){
            curBlk.add(new Store_Inst(curBlk,node.lhs.baseaddr, node.lhs.offset, intsize, node.rhs.datasrc, intsize));
        }else{
            curBlk.add(new Move_Inst(curBlk, node.lhs.datasrc, node.rhs.datasrc));
        }
    }

    private void Construct_Comparation(Binary_Expression node){
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
            curBlk.cmp = new CMP(curBlk, node.lhs.datasrc, node.rhs.datasrc);
            curBlk.Close_B(null, node.ifTrue, node.ifFalse);
        }else{
            inst.L_operand = node.lhs.datasrc;
            inst.R_operand = node.rhs.datasrc;
            curBlk.add(inst);
            node.datasrc = inst.dest;
        }
    }

    private void Construct_Arithmatic(Binary_Expression node){
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
        inst.dest = new VirtualReg(null);
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

    private void enterMem(){
        preNeedAddr = curNeedAddr;
        curNeedAddr = false;
    }

    private void exitMem(){
        curNeedAddr = preNeedAddr;
    }

    private boolean hasBranch(Expression node){
        return node.ifFalse != null;
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
        if(hasBranch(node)){
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
        Immediate RHS = new Immediate(1);
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
                Construct_ShortCircuit(node);
                MergeCircuit(node);
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
    }

    @Override
    public void visit(STRING node) {
        node.datasrc = new StringLiteral(node.value);
    }

    private void getDim(Array_Type t, List<Integer> dim){
        Base_Type type = t;
        while(type instanceof Array_Type){
            Expression temDim = ((Array_Type) type).size;
            int value = ((IntLiteral)((INT)temDim).datasrc).value;
            dim.add(value);
            type = ((Array_Type) type).basetype;
        }
    }

    private void Alloc_Heap(Register baseaddr, int pos, int basetypesize, List<DataSrc> dim, int level){
        if(dim.get(level) == null)
            return;
        /*
        #compute offset
            lea     rdx, [rax*8]

        #load baseaddr:
            mov     rax, qword [rbp-20H]

        #compute baseaddr + offset
            lea     rbx, [rdx+rax]

        #load size 3*4
            mov     edi, 12
            call    _Znam

        #save array baseaddr into pointer addr
            mov     qword [rbx], rax
        */

        int typesize = level == dim.size()-1 ? basetypesize : PointerSize;
        Immediate offset = new Immediate(pos);
        Register spacesize = new VirtualReg("__SpaceSize__");
        Register blockaddr = new VirtualReg("__blockaddr__");

        // compute the size of block we want to alloc
        curBlk.add(new BinaryOp_Inst(curBlk, spacesize, dim.get(level), new Immediate(typesize), binaryop.Mul));

        // Alloc
        curBlk.add(new Alloc_Inst(curBlk, blockaddr, spacesize));

        // Write back
        curBlk.add(new Store_Inst(curBlk, baseaddr, offset, PointerSize, blockaddr, PointerSize));
    }

    private void Construct_Forloop(DataSrc baseaddr, DataSrc thisdim, int level){
        //NewDimList.size() - level
        Array_Type now = NewDimList.get(NewDimList.size() - level);
        if(now == null)
            return;

        DataSrc nextdim = now.size.datasrc;
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
        Register cnt = new VirtualReg("cnt");
        Register flag = new VirtualReg("flag");
        Register size = new VirtualReg("size");
        Register addr = new VirtualReg("addr");

        //----construct init block
        curBlk.add(new Move_Inst(curBlk, cnt, new Immediate(0)));
        curBlk.Close_J(ForCond);

        //------construct condition block
        curBlk = ForCond;
        curBlk.add(new RelationOp_Inst(curBlk, flag, cnt, thisdim, CmpOp.LS));
        curBlk.Close_B(flag, ForBody, ForAfter);

        //------construct body block
        curBlk = ForBody;

        curBlk.add(new BinaryOp_Inst(curBlk, size, nextdim, new Immediate(PointerSize), binaryop.Mul));

        curBlk.add(new Alloc_Inst(curBlk, addr, size));
        fuck here, the first?
        curBlk.add(new Store_Inst(curBlk, baseaddr, cnt, PointerSize, addr, PointerSize));

        if(level < NewDimList.size())
            Construct_Forloop(addr, nextdim, level + 1);

        curBlk.Close_J(ForUpdate);

        //-------construct update block
        curBlk = ForUpdate;
        curBlk.add(new BinaryOp_Inst(curBlk, cnt, cnt, new Immediate(1), binaryop.Add));
        curBlk.Close_J(ForCond);

        //-------shift to after block
        exitLoop();
        curBlk = ForAfter;
    }

    @Override
    public void visit(Array_Type node) {
        VISIT(node.size);
        VISIT(node.basetype);
        NewDimList.add(node);
    }

    @Override
    public void visit(Newexpr node) {

        if(node.type instanceof Array_Type){
            NewDimList.clear();
            VISIT(node.type);
            node.datasrc = new VirtualReg("new");
            Construct_Forloop(node.datasrc, new Immediate(1), 1);
        }else{
            int size = ((Class_Type)node.type).getSize();
            node.datasrc = new VirtualReg(null);
            curBlk.add(new Alloc_Inst(curBlk, (Register)node.datasrc, new Immediate(size)));
        }
    }

    @Override
    public void visit(Indexing node) {
        VISIT(node.index);
        enterMem();
        VISIT(node.name);
        exitMem();
        if(curNeedAddr){
            node.baseaddr = node.name.datasrc;
            node.offset = node.index.datasrc;
        }else{
            node.datasrc = new VirtualReg(null);
            curBlk.add(new Load_Inst(curBlk, (Register)node.datasrc, node.name.datasrc, node.index.datasrc, intsize));
            if(hasBranch(node)){
                curBlk.Close_B(node.datasrc, node.ifTrue, node.ifFalse);
            }
        }
    }

    @Override
    public void visit(Accessing node) {
        enterMem();
        VISIT(node.body);
        exitMem();

        Class_Type class_type = typeTable.ClassTypeTable.get(((Class_Type)(node.body.type)).name);
        int offset = class_type.getOffset(node.components);

        if(curNeedAddr){
            node.baseaddr = node.body.datasrc;
            node.offset = new Immediate(offset);
        }else{
            node.datasrc = new VirtualReg(null);
            curBlk.add(new Load_Inst(curBlk, (Register)node.datasrc, node.body.datasrc, new Immediate(offset),intsize));
            if(hasBranch(node)){
                curBlk.Close_B(node.datasrc, node.ifTrue, node.ifFalse);
            }
        }
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
    public void visit(Base_Type node) {

    }

    @Override
    public void visit(Statement node) {

    }

    @Override
    public void visit(Expression node) {

    }
}
