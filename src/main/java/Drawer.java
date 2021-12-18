import datastructure.Vertex;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.model.Link.to;

public class Drawer {

    public static boolean getBool(Vertex vertex) {
        return vertex.getVal() == 1;
    }

    public void draw(List<Vertex> traverse) throws IOException {
        MutableGraph g = mutGraph("graph").setDirected(true).use((gr, ctx) -> {
            Color color = Color.rgb(168, 214, 255);
            for (Vertex vertex : traverse) {
                if (isNondeterminal(vertex)) {
                    MutableNode startNode = mutNode(String.valueOf(vertex.getId())).add(Label.of(vertex.getIndex() + ""));
                    if (isNondeterminal(vertex.getLow())) {
                        MutableNode endNode = mutNode(String.valueOf(vertex.getLow().getId())).add(Label.of(vertex.getLow().getIndex() + ""));
                        startNode.addLink(to((endNode)).add(color).add(Label.of("0")));
                    } else {
                        MutableNode endNode = mutNode(getBool(vertex.getLow()) ? "true" : "false");
                        startNode.addLink(to((endNode)).add(color).add(Label.of("0")));
                    }
                    if (isNondeterminal(vertex.getHigh())) {
                        MutableNode endNode = mutNode(String.valueOf(vertex.getHigh().getId())).add(Label.of(vertex.getHigh().getIndex() + ""));
                        startNode.addLink(to((endNode)).add(color).add(Label.of("1")));
                    } else {
                        MutableNode endNode = mutNode(getBool(vertex.getHigh()) ? "true" : "false");
                        startNode.addLink(to((endNode)).add(color).add(Label.of("1")));
                    }
                }
            }
        });
        Graphviz.fromGraph(g).width(500).render(Format.PNG).toFile(new File("graph.png"));
    }
    private boolean isNondeterminal(Vertex vertex) {
        return vertex.getLow() != null && vertex.getHigh() != null;
    }
}
