package study.ds;

public class WeightedQuickUnion {


    public int [] ds, weight;
    public int components;


    public WeightedQuickUnion(int size) {
        ds = new int[size];
        weight = new int[size];
        for (int i = 0; i <size ; i++) {
            ds[i] = i;
            weight[i] = 1;
        }
        components = size;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ)
            return;
        if(weight[p] < weight[q]) {
            ds[rootP] = rootQ;
            weight[rootQ] += weight[rootP];
        }
        else {
            ds[rootQ] = ds[rootP];
            weight[rootP] += weight[rootQ];
        }
        components--;
    }

    public int find(int p) {
        if(p == ds[p])
            return p;
        return find(ds[p]);
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }


    public static void main(String[] args) {

    }

}
