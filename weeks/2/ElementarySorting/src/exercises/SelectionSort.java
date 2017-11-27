package exercises;

import java.util.Random;

public class SelectionSort {

    public static int [] sample(int p, int q) {
        Random random = new Random();
        int limit = Math.abs(p-q)+1;
        int min = Math.min(p, q);
        int [] array = new int[limit];
        for(int i=0; i<limit; i++) {
            array[i] = random.nextInt(limit) + min + 1;
        }
        return array;
    }

    public static boolean lessThan(int a, int b) {
        return a < b;
    }

    public static void swap(int [] array, int i, int j) {
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }

    /**
     * Ver o comentario no arquivo InsertionSort.java comparando
     * SelectionSort com InsertionSort
     * */
    public static void sort(int [] array) {
        int sz = array.length;
        for (int i = 0; i <sz ; i++) {
            int min = i;
            for (int j = i+1; j <sz ; j++) {
                if(lessThan(array[j], array[min]))
                    min = j;
            }
            if(min>i)
                swap(array, min, i);
        }
    }

    public static void print(int [] array) {
        for(int a : array) {
            System.out.printf("%d ", a);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int [] array = sample(10,30);
        print(array);
        sort(array);
        print(array);

    }

}
