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
 | left=expression firstOp=op1 right=expression     #op1Expression
 | left=expression secondOp=op2 right=expression     #op2Expression
 | IDENTIFIER                                       #identifierExpression
 ;

op1
 : AND
 ;

op2
 : OR | XOR | IMPLY
 ;

bool
 : TRUE | FALSE
 ;