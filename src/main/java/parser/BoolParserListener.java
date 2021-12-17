// Generated from /Users/yuechen/Developer/java-projects/BDD/src/main/java/parser/BoolParser.g4 by ANTLR 4.9.2
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BoolParser}.
 */
public interface BoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BoolParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(BoolParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoolParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(BoolParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code op1Expression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOp1Expression(BoolParser.Op1ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code op1Expression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOp1Expression(BoolParser.Op1ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(BoolParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(BoolParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code op2Expression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOp2Expression(BoolParser.Op2ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code op2Expression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOp2Expression(BoolParser.Op2ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(BoolParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(BoolParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(BoolParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(BoolParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoolParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(BoolParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link BoolParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(BoolParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link BoolParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(BoolParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link BoolParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(BoolParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link BoolParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(BoolParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoolParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(BoolParser.BoolContext ctx);
}