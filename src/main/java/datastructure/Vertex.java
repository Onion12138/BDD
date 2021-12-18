package datastructure;

import lombok.Data;

@Data
public class Vertex {
    private Vertex low;
    private Vertex high;
    private int index;
    private int val;
    private int id;
    private boolean mask;
    private boolean inverted;  // for negation
    private String token;
    public Vertex(int index, int maxIndex, String token) {
        this.low = new Vertex(true, maxIndex);
        this.high = new Vertex(false, maxIndex);
        this.index = index;
        this.val = -1;
        this.mask = false;
        this.inverted = false;
        this.token = token;
    }
    public Vertex(boolean low, int maxIndex) {
        this.low = null;
        this.high = null;
        this.index = maxIndex + 1;
        this.val = low ? 0 : 1;
        this.mask = false;
        this.inverted = false;
        this.token = "";
    }
    public Vertex() {
        this.token = "";
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "index=" + index +
                ", val=" + val +
                ", id=" + id +
                '}';
    }
}
