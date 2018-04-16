// Generated from XYX.g4 by ANTLR 4.7.1
package XYXCompiler.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XYXParser}.
 */
public interface XYXListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XYXParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(XYXParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(XYXParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(XYXParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(XYXParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void enterVoidType(XYXParser.VoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void exitVoidType(XYXParser.VoidTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(XYXParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(XYXParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(XYXParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(XYXParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(XYXParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(XYXParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(XYXParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(XYXParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIdType(XYXParser.IdTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIdType(XYXParser.IdTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(XYXParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(XYXParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#globalVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariableDeclaration(XYXParser.GlobalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#globalVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariableDeclaration(XYXParser.GlobalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(XYXParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(XYXParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(XYXParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(XYXParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#constructFunctionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructFunctionDeclaration(XYXParser.ConstructFunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#constructFunctionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructFunctionDeclaration(XYXParser.ConstructFunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(XYXParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(XYXParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(XYXParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(XYXParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#suffixExpression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixExpression(XYXParser.SuffixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#suffixExpression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixExpression(XYXParser.SuffixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(XYXParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(XYXParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(XYXParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(XYXParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(XYXParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(XYXParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(XYXParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(XYXParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#relationExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationExpression(XYXParser.RelationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#relationExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationExpression(XYXParser.RelationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(XYXParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(XYXParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#bitwiseAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseAndExpression(XYXParser.BitwiseAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#bitwiseAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseAndExpression(XYXParser.BitwiseAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#bitwiseExclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseExclusiveOrExpression(XYXParser.BitwiseExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#bitwiseExclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseExclusiveOrExpression(XYXParser.BitwiseExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#bitwiseInclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseInclusiveOrExpression(XYXParser.BitwiseInclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#bitwiseInclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseInclusiveOrExpression(XYXParser.BitwiseInclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(XYXParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(XYXParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(XYXParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(XYXParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(XYXParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(XYXParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(XYXParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(XYXParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(XYXParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(XYXParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(XYXParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(XYXParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(XYXParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(XYXParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(XYXParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(XYXParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link XYXParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(XYXParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link XYXParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(XYXParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link XYXParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(XYXParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link XYXParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(XYXParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(XYXParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(XYXParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(XYXParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(XYXParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#step}.
	 * @param ctx the parse tree
	 */
	void enterStep(XYXParser.StepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#step}.
	 * @param ctx the parse tree
	 */
	void exitStep(XYXParser.StepContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(XYXParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(XYXParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(XYXParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(XYXParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(XYXParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(XYXParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XYXParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(XYXParser.VariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XYXParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(XYXParser.VariableDeclarationStatementContext ctx);
}