package core;

import core.*;
import datastructure.Vertex;
import javafx.scene.control.Alert;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Slf4j(topic = "Transfer")
public class Transfer {

//    public static void main(String[] args) {
//        run();
//    }

    public static File run(String input,int quality) {
//        String input;
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("========Welcome to the BDD Diagram Generator System========");
//        System.out.println("Please input the formula in the following format:");
//        System.out.println("formula  := (formula)");
//        System.out.println("          | ! formula");
//        System.out.println("          | formula op formula");
//        System.out.println("          | variable");
//        System.out.println("variable := [a-zA-Z_] [a-zA-Z_0-9]*");
//        System.out.println("op       := + | *");
//        boolean quit = false;
//        while (!quit) {
//            System.out.println("Input a valid formula and end with ';' or Quit with 'quit;': ");
//            System.out.print(">>> ");
//            StringBuilder formula = new StringBuilder();
//            while (true) {
//                input = scanner.nextLine();
//                if (input.length() == 0) {
//                    System.out.print("... ");
//                    continue;
//                }
//                if (!";".equals(input.substring(input.length() - 1))) {
//                    formula.append(input);
//                } else {
//                    formula.append(input, 0, input.length() - 1);
//                    break;
//                }
//                System.out.print("... ");
//            }
//            String formulaStr = formula.toString();
//            if ("quit".equals(formulaStr)) {
//                quit = true;
//            } else {
//                BDD(formulaStr);
//            }
//        }
        return BDD(input,quality);
    }

    private static File BDD(String formula,int quality) {
        Parser parser = new Parser();
        List<String> tokens;
        try {
            tokens = parser.parse(formula);
        } catch (Exception e) {
            log.warn("Invalid input: " + e.getMessage());
            log.warn("Please input a valid formula.");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INVALID INPUT");
            alert.setHeaderText("Syntax wrong, check your input");
            alert.showAndWait();
//            System.out.println("Invalid input: " + e.getMessage());
//            System.out.println("Please input a valid formula.");
            return null;
        }
        Vertex vertex = Algorithm.getRootVertex(tokens);
        Algorithm.reset();
        Algorithm.traverse(vertex);
        List<Vertex> traverseList = Algorithm.getTraverse();
        Drawer drawer = new Drawer();
//        System.out.println("Drawing the diagram...");
        try {
            return drawer.draw(traverseList,quality);
        } catch (IOException e) {
            log.error("Error: " + e.getMessage());
            log.error("Try again.");
//            return;
        }
//        try {
//            Runtime.getRuntime().exec("open ./graph.png");
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//            System.out.println("Something wrong and can't open the graph.");
//        }
        return null;
    }


}


//========Welcome to the BDD Diagram Generator System========
//Please input the formula in the following format:
//formula  := (formula)
//          | ! formula
//          | formula op formula
//          | variable
//variable := [a-zA-Z_] [a-zA-Z_0-9]*
//op       := + | *