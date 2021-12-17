import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Key implements Comparable<Key>{
    private int first;
    private int second;
    Key() {}
    Key(int first, int second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public int compareTo(Key key) {
        if (this.first < key.first) {
            return -1;
        } else if (this.first == key.first) {
            if (this.second <= key.second) {
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
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
