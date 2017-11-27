package exercises;

import java.util.Random;

public class Shellsort {

    public static Comparable [] sample(int p, int q) {
        Random random = new Random();
        int limit = Math.abs(p-q)+1;
        int min = Math.min(p, q);
        Comparable [] array = new Integer[limit];
        for(int i=0; i<limit; i++) {
            array[i] = random.nextInt(limit) + min + 1;
        }
        return array;
    }

    public static boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void swap(Comparable [] array, int i, int j) {
        Comparable aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }



    public static void main(String[] args) {

    }

}
