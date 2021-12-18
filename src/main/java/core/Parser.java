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
            } else { // expr op expr
                visit((BoolParser.ExpressionContext) ctx.getChild(0));
                visit((BoolParser.ExpressionContext) ctx.getChild(2));
                stringTree.add(ctx.getChild(1).getText());
            }
        } else if (ctx.getChildCount() == 2) { // not expr
            visit((BoolParser.ExpressionContext) ctx.getChild(1));
            stringTree.add("!");
        } else {
            stringTree.add(ctx.getChild(0).getText());
        }
    }

    public List<String> parse(String input) {
        CodePointCharStream stream = CharStreams.fromString(input);
        BoolLexer lexer = new BoolLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BoolParser parser = new BoolParser(tokens);
        BoolParser.ParseContext ctx = parser.parse();
        BoolParser.ExpressionContext exp = ctx.expression();
        stringTree = new ArrayList<>();
        visit(exp);
        return stringTree;
    }

//    public static void main(String[] args) {
//
//        core.Parser parser = new core.Parser();
//        List<String> parse = parser.parse("(a + b)*c");
//        System.out.println(111);
//    }
}
