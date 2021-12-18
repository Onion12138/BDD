package core;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.BoolLexer;
import parser.BoolParser;

import java.util.*;

public class Parser {

    private List<String> stringTree;

    private void visit(BoolParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            if (ctx.getChild(0).getText().equals("(")) { // (expr)
                visit((BoolParser.ExpressionContext) ctx.getChild(1));
            } else if (!ctx.getChild(1).getText().equals("->") && !ctx.getChild(1).getText().equals("^")) { // expr op expr and op is not -> and op is not ^
                visit((BoolParser.ExpressionContext) ctx.getChild(0));
                visit((BoolParser.ExpressionContext) ctx.getChild(2));
                String op = ctx.getChild(1).getText();
                if (op.equals("&")) {
                    stringTree.add("*");
                } else if (op.equals("|")) {
                    stringTree.add("+");
                } else {
                    stringTree.add(ctx.getChild(1).getText());
                }
            } else { // expr -> expr or expr ^ expr
                if (ctx.getChild(1).getText().equals("->")) { // expr -> expr
                    visit((BoolParser.ExpressionContext) ctx.getChild(0));
                    stringTree.add("!");
                    visit((BoolParser.ExpressionContext) ctx.getChild(2));
                    stringTree.add("+");
                } else { // ^
                    visit((BoolParser.ExpressionContext) ctx.getChild(0));
                    stringTree.add("!");
                    visit((BoolParser.ExpressionContext) ctx.getChild(2));
                    stringTree.add("*");
                    visit((BoolParser.ExpressionContext) ctx.getChild(2));
                    stringTree.add("!");
                    visit((BoolParser.ExpressionContext) ctx.getChild(0));
                    stringTree.add("*");
                    stringTree.add("+");
                }
            }
        } else if (ctx.getChildCount() == 2) { // not expr
            visit((BoolParser.ExpressionContext) ctx.getChild(1));
            stringTree.add("!");
        } else {
            stringTree.add(ctx.getChild(0).getText());
        }
    }

    public List<String> parse(String input) throws Exception {
        try {
            CodePointCharStream stream = CharStreams.fromString(input);
            BoolLexer lexer = new BoolLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            BoolParser parser = new BoolParser(tokens);
            BoolParser.ParseContext ctx = parser.parse();
            BoolParser.ExpressionContext exp = ctx.expression();
            stringTree = new ArrayList<>();
            visit(exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringTree;
    }
}
