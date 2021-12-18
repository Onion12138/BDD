package datastructure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Key implements Comparable<Key>{
    private int lowid;
    private int highid;
    public Key() {}
    public Key(int lowid, int highid) {
        this.lowid = lowid;
        this.highid = highid;
    }
    @Override
    public int compareTo(Key key) {
        if (this.lowid < key.lowid) {
            return -1;
        } else if (this.lowid == key.lowid) {
            if (this.highid <= key.highid) {
                return -1;
            } else {
                return 1;
            }
        }
        return 1;
    }
    @Override
    public String toString() {
        return "Pair{" +
                "first=" + lowid +
                ", second=" + highid +
                '}';
    }
}

