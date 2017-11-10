package study.ds;

public class WeightedQuickUnionPathCompression {

    public static int [] tree;
    public static int [] weight;
    public static int count = 0;

    public static void init(int size) {
        tree = new int [size];
        weight = new int[size];
        for(int i=0; i<size; i++) {
            tree[i] = i;
            weight[i] = 1;
        }
        count = size;
    }


    public static void main(String[] args) {

    }

}
