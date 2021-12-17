import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> tokens = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        input = scanner.nextLine();
//        while (!(s = scanner.next()).equals("#")) {
//            tokens.add(s);
//        }
//        ParserProtocol parser = new SimpleParser();
//        List<String> parsedTokens = parser.parse(tokens);
        Parser parser = new Parser();
        List<String> parsedTokens = parser.parse(input);
        Algorithm.calculateMaxIndex(parsedTokens);
        Vertex vertex = Algorithm.generateGraph(parsedTokens);
        Traverse traverse = new Traverse();
        traverse.dfs(vertex);
        List<Vertex> traverseList = traverse.getTraverse();
        // todo draw the graph

    }
}
