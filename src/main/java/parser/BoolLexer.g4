lexer grammar BoolLexer;

AND        : '&' | '*' ;
OR         : '|' | '+';
NOT        : '~' | '!';
XOR        : '^';
IMPLY       : '->';

TRUE       : 'true' ;
FALSE      : 'false' ;
LPAREN     : '(' ;
RPAREN     : ')' ;

IDENTIFIER : [a-zA-Z_] [a-zA-Z_0-9]* ;

WS         : [ \r\t\n]+ -> skip;