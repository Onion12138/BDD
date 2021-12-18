package core;

import datastructure.Vertex;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.model.Link.to;

public class Drawer {
    public static boolean getBool(Vertex vertex) {
        return vertex.getVal() == 1;
    }
    public void draw(List<Vertex> traverse) throws IOException {
        List<String> tokenList = new ArrayList<>(Algorithm.getTokenSet());
        MutableGraph g = mutGraph("graph").setDirected(true).use((gr, ctx) -> {
            for (Vertex vertex : traverse) {
                if (isNondeterminal(vertex)) {
                    MutableNode startNode = mutNode(String.valueOf(vertex.getId()));
                    int index = vertex.getIndex();;
                    startNode.add(Label.of(tokenList.get(index - 1)));
                    startNode.add(Shape.CIRCLE);
                    if (isNondeterminal(vertex.getLow())) {
                        MutableNode endNode = mutNode(String.valueOf(vertex.getLow().getId()));
                        index = vertex.getLow().getIndex();
                        endNode.add(Label.of(tokenList.get(index - 1)));
                        startNode.addLink(to((endNode)).with(Style.DASHED).add(Label.of("0")));
                    } else {
                        MutableNode endNode = mutNode(getBool(vertex.getLow()) ? "1" : "0");
                        endNode.add(Shape.RECTANGLE);
                        startNode.addLink(to((endNode)).with(Style.DASHED).add(Label.of("0")));
                    }
                    if (isNondeterminal(vertex.getHigh())) {
                        MutableNode endNode = mutNode(String.valueOf(vertex.getHigh().getId()));
                        index = vertex.getHigh().getIndex();
                        endNode.add(Label.of(tokenList.get(index - 1)));
                        startNode.addLink(to((endNode)).add(Label.of("1")));
                    } else {
                        MutableNode endNode = mutNode(getBool(vertex.getHigh()) ? "1" : "0");
                        endNode.add(Shape.RECTANGLE);
                        startNode.addLink(to((endNode)).add(Label.of("1")));
                    }
                }
            }
        });
        Graphviz.fromGraph(g).width(900).height(900).render(Format.PNG).toFile(new File("graph.png"));
    }
    private boolean isNondeterminal(Vertex vertex) {
        return vertex.getLow() != null && vertex.getHigh() != null;
    }
}
