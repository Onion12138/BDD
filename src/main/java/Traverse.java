import java.util.ArrayList;
import java.util.List;

public class Traverse {
    private List<Vertex> traverseVertex;
    public Traverse() {
        traverseVertex = new ArrayList<>();
    }
    public void dfs(Vertex vertex) {
        if (vertex != null) {
            vertex.setMask(!vertex.isMask());
            traverseVertex.add(vertex);
            if (vertex.getLow() != null) {
                if (vertex.isMask() != vertex.getLow().isMask()) {
                    dfs(vertex.getLow());
                }
            }
            if (vertex.getHigh() != null) {
                if (vertex.isMask() != vertex.getHigh().isMask()) {
                    dfs(vertex.getHigh());
                }
            }
        }
    }
    public void reset() {
        traverseVertex.clear();
    }
    public List<Vertex> getTraverse() {
        return new ArrayList<>(traverseVertex);
    }
}
