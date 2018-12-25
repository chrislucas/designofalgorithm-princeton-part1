public class ImplHeap {


    private static void heapify(Comparable<Comparable> [] data, int parent, int n) {
        while (2*parent <= n) {
            int c = 2*parent; // left child node
            if (c < n && lessThan(data, c, c+1))
                c++;
            if (!lessThan(data, parent, c))
                break;
            exch(data, parent, c);
            parent = c;
        }
    }

    private static boolean lessThan(Comparable<Comparable> [] data, int i, int j) {
        return data[i].compareTo(data[j]) < 0;
    }

    private static void exch(Object [] data, int i, int j) {
        Object e = data[i];
        data[i] = data[j];
        data[j] = e;
    }

    public static void main(String[] args) {

    }
}
