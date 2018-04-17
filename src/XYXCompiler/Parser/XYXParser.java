// Generated from XYX.g4 by ANTLR 4.7.1
package XYXCompiler.Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XYXParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, Bool=3, Int=4, String=5, Null=6, Void=7, True=8, False=9, 
		If=10, Else=11, For=12, While=13, Break=14, Continue=15, Return=16, New=17, 
		Class=18, Identifier=19, IntegerConstant=20, StringConstant=21, Plus=22, 
		Minus=23, Mul=24, Div=25, Mod=26, Less=27, Greater=28, Equal=29, NotEqual=30, 
		GreaterEqual=31, LessEqual=32, AndAnd=33, OrOr=34, Not=35, LeftShift=36, 
		RightShift=37, Tilde=38, Or=39, Xor=40, And=41, LeftParen=42, RightParen=43, 
		Assign=44, PlusPlus=45, MinusMinus=46, Dot=47, LeftBracket=48, RightBracket=49, 
		Colon=50, Semi=51, Comma=52, Question=53, WhiteSpace=54, NewLine=55, LineComment=56;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_type = 2, RULE_classDeclaration = 3, 
		RULE_globalVariableDeclaration = 4, RULE_variableDeclaration = 5, RULE_functionDeclaration = 6, 
		RULE_constructFunctionDeclaration = 7, RULE_constant = 8, RULE_primaryExpression = 9, 
		RULE_suffixExpression = 10, RULE_unaryExpression = 11, RULE_multiplicativeExpression = 12, 
		RULE_additiveExpression = 13, RULE_shiftExpression = 14, RULE_relationExpression = 15, 
		RULE_equalityExpression = 16, RULE_bitwiseAndExpression = 17, RULE_bitwiseExclusiveOrExpression = 18, 
		RULE_bitwiseInclusiveOrExpression = 19, RULE_logicalAndExpression = 20, 
		RULE_logicalOrExpression = 21, RULE_assignmentExpression = 22, RULE_expression = 23, 
		RULE_statement = 24, RULE_compoundStatement = 25, RULE_expressionStatement = 26, 
		RULE_selectionStatement = 27, RULE_iterationStatement = 28, RULE_init = 29, 
		RULE_condition = 30, RULE_step = 31, RULE_jumpStatement = 32, RULE_variableDeclarationStatement = 33;
	public static final String[] ruleNames = {
		"program", "declaration", "type", "classDeclaration", "globalVariableDeclaration", 
		"variableDeclaration", "functionDeclaration", "constructFunctionDeclaration", 
		"constant", "primaryExpression", "suffixExpression", "unaryExpression", 
		"multiplicativeExpression", "additiveExpression", "shiftExpression", "relationExpression", 
		"equalityExpression", "bitwiseAndExpression", "bitwiseExclusiveOrExpression", 
		"bitwiseInclusiveOrExpression", "logicalAndExpression", "logicalOrExpression", 
		"assignmentExpression", "expression", "statement", "compoundStatement", 
		"expressionStatement", "selectionStatement", "iterationStatement", "init", 
		"condition", "step", "jumpStatement", "variableDeclarationStatement"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'bool'", "'int'", "'string'", "'null'", "'void'", 
		"'true'", "'false'", "'if'", "'else'", "'for'", "'while'", "'break'", 
		"'continue'", "'return'", "'new'", "'class'", null, null, null, "'+'", 
		"'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'=='", "'!='", "'>='", "'<='", 
		"'&&'", "'||'", "'!'", "'<<'", "'>>'", "'~'", "'|'", "'^'", "'&'", "'('", 
		"')'", "'='", "'++'", "'--'", "'.'", "'['", "']'", "':'", "';'", "','", 
		"'?'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "Bool", "Int", "String", "Null", "Void", "True", "False", 
		"If", "Else", "For", "While", "Break", "Continue", "Return", "New", "Class", 
		"Identifier", "IntegerConstant", "StringConstant", "Plus", "Minus", "Mul", 
		"Div", "Mod", "Less", "Greater", "Equal", "NotEqual", "GreaterEqual", 
		"LessEqual", "AndAnd", "OrOr", "Not", "LeftShift", "RightShift", "Tilde", 
		"Or", "Xor", "And", "LeftParen", "RightParen", "Assign", "PlusPlus", "MinusMinus", 
		"Dot", "LeftBracket", "RightBracket", "Colon", "Semi", "Comma", "Question", 
		"WhiteSpace", "NewLine", "LineComment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "XYX.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XYXParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				declaration();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public GlobalVariableDeclarationContext globalVariableDeclaration() {
			return getRuleContext(GlobalVariableDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				functionDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				classDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				globalVariableDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VoidTypeContext extends TypeContext {
		public TerminalNode Void() { return getToken(XYXParser.Void, 0); }
		public VoidTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterVoidType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitVoidType(this);
		}
	}
	public static class ArrayTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LeftBracket() { return getToken(XYXParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(XYXParser.RightBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitArrayType(this);
		}
	}
	public static class BoolTypeContext extends TypeContext {
		public TerminalNode Bool() { return getToken(XYXParser.Bool, 0); }
		public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterBoolType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitBoolType(this);
		}
	}
	public static class StringTypeContext extends TypeContext {
		public TerminalNode String() { return getToken(XYXParser.String, 0); }
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitStringType(this);
		}
	}
	public static class IntTypeContext extends TypeContext {
		public TerminalNode Int() { return getToken(XYXParser.Int, 0); }
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitIntType(this);
		}
	}
	public static class IdTypeContext extends TypeContext {
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public IdTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterIdType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitIdType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_type, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bool:
				{
				_localctx = new BoolTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(79);
				match(Bool);
				}
				break;
			case Int:
				{
				_localctx = new IntTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				match(Int);
				}
				break;
			case String:
				{
				_localctx = new StringTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(String);
				}
				break;
			case Void:
				{
				_localctx = new VoidTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(Void);
				}
				break;
			case Identifier:
				{
				_localctx = new IdTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayTypeContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(86);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(87);
					match(LeftBracket);
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
						{
						setState(88);
						expression();
						}
					}

					setState(91);
					match(RightBracket);
					}
					} 
				}
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(XYXParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public List<VariableDeclarationStatementContext> variableDeclarationStatement() {
			return getRuleContexts(VariableDeclarationStatementContext.class);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement(int i) {
			return getRuleContext(VariableDeclarationStatementContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public List<ConstructFunctionDeclarationContext> constructFunctionDeclaration() {
			return getRuleContexts(ConstructFunctionDeclarationContext.class);
		}
		public ConstructFunctionDeclarationContext constructFunctionDeclaration(int i) {
			return getRuleContext(ConstructFunctionDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(Class);
			setState(98);
			match(Identifier);
			setState(99);
			match(T__0);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(103);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(100);
					variableDeclarationStatement();
					}
					break;
				case 2:
					{
					setState(101);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(102);
					constructFunctionDeclaration();
					}
					break;
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GlobalVariableDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GlobalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterGlobalVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitGlobalVariableDeclaration(this);
		}
	}

	public final GlobalVariableDeclarationContext globalVariableDeclaration() throws RecognitionException {
		GlobalVariableDeclarationContext _localctx = new GlobalVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_globalVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			type(0);
			setState(111);
			match(Identifier);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(112);
				match(Assign);
				setState(113);
				expression();
				}
			}

			setState(116);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitVariableDeclaration(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(118);
			type(0);
			setState(119);
			match(Identifier);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(120);
				match(Assign);
				setState(121);
				expression();
				}
			}

			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public TerminalNode Void() { return getToken(XYXParser.Void, 0); }
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitFunctionDeclaration(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_functionDeclaration);
		int _la;
		try {
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				type(0);
				setState(125);
				match(Identifier);
				setState(126);
				match(LeftParen);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
					{
					setState(127);
					variableDeclaration();
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==Comma) {
						{
						{
						setState(128);
						match(Comma);
						setState(129);
						variableDeclaration();
						}
						}
						setState(134);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(137);
				match(RightParen);
				setState(138);
				compoundStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				match(Void);
				setState(141);
				match(Identifier);
				setState(142);
				match(LeftParen);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
					{
					setState(143);
					variableDeclaration();
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==Comma) {
						{
						{
						setState(144);
						match(Comma);
						setState(145);
						variableDeclaration();
						}
						}
						setState(150);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(153);
				match(RightParen);
				setState(154);
				compoundStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructFunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public ConstructFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructFunctionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterConstructFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitConstructFunctionDeclaration(this);
		}
	}

	public final ConstructFunctionDeclarationContext constructFunctionDeclaration() throws RecognitionException {
		ConstructFunctionDeclarationContext _localctx = new ConstructFunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constructFunctionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(Identifier);
			setState(158);
			match(LeftParen);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(159);
				variableDeclaration();
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(160);
					match(Comma);
					setState(161);
					variableDeclaration();
					}
					}
					setState(166);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(169);
			match(RightParen);
			setState(170);
			compoundStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode True() { return getToken(XYXParser.True, 0); }
		public TerminalNode False() { return getToken(XYXParser.False, 0); }
		public TerminalNode IntegerConstant() { return getToken(XYXParser.IntegerConstant, 0); }
		public TerminalNode StringConstant() { return getToken(XYXParser.StringConstant, 0); }
		public TerminalNode Null() { return getToken(XYXParser.Null, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitConstant(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << IntegerConstant) | (1L << StringConstant))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(XYXParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(XYXParser.RightParen, 0); }
		public TerminalNode New() { return getToken(XYXParser.New, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitPrimaryExpression(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_primaryExpression);
		try {
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case True:
			case False:
			case IntegerConstant:
			case StringConstant:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				constant();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(Identifier);
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 3);
				{
				setState(176);
				match(LeftParen);
				setState(177);
				expression();
				setState(178);
				match(RightParen);
				}
				break;
			case New:
				enterOuterAlt(_localctx, 4);
				{
				setState(180);
				match(New);
				setState(181);
				type(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuffixExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public SuffixExpressionContext suffixExpression() {
			return getRuleContext(SuffixExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(XYXParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public TerminalNode LeftBracket() { return getToken(XYXParser.LeftBracket, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightBracket() { return getToken(XYXParser.RightBracket, 0); }
		public TerminalNode LeftParen() { return getToken(XYXParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(XYXParser.RightParen, 0); }
		public TerminalNode PlusPlus() { return getToken(XYXParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(XYXParser.MinusMinus, 0); }
		public SuffixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suffixExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterSuffixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitSuffixExpression(this);
		}
	}

	public final SuffixExpressionContext suffixExpression() throws RecognitionException {
		return suffixExpression(0);
	}

	private SuffixExpressionContext suffixExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SuffixExpressionContext _localctx = new SuffixExpressionContext(_ctx, _parentState);
		SuffixExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_suffixExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(185);
			primaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(212);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(187);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(188);
						match(Dot);
						setState(189);
						match(Identifier);
						}
						break;
					case 2:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(190);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(191);
						match(LeftBracket);
						setState(192);
						expression();
						setState(193);
						match(RightBracket);
						}
						break;
					case 3:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(195);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(196);
						match(LeftParen);
						setState(205);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
							{
							setState(197);
							expression();
							setState(202);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==Comma) {
								{
								{
								setState(198);
								match(Comma);
								setState(199);
								expression();
								}
								}
								setState(204);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(207);
						match(RightParen);
						}
						break;
					case 4:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(208);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(209);
						match(PlusPlus);
						}
						break;
					case 5:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(210);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(211);
						match(MinusMinus);
						}
						break;
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public SuffixExpressionContext suffixExpression() {
			return getRuleContext(SuffixExpressionContext.class,0);
		}
		public TerminalNode PlusPlus() { return getToken(XYXParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(XYXParser.MinusMinus, 0); }
		public TerminalNode Tilde() { return getToken(XYXParser.Tilde, 0); }
		public TerminalNode Not() { return getToken(XYXParser.Not, 0); }
		public TerminalNode Plus() { return getToken(XYXParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(XYXParser.Minus, 0); }
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitUnaryExpression(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_unaryExpression);
		try {
			setState(230);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case True:
			case False:
			case New:
			case Identifier:
			case IntegerConstant:
			case StringConstant:
			case LeftParen:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				suffixExpression(0);
				}
				break;
			case PlusPlus:
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(PlusPlus);
				setState(219);
				suffixExpression(0);
				}
				break;
			case MinusMinus:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				match(MinusMinus);
				setState(221);
				suffixExpression(0);
				}
				break;
			case Tilde:
				enterOuterAlt(_localctx, 4);
				{
				setState(222);
				match(Tilde);
				setState(223);
				suffixExpression(0);
				}
				break;
			case Not:
				enterOuterAlt(_localctx, 5);
				{
				setState(224);
				match(Not);
				setState(225);
				suffixExpression(0);
				}
				break;
			case Plus:
				enterOuterAlt(_localctx, 6);
				{
				setState(226);
				match(Plus);
				setState(227);
				suffixExpression(0);
				}
				break;
			case Minus:
				enterOuterAlt(_localctx, 7);
				{
				setState(228);
				match(Minus);
				setState(229);
				suffixExpression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public TerminalNode Mul() { return getToken(XYXParser.Mul, 0); }
		public TerminalNode Div() { return getToken(XYXParser.Div, 0); }
		public TerminalNode Mod() { return getToken(XYXParser.Mod, 0); }
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitMultiplicativeExpression(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		return multiplicativeExpression(0);
	}

	private MultiplicativeExpressionContext multiplicativeExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, _parentState);
		MultiplicativeExpressionContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_multiplicativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(233);
			unaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(246);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(244);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(235);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(236);
						match(Mul);
						setState(237);
						unaryExpression();
						}
						break;
					case 2:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(238);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(239);
						match(Div);
						setState(240);
						unaryExpression();
						}
						break;
					case 3:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(241);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(242);
						match(Mod);
						setState(243);
						unaryExpression();
						}
						break;
					}
					} 
				}
				setState(248);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public TerminalNode Plus() { return getToken(XYXParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(XYXParser.Minus, 0); }
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitAdditiveExpression(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		return additiveExpression(0);
	}

	private AdditiveExpressionContext additiveExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, _parentState);
		AdditiveExpressionContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_additiveExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(250);
			multiplicativeExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(260);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(258);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(252);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(253);
						match(Plus);
						setState(254);
						multiplicativeExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(255);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(256);
						match(Minus);
						setState(257);
						multiplicativeExpression(0);
						}
						break;
					}
					} 
				}
				setState(262);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ShiftExpressionContext extends ParserRuleContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public ShiftExpressionContext shiftExpression() {
			return getRuleContext(ShiftExpressionContext.class,0);
		}
		public TerminalNode LeftShift() { return getToken(XYXParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(XYXParser.RightShift, 0); }
		public ShiftExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterShiftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitShiftExpression(this);
		}
	}

	public final ShiftExpressionContext shiftExpression() throws RecognitionException {
		return shiftExpression(0);
	}

	private ShiftExpressionContext shiftExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ShiftExpressionContext _localctx = new ShiftExpressionContext(_ctx, _parentState);
		ShiftExpressionContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_shiftExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(264);
			additiveExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(274);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(272);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new ShiftExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shiftExpression);
						setState(266);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(267);
						match(LeftShift);
						setState(268);
						additiveExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new ShiftExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shiftExpression);
						setState(269);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(270);
						match(RightShift);
						setState(271);
						additiveExpression(0);
						}
						break;
					}
					} 
				}
				setState(276);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class RelationExpressionContext extends ParserRuleContext {
		public ShiftExpressionContext shiftExpression() {
			return getRuleContext(ShiftExpressionContext.class,0);
		}
		public RelationExpressionContext relationExpression() {
			return getRuleContext(RelationExpressionContext.class,0);
		}
		public TerminalNode Less() { return getToken(XYXParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(XYXParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(XYXParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(XYXParser.GreaterEqual, 0); }
		public RelationExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterRelationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitRelationExpression(this);
		}
	}

	public final RelationExpressionContext relationExpression() throws RecognitionException {
		return relationExpression(0);
	}

	private RelationExpressionContext relationExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationExpressionContext _localctx = new RelationExpressionContext(_ctx, _parentState);
		RelationExpressionContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_relationExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(278);
			shiftExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(294);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(292);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(280);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(281);
						match(Less);
						setState(282);
						shiftExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(283);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(284);
						match(LessEqual);
						setState(285);
						shiftExpression(0);
						}
						break;
					case 3:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(286);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(287);
						match(Greater);
						setState(288);
						shiftExpression(0);
						}
						break;
					case 4:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(289);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(290);
						match(GreaterEqual);
						setState(291);
						shiftExpression(0);
						}
						break;
					}
					} 
				}
				setState(296);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public RelationExpressionContext relationExpression() {
			return getRuleContext(RelationExpressionContext.class,0);
		}
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public TerminalNode Equal() { return getToken(XYXParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(XYXParser.NotEqual, 0); }
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitEqualityExpression(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		return equalityExpression(0);
	}

	private EqualityExpressionContext equalityExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, _parentState);
		EqualityExpressionContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_equalityExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(298);
			relationExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(308);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(306);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new EqualityExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(300);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(301);
						match(Equal);
						setState(302);
						relationExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new EqualityExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(303);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(304);
						match(NotEqual);
						setState(305);
						relationExpression(0);
						}
						break;
					}
					} 
				}
				setState(310);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BitwiseAndExpressionContext extends ParserRuleContext {
		public EqualityExpressionContext equalityExpression() {
			return getRuleContext(EqualityExpressionContext.class,0);
		}
		public BitwiseAndExpressionContext bitwiseAndExpression() {
			return getRuleContext(BitwiseAndExpressionContext.class,0);
		}
		public BitwiseAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterBitwiseAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitBitwiseAndExpression(this);
		}
	}

	public final BitwiseAndExpressionContext bitwiseAndExpression() throws RecognitionException {
		return bitwiseAndExpression(0);
	}

	private BitwiseAndExpressionContext bitwiseAndExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BitwiseAndExpressionContext _localctx = new BitwiseAndExpressionContext(_ctx, _parentState);
		BitwiseAndExpressionContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_bitwiseAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(312);
			equalityExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BitwiseAndExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_bitwiseAndExpression);
					setState(314);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(315);
					match(And);
					setState(316);
					equalityExpression(0);
					}
					} 
				}
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BitwiseExclusiveOrExpressionContext extends ParserRuleContext {
		public BitwiseAndExpressionContext bitwiseAndExpression() {
			return getRuleContext(BitwiseAndExpressionContext.class,0);
		}
		public BitwiseExclusiveOrExpressionContext bitwiseExclusiveOrExpression() {
			return getRuleContext(BitwiseExclusiveOrExpressionContext.class,0);
		}
		public BitwiseExclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseExclusiveOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterBitwiseExclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitBitwiseExclusiveOrExpression(this);
		}
	}

	public final BitwiseExclusiveOrExpressionContext bitwiseExclusiveOrExpression() throws RecognitionException {
		return bitwiseExclusiveOrExpression(0);
	}

	private BitwiseExclusiveOrExpressionContext bitwiseExclusiveOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BitwiseExclusiveOrExpressionContext _localctx = new BitwiseExclusiveOrExpressionContext(_ctx, _parentState);
		BitwiseExclusiveOrExpressionContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_bitwiseExclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(323);
			bitwiseAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(330);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BitwiseExclusiveOrExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_bitwiseExclusiveOrExpression);
					setState(325);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(326);
					match(Xor);
					setState(327);
					bitwiseAndExpression(0);
					}
					} 
				}
				setState(332);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BitwiseInclusiveOrExpressionContext extends ParserRuleContext {
		public BitwiseExclusiveOrExpressionContext bitwiseExclusiveOrExpression() {
			return getRuleContext(BitwiseExclusiveOrExpressionContext.class,0);
		}
		public BitwiseInclusiveOrExpressionContext bitwiseInclusiveOrExpression() {
			return getRuleContext(BitwiseInclusiveOrExpressionContext.class,0);
		}
		public BitwiseInclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseInclusiveOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterBitwiseInclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitBitwiseInclusiveOrExpression(this);
		}
	}

	public final BitwiseInclusiveOrExpressionContext bitwiseInclusiveOrExpression() throws RecognitionException {
		return bitwiseInclusiveOrExpression(0);
	}

	private BitwiseInclusiveOrExpressionContext bitwiseInclusiveOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BitwiseInclusiveOrExpressionContext _localctx = new BitwiseInclusiveOrExpressionContext(_ctx, _parentState);
		BitwiseInclusiveOrExpressionContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_bitwiseInclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(334);
			bitwiseExclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(341);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BitwiseInclusiveOrExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_bitwiseInclusiveOrExpression);
					setState(336);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(337);
					match(Or);
					setState(338);
					bitwiseExclusiveOrExpression(0);
					}
					} 
				}
				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public BitwiseInclusiveOrExpressionContext bitwiseInclusiveOrExpression() {
			return getRuleContext(BitwiseInclusiveOrExpressionContext.class,0);
		}
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitLogicalAndExpression(this);
		}
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		return logicalAndExpression(0);
	}

	private LogicalAndExpressionContext logicalAndExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, _parentState);
		LogicalAndExpressionContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_logicalAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(345);
			bitwiseInclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(352);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalAndExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_logicalAndExpression);
					setState(347);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(348);
					match(AndAnd);
					setState(349);
					bitwiseInclusiveOrExpression(0);
					}
					} 
				}
				setState(354);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public LogicalAndExpressionContext logicalAndExpression() {
			return getRuleContext(LogicalAndExpressionContext.class,0);
		}
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterLogicalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitLogicalOrExpression(this);
		}
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		return logicalOrExpression(0);
	}

	private LogicalOrExpressionContext logicalOrExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, _parentState);
		LogicalOrExpressionContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_logicalOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(356);
			logicalAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(363);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LogicalOrExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_logicalOrExpression);
					setState(358);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(359);
					match(OrOr);
					setState(360);
					logicalAndExpression(0);
					}
					} 
				}
				setState(365);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitAssignmentExpression(this);
		}
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_assignmentExpression);
		try {
			setState(371);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(366);
				logicalOrExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(367);
				unaryExpression();
				setState(368);
				match(Assign);
				setState(369);
				assignmentExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			assignmentExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
		}
		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class,0);
		}
		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class,0);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_statement);
		try {
			setState(381);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				compoundStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				expressionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(377);
				selectionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(378);
				iterationStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(379);
				jumpStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(380);
				variableDeclarationStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitCompoundStatement(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(T__0);
			setState(387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << Null) | (1L << Void) | (1L << True) | (1L << False) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Semi))) != 0)) {
				{
				{
				setState(384);
				statement();
				}
				}
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(390);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitExpressionStatement(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
				{
				setState(392);
				expression();
				}
			}

			setState(395);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(XYXParser.If, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(XYXParser.Else, 0); }
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitSelectionStatement(this);
		}
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			match(If);
			setState(398);
			match(LeftParen);
			setState(399);
			expression();
			setState(400);
			match(RightParen);
			setState(401);
			statement();
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(402);
				match(Else);
				setState(403);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iterationStatement; }
	 
		public IterationStatementContext() { }
		public void copyFrom(IterationStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class WhileLoopContext extends IterationStatementContext {
		public TerminalNode While() { return getToken(XYXParser.While, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileLoopContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitWhileLoop(this);
		}
	}
	public static class ForLoopContext extends IterationStatementContext {
		public TerminalNode For() { return getToken(XYXParser.For, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public InitContext init() {
			return getRuleContext(InitContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StepContext step() {
			return getRuleContext(StepContext.class,0);
		}
		public ForLoopContext(IterationStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitForLoop(this);
		}
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_iterationStatement);
		int _la;
		try {
			setState(427);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				match(While);
				setState(407);
				match(LeftParen);
				setState(408);
				expression();
				setState(409);
				match(RightParen);
				setState(410);
				statement();
				}
				break;
			case For:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				match(For);
				setState(413);
				match(LeftParen);
				setState(415);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(414);
					init();
					}
				}

				setState(417);
				match(Semi);
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(418);
					condition();
					}
				}

				setState(421);
				match(Semi);
				setState(423);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(422);
					step();
					}
				}

				setState(425);
				match(RightParen);
				setState(426);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitInit(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StepContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitStep(this);
		}
	}

	public final StepContext step() throws RecognitionException {
		StepContext _localctx = new StepContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_step);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpStatement; }
	 
		public JumpStatementContext() { }
		public void copyFrom(JumpStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BreakStatementContext extends JumpStatementContext {
		public TerminalNode Break() { return getToken(XYXParser.Break, 0); }
		public BreakStatementContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitBreakStatement(this);
		}
	}
	public static class ContinueStatementContext extends JumpStatementContext {
		public TerminalNode Continue() { return getToken(XYXParser.Continue, 0); }
		public ContinueStatementContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitContinueStatement(this);
		}
	}
	public static class ReturnStatementContext extends JumpStatementContext {
		public TerminalNode Return() { return getToken(XYXParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(JumpStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitReturnStatement(this);
		}
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_jumpStatement);
		int _la;
		try {
			setState(444);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
				_localctx = new BreakStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(435);
				match(Break);
				setState(436);
				match(Semi);
				}
				break;
			case Continue:
				_localctx = new ContinueStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(437);
				match(Continue);
				setState(438);
				match(Semi);
				}
				break;
			case Return:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(439);
				match(Return);
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(440);
					expression();
					}
				}

				setState(443);
				match(Semi);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(XYXParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitVariableDeclarationStatement(this);
		}
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_variableDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			type(0);
			setState(447);
			match(Identifier);
			setState(450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(448);
				match(Assign);
				setState(449);
				expression();
				}
			}

			setState(452);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 10:
			return suffixExpression_sempred((SuffixExpressionContext)_localctx, predIndex);
		case 12:
			return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		case 13:
			return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);
		case 14:
			return shiftExpression_sempred((ShiftExpressionContext)_localctx, predIndex);
		case 15:
			return relationExpression_sempred((RelationExpressionContext)_localctx, predIndex);
		case 16:
			return equalityExpression_sempred((EqualityExpressionContext)_localctx, predIndex);
		case 17:
			return bitwiseAndExpression_sempred((BitwiseAndExpressionContext)_localctx, predIndex);
		case 18:
			return bitwiseExclusiveOrExpression_sempred((BitwiseExclusiveOrExpressionContext)_localctx, predIndex);
		case 19:
			return bitwiseInclusiveOrExpression_sempred((BitwiseInclusiveOrExpressionContext)_localctx, predIndex);
		case 20:
			return logicalAndExpression_sempred((LogicalAndExpressionContext)_localctx, predIndex);
		case 21:
			return logicalOrExpression_sempred((LogicalOrExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean suffixExpression_sempred(SuffixExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean multiplicativeExpression_sempred(MultiplicativeExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean additiveExpression_sempred(AdditiveExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean shiftExpression_sempred(ShiftExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean relationExpression_sempred(RelationExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 13:
			return precpred(_ctx, 4);
		case 14:
			return precpred(_ctx, 3);
		case 15:
			return precpred(_ctx, 2);
		case 16:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean equalityExpression_sempred(EqualityExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17:
			return precpred(_ctx, 2);
		case 18:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean bitwiseAndExpression_sempred(BitwiseAndExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 19:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean bitwiseExclusiveOrExpression_sempred(BitwiseExclusiveOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 20:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean bitwiseInclusiveOrExpression_sempred(BitwiseInclusiveOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 21:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean logicalAndExpression_sempred(LogicalAndExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 22:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean logicalOrExpression_sempred(LogicalOrExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 23:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u01c9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\6\2H\n\2\r\2\16\2I\3\3\3\3\3\3\5\3O\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\5\4W\n\4\3\4\3\4\3\4\5\4\\\n\4\3\4\7\4_\n\4\f\4\16\4"+
		"b\13\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5j\n\5\f\5\16\5m\13\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\5\6u\n\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7}\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u0085\n\b\f\b\16\b\u0088\13\b\5\b\u008a\n\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u0095\n\b\f\b\16\b\u0098\13\b\5\b\u009a\n\b\3\b"+
		"\3\b\5\b\u009e\n\b\3\t\3\t\3\t\3\t\3\t\7\t\u00a5\n\t\f\t\16\t\u00a8\13"+
		"\t\5\t\u00aa\n\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00b9\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\7\f\u00cb\n\f\f\f\16\f\u00ce\13\f\5\f\u00d0\n\f\3\f\3"+
		"\f\3\f\3\f\3\f\7\f\u00d7\n\f\f\f\16\f\u00da\13\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00e9\n\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00f7\n\16\f\16\16\16\u00fa\13"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0105\n\17\f\17"+
		"\16\17\u0108\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0113"+
		"\n\20\f\20\16\20\u0116\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0127\n\21\f\21\16\21\u012a\13"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0135\n\22\f\22"+
		"\16\22\u0138\13\22\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0140\n\23\f\23"+
		"\16\23\u0143\13\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u014b\n\24\f\24"+
		"\16\24\u014e\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0156\n\25\f\25"+
		"\16\25\u0159\13\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0161\n\26\f\26"+
		"\16\26\u0164\13\26\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u016c\n\27\f\27"+
		"\16\27\u016f\13\27\3\30\3\30\3\30\3\30\3\30\5\30\u0176\n\30\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0180\n\32\3\33\3\33\7\33\u0184\n"+
		"\33\f\33\16\33\u0187\13\33\3\33\3\33\3\34\5\34\u018c\n\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0197\n\35\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\5\36\u01a2\n\36\3\36\3\36\5\36\u01a6\n\36\3"+
		"\36\3\36\5\36\u01aa\n\36\3\36\3\36\5\36\u01ae\n\36\3\37\3\37\3 \3 \3!"+
		"\3!\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u01bc\n\"\3\"\5\"\u01bf\n\"\3#\3#\3#\3"+
		"#\5#\u01c5\n#\3#\3#\3#\2\16\6\26\32\34\36 \"$&(*,$\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BD\2\3\5\2\b\b\n\13\26\27"+
		"\2\u01ee\2G\3\2\2\2\4N\3\2\2\2\6V\3\2\2\2\bc\3\2\2\2\np\3\2\2\2\fx\3\2"+
		"\2\2\16\u009d\3\2\2\2\20\u009f\3\2\2\2\22\u00ae\3\2\2\2\24\u00b8\3\2\2"+
		"\2\26\u00ba\3\2\2\2\30\u00e8\3\2\2\2\32\u00ea\3\2\2\2\34\u00fb\3\2\2\2"+
		"\36\u0109\3\2\2\2 \u0117\3\2\2\2\"\u012b\3\2\2\2$\u0139\3\2\2\2&\u0144"+
		"\3\2\2\2(\u014f\3\2\2\2*\u015a\3\2\2\2,\u0165\3\2\2\2.\u0175\3\2\2\2\60"+
		"\u0177\3\2\2\2\62\u017f\3\2\2\2\64\u0181\3\2\2\2\66\u018b\3\2\2\28\u018f"+
		"\3\2\2\2:\u01ad\3\2\2\2<\u01af\3\2\2\2>\u01b1\3\2\2\2@\u01b3\3\2\2\2B"+
		"\u01be\3\2\2\2D\u01c0\3\2\2\2FH\5\4\3\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2"+
		"IJ\3\2\2\2J\3\3\2\2\2KO\5\16\b\2LO\5\b\5\2MO\5\n\6\2NK\3\2\2\2NL\3\2\2"+
		"\2NM\3\2\2\2O\5\3\2\2\2PQ\b\4\1\2QW\7\5\2\2RW\7\6\2\2SW\7\7\2\2TW\7\t"+
		"\2\2UW\7\25\2\2VP\3\2\2\2VR\3\2\2\2VS\3\2\2\2VT\3\2\2\2VU\3\2\2\2W`\3"+
		"\2\2\2XY\f\3\2\2Y[\7\62\2\2Z\\\5\60\31\2[Z\3\2\2\2[\\\3\2\2\2\\]\3\2\2"+
		"\2]_\7\63\2\2^X\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\7\3\2\2\2b`\3\2"+
		"\2\2cd\7\24\2\2de\7\25\2\2ek\7\3\2\2fj\5D#\2gj\5\16\b\2hj\5\20\t\2if\3"+
		"\2\2\2ig\3\2\2\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3"+
		"\2\2\2no\7\4\2\2o\t\3\2\2\2pq\5\6\4\2qt\7\25\2\2rs\7.\2\2su\5\60\31\2"+
		"tr\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\7\65\2\2w\13\3\2\2\2xy\5\6\4\2y|\7\25"+
		"\2\2z{\7.\2\2{}\5\60\31\2|z\3\2\2\2|}\3\2\2\2}\r\3\2\2\2~\177\5\6\4\2"+
		"\177\u0080\7\25\2\2\u0080\u0089\7,\2\2\u0081\u0086\5\f\7\2\u0082\u0083"+
		"\7\66\2\2\u0083\u0085\5\f\7\2\u0084\u0082\3\2\2\2\u0085\u0088\3\2\2\2"+
		"\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086"+
		"\3\2\2\2\u0089\u0081\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\7-\2\2\u008c\u008d\5\64\33\2\u008d\u009e\3\2\2\2\u008e\u008f\7"+
		"\t\2\2\u008f\u0090\7\25\2\2\u0090\u0099\7,\2\2\u0091\u0096\5\f\7\2\u0092"+
		"\u0093\7\66\2\2\u0093\u0095\5\f\7\2\u0094\u0092\3\2\2\2\u0095\u0098\3"+
		"\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u009a\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0099\u0091\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009c\7-\2\2\u009c\u009e\5\64\33\2\u009d~\3\2\2\2\u009d\u008e"+
		"\3\2\2\2\u009e\17\3\2\2\2\u009f\u00a0\7\25\2\2\u00a0\u00a9\7,\2\2\u00a1"+
		"\u00a6\5\f\7\2\u00a2\u00a3\7\66\2\2\u00a3\u00a5\5\f\7\2\u00a4\u00a2\3"+
		"\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00a1\3\2\2\2\u00a9\u00aa\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\7-\2\2\u00ac\u00ad\5\64\33\2\u00ad"+
		"\21\3\2\2\2\u00ae\u00af\t\2\2\2\u00af\23\3\2\2\2\u00b0\u00b9\5\22\n\2"+
		"\u00b1\u00b9\7\25\2\2\u00b2\u00b3\7,\2\2\u00b3\u00b4\5\60\31\2\u00b4\u00b5"+
		"\7-\2\2\u00b5\u00b9\3\2\2\2\u00b6\u00b7\7\23\2\2\u00b7\u00b9\5\6\4\2\u00b8"+
		"\u00b0\3\2\2\2\u00b8\u00b1\3\2\2\2\u00b8\u00b2\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b9\25\3\2\2\2\u00ba\u00bb\b\f\1\2\u00bb\u00bc\5\24\13\2\u00bc"+
		"\u00d8\3\2\2\2\u00bd\u00be\f\7\2\2\u00be\u00bf\7\61\2\2\u00bf\u00d7\7"+
		"\25\2\2\u00c0\u00c1\f\6\2\2\u00c1\u00c2\7\62\2\2\u00c2\u00c3\5\60\31\2"+
		"\u00c3\u00c4\7\63\2\2\u00c4\u00d7\3\2\2\2\u00c5\u00c6\f\5\2\2\u00c6\u00cf"+
		"\7,\2\2\u00c7\u00cc\5\60\31\2\u00c8\u00c9\7\66\2\2\u00c9\u00cb\5\60\31"+
		"\2\u00ca\u00c8\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd"+
		"\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00c7\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d7\7-\2\2\u00d2\u00d3\f\4"+
		"\2\2\u00d3\u00d7\7/\2\2\u00d4\u00d5\f\3\2\2\u00d5\u00d7\7\60\2\2\u00d6"+
		"\u00bd\3\2\2\2\u00d6\u00c0\3\2\2\2\u00d6\u00c5\3\2\2\2\u00d6\u00d2\3\2"+
		"\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8"+
		"\u00d9\3\2\2\2\u00d9\27\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00e9\5\26\f"+
		"\2\u00dc\u00dd\7/\2\2\u00dd\u00e9\5\26\f\2\u00de\u00df\7\60\2\2\u00df"+
		"\u00e9\5\26\f\2\u00e0\u00e1\7(\2\2\u00e1\u00e9\5\26\f\2\u00e2\u00e3\7"+
		"%\2\2\u00e3\u00e9\5\26\f\2\u00e4\u00e5\7\30\2\2\u00e5\u00e9\5\26\f\2\u00e6"+
		"\u00e7\7\31\2\2\u00e7\u00e9\5\26\f\2\u00e8\u00db\3\2\2\2\u00e8\u00dc\3"+
		"\2\2\2\u00e8\u00de\3\2\2\2\u00e8\u00e0\3\2\2\2\u00e8\u00e2\3\2\2\2\u00e8"+
		"\u00e4\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\31\3\2\2\2\u00ea\u00eb\b\16\1"+
		"\2\u00eb\u00ec\5\30\r\2\u00ec\u00f8\3\2\2\2\u00ed\u00ee\f\5\2\2\u00ee"+
		"\u00ef\7\32\2\2\u00ef\u00f7\5\30\r\2\u00f0\u00f1\f\4\2\2\u00f1\u00f2\7"+
		"\33\2\2\u00f2\u00f7\5\30\r\2\u00f3\u00f4\f\3\2\2\u00f4\u00f5\7\34\2\2"+
		"\u00f5\u00f7\5\30\r\2\u00f6\u00ed\3\2\2\2\u00f6\u00f0\3\2\2\2\u00f6\u00f3"+
		"\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\33\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fc\b\17\1\2\u00fc\u00fd\5\32"+
		"\16\2\u00fd\u0106\3\2\2\2\u00fe\u00ff\f\4\2\2\u00ff\u0100\7\30\2\2\u0100"+
		"\u0105\5\32\16\2\u0101\u0102\f\3\2\2\u0102\u0103\7\31\2\2\u0103\u0105"+
		"\5\32\16\2\u0104\u00fe\3\2\2\2\u0104\u0101\3\2\2\2\u0105\u0108\3\2\2\2"+
		"\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\35\3\2\2\2\u0108\u0106"+
		"\3\2\2\2\u0109\u010a\b\20\1\2\u010a\u010b\5\34\17\2\u010b\u0114\3\2\2"+
		"\2\u010c\u010d\f\4\2\2\u010d\u010e\7&\2\2\u010e\u0113\5\34\17\2\u010f"+
		"\u0110\f\3\2\2\u0110\u0111\7\'\2\2\u0111\u0113\5\34\17\2\u0112\u010c\3"+
		"\2\2\2\u0112\u010f\3\2\2\2\u0113\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114"+
		"\u0115\3\2\2\2\u0115\37\3\2\2\2\u0116\u0114\3\2\2\2\u0117\u0118\b\21\1"+
		"\2\u0118\u0119\5\36\20\2\u0119\u0128\3\2\2\2\u011a\u011b\f\6\2\2\u011b"+
		"\u011c\7\35\2\2\u011c\u0127\5\36\20\2\u011d\u011e\f\5\2\2\u011e\u011f"+
		"\7\"\2\2\u011f\u0127\5\36\20\2\u0120\u0121\f\4\2\2\u0121\u0122\7\36\2"+
		"\2\u0122\u0127\5\36\20\2\u0123\u0124\f\3\2\2\u0124\u0125\7!\2\2\u0125"+
		"\u0127\5\36\20\2\u0126\u011a\3\2\2\2\u0126\u011d\3\2\2\2\u0126\u0120\3"+
		"\2\2\2\u0126\u0123\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129!\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c\b\22\1\2"+
		"\u012c\u012d\5 \21\2\u012d\u0136\3\2\2\2\u012e\u012f\f\4\2\2\u012f\u0130"+
		"\7\37\2\2\u0130\u0135\5 \21\2\u0131\u0132\f\3\2\2\u0132\u0133\7 \2\2\u0133"+
		"\u0135\5 \21\2\u0134\u012e\3\2\2\2\u0134\u0131\3\2\2\2\u0135\u0138\3\2"+
		"\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137#\3\2\2\2\u0138\u0136"+
		"\3\2\2\2\u0139\u013a\b\23\1\2\u013a\u013b\5\"\22\2\u013b\u0141\3\2\2\2"+
		"\u013c\u013d\f\3\2\2\u013d\u013e\7+\2\2\u013e\u0140\5\"\22\2\u013f\u013c"+
		"\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142"+
		"%\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\b\24\1\2\u0145\u0146\5$\23\2"+
		"\u0146\u014c\3\2\2\2\u0147\u0148\f\3\2\2\u0148\u0149\7*\2\2\u0149\u014b"+
		"\5$\23\2\u014a\u0147\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c"+
		"\u014d\3\2\2\2\u014d\'\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\b\25\1"+
		"\2\u0150\u0151\5&\24\2\u0151\u0157\3\2\2\2\u0152\u0153\f\3\2\2\u0153\u0154"+
		"\7)\2\2\u0154\u0156\5&\24\2\u0155\u0152\3\2\2\2\u0156\u0159\3\2\2\2\u0157"+
		"\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158)\3\2\2\2\u0159\u0157\3\2\2\2"+
		"\u015a\u015b\b\26\1\2\u015b\u015c\5(\25\2\u015c\u0162\3\2\2\2\u015d\u015e"+
		"\f\3\2\2\u015e\u015f\7#\2\2\u015f\u0161\5(\25\2\u0160\u015d\3\2\2\2\u0161"+
		"\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163+\3\2\2\2"+
		"\u0164\u0162\3\2\2\2\u0165\u0166\b\27\1\2\u0166\u0167\5*\26\2\u0167\u016d"+
		"\3\2\2\2\u0168\u0169\f\3\2\2\u0169\u016a\7$\2\2\u016a\u016c\5*\26\2\u016b"+
		"\u0168\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2"+
		"\2\2\u016e-\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0176\5,\27\2\u0171\u0172"+
		"\5\30\r\2\u0172\u0173\7.\2\2\u0173\u0174\5.\30\2\u0174\u0176\3\2\2\2\u0175"+
		"\u0170\3\2\2\2\u0175\u0171\3\2\2\2\u0176/\3\2\2\2\u0177\u0178\5.\30\2"+
		"\u0178\61\3\2\2\2\u0179\u0180\5\64\33\2\u017a\u0180\5\66\34\2\u017b\u0180"+
		"\58\35\2\u017c\u0180\5:\36\2\u017d\u0180\5B\"\2\u017e\u0180\5D#\2\u017f"+
		"\u0179\3\2\2\2\u017f\u017a\3\2\2\2\u017f\u017b\3\2\2\2\u017f\u017c\3\2"+
		"\2\2\u017f\u017d\3\2\2\2\u017f\u017e\3\2\2\2\u0180\63\3\2\2\2\u0181\u0185"+
		"\7\3\2\2\u0182\u0184\5\62\32\2\u0183\u0182\3\2\2\2\u0184\u0187\3\2\2\2"+
		"\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0188\3\2\2\2\u0187\u0185"+
		"\3\2\2\2\u0188\u0189\7\4\2\2\u0189\65\3\2\2\2\u018a\u018c\5\60\31\2\u018b"+
		"\u018a\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e\7\65"+
		"\2\2\u018e\67\3\2\2\2\u018f\u0190\7\f\2\2\u0190\u0191\7,\2\2\u0191\u0192"+
		"\5\60\31\2\u0192\u0193\7-\2\2\u0193\u0196\5\62\32\2\u0194\u0195\7\r\2"+
		"\2\u0195\u0197\5\62\32\2\u0196\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197"+
		"9\3\2\2\2\u0198\u0199\7\17\2\2\u0199\u019a\7,\2\2\u019a\u019b\5\60\31"+
		"\2\u019b\u019c\7-\2\2\u019c\u019d\5\62\32\2\u019d\u01ae\3\2\2\2\u019e"+
		"\u019f\7\16\2\2\u019f\u01a1\7,\2\2\u01a0\u01a2\5<\37\2\u01a1\u01a0\3\2"+
		"\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a5\7\65\2\2\u01a4"+
		"\u01a6\5> \2\u01a5\u01a4\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a7\3\2\2"+
		"\2\u01a7\u01a9\7\65\2\2\u01a8\u01aa\5@!\2\u01a9\u01a8\3\2\2\2\u01a9\u01aa"+
		"\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ac\7-\2\2\u01ac\u01ae\5\62\32\2"+
		"\u01ad\u0198\3\2\2\2\u01ad\u019e\3\2\2\2\u01ae;\3\2\2\2\u01af\u01b0\5"+
		"\60\31\2\u01b0=\3\2\2\2\u01b1\u01b2\5\60\31\2\u01b2?\3\2\2\2\u01b3\u01b4"+
		"\5\60\31\2\u01b4A\3\2\2\2\u01b5\u01b6\7\20\2\2\u01b6\u01bf\7\65\2\2\u01b7"+
		"\u01b8\7\21\2\2\u01b8\u01bf\7\65\2\2\u01b9\u01bb\7\22\2\2\u01ba\u01bc"+
		"\5\60\31\2\u01bb\u01ba\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc\u01bd\3\2\2\2"+
		"\u01bd\u01bf\7\65\2\2\u01be\u01b5\3\2\2\2\u01be\u01b7\3\2\2\2\u01be\u01b9"+
		"\3\2\2\2\u01bfC\3\2\2\2\u01c0\u01c1\5\6\4\2\u01c1\u01c4\7\25\2\2\u01c2"+
		"\u01c3\7.\2\2\u01c3\u01c5\5\60\31\2\u01c4\u01c2\3\2\2\2\u01c4\u01c5\3"+
		"\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c7\7\65\2\2\u01c7E\3\2\2\2\63INV["+
		"`ikt|\u0086\u0089\u0096\u0099\u009d\u00a6\u00a9\u00b8\u00cc\u00cf\u00d6"+
		"\u00d8\u00e8\u00f6\u00f8\u0104\u0106\u0112\u0114\u0126\u0128\u0134\u0136"+
		"\u0141\u014c\u0157\u0162\u016d\u0175\u017f\u0185\u018b\u0196\u01a1\u01a5"+
		"\u01a9\u01ad\u01bb\u01be\u01c4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}