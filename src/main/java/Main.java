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
//        input = scanner.nextLine();
        while (!(input = scanner.next()).equals("#")) {
            tokens.add(input);
        }
        ParserProtocol parser = new SimpleParser();
        List<String> parsedTokens = parser.parse(tokens);
//        Parser parser = new Parser();
//        List<String> parsedTokens = parser.parse(input);
        System.out.println(parsedTokens);
        Algorithm.calculateMaxIndex(parsedTokens);
        Vertex vertex = Algorithm.getRootVertex(parsedTokens);
        Algorithm.reset();
        Algorithm.traverse(vertex);
        List<Vertex> traverseList = Algorithm.getTraverse();
        Drawer drawer = new Drawer();
        drawer.draw(traverseList);
    }
}
