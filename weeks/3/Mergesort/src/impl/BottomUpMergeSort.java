package impl;

public class BottomUpMergeSort {

    private static boolean less(Comparable [] dat, int i, int j) {
        return dat[i].compareTo(dat[j]) < 0;
    }

    private static long merge(Comparable [] dat, Comparable [] aux, int lo, int mid, int hi) {
        long acc = 0;
        int i=lo, j=mid+1;
        System.arraycopy(dat, lo, aux, lo, hi + 1 - lo);
        for(int k=lo; k<=hi; k++) {
            if(i > mid)
                dat[k] = aux[j++];
            else if(j > hi)
                dat[k] = aux[i++];
            else if(less(dat, j, i)) {
                dat[k] = aux[j++];
                acc += (mid - 1) + i;
            }
            else
                dat[k] = aux[i++];
        }
        return acc;
    }

    private static long sort(Comparable [] dat, Comparable [] aux, int lo, int hi) {
        long acc = 0;
        for (int i = 1; i <hi ; i*=2) {
            for (int j = 0; j<hi-i; lo=lo+2*i) {
                int m = Math.min(j+2*i-1, hi-1);
                acc+=merge(dat, aux, j, j+i-1, m);
            }
        }
        return acc;
    }

    private static void print(Comparable [] comparables) {
        for(Comparable comparable : comparables)
            System.out.printf("%s ", comparable.toString());
        System.out.println("");
    }

    public static void main(String[] args) {
        Integer [][] ints = new Integer[][] {
             {4,3,2,1}
            ,{1,5,4,8,10,2,6,9,3,7}
            ,{2,1,3,1,2}
        };
        int idx = 1;
        print(ints[idx]);
        int n = ints[idx].length;
        Integer [] cpy = new Integer[n];
        sort(ints[idx], cpy, 0, n);
        print(ints[idx]);
    }
}
