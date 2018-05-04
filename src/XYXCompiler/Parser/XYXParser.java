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
		RULE_classMembers = 4, RULE_globalVariableDeclaration = 5, RULE_variableDeclaration = 6, 
		RULE_functionDeclaration = 7, RULE_constructFunctionDeclaration = 8, RULE_constant = 9, 
		RULE_primaryExpression = 10, RULE_suffixExpression = 11, RULE_unaryExpression = 12, 
		RULE_multiplicativeExpression = 13, RULE_additiveExpression = 14, RULE_shiftExpression = 15, 
		RULE_relationExpression = 16, RULE_equalityExpression = 17, RULE_bitwiseAndExpression = 18, 
		RULE_bitwiseExclusiveOrExpression = 19, RULE_bitwiseInclusiveOrExpression = 20, 
		RULE_logicalAndExpression = 21, RULE_logicalOrExpression = 22, RULE_assignmentExpression = 23, 
		RULE_expression = 24, RULE_statement = 25, RULE_compoundStatement = 26, 
		RULE_expressionStatement = 27, RULE_selectionStatement = 28, RULE_iterationStatement = 29, 
		RULE_init = 30, RULE_condition = 31, RULE_step = 32, RULE_jumpStatement = 33, 
		RULE_variableDeclarationStatement = 34;
	public static final String[] ruleNames = {
		"program", "declaration", "type", "classDeclaration", "classMembers", 
		"globalVariableDeclaration", "variableDeclaration", "functionDeclaration", 
		"constructFunctionDeclaration", "constant", "primaryExpression", "suffixExpression", 
		"unaryExpression", "multiplicativeExpression", "additiveExpression", "shiftExpression", 
		"relationExpression", "equalityExpression", "bitwiseAndExpression", "bitwiseExclusiveOrExpression", 
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
			setState(71); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(70);
				declaration();
				}
				}
				setState(73); 
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
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				functionDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				classDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
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
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Bool:
				{
				_localctx = new BoolTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(81);
				match(Bool);
				}
				break;
			case Int:
				{
				_localctx = new IntTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(Int);
				}
				break;
			case String:
				{
				_localctx = new StringTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(String);
				}
				break;
			case Void:
				{
				_localctx = new VoidTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				match(Void);
				}
				break;
			case Identifier:
				{
				_localctx = new IdTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(85);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(96);
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
					setState(88);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(89);
					match(LeftBracket);
					setState(91);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
						{
						setState(90);
						expression();
						}
					}

					setState(93);
					match(RightBracket);
					}
					} 
				}
				setState(98);
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
		public List<ClassMembersContext> classMembers() {
			return getRuleContexts(ClassMembersContext.class);
		}
		public ClassMembersContext classMembers(int i) {
			return getRuleContext(ClassMembersContext.class,i);
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
			setState(99);
			match(Class);
			setState(100);
			match(Identifier);
			setState(101);
			match(T__0);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				{
				setState(102);
				classMembers();
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

	public static class ClassMembersContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ConstructFunctionDeclarationContext constructFunctionDeclaration() {
			return getRuleContext(ConstructFunctionDeclarationContext.class,0);
		}
		public ClassMembersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classMembers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).enterClassMembers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XYXListener ) ((XYXListener)listener).exitClassMembers(this);
		}
	}

	public final ClassMembersContext classMembers() throws RecognitionException {
		ClassMembersContext _localctx = new ClassMembersContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_classMembers);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				variableDeclaration();
				setState(111);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				functionDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				constructFunctionDeclaration();
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
		enterRule(_localctx, 10, RULE_globalVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			type(0);
			setState(118);
			match(Identifier);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(119);
				match(Assign);
				setState(120);
				expression();
				}
			}

			setState(123);
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
		enterRule(_localctx, 12, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(125);
			type(0);
			setState(126);
			match(Identifier);
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(127);
				match(Assign);
				setState(128);
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
		enterRule(_localctx, 14, RULE_functionDeclaration);
		int _la;
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				type(0);
				setState(132);
				match(Identifier);
				setState(133);
				match(LeftParen);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
					{
					setState(134);
					variableDeclaration();
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==Comma) {
						{
						{
						setState(135);
						match(Comma);
						setState(136);
						variableDeclaration();
						}
						}
						setState(141);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(144);
				match(RightParen);
				setState(145);
				compoundStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				match(Void);
				setState(148);
				match(Identifier);
				setState(149);
				match(LeftParen);
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
					{
					setState(150);
					variableDeclaration();
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==Comma) {
						{
						{
						setState(151);
						match(Comma);
						setState(152);
						variableDeclaration();
						}
						}
						setState(157);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(160);
				match(RightParen);
				setState(161);
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
		enterRule(_localctx, 16, RULE_constructFunctionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(Identifier);
			setState(165);
			match(LeftParen);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Bool) | (1L << Int) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(166);
				variableDeclaration();
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(167);
					match(Comma);
					setState(168);
					variableDeclaration();
					}
					}
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(176);
			match(RightParen);
			setState(177);
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
		enterRule(_localctx, 18, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
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
		enterRule(_localctx, 20, RULE_primaryExpression);
		try {
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case True:
			case False:
			case IntegerConstant:
			case StringConstant:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				constant();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(Identifier);
				}
				break;
			case LeftParen:
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				match(LeftParen);
				setState(184);
				expression();
				setState(185);
				match(RightParen);
				}
				break;
			case New:
				enterOuterAlt(_localctx, 4);
				{
				setState(187);
				match(New);
				setState(188);
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_suffixExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(192);
			primaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(219);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(194);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(195);
						match(Dot);
						setState(196);
						match(Identifier);
						}
						break;
					case 2:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(197);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(198);
						match(LeftBracket);
						setState(199);
						expression();
						setState(200);
						match(RightBracket);
						}
						break;
					case 3:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(202);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(203);
						match(LeftParen);
						setState(212);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
							{
							setState(204);
							expression();
							setState(209);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==Comma) {
								{
								{
								setState(205);
								match(Comma);
								setState(206);
								expression();
								}
								}
								setState(211);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(214);
						match(RightParen);
						}
						break;
					case 4:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(215);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(216);
						match(PlusPlus);
						}
						break;
					case 5:
						{
						_localctx = new SuffixExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_suffixExpression);
						setState(217);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(218);
						match(MinusMinus);
						}
						break;
					}
					} 
				}
				setState(223);
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
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
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
		enterRule(_localctx, 24, RULE_unaryExpression);
		try {
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				suffixExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				match(PlusPlus);
				setState(226);
				suffixExpression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(227);
				match(MinusMinus);
				setState(228);
				suffixExpression(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(229);
				match(Tilde);
				setState(230);
				suffixExpression(0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(231);
				match(Not);
				setState(232);
				suffixExpression(0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(233);
				match(Plus);
				setState(234);
				suffixExpression(0);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(235);
				match(Minus);
				setState(236);
				suffixExpression(0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(237);
				match(Tilde);
				setState(238);
				unaryExpression();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(239);
				match(Minus);
				setState(240);
				unaryExpression();
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_multiplicativeExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(244);
			unaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(255);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(246);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(247);
						match(Mul);
						setState(248);
						unaryExpression();
						}
						break;
					case 2:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(249);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(250);
						match(Div);
						setState(251);
						unaryExpression();
						}
						break;
					case 3:
						{
						_localctx = new MultiplicativeExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpression);
						setState(252);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(253);
						match(Mod);
						setState(254);
						unaryExpression();
						}
						break;
					}
					} 
				}
				setState(259);
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_additiveExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(261);
			multiplicativeExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(269);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(263);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(264);
						match(Plus);
						setState(265);
						multiplicativeExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new AdditiveExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_additiveExpression);
						setState(266);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(267);
						match(Minus);
						setState(268);
						multiplicativeExpression(0);
						}
						break;
					}
					} 
				}
				setState(273);
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
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_shiftExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(275);
			additiveExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(285);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(283);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new ShiftExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shiftExpression);
						setState(277);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(278);
						match(LeftShift);
						setState(279);
						additiveExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new ShiftExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_shiftExpression);
						setState(280);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(281);
						match(RightShift);
						setState(282);
						additiveExpression(0);
						}
						break;
					}
					} 
				}
				setState(287);
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_relationExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(289);
			shiftExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(305);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(303);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(291);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(292);
						match(Less);
						setState(293);
						shiftExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(294);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(295);
						match(LessEqual);
						setState(296);
						shiftExpression(0);
						}
						break;
					case 3:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(297);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(298);
						match(Greater);
						setState(299);
						shiftExpression(0);
						}
						break;
					case 4:
						{
						_localctx = new RelationExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_relationExpression);
						setState(300);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(301);
						match(GreaterEqual);
						setState(302);
						shiftExpression(0);
						}
						break;
					}
					} 
				}
				setState(307);
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_equalityExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(309);
			relationExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(317);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new EqualityExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(311);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(312);
						match(Equal);
						setState(313);
						relationExpression(0);
						}
						break;
					case 2:
						{
						_localctx = new EqualityExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_equalityExpression);
						setState(314);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(315);
						match(NotEqual);
						setState(316);
						relationExpression(0);
						}
						break;
					}
					} 
				}
				setState(321);
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
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_bitwiseAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(323);
			equalityExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(330);
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
					setState(325);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(326);
					match(And);
					setState(327);
					equalityExpression(0);
					}
					} 
				}
				setState(332);
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_bitwiseExclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(334);
			bitwiseAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(341);
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
					setState(336);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(337);
					match(Xor);
					setState(338);
					bitwiseAndExpression(0);
					}
					} 
				}
				setState(343);
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_bitwiseInclusiveOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(345);
			bitwiseExclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(352);
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
					setState(347);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(348);
					match(Or);
					setState(349);
					bitwiseExclusiveOrExpression(0);
					}
					} 
				}
				setState(354);
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
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_logicalAndExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(356);
			bitwiseInclusiveOrExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(363);
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
					setState(358);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(359);
					match(AndAnd);
					setState(360);
					bitwiseInclusiveOrExpression(0);
					}
					} 
				}
				setState(365);
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
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_logicalOrExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(367);
			logicalAndExpression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(374);
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
					setState(369);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(370);
					match(OrOr);
					setState(371);
					logicalAndExpression(0);
					}
					} 
				}
				setState(376);
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
		enterRule(_localctx, 46, RULE_assignmentExpression);
		try {
			setState(382);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(377);
				logicalOrExpression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(378);
				unaryExpression();
				setState(379);
				match(Assign);
				setState(380);
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
		enterRule(_localctx, 48, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
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
		enterRule(_localctx, 50, RULE_statement);
		try {
			setState(392);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				compoundStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				expressionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(388);
				selectionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(389);
				iterationStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(390);
				jumpStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(391);
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
		enterRule(_localctx, 52, RULE_compoundStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			match(T__0);
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Bool) | (1L << Int) | (1L << String) | (1L << Null) | (1L << Void) | (1L << True) | (1L << False) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus) | (1L << Semi))) != 0)) {
				{
				{
				setState(395);
				statement();
				}
				}
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(401);
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
		enterRule(_localctx, 54, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
				{
				setState(403);
				expression();
				}
			}

			setState(406);
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
		enterRule(_localctx, 56, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			match(If);
			setState(409);
			match(LeftParen);
			setState(410);
			expression();
			setState(411);
			match(RightParen);
			setState(412);
			statement();
			setState(415);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(413);
				match(Else);
				setState(414);
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
		enterRule(_localctx, 58, RULE_iterationStatement);
		int _la;
		try {
			setState(438);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case While:
				_localctx = new WhileLoopContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				match(While);
				setState(418);
				match(LeftParen);
				setState(419);
				expression();
				setState(420);
				match(RightParen);
				setState(421);
				statement();
				}
				break;
			case For:
				_localctx = new ForLoopContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(423);
				match(For);
				setState(424);
				match(LeftParen);
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(425);
					init();
					}
				}

				setState(428);
				match(Semi);
				setState(430);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(429);
					condition();
					}
				}

				setState(432);
				match(Semi);
				setState(434);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(433);
					step();
					}
				}

				setState(436);
				match(RightParen);
				setState(437);
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
		enterRule(_localctx, 60, RULE_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
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
		enterRule(_localctx, 62, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
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
		enterRule(_localctx, 64, RULE_step);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
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
		enterRule(_localctx, 66, RULE_jumpStatement);
		int _la;
		try {
			setState(455);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Break:
				_localctx = new BreakStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(446);
				match(Break);
				setState(447);
				match(Semi);
				}
				break;
			case Continue:
				_localctx = new ContinueStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(448);
				match(Continue);
				setState(449);
				match(Semi);
				}
				break;
			case Return:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(450);
				match(Return);
				setState(452);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << True) | (1L << False) | (1L << New) | (1L << Identifier) | (1L << IntegerConstant) | (1L << StringConstant) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << LeftParen) | (1L << PlusPlus) | (1L << MinusMinus))) != 0)) {
					{
					setState(451);
					expression();
					}
				}

				setState(454);
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
		enterRule(_localctx, 68, RULE_variableDeclarationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			type(0);
			setState(458);
			match(Identifier);
			setState(461);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(459);
				match(Assign);
				setState(460);
				expression();
				}
			}

			setState(463);
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
		case 11:
			return suffixExpression_sempred((SuffixExpressionContext)_localctx, predIndex);
		case 13:
			return multiplicativeExpression_sempred((MultiplicativeExpressionContext)_localctx, predIndex);
		case 14:
			return additiveExpression_sempred((AdditiveExpressionContext)_localctx, predIndex);
		case 15:
			return shiftExpression_sempred((ShiftExpressionContext)_localctx, predIndex);
		case 16:
			return relationExpression_sempred((RelationExpressionContext)_localctx, predIndex);
		case 17:
			return equalityExpression_sempred((EqualityExpressionContext)_localctx, predIndex);
		case 18:
			return bitwiseAndExpression_sempred((BitwiseAndExpressionContext)_localctx, predIndex);
		case 19:
			return bitwiseExclusiveOrExpression_sempred((BitwiseExclusiveOrExpressionContext)_localctx, predIndex);
		case 20:
			return bitwiseInclusiveOrExpression_sempred((BitwiseInclusiveOrExpressionContext)_localctx, predIndex);
		case 21:
			return logicalAndExpression_sempred((LogicalAndExpressionContext)_localctx, predIndex);
		case 22:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u01d4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\6\2J\n\2\r\2\16\2K\3\3\3\3\3\3\5\3Q\n\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4Y\n\4\3\4\3\4\3\4\5\4^\n\4\3\4\7\4a\n\4\f\4"+
		"\16\4d\13\4\3\5\3\5\3\5\3\5\7\5j\n\5\f\5\16\5m\13\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\5\6v\n\6\3\7\3\7\3\7\3\7\5\7|\n\7\3\7\3\7\3\b\3\b\3\b\3\b\5"+
		"\b\u0084\n\b\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u008c\n\t\f\t\16\t\u008f\13\t"+
		"\5\t\u0091\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u009c\n\t\f\t\16"+
		"\t\u009f\13\t\5\t\u00a1\n\t\3\t\3\t\5\t\u00a5\n\t\3\n\3\n\3\n\3\n\3\n"+
		"\7\n\u00ac\n\n\f\n\16\n\u00af\13\n\5\n\u00b1\n\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c0\n\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00d2\n\r\f\r\16\r\u00d5"+
		"\13\r\5\r\u00d7\n\r\3\r\3\r\3\r\3\r\3\r\7\r\u00de\n\r\f\r\16\r\u00e1\13"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\5\16\u00f4\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u0102\n\17\f\17\16\17\u0105\13\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0110\n\20\f\20\16\20\u0113"+
		"\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u011e\n\21\f"+
		"\21\16\21\u0121\13\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\7\22\u0132\n\22\f\22\16\22\u0135\13\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0140\n\23\f\23\16\23\u0143"+
		"\13\23\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u014b\n\24\f\24\16\24\u014e"+
		"\13\24\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0156\n\25\f\25\16\25\u0159"+
		"\13\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0161\n\26\f\26\16\26\u0164"+
		"\13\26\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u016c\n\27\f\27\16\27\u016f"+
		"\13\27\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u0177\n\30\f\30\16\30\u017a"+
		"\13\30\3\31\3\31\3\31\3\31\3\31\5\31\u0181\n\31\3\32\3\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\5\33\u018b\n\33\3\34\3\34\7\34\u018f\n\34\f\34\16\34"+
		"\u0192\13\34\3\34\3\34\3\35\5\35\u0197\n\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\5\36\u01a2\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\5\37\u01ad\n\37\3\37\3\37\5\37\u01b1\n\37\3\37\3\37\5\37\u01b5"+
		"\n\37\3\37\3\37\5\37\u01b9\n\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3#\3#\3#\3#"+
		"\5#\u01c7\n#\3#\5#\u01ca\n#\3$\3$\3$\3$\5$\u01d0\n$\3$\3$\3$\2\16\6\30"+
		"\34\36 \"$&(*,.%\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDF\2\3\5\2\b\b\n\13\26\27\2\u01fa\2I\3\2\2\2\4P\3\2\2\2\6"+
		"X\3\2\2\2\be\3\2\2\2\nu\3\2\2\2\fw\3\2\2\2\16\177\3\2\2\2\20\u00a4\3\2"+
		"\2\2\22\u00a6\3\2\2\2\24\u00b5\3\2\2\2\26\u00bf\3\2\2\2\30\u00c1\3\2\2"+
		"\2\32\u00f3\3\2\2\2\34\u00f5\3\2\2\2\36\u0106\3\2\2\2 \u0114\3\2\2\2\""+
		"\u0122\3\2\2\2$\u0136\3\2\2\2&\u0144\3\2\2\2(\u014f\3\2\2\2*\u015a\3\2"+
		"\2\2,\u0165\3\2\2\2.\u0170\3\2\2\2\60\u0180\3\2\2\2\62\u0182\3\2\2\2\64"+
		"\u018a\3\2\2\2\66\u018c\3\2\2\28\u0196\3\2\2\2:\u019a\3\2\2\2<\u01b8\3"+
		"\2\2\2>\u01ba\3\2\2\2@\u01bc\3\2\2\2B\u01be\3\2\2\2D\u01c9\3\2\2\2F\u01cb"+
		"\3\2\2\2HJ\5\4\3\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\3\3\2\2\2"+
		"MQ\5\20\t\2NQ\5\b\5\2OQ\5\f\7\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\5\3\2\2"+
		"\2RS\b\4\1\2SY\7\5\2\2TY\7\6\2\2UY\7\7\2\2VY\7\t\2\2WY\7\25\2\2XR\3\2"+
		"\2\2XT\3\2\2\2XU\3\2\2\2XV\3\2\2\2XW\3\2\2\2Yb\3\2\2\2Z[\f\3\2\2[]\7\62"+
		"\2\2\\^\5\62\32\2]\\\3\2\2\2]^\3\2\2\2^_\3\2\2\2_a\7\63\2\2`Z\3\2\2\2"+
		"ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\7\3\2\2\2db\3\2\2\2ef\7\24\2\2fg\7\25"+
		"\2\2gk\7\3\2\2hj\5\n\6\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2"+
		"\2\2mk\3\2\2\2no\7\4\2\2o\t\3\2\2\2pq\5\16\b\2qr\7\65\2\2rv\3\2\2\2sv"+
		"\5\20\t\2tv\5\22\n\2up\3\2\2\2us\3\2\2\2ut\3\2\2\2v\13\3\2\2\2wx\5\6\4"+
		"\2x{\7\25\2\2yz\7.\2\2z|\5\62\32\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7\65"+
		"\2\2~\r\3\2\2\2\177\u0080\5\6\4\2\u0080\u0083\7\25\2\2\u0081\u0082\7."+
		"\2\2\u0082\u0084\5\62\32\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\17\3\2\2\2\u0085\u0086\5\6\4\2\u0086\u0087\7\25\2\2\u0087\u0090\7,\2"+
		"\2\u0088\u008d\5\16\b\2\u0089\u008a\7\66\2\2\u008a\u008c\5\16\b\2\u008b"+
		"\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0088\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\7-\2\2\u0093\u0094\5\66"+
		"\34\2\u0094\u00a5\3\2\2\2\u0095\u0096\7\t\2\2\u0096\u0097\7\25\2\2\u0097"+
		"\u00a0\7,\2\2\u0098\u009d\5\16\b\2\u0099\u009a\7\66\2\2\u009a\u009c\5"+
		"\16\b\2\u009b\u0099\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u0098\3\2"+
		"\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7-\2\2\u00a3"+
		"\u00a5\5\66\34\2\u00a4\u0085\3\2\2\2\u00a4\u0095\3\2\2\2\u00a5\21\3\2"+
		"\2\2\u00a6\u00a7\7\25\2\2\u00a7\u00b0\7,\2\2\u00a8\u00ad\5\16\b\2\u00a9"+
		"\u00aa\7\66\2\2\u00aa\u00ac\5\16\b\2\u00ab\u00a9\3\2\2\2\u00ac\u00af\3"+
		"\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b3\7-\2\2\u00b3\u00b4\5\66\34\2\u00b4\23\3\2\2\2\u00b5\u00b6"+
		"\t\2\2\2\u00b6\25\3\2\2\2\u00b7\u00c0\5\24\13\2\u00b8\u00c0\7\25\2\2\u00b9"+
		"\u00ba\7,\2\2\u00ba\u00bb\5\62\32\2\u00bb\u00bc\7-\2\2\u00bc\u00c0\3\2"+
		"\2\2\u00bd\u00be\7\23\2\2\u00be\u00c0\5\6\4\2\u00bf\u00b7\3\2\2\2\u00bf"+
		"\u00b8\3\2\2\2\u00bf\u00b9\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\27\3\2\2"+
		"\2\u00c1\u00c2\b\r\1\2\u00c2\u00c3\5\26\f\2\u00c3\u00df\3\2\2\2\u00c4"+
		"\u00c5\f\7\2\2\u00c5\u00c6\7\61\2\2\u00c6\u00de\7\25\2\2\u00c7\u00c8\f"+
		"\6\2\2\u00c8\u00c9\7\62\2\2\u00c9\u00ca\5\62\32\2\u00ca\u00cb\7\63\2\2"+
		"\u00cb\u00de\3\2\2\2\u00cc\u00cd\f\5\2\2\u00cd\u00d6\7,\2\2\u00ce\u00d3"+
		"\5\62\32\2\u00cf\u00d0\7\66\2\2\u00d0\u00d2\5\62\32\2\u00d1\u00cf\3\2"+
		"\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00ce\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00de\7-\2\2\u00d9\u00da\f\4\2\2\u00da"+
		"\u00de\7/\2\2\u00db\u00dc\f\3\2\2\u00dc\u00de\7\60\2\2\u00dd\u00c4\3\2"+
		"\2\2\u00dd\u00c7\3\2\2\2\u00dd\u00cc\3\2\2\2\u00dd\u00d9\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0\31\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00f4\5\30\r\2\u00e3\u00e4"+
		"\7/\2\2\u00e4\u00f4\5\30\r\2\u00e5\u00e6\7\60\2\2\u00e6\u00f4\5\30\r\2"+
		"\u00e7\u00e8\7(\2\2\u00e8\u00f4\5\30\r\2\u00e9\u00ea\7%\2\2\u00ea\u00f4"+
		"\5\30\r\2\u00eb\u00ec\7\30\2\2\u00ec\u00f4\5\30\r\2\u00ed\u00ee\7\31\2"+
		"\2\u00ee\u00f4\5\30\r\2\u00ef\u00f0\7(\2\2\u00f0\u00f4\5\32\16\2\u00f1"+
		"\u00f2\7\31\2\2\u00f2\u00f4\5\32\16\2\u00f3\u00e2\3\2\2\2\u00f3\u00e3"+
		"\3\2\2\2\u00f3\u00e5\3\2\2\2\u00f3\u00e7\3\2\2\2\u00f3\u00e9\3\2\2\2\u00f3"+
		"\u00eb\3\2\2\2\u00f3\u00ed\3\2\2\2\u00f3\u00ef\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f4\33\3\2\2\2\u00f5\u00f6\b\17\1\2\u00f6\u00f7\5\32\16\2\u00f7"+
		"\u0103\3\2\2\2\u00f8\u00f9\f\5\2\2\u00f9\u00fa\7\32\2\2\u00fa\u0102\5"+
		"\32\16\2\u00fb\u00fc\f\4\2\2\u00fc\u00fd\7\33\2\2\u00fd\u0102\5\32\16"+
		"\2\u00fe\u00ff\f\3\2\2\u00ff\u0100\7\34\2\2\u0100\u0102\5\32\16\2\u0101"+
		"\u00f8\3\2\2\2\u0101\u00fb\3\2\2\2\u0101\u00fe\3\2\2\2\u0102\u0105\3\2"+
		"\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\35\3\2\2\2\u0105\u0103"+
		"\3\2\2\2\u0106\u0107\b\20\1\2\u0107\u0108\5\34\17\2\u0108\u0111\3\2\2"+
		"\2\u0109\u010a\f\4\2\2\u010a\u010b\7\30\2\2\u010b\u0110\5\34\17\2\u010c"+
		"\u010d\f\3\2\2\u010d\u010e\7\31\2\2\u010e\u0110\5\34\17\2\u010f\u0109"+
		"\3\2\2\2\u010f\u010c\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111"+
		"\u0112\3\2\2\2\u0112\37\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0115\b\21\1"+
		"\2\u0115\u0116\5\36\20\2\u0116\u011f\3\2\2\2\u0117\u0118\f\4\2\2\u0118"+
		"\u0119\7&\2\2\u0119\u011e\5\36\20\2\u011a\u011b\f\3\2\2\u011b\u011c\7"+
		"\'\2\2\u011c\u011e\5\36\20\2\u011d\u0117\3\2\2\2\u011d\u011a\3\2\2\2\u011e"+
		"\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120!\3\2\2\2"+
		"\u0121\u011f\3\2\2\2\u0122\u0123\b\22\1\2\u0123\u0124\5 \21\2\u0124\u0133"+
		"\3\2\2\2\u0125\u0126\f\6\2\2\u0126\u0127\7\35\2\2\u0127\u0132\5 \21\2"+
		"\u0128\u0129\f\5\2\2\u0129\u012a\7\"\2\2\u012a\u0132\5 \21\2\u012b\u012c"+
		"\f\4\2\2\u012c\u012d\7\36\2\2\u012d\u0132\5 \21\2\u012e\u012f\f\3\2\2"+
		"\u012f\u0130\7!\2\2\u0130\u0132\5 \21\2\u0131\u0125\3\2\2\2\u0131\u0128"+
		"\3\2\2\2\u0131\u012b\3\2\2\2\u0131\u012e\3\2\2\2\u0132\u0135\3\2\2\2\u0133"+
		"\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134#\3\2\2\2\u0135\u0133\3\2\2\2"+
		"\u0136\u0137\b\23\1\2\u0137\u0138\5\"\22\2\u0138\u0141\3\2\2\2\u0139\u013a"+
		"\f\4\2\2\u013a\u013b\7\37\2\2\u013b\u0140\5\"\22\2\u013c\u013d\f\3\2\2"+
		"\u013d\u013e\7 \2\2\u013e\u0140\5\"\22\2\u013f\u0139\3\2\2\2\u013f\u013c"+
		"\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142"+
		"%\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\b\24\1\2\u0145\u0146\5$\23\2"+
		"\u0146\u014c\3\2\2\2\u0147\u0148\f\3\2\2\u0148\u0149\7+\2\2\u0149\u014b"+
		"\5$\23\2\u014a\u0147\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c"+
		"\u014d\3\2\2\2\u014d\'\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\b\25\1"+
		"\2\u0150\u0151\5&\24\2\u0151\u0157\3\2\2\2\u0152\u0153\f\3\2\2\u0153\u0154"+
		"\7*\2\2\u0154\u0156\5&\24\2\u0155\u0152\3\2\2\2\u0156\u0159\3\2\2\2\u0157"+
		"\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158)\3\2\2\2\u0159\u0157\3\2\2\2"+
		"\u015a\u015b\b\26\1\2\u015b\u015c\5(\25\2\u015c\u0162\3\2\2\2\u015d\u015e"+
		"\f\3\2\2\u015e\u015f\7)\2\2\u015f\u0161\5(\25\2\u0160\u015d\3\2\2\2\u0161"+
		"\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163+\3\2\2\2"+
		"\u0164\u0162\3\2\2\2\u0165\u0166\b\27\1\2\u0166\u0167\5*\26\2\u0167\u016d"+
		"\3\2\2\2\u0168\u0169\f\3\2\2\u0169\u016a\7#\2\2\u016a\u016c\5*\26\2\u016b"+
		"\u0168\3\2\2\2\u016c\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2"+
		"\2\2\u016e-\3\2\2\2\u016f\u016d\3\2\2\2\u0170\u0171\b\30\1\2\u0171\u0172"+
		"\5,\27\2\u0172\u0178\3\2\2\2\u0173\u0174\f\3\2\2\u0174\u0175\7$\2\2\u0175"+
		"\u0177\5,\27\2\u0176\u0173\3\2\2\2\u0177\u017a\3\2\2\2\u0178\u0176\3\2"+
		"\2\2\u0178\u0179\3\2\2\2\u0179/\3\2\2\2\u017a\u0178\3\2\2\2\u017b\u0181"+
		"\5.\30\2\u017c\u017d\5\32\16\2\u017d\u017e\7.\2\2\u017e\u017f\5\60\31"+
		"\2\u017f\u0181\3\2\2\2\u0180\u017b\3\2\2\2\u0180\u017c\3\2\2\2\u0181\61"+
		"\3\2\2\2\u0182\u0183\5\60\31\2\u0183\63\3\2\2\2\u0184\u018b\5\66\34\2"+
		"\u0185\u018b\58\35\2\u0186\u018b\5:\36\2\u0187\u018b\5<\37\2\u0188\u018b"+
		"\5D#\2\u0189\u018b\5F$\2\u018a\u0184\3\2\2\2\u018a\u0185\3\2\2\2\u018a"+
		"\u0186\3\2\2\2\u018a\u0187\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u0189\3\2"+
		"\2\2\u018b\65\3\2\2\2\u018c\u0190\7\3\2\2\u018d\u018f\5\64\33\2\u018e"+
		"\u018d\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2"+
		"\2\2\u0191\u0193\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0194\7\4\2\2\u0194"+
		"\67\3\2\2\2\u0195\u0197\5\62\32\2\u0196\u0195\3\2\2\2\u0196\u0197\3\2"+
		"\2\2\u0197\u0198\3\2\2\2\u0198\u0199\7\65\2\2\u01999\3\2\2\2\u019a\u019b"+
		"\7\f\2\2\u019b\u019c\7,\2\2\u019c\u019d\5\62\32\2\u019d\u019e\7-\2\2\u019e"+
		"\u01a1\5\64\33\2\u019f\u01a0\7\r\2\2\u01a0\u01a2\5\64\33\2\u01a1\u019f"+
		"\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2;\3\2\2\2\u01a3\u01a4\7\17\2\2\u01a4"+
		"\u01a5\7,\2\2\u01a5\u01a6\5\62\32\2\u01a6\u01a7\7-\2\2\u01a7\u01a8\5\64"+
		"\33\2\u01a8\u01b9\3\2\2\2\u01a9\u01aa\7\16\2\2\u01aa\u01ac\7,\2\2\u01ab"+
		"\u01ad\5> \2\u01ac\u01ab\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01ae\3\2\2"+
		"\2\u01ae\u01b0\7\65\2\2\u01af\u01b1\5@!\2\u01b0\u01af\3\2\2\2\u01b0\u01b1"+
		"\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b4\7\65\2\2\u01b3\u01b5\5B\"\2\u01b4"+
		"\u01b3\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b7\7-"+
		"\2\2\u01b7\u01b9\5\64\33\2\u01b8\u01a3\3\2\2\2\u01b8\u01a9\3\2\2\2\u01b9"+
		"=\3\2\2\2\u01ba\u01bb\5\62\32\2\u01bb?\3\2\2\2\u01bc\u01bd\5\62\32\2\u01bd"+
		"A\3\2\2\2\u01be\u01bf\5\62\32\2\u01bfC\3\2\2\2\u01c0\u01c1\7\20\2\2\u01c1"+
		"\u01ca\7\65\2\2\u01c2\u01c3\7\21\2\2\u01c3\u01ca\7\65\2\2\u01c4\u01c6"+
		"\7\22\2\2\u01c5\u01c7\5\62\32\2\u01c6\u01c5\3\2\2\2\u01c6\u01c7\3\2\2"+
		"\2\u01c7\u01c8\3\2\2\2\u01c8\u01ca\7\65\2\2\u01c9\u01c0\3\2\2\2\u01c9"+
		"\u01c2\3\2\2\2\u01c9\u01c4\3\2\2\2\u01caE\3\2\2\2\u01cb\u01cc\5\6\4\2"+
		"\u01cc\u01cf\7\25\2\2\u01cd\u01ce\7.\2\2\u01ce\u01d0\5\62\32\2\u01cf\u01cd"+
		"\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d2\7\65\2\2"+
		"\u01d2G\3\2\2\2\63KPX]bku{\u0083\u008d\u0090\u009d\u00a0\u00a4\u00ad\u00b0"+
		"\u00bf\u00d3\u00d6\u00dd\u00df\u00f3\u0101\u0103\u010f\u0111\u011d\u011f"+
		"\u0131\u0133\u013f\u0141\u014c\u0157\u0162\u016d\u0178\u0180\u018a\u0190"+
		"\u0196\u01a1\u01ac\u01b0\u01b4\u01b8\u01c6\u01c9\u01cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}