package core;

import java.util.*;

public class SimpleParser implements ParserProtocol{
    @Override
    public List<String> parse(List<String> tokens) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String token : tokens) {
            if (Util.isOperator(token)) {
                if (s1.isEmpty() || s1.peek().equals("(")) {
                    s1.push(token);
                } else if (token.equals(")")) {
                    while (!s1.peek().equals("(")) {
                        s2.add(s1.pop());
                    }
                    s1.pop();
                } else {
                    while (!s1.isEmpty() && priority(s1.peek()) >= priority(token)) {
                        s2.add(s1.pop());
                    }
                    s1.push(token);
                }
            } else {
                s2.add(token);
            }
        }
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }
    private static int priority(String operator) {
        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("+", 1);
        priorityMap.put("*", 2);
        priorityMap.put("!", 3);
        priorityMap.put("(", 4);
        priorityMap.put(")", 4);
        return priorityMap.get(operator);
    }
}
