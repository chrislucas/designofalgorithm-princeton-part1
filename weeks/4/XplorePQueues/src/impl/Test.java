package impl;

import java.util.Iterator;

public class Test {

    static String [][] strings = {
            {"P", "r", "i", "o", "r"
                    , "i", "t", "y", "Q", "u", "e", "u", "e"}
            , {"T", "S", "R", "P", "N", "O", "A", "E", "I", "H", "G"}
            , {"S", "P", "R", "G", "T", "O", "A", "E", "I", "H", "G"}
            , {"U", "O", "I", "E", "A"}
    };

    static Integer [][] values ={
        {6, 4, 5, 3, 2, 0, 1}
    };

    private static void printIterator(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void testMaxPQStrings() {
        MaxPQ<String> maxPQ = new MaxPQ<>(strings[2]);
        printIterator(maxPQ.iterator());
    }

    private static void testMaxPQIntegers() {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(values[0]);
        printIterator(maxPQ.iterator());
    }

    private static void testMinPQString() {
        MinPQ<String> minPQ = new MinPQ<>(strings[2]);
        printIterator(minPQ.iterator());
    }

    static void print(Comparable [] values) {
        for (Comparable<?> s : values) {
            System.out.printf(" %s", s);
        }
        System.out.println("");
    }

    private static void testHeapSort() {
        Comparable[] vector = strings[3];
        Heap.sort(vector);

        vector = values[0];
        Heap.sort(vector);
    }

    public static void main(String[] args) {
        //testMaxPQStrings();
        testHeapSort();
    }
}
