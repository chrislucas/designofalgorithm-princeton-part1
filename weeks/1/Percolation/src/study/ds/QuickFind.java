package study.ds;

public class QuickFind {


    int count;

    public QuickFind(int size) {
        ds = new int[size];
        for(int i=0; i<size; i++) {
            ds[i] = i+1;
        }
        count = size;
    }

    public int [] ds;

    public boolean isConnected(int p, int q) {
        return ds[p] == ds[q];
    }

    /**
     * Unir o no p ao q
     * todos os nos que eram filhos de quem p eh filho
     * se tornaram filhos do pai de q
     * */
    public void union(int p, int q) {
        int pid = ds[p];    // no ancestral de p
        int qid = ds[p];    // no ancestral de q
        for(int idx=0; idx<ds.length; idx++) {
            if(ds[idx] == pid) {
                ds[idx] = qid;
            }
        }
        count--;
    }

    public boolean validate(int p) {
        return p > -1 && p < ds.length;
    }


    public int find(int p) {
        return validate(p) ? ds[p] : -1;
    }

    public static void main(String[] args) {

    }
}
