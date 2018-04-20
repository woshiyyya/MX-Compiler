package XYXCompiler.Builder;
import XYXCompiler.ASTNode.*;
import XYXCompiler.ASTNode.Declaration.*;
import XYXCompiler.ASTNode.Expression.Binary_Expression;
import XYXCompiler.ASTNode.Expression.Expression;
import XYXCompiler.ASTNode.Expression.Primitive.*;
import XYXCompiler.ASTNode.Expression.Suffix.*;
import XYXCompiler.ASTNode.Expression.Unary_Expression;
import XYXCompiler.ASTNode.Statement.*;
import XYXCompiler.ASTNode.Node;
import XYXCompiler.ASTNode.Type.*;
import XYXCompiler.Parser.*;
import XYXCompiler.Semantic.Symbol.Symbol;
import static XYXCompiler.ASTNode.Expression.Unary_Expression.UnaryOP;
import static XYXCompiler.ASTNode.Expression.Binary_Expression.BinaryOP;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class ASTBuilder extends XYXBaseListener{
    private ParseTreeProperty<Node> tag = new ParseTreeProperty<>();
    public ASTRoot Root = new ASTRoot();

    public void setPosition(Node node, ParserRuleContext ctx){
        node.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public void exitProgram(XYXParser.ProgramContext ctx) {
        for(XYXParser.DeclarationContext X : ctx.declaration()){
            Node node = tag.get(X);
            Root.declarations.add((Declaration) node);
            //System.out.println("Declaration:" + X.getClass() + "  " + X.getText());
        }
    }

    @Override
    public void exitDeclaration(XYXParser.DeclarationContext ctx) {
        tag.put(ctx, tag.get(ctx.children.get(0)));
        //for(ParseTree X: ctx.children)
        //    System.out.println("Declaration:" + X.getClass() + "  " + X.getText());
    }

    @Override
    public void exitFunctionDeclaration(XYXParser.FunctionDeclarationContext ctx) {
        Function_Declaration node = new Function_Declaration();
        node.name = ctx.Identifier().getText();
        node.symbol = new Symbol(ctx.Identifier().getText());
        node.returntype = (Base_Type) tag.get(ctx.type());
        node.body = (Compound_Statement) tag.get(ctx.compoundStatement());
        for(XYXParser.VariableDeclarationContext X:ctx.variableDeclaration()){
            node.params.add((Variable_Declaration) tag.get(X));
        }
        node.updateType();
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    // It is an ugly implementation
    private Declaration FabricateThisMember(String name){
        Variable_Declaration THIS = new Variable_Declaration(new Class_Type(name), "this");
        return THIS;
    }

    @Override
    public void exitClassDeclaration(XYXParser.ClassDeclarationContext ctx) {
        Class_Declaration node = new Class_Declaration();
        node.name = ctx.Identifier().getText();
        node.symbol = new Symbol(ctx.Identifier().getText());

        //Manually Add a 'this'
        node.Members.add(FabricateThisMember(node.name));

        for(XYXParser.ClassMembersContext X: ctx.classMembers()){
            node.Members.add((Declaration) tag.get(X));
        }
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitClassMembers(XYXParser.ClassMembersContext ctx) {
        if(ctx.children.get(0) != null)
            tag.put(ctx, tag.get(ctx.children.get(0)));
    }

    @Override
    public void exitConstructFunctionDeclaration(XYXParser.ConstructFunctionDeclarationContext ctx) {
        Construct_Function node = new Construct_Function();
        node.name = ctx.Identifier().getText();
        node.body = (Compound_Statement) tag.get(ctx.compoundStatement());
        for(XYXParser.VariableDeclarationContext X: ctx.variableDeclaration())
            node.params.add((Variable_Declaration) tag.get(X));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitGlobalVariableDeclaration(XYXParser.GlobalVariableDeclarationContext ctx) {
        Global_Variable_Declaration node = new Global_Variable_Declaration();
        node.type = (Base_Type) tag.get(ctx.type());
        node.name = ctx.Identifier().getText();
        node.symbol = new Symbol(ctx.Identifier().getText());
        node.symbol.SetType(node.type);
        node.RHS = (Expression) tag.get(ctx.expression());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override //VariableDeclaration (Only for Parameter List & Class Member Declaration)
    public void exitVariableDeclaration(XYXParser.VariableDeclarationContext ctx) {
        Variable_Declaration node = new Variable_Declaration();
        node.type = (Base_Type) tag.get(ctx.type());
        node.name = ctx.Identifier().getText();
        node.symbol = new Symbol(ctx.Identifier().getText());
        node.symbol.SetType(node.type);
        node.RHS = (Expression) tag.get(ctx.expression());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitPrimaryExpression(XYXParser.PrimaryExpressionContext ctx) {
        if(ctx.type() != null){
            Newexpr node = new Newexpr((Base_Type) tag.get(ctx.type()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else if(ctx.expression() != null){
            tag.put(ctx, tag.get(ctx.expression()));
        }else if(ctx.constant() != null){
            tag.put(ctx, tag.get(ctx.children.get(0)));
        }else if(ctx.Identifier() != null){
            ID node =  new ID(ctx.Identifier().getText());
            setPosition(node, ctx);
            tag.put(ctx, node);
        }
    }

    @Override
    public void exitConstant(XYXParser.ConstantContext ctx) {
        if(ctx.True() != null){
            Bool node = new Bool(true);
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else if(ctx.False() != null){
            Bool node = new Bool(false);
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else if(ctx.IntegerConstant() != null){
            INT node = new INT(ctx.IntegerConstant().getText());
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else if(ctx.StringConstant() != null){
            STRING node = new STRING(ctx.StringConstant().getText());
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else if(ctx.Null() != null){
            Null node = new Null();
            setPosition(node, ctx);
            tag.put(ctx, node);
        }
    }

    @Override
    public void exitSuffixExpression(XYXParser.SuffixExpressionContext ctx) {
        if(ctx.primaryExpression() != null){
            tag.put(ctx, tag.get(ctx.primaryExpression()));
        }else{
            if(ctx.Dot() != null){
                Accessing node = new Accessing();
                setPosition(node, ctx);
                node.body = (Expression) tag.get(ctx.suffixExpression());
                node.components =  ctx.Identifier().getText();
                tag.put(ctx, node);
            }else if(ctx.RightBracket() != null){
                Indexing node = new Indexing();
                setPosition(node, ctx);
                node.index = (Expression) tag.get(ctx.expression().get(0));
                node.name = (Expression) tag.get(ctx.suffixExpression());
                tag.put(ctx, node);
            }else if(ctx.RightParen() != null){  //Construct Function???
                Node body = tag.get(ctx.suffixExpression());
                if (body instanceof ID){
                    Function_call node = new Function_call();
                    setPosition(node, ctx);
                    node.name = ((ID) body).name;
                    node.body = (ID) body;
                    for(XYXParser.ExpressionContext X: ctx.expression()){
                        node.params.add((Expression) tag.get(X));
                    }
                    tag.put(ctx, node);
                }else{
                    Class_Method node = new Class_Method();
                    setPosition(node, ctx);
                    node.body = (Expression) tag.get(ctx.suffixExpression());
                    for(XYXParser.ExpressionContext X: ctx.expression()){
                        node.params.add((Expression) tag.get(X));
                    }
                    tag.put(ctx, node);
                }
            }else if(ctx.PlusPlus() != null){
                Self_Increasing node = new Self_Increasing();
                setPosition(node, ctx);
                node.body = (Expression) tag.get(ctx.suffixExpression());
                tag.put(ctx, node);
            }else if(ctx.MinusMinus() != null){
                Self_Decreasing node = new Self_Decreasing();
                setPosition(node, ctx);
                node.body = (Expression) tag.get(ctx.suffixExpression());
                tag.put(ctx, node);
            }
        }
    }

    @Override
    public void exitUnaryExpression(XYXParser.UnaryExpressionContext ctx) {
        UnaryOP op = null;
        if(ctx.PlusPlus() != null)
            op = UnaryOP.PlusPlus;
        else if(ctx.MinusMinus() != null)
            op = UnaryOP.MinusMinus;
        else if(ctx.Tilde() != null)
            op = UnaryOP.Tilde;
        else if(ctx.Not() != null)
            op = UnaryOP.Not;
        else if(ctx.Plus() != null)
            op = UnaryOP.Plus;
        else if(ctx.Minus() != null)
            op = UnaryOP.Minus;
        else{
            tag.put(ctx, tag.get(ctx.suffixExpression()));
            return;
        }
        Unary_Expression node = new Unary_Expression(op, (Expression) tag.get(ctx.suffixExpression()));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitMultiplicativeExpression(XYXParser.MultiplicativeExpressionContext ctx) {
        BinaryOP op = null;
        if(ctx.Mul() != null)
            op = BinaryOP.Mul;
        else if(ctx.Div() != null)
            op = BinaryOP.Div;
        else if(ctx.Mod() != null)
            op = BinaryOP.Mod;
        else{
            tag.put(ctx, tag.get(ctx.unaryExpression()));
            return;
        }

        Binary_Expression node = new Binary_Expression(op,
                (Expression) tag.get(ctx.multiplicativeExpression()),
                (Expression) tag.get(ctx.unaryExpression()));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitAdditiveExpression(XYXParser.AdditiveExpressionContext ctx) {
        BinaryOP op = null;
        if(ctx.Plus() != null)
            op = BinaryOP.Plus;
        else if(ctx.Minus() != null)
            op = BinaryOP.Minus;
        else{
            tag.put(ctx, tag.get(ctx.multiplicativeExpression()));
            return;
        }

        Binary_Expression node = new Binary_Expression(op,
                (Expression) tag.get(ctx.additiveExpression()),
                (Expression) tag.get(ctx.multiplicativeExpression()));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitShiftExpression(XYXParser.ShiftExpressionContext ctx) {
        BinaryOP op = null;
        if(ctx.LeftShift() != null)
            op = BinaryOP.LeftShift;
        else if(ctx.RightShift() != null)
            op = BinaryOP.RightShift;
        else{
            tag.put(ctx, tag.get(ctx.additiveExpression()));
            return;
        }

        Binary_Expression node =  new Binary_Expression(op,
                (Expression) tag.get(ctx.shiftExpression()),
                (Expression) tag.get(ctx.additiveExpression()));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitRelationExpression(XYXParser.RelationExpressionContext ctx) {
        BinaryOP op = null;
        if(ctx.Less() != null)
            op = BinaryOP.Less;
        else if(ctx.LessEqual() != null)
            op = BinaryOP.LessEqual;
        else if(ctx.Greater() != null)
            op = BinaryOP.Greater;
        else if(ctx.GreaterEqual() != null)
            op = BinaryOP.GreaterEqual;
        else{
            tag.put(ctx, tag.get(ctx.shiftExpression()));
            return;
        }

        Binary_Expression node = new Binary_Expression(op,
                (Expression) tag.get(ctx.relationExpression()),
                (Expression) tag.get(ctx.shiftExpression()));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitEqualityExpression(XYXParser.EqualityExpressionContext ctx) {
        BinaryOP op = null;
        if(ctx.Equal() != null)
            op = BinaryOP.Equal;
        else if(ctx.NotEqual() != null)
            op = BinaryOP.NotEqual;
        else{
            tag.put(ctx, tag.get(ctx.relationExpression()));
            return;
        }


        Binary_Expression node = new Binary_Expression(op,
                (Expression) tag.get(ctx.equalityExpression()),
                (Expression) tag.get(ctx.relationExpression()));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitBitwiseAndExpression(XYXParser.BitwiseAndExpressionContext ctx) {
        if(ctx.bitwiseAndExpression() != null){
            Binary_Expression node = new Binary_Expression(BinaryOP.BitAnd,
                    (Expression) tag.get(ctx.bitwiseAndExpression()),
                    (Expression) tag.get(ctx.equalityExpression()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else
            tag.put(ctx, tag.get(ctx.equalityExpression()));
    }

    @Override
    public void exitBitwiseExclusiveOrExpression(XYXParser.BitwiseExclusiveOrExpressionContext ctx) {
        if(ctx.bitwiseExclusiveOrExpression() != null){
            Binary_Expression node =  new Binary_Expression(BinaryOP.BitXor,
                    (Expression) tag.get(ctx.bitwiseExclusiveOrExpression()),
                    (Expression) tag.get(ctx.bitwiseAndExpression()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else
            tag.put(ctx, tag.get(ctx.bitwiseAndExpression()));
    }

    @Override
    public void exitBitwiseInclusiveOrExpression(XYXParser.BitwiseInclusiveOrExpressionContext ctx) {
        if(ctx.bitwiseInclusiveOrExpression() != null){
            Binary_Expression node = new Binary_Expression(BinaryOP.BitOr,
                    (Expression) tag.get(ctx.bitwiseInclusiveOrExpression()),
                    (Expression) tag.get(ctx.bitwiseExclusiveOrExpression()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else
            tag.put(ctx, tag.get(ctx.bitwiseExclusiveOrExpression()));
    }

    @Override
    public void exitLogicalAndExpression(XYXParser.LogicalAndExpressionContext ctx) {
        if(ctx.logicalAndExpression() != null){
            Binary_Expression node =  new Binary_Expression(BinaryOP.LogicalAnd,
                    (Expression) tag.get(ctx.logicalAndExpression()),
                    (Expression) tag.get(ctx.bitwiseInclusiveOrExpression()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else
            tag.put(ctx, tag.get(ctx.bitwiseInclusiveOrExpression()));
    }

    @Override
    public void exitLogicalOrExpression(XYXParser.LogicalOrExpressionContext ctx) {
        if(ctx.logicalOrExpression() != null){
            Binary_Expression node = new Binary_Expression(BinaryOP.LogicalOr,
                    (Expression) tag.get(ctx.logicalOrExpression()),
                    (Expression) tag.get(ctx.logicalAndExpression()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else
            tag.put(ctx, tag.get(ctx.logicalAndExpression()));
    }

    @Override
    public void exitAssignmentExpression(XYXParser.AssignmentExpressionContext ctx) {
        if(ctx.unaryExpression() != null){
            Binary_Expression node = new Binary_Expression(BinaryOP.Assign,
                    (Expression) tag.get(ctx.unaryExpression()),
                    (Expression) tag.get(ctx.assignmentExpression()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else
            tag.put(ctx, tag.get(ctx.logicalOrExpression()));
    }

    @Override
    public void exitExpression(XYXParser.ExpressionContext ctx) {
        tag.put(ctx, tag.get(ctx.assignmentExpression()));
    }

    @Override
    public void exitStatement(XYXParser.StatementContext ctx) {
        tag.put(ctx, tag.get(ctx.children.get(0)));
    }

    @Override
    public void exitCompoundStatement(XYXParser.CompoundStatementContext ctx) {
        Compound_Statement node = new Compound_Statement();
        for(XYXParser.StatementContext X:ctx.statement()){
            node.stmts.add((Statement) tag.get(X));
        }
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitExpressionStatement(XYXParser.ExpressionStatementContext ctx) {
        Expression_Statement node = new Expression_Statement();
        if(ctx.expression() != null)
            node.body = (Expression) tag.get(ctx.expression());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitSelectionStatement(XYXParser.SelectionStatementContext ctx) {
        Selection_Statement node = new Selection_Statement();
        node.condition = (Expression) tag.get(ctx.expression());
        node.body = (Statement) tag.get(ctx.statement().get(0));
        if(ctx.Else() != null)
            node.Else_body = (Statement) tag.get(ctx.statement().get(1));
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitWhileLoop(XYXParser.WhileLoopContext ctx) {
        While_Statement node = new While_Statement();
        node.condition = (Expression) tag.get(ctx.expression());
        node.body = (Statement) tag.get(ctx.statement());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitForLoop(XYXParser.ForLoopContext ctx) {
        For_Statement node = new For_Statement();
        if(ctx.init() != null)
            node.init = (Expression) tag.get(ctx.init().expression());
        if(ctx.condition() != null)
            node.condition = (Expression) tag.get(ctx.condition().expression());
        if(ctx.step() != null)
            node.update = (Expression) tag.get(ctx.step().expression());
        node.body = (Statement) tag.get(ctx.statement());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitBreakStatement(XYXParser.BreakStatementContext ctx) {
        Break node = new Break();
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitContinueStatement(XYXParser.ContinueStatementContext ctx) {
        Continue node = new Continue();
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitReturnStatement(XYXParser.ReturnStatementContext ctx) {
        if(tag.get(ctx.expression()) != null){
            Return node = new Return((Expression)tag.get(ctx.expression()));
            setPosition(node, ctx);
            tag.put(ctx, node);
        }else{
            Return node = new Return();
            setPosition(node, ctx);
            tag.put(ctx, node);
        }
    }

    @Override
    public void exitVariableDeclarationStatement(XYXParser.VariableDeclarationStatementContext ctx) {
        Variable_Declaration_Statement node = new Variable_Declaration_Statement();
        node.type = (Base_Type) tag.get(ctx.type());
        node.name = ctx.Identifier().getText();
        node.symbol = new Symbol(ctx.Identifier().getText());
        node.symbol.SetType(node.type);
        if(ctx.expression() != null)
            node.RHS = (Expression) tag.get(ctx.expression());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitVoidType(XYXParser.VoidTypeContext ctx) {
        Void_Type node = new Void_Type();
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitIntType(XYXParser.IntTypeContext ctx) {
        Int_Type node = new Int_Type();
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitStringType(XYXParser.StringTypeContext ctx) {
        String_Type node = new String_Type();
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitBoolType(XYXParser.BoolTypeContext ctx) {
        Bool_Type node = new Bool_Type();
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitIdType(XYXParser.IdTypeContext ctx) {
        Class_Type node = new Class_Type(ctx.getText());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }

    @Override
    public void exitArrayType(XYXParser.ArrayTypeContext ctx) {
        Array_Type node = new Array_Type();
        node.basetype = (Base_Type) tag.get(ctx.type());
        if(ctx.expression() != null)
            node.size = (Expression) tag.get(ctx.expression());
        setPosition(node, ctx);
        tag.put(ctx, node);
    }
}
