import com.kitfox.svg.A;

import java.util.*;

public class Algorithm {
    private static int maxIndex;
    private static Map<String, Integer> token2id;
    public static void calculateMaxIndex(List<String> parsedTokens) {
        token2id = new HashMap<>();
        for (String token : parsedTokens) {
            if (!Util.isOperator(token)) {
                if (!token2id.containsKey(token)) {
                    token2id.put(token, token2id.size() + 1);
                }
            }
        }
        maxIndex = token2id.size();
    }
    public static Vertex generateGraph(List<String> parsedTokens) {
        Stack<Vertex> stack = new Stack<>();
        for (String token : parsedTokens) {
            if (Util.isOperator(token)) {
                if ("+".equals(token) || "*".equals(token)) {
                    Vertex b = stack.pop();
                    Vertex a = stack.pop();
                    stack.push(apply(a, b, token));
                }
            } else {
//                stack.push(new Vertex(token2id.get(token), maxIndex));
                stack.push(new Vertex(token.charAt(0) - 'A' + 1, maxIndex));
            }
        }
        return stack.pop();
    }
    private static Vertex apply(Vertex v1, Vertex v2, String operator) {
        Traverse traverse = new Traverse();
        traverse.dfs(v1);
        List<Vertex> firstTraverse = traverse.getTraverse();
        int m = firstTraverse.size();
        traverse.reset();
        traverse.dfs(v2);
        List<Vertex> secondTraverse = traverse.getTraverse();
        int n = secondTraverse.size();
        for (int i = 0; i < m; i++) {
            firstTraverse.get(i).setId(i);
        }
        for (int i = 0; i < n; i++) {
            secondTraverse.get(i).setId(i);
        }
        Vertex first = firstTraverse.get(0);
        Vertex second = secondTraverse.get(0);
        Vertex[][] T = new Vertex[m][n];
        Vertex u = applyStep(first, second, operator, T);
        return reduce(u);
    }
    private static Vertex applyStep(Vertex v1, Vertex v2, String operator, Vertex[][] T) {
        Vertex u = T[v1.getId()][v2.getId()];
        if (u != null) {
            return u;
        }
        u = new Vertex();
        T[v1.getId()][v2.getId()] = u;
        u.setVal(Util.calculate(v1.getVal(), v2.getVal(), operator));
        if (u.getVal() != -1) {  // terminal vertex
            u.setIndex(maxIndex + 1);
            u.setLow(null);
            u.setHigh(null);
        } else {  // nonterminal vertex
            u.setIndex(Math.min(v1.getIndex(), v2.getIndex()));
            Vertex vlow1, vhigh1, vlow2, vhigh2;
            if (v1.getIndex() == u.getIndex()) {
                vlow1 = v1.getLow();
                vhigh1 = v1.getHigh();
            } else {
                vlow1 = v1;
                vhigh1 = v1;
            }
            if (v2.getIndex() == u.getIndex()) {
                vlow2 = v2.getLow();
                vhigh2 = v2.getHigh();
            } else {
                vlow2 = v2;
                vhigh2 = v2;
            }
            u.setLow(applyStep(vlow1, vlow2, operator, T));
            u.setHigh(applyStep(vhigh1, vhigh2, operator, T));
        }
        return u;
    }
    private static Map<Integer, List<Vertex>> generateMap(List<Vertex> vertexList) {
        Map<Integer, List<Vertex>> ret = new HashMap<>();
        for (Vertex vertex : vertexList) {
            int index = vertex.getIndex();
            if (!ret.containsKey(index)) {
                List<Vertex> vt = new ArrayList<>();
                ret.put(index, vt);
            }
            List<Vertex> vertices = ret.get(index);
            vertices.add(vertex);
            ret.put(index, vertices);
        }
        return ret;
    }
    private static Vertex reduce(Vertex vertex) {
        Traverse traverse = new Traverse();
        traverse.dfs(vertex);
        List<Vertex> vertexList = traverse.getTraverse();
        Vertex[] subgraph = new Vertex[vertexList.size() + 1];
        Map<Integer, List<Vertex>> vlist = generateMap(vertexList);
        int maxIndex = vlist.keySet().stream().max(Comparator.comparingInt(a -> a)).get();
        int nextId = 0;
        for (int i = maxIndex; i > 0; i--) {
            if (vlist.containsKey(i)) {
                Map<Key, Vertex> q = new HashMap<>();
                List<Vertex> vertices = vlist.get(i);
                for (int j = 0; j < vertices.size(); j++) {
                    Vertex u = vertices.get(j);
                    if (u.getIndex() == maxIndex) {
                        Key key = new Key(u.getVal(), 0);
                        q.put(key, u);
                    } else if (u.getLow().getId() == u.getHigh().getId()) {
                        u.setId(u.getLow().getId());
                    } else {
                        Key key = new Key(u.getLow().getId(), u.getHigh().getId());
                        q.put(key, u);
                    }
                }
                List<Key> keyList = new ArrayList<>(q.keySet());
                Collections.sort(keyList);
                Key oldKey = new Key(-1, -1);
                for (Key key : keyList) {
                    if (key.getFirst() == oldKey.getFirst() && key.getSecond() == oldKey.getSecond()) {
                        q.get(key).setId(nextId);
                    } else {
                        nextId ++;
                        Vertex cur = q.get(key);
                        cur.setId(nextId);
                        subgraph[nextId] = cur;
                        if (cur.getLow() != null) {
                            cur.setLow(subgraph[cur.getLow().getId()]);
                        }
                        if (cur.getHigh() != null) {
                            cur.setHigh(subgraph[cur.getHigh().getId()]);
                        }
                        oldKey.setFirst(key.getFirst());
                        oldKey.setSecond(key.getSecond());
                    }
                }
            }
        }
        return subgraph[vertex.getId()];
    }
}
