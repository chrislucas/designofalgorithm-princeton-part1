package impl;

public class Heap {

    public static void sort(Comparable<? super Comparable> [] values) {
        int n = values.length;
        for (int i = n/2; i >= 1 ; i--) {
            topDown(values, i, n);
        }
        while (n>1) {
            swap(values, 1, n--);
            topDown(values, 1, n);
        }
    }

    private  static boolean less(Comparable<? super Comparable> [] values, int i, int j) {
        i--; j--;
        return values[i].compareTo(values[j]) < 0;
    }

    private static void swap(Object [] values, int i, int j) {
        i--; j--;
        Object aux = values[i];
        values[i] = values[j];
        values[j] = aux;
    }

    private static void topDown(Comparable<? super Comparable>[] values, int parent, int n) {
        while (2*parent <= n) {
            int c = 2 * parent; // child-left
            if (c < n && less(values, c, c+1))
                c++; // child-right
            // no pai for maior que o filho
            if (!less(values, parent, c))
                break;
            swap(values, parent, c);
            parent = c;
        }
    }
}
