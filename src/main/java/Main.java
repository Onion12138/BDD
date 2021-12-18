import core.Algorithm;
import core.Drawer;
import core.ParserProtocol;
import core.SimpleParser;
import datastructure.Vertex;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> tokens = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!"#".equals(input = scanner.next())) {
            tokens.add(input);
        }
        ParserProtocol parser = new SimpleParser();
        List<String> parsedTokens = parser.parse(tokens);
        Vertex vertex = Algorithm.getRootVertex(parsedTokens);
        Algorithm.reset();
        Algorithm.traverse(vertex);
        List<Vertex> traverseList = Algorithm.getTraverse();
        Drawer drawer = new Drawer();
        drawer.draw(traverseList);
    }
}
