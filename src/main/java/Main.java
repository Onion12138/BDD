import core.*;
import datastructure.Vertex;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("========Welcome to the BDD Diagram Generator System========");
        System.out.println("Please input the formula in the following format:");
        System.out.println("formula  := (formula)");
        System.out.println("          | ! formula");
        System.out.println("          | formula op formula");
        System.out.println("          | variable");
        System.out.println("variable := [a-zA-Z_] [a-zA-Z_0-9]*");
        System.out.println("op       := + | *");
        boolean quit = false;
        while (!quit) {
            System.out.println("Input a valid formula and end with ';' or Quit with 'quit;': ");
            System.out.print(">>> ");
            StringBuilder formula = new StringBuilder();
            while (true) {
                input = scanner.nextLine();
                if (!";".equals(input.substring(input.length() - 1))) {
                    formula.append(input);
                } else {
                    formula.append(input, 0, input.length() - 1);
                    break;
                }
                System.out.print("... ");
            }
            String formulaStr = formula.toString();
            if ("quit".equals(formulaStr)) {
                quit = true;
            } else {
                BDD(formulaStr);
            }
        }
    }

    private static void BDD(String formula) {
        Parser parser = new Parser();
        List<String> tokens;
        try {
            tokens = parser.parse(formula);
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
            System.out.println("Please input a valid formula.");
            return;
        }
        Vertex vertex = Algorithm.getRootVertex(tokens);
        Algorithm.reset();
        Algorithm.traverse(vertex);
        List<Vertex> traverseList = Algorithm.getTraverse();
        Drawer drawer = new Drawer();
        System.out.println("Drawing the diagram...");
        try {
            drawer.draw(traverseList);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Try again.");
            return;
        }
        try {
            Runtime.getRuntime().exec("open ./graph.png");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Something wrong and can't open the graph.");
        }
    }


}
