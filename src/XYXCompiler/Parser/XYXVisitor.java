// Generated from XYX.g4 by ANTLR 4.7.1
package XYXCompiler.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XYXParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XYXVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XYXParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(XYXParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(XYXParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VoidType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidType(XYXParser.VoidTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(XYXParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(XYXParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(XYXParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(XYXParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdType}
	 * labeled alternative in {@link XYXParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdType(XYXParser.IdTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(XYXParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#globalVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalVariableDeclaration(XYXParser.GlobalVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(XYXParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(XYXParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#constructFunctionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructFunctionDeclaration(XYXParser.ConstructFunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(XYXParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(XYXParser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#suffixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixExpression(XYXParser.SuffixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(XYXParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(XYXParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(XYXParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(XYXParser.ShiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#relationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationExpression(XYXParser.RelationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(XYXParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#bitwiseAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseAndExpression(XYXParser.BitwiseAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#bitwiseExclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseExclusiveOrExpression(XYXParser.BitwiseExclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#bitwiseInclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseInclusiveOrExpression(XYXParser.BitwiseInclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(XYXParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(XYXParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(XYXParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(XYXParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(XYXParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(XYXParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(XYXParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(XYXParser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link XYXParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(XYXParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link XYXParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(XYXParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(XYXParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(XYXParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#step}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStep(XYXParser.StepContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(XYXParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(XYXParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link XYXParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(XYXParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XYXParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStatement(XYXParser.VariableDeclarationStatementContext ctx);
}