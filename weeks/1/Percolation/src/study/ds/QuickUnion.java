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

    public int [] ds;


    public void union(int p, int q) {

    }




    public static void main(String[] args) {

    }

}
