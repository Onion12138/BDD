import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> tokens = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String s;
        while (!(s = scanner.next()).equals("#")) {
            tokens.add(s);
        }
        Parser parser = new SimpleParser();
        List<String> parsedTokens = parser.parse(tokens);
        Algorithm.calculateMaxIndex(parsedTokens);
        Vertex vertex = Algorithm.generateGraph(parsedTokens);
        Traverse traverse = new Traverse();
        traverse.dfs(vertex);
        List<Vertex> traverseList = traverse.getTraverse();
        System.out.println(traverseList);
        // todo draw the graph
        Drawer drawer = new Drawer();
        drawer.draw(traverseList);
    }
}
