package utility;

public class Node {
    public int key;
    public Object value;
    public int height;
    public Node leftChild;
    public Node rightChild;

    public Node(int key, Object value) {
        leftChild = null;
        rightChild = null;
        this.key = key;
        this.value = value;
        height = 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
