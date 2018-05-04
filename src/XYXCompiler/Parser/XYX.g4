grammar XYX;

program //ok
    :   declaration+
    ;

declaration  //ok
    :   functionDeclaration
    |   classDeclaration
    |   globalVariableDeclaration
    ;

type //ok
    :   Bool                                            #BoolType
    |   Int                                             #IntType
    |   String                                          #StringType
    |   Void                                            #VoidType
    |   Identifier                                      #IdType
    |   type LeftBracket expression? RightBracket       #ArrayType
    ;

classDeclaration //ok
    :   Class Identifier '{' classMembers* '}'
    ;

classMembers
    :   variableDeclaration ';'
    |   functionDeclaration
    |   constructFunctionDeclaration
    ;

globalVariableDeclaration //ok
    :   type Identifier ('=' expression)? ';' //这是变量声明语句
    ;

variableDeclaration //ok
    :   (type Identifier ('=' expression)?)  //这是函数参数声明
    ;

functionDeclaration //ok
    :   type Identifier '(' (variableDeclaration (',' variableDeclaration)*)? ')' compoundStatement
    |   Void Identifier '(' (variableDeclaration (',' variableDeclaration)*)? ')' compoundStatement
    ;

constructFunctionDeclaration //ok
    :   Identifier '(' (variableDeclaration (',' variableDeclaration)*)? ')' compoundStatement
    ;

// 字面值
constant //ok
    :   True
    |   False
    |   IntegerConstant
    |   StringConstant
    |   Null
    ;

primaryExpression //ok
    :   constant
    |   Identifier
    |   LeftParen expression RightParen
    |   New type
    ;

suffixExpression //ok
    :   primaryExpression
//  |   suffixExpression Dot Identifier LeftParen (expression (',' expression)*)? RightParen
    |   suffixExpression Dot Identifier //Accessing
    |   suffixExpression LeftBracket expression RightBracket //Indexing
    |   suffixExpression LeftParen (expression (',' expression)*)? RightParen //funccall
    |   suffixExpression PlusPlus
    |   suffixExpression MinusMinus
//  |   suffixExpression '.size()'
    ;

unaryExpression //ok
    :   suffixExpression
    |   PlusPlus suffixExpression
    |   MinusMinus suffixExpression
    |   Tilde suffixExpression
    |   Not suffixExpression
    |   Plus suffixExpression
    |   Minus suffixExpression
    |   Tilde unaryExpression  //add
    |   Minus unaryExpression  //add
    ;

multiplicativeExpression //ok
    :   unaryExpression
    |   multiplicativeExpression Mul unaryExpression
    |   multiplicativeExpression Div unaryExpression
    |   multiplicativeExpression Mod unaryExpression
    ;

additiveExpression //ok
    :   multiplicativeExpression
    |   additiveExpression Plus multiplicativeExpression
    |   additiveExpression Minus multiplicativeExpression
    ;

shiftExpression //ok
    :   additiveExpression
    |   shiftExpression LeftShift additiveExpression
    |   shiftExpression RightShift additiveExpression
    ;

relationExpression //ok
    :   shiftExpression
    |   relationExpression Less shiftExpression
    |   relationExpression LessEqual shiftExpression
    |   relationExpression Greater shiftExpression
    |   relationExpression GreaterEqual shiftExpression
    ;

equalityExpression //ok
    :   relationExpression
    |   equalityExpression Equal relationExpression
    |   equalityExpression NotEqual relationExpression
    ;

bitwiseAndExpression //ok
    :   equalityExpression
    |   bitwiseAndExpression '&' equalityExpression
    ;

bitwiseExclusiveOrExpression //ok
    :   bitwiseAndExpression
    |   bitwiseExclusiveOrExpression '^' bitwiseAndExpression
    ;

bitwiseInclusiveOrExpression //ok
    :   bitwiseExclusiveOrExpression
    |   bitwiseInclusiveOrExpression '|' bitwiseExclusiveOrExpression
    ;

logicalAndExpression //ok
    :   bitwiseInclusiveOrExpression
    |   logicalAndExpression '&&' bitwiseInclusiveOrExpression
    ;

logicalOrExpression //ok
    :   logicalAndExpression
    |   logicalOrExpression '||' logicalAndExpression
    ;

assignmentExpression //ok
    :   logicalOrExpression
    |   unaryExpression '=' assignmentExpression
    ;

expression //ok
    :   assignmentExpression
//    |   expression (Comma expression) +
    ;

statement //ok
    :   compoundStatement
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   jumpStatement
    |   variableDeclarationStatement
    ;

compoundStatement //ok
    :   '{' statement* '}'
    ;

expressionStatement //ok
    :   expression? ';'
    ;

selectionStatement //ok
    :   If '('expression')' statement (Else statement)?
    ;

iterationStatement //ok
    :   While '('expression')' statement    # whileLoop
    |   For '('init?';'condition?';'step?')' statement     # forLoop
    ;
// overlooked
init
    :   expression
    ;

condition
    :   expression
    ;

step
    :   expression
    ;

jumpStatement //ok
    :   Break ';'               # breakStatement
    |   Continue ';'            # continueStatement
    |   Return expression? ';'  # returnStatement
    ;

variableDeclarationStatement //ok
    :   type Identifier ('=' expression)? ';'
    ;


// reserved keywords
Bool    :   'bool';
Int     :   'int';
String  :   'string';
Null    :   'null';
Void    :   'void';
True    :   'true';
False   :   'false';
If      :   'if';
Else    :   'else';
For     :   'for';
While   :   'while';
Break   :   'break';
Continue:   'continue';
Return  :   'return';
New     :   'new';
Class   :   'class';


Identifier
    :   Nondigit([a-zA-Z0-9_])*
    ;

fragment
Nondigit
    :   [a-zA-Z_]
    ;

fragment
Digit
    :   [0-9]
    ;


IntegerConstant     // always DecimalConstant
    :   NonzeroDigit Digit*
    |   '0'
    ;

fragment
NonzeroDigit
    :   [1-9]
    ;

StringConstant
    :   '"' Char* '"'
    ;

fragment
Char
    :   PrintableChar
    |   EscapeChar
    ;

fragment
PrintableChar
    :   ~["\\\r\n]
    ;

fragment
EscapeChar
    : '\\\''
	| '\\"'
	| '\\?'
	| '\\\\'
	| '\\a'
	| '\\b'
	| '\\f'
	| '\\n'
	| '\\r'
	| '\\t'
	| '\\v'
    ;


//operators
Plus            :  '+';
Minus           :  '-';
Mul             :  '*';
Div             :  '/';
Mod             :  '%';
Less            :  '<';
Greater         :  '>';
Equal           : '==';
NotEqual        : '!=';
GreaterEqual    : '>=';
LessEqual       : '<=';
AndAnd          : '&&';
OrOr            : '||';
Not             :  '!';
LeftShift       : '<<';
RightShift      : '>>';
Tilde           :  '~';
Or              :  '|';
Xor             :  '^';
And             :  '&';
LeftParen       :  '(';
RightParen      :  ')';
Assign          :  '=';
PlusPlus        : '++';
MinusMinus      : '--';
Dot             :  '.';
LeftBracket     :  '[';
RightBracket    :  ']';
Colon           :  ':';
Semi            :  ';';
Comma           :  ',';
Question        :  '?';


WhiteSpace
    :   [ \t]+ -> skip ;

NewLine
    :   (   '\r' '\n'? | '\n' ) -> skip ;

// 注释
LineComment
    :   '//' ~[\r\n]* -> skip ;