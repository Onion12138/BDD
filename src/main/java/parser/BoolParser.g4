parser grammar BoolParser;

options
{
tokenVocab=BoolLexer;
}

parse
 : expression EOF
 ;

expression
 : LPAREN expression RPAREN                       #parenExpression
 | NOT expression                                 #notExpression
 | left=expression op=binary right=expression     #binaryExpression
 | IDENTIFIER                                           #boolExpression
 | bool                                     #identifierExpression
 ;

binary
 : AND | OR | XOR | IMPLY
 ;

bool
 : TRUE | FALSE
 ;