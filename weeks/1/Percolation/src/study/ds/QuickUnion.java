package study.ds;

public class QuickUnion {

    int count;

    public QuickUnion(int size) {
        ds = new int[size];
        for(int i=0; i<size; i++) {
            ds[i] = i+1;
        }
        count = 0;
    }

    /**
     * ds[ith] guarda a referencia para o no pai de ith
     *
     * [0,1,9,4,9,6,6,7,8,9]
     *
     * o vetor acima quer dizer que o no 9 tem como filhos (2,4)
     * e o no seis temo como filho (5)
     *
     * O no raiz de ith eh encontrado de forma recursiva
     * ds[ ds[ ds[ ds[ith] ] ]] ate que ds[ith] == ith
     *
     * */

    public int [] ds;

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        ds[rootP] = rootQ;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }


    public int root(int ith) {
        if(ds[ith] == ith)
            return ith;
        return root(ds[ith]);
    }


    public static void main(String[] args) {

    }

}
