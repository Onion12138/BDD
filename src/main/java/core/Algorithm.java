package core;

import datastructure.Key;
import datastructure.Vertex;

import java.util.*;

public class Algorithm {
    private static int maxIndex;
    private static Set<String> tokenSet;
    private static final List<Vertex> traverseVertex = new ArrayList<>();
    private static int globalId = 0;
    public static Set<String> getTokenSet() {
        return tokenSet;
    }
    public static void traverse(Vertex vertex) {
        if (vertex != null) {
            vertex.setMask(!vertex.isMask());
            traverseVertex.add(vertex);
            if (vertex.getLow() != null) {
                if (vertex.isMask() != vertex.getLow().isMask()) {
                    traverse(vertex.getLow());
                }
            }
            if (vertex.getHigh() != null) {
                if (vertex.isMask() != vertex.getHigh().isMask()) {
                    traverse(vertex.getHigh());
                }
            }
        }
    }
    public static void reset() {
        traverseVertex.clear();
    }
    public static List<Vertex> getTraverse() {
        return new ArrayList<>(traverseVertex);
    }
    private static Vertex traverseAndInvert(Vertex v) {
        if (v != null) {
            if (v.getLow() != null) {
                traverseAndInvert(v.getLow());
            }
            if (v.getHigh() != null) {
                traverseAndInvert(v.getHigh());
            }
            if (!v.isInverted()) {
                if (v.getVal() != -1) {
                    v.setVal(1 - v.getVal());
                }
                v.setInverted(true);
            }
        }
        return v;
    }
    public static Map<String, Integer> assignIndex(List<String> parsedTokens) {
        tokenSet = new TreeSet<>();
        Map<String, Integer> token2id = new HashMap<>();
        for (String token : parsedTokens) {
            if (!Util.isOperator(token)) {
                tokenSet.add(token);
            }
        }
        int index = 1;
        for (String token : tokenSet) {
            token2id.put(token, index++);
        }
        maxIndex = tokenSet.size();
        return token2id;
    }
    public static Vertex getRootVertex(List<String> parsedTokens) {
        Stack<Vertex> stack = new Stack<>();
        Map<String, Integer> token2id = assignIndex(parsedTokens);
        for (String token : parsedTokens) {
            if (Util.isOperator(token)) {
                if ("+".equals(token) || "*".equals(token)) {
                    Vertex b = stack.pop();
                    Vertex a = stack.pop();
                    stack.push(apply(a, b, token));
                } else if ("!".equals(token)){
                    Vertex a = stack.pop();
                    Vertex ra = traverseAndInvert(a);
                    stack.push(ra);
                }
            } else {
                stack.push(new Vertex(token2id.get(token), maxIndex));
            }
        }
        return stack.pop();
    }
    private static void traverseAndSetId(Vertex vertex) {
        if (vertex != null) {
            vertex.setMask(!vertex.isMask());
            vertex.setId(globalId);
            globalId ++;
            if (vertex.getLow() != null) {
                if (vertex.isMask() != vertex.getLow().isMask()) {
                    traverseAndSetId(vertex.getLow());
                }
            }
            if (vertex.getHigh() != null) {
                if (vertex.isMask() != vertex.getHigh().isMask()) {
                    traverseAndSetId(vertex.getHigh());
                }
            }
        }
    }
    private static Vertex apply(Vertex v1, Vertex v2, String operator) {
        traverseAndSetId(v1);
        int m = globalId;
        globalId = 0;
        traverseAndSetId(v2);
        int n = globalId;
        Vertex[][] T = new Vertex[m][n];  // sparse matrix, use hash table to optimize
        Vertex u = applyStep(v1, v2, operator, T);
        return reduce(u);
    }
    private static Vertex applyStep(Vertex v1, Vertex v2, String operator, Vertex[][] T) {
        Vertex u = T[v1.getId()][v2.getId()];
        if (u != null) {  // have already evaluated
            return u;
        }
        u = new Vertex();
        T[v1.getId()][v2.getId()] = u;  // add vertex to table
        u.setVal(Util.calculate(v1.getVal(), v2.getVal(), operator));
        if (u.getVal() != -1) {  // create terminal vertex
            u.setIndex(maxIndex + 1);
            u.setLow(null);
            u.setHigh(null);
        } else {  // create nonterminal vertex and evaluate further down
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
    private static Map<Integer, List<Vertex>> getVlist(List<Vertex> vertexList) {
        Map<Integer, List<Vertex>> ret = new HashMap<>();
        for (Vertex vertex : vertexList) {
            ret.computeIfAbsent(vertex.getIndex(), $ -> new ArrayList<>()).add(vertex);
        }
        return ret;
    }
    private static class Pair implements Comparable<Pair>{
        private Key key;
        private Vertex u;
        Pair(Key key, Vertex u) {
            this.key = key;
            this.u = u;
        }
        @Override
        public int compareTo(Pair pair) {
            return this.key.compareTo(pair.key);
        }
    }
    private static Vertex reduce(Vertex vertex) {
        reset();
        traverse(vertex);
        List<Vertex> vertexList = getTraverse();
        Vertex[] subgraph = new Vertex[vertexList.size() + 1];
        int n = vertexList.stream().map(Vertex::getIndex).max(Comparator.comparingInt(a -> a)).get();
        Map<Integer, List<Vertex>> vlist = getVlist(vertexList);
        int nextId = 0;
        for (int i = n; i >= 1; i--) {
            if (!vlist.containsKey(i)) {
                continue;
            }
            List<Pair> Q = new ArrayList<>();
            List<Vertex> vertices = vlist.get(i);
            for (Vertex u : vertices) {
                if (u.getIndex() == n) { // terminal vertex
                    Key key = new Key(u.getVal(), 0);
                    Q.add(new Pair(key, u));
                } else if (u.getLow().getId() == u.getHigh().getId()) { // redundant vertex
                    u.setId(u.getLow().getId());
                } else {
                    Key key = new Key(u.getLow().getId(), u.getHigh().getId());
                    Q.add(new Pair(key, u));
                }
            }
            Collections.sort(Q);
            Key oldKey = new Key(-1, -1);  // unmatchable key
            for (Pair pair : Q) {
                Key key = pair.key;
                Vertex u = pair.u;
                if (key.getLowid() == oldKey.getLowid() && key.getHighid() == oldKey.getHighid()) {
                    u.setId(nextId);  // matches existing vertex
                } else {  // unique vertex
                    nextId ++;
                    u.setId(nextId);
                    subgraph[nextId] = u;
                    if (u.getLow() != null) {
                        u.setLow(subgraph[u.getLow().getId()]);
                    }
                    if (u.getHigh() != null) {
                        u.setHigh(subgraph[u.getHigh().getId()]);
                    }
                    oldKey.setLowid(key.getLowid());
                    oldKey.setHighid(key.getHighid());
                }
            }
        }
        return subgraph[vertex.getId()];
    }
}
