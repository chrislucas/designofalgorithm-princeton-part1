package impl;

public class MaxPQ<Key extends Comparable<Key>> {
    private final Key [] keys;
    private int controlSize = 0;
    public MaxPQ(Key[] keys) {
        this.keys = keys;
    }

    public void insert(Key key) { }

    public Key delMax() { return null; }

    public boolean isEmpty() { return controlSize == 0; }

    public Key max() { return null;}

    public int size() { return  0;}

    public static void main(String[] args) {

    }
}
