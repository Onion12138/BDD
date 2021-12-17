// Generated from /Users/yuechen/Developer/java-projects/BDD/src/main/java/parser/BoolParser.g4 by ANTLR 4.9.2
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BoolParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(BoolParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code op1Expression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1Expression(BoolParser.Op1ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(BoolParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code op2Expression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2Expression(BoolParser.Op2ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(BoolParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link BoolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(BoolParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BoolParser#op1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1(BoolParser.Op1Context ctx);
	/**
	 * Visit a parse tree produced by {@link BoolParser#op2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2(BoolParser.Op2Context ctx);
	/**
	 * Visit a parse tree produced by {@link BoolParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(BoolParser.BoolContext ctx);
}