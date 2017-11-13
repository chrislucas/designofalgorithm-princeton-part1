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


    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)
            return;
        if(weight[p] < weight[q]) {
            weight[rootP] = rootQ;
            weight[rootQ] += weight[rootP];
        }
        else {
            weight[rootQ] = weight[rootP];
            weight[rootP] += weight[rootQ];
        }
        count--;
    }

    public int find(int p) {
        if(p == tree[p])
            return p;
        tree[p] = tree[tree[p]];
        return find(tree[p]);
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public static void main(String[] args) {

    }

}
