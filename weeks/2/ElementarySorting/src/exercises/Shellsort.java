package exercises;

import java.util.Random;

public class Shellsort {


    public static boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void swap(Comparable [] array, int i, int j) {
        Comparable aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }


    /**
     *
     * A idea do shellsort eh mover um elemento do array mais do que uma posicao de distância
     * por vez, utilizando o denominado um valor de distância h-sorting do array
     *
     * Mas como escolher o h-sorting para ordenacao
     * */

    public static void sort(Comparable [] array) {
        int limit = array.length;
        int h = 1;
        /**
         * No curso de design de algoritmos o professor Robert Sedgewicj sugere que h cresça usando
         * a formula h=3h+1 => {4, 13, 40, 121, 364 ...}
         * */
        while (h<limit/3)
            h=h*3+1;
        System.out.printf("Size: %d h-index %d\n", limit, h);
        while (h>0) {
            for (int i = h; i < limit ; i++) {
                /**
                 * o loop abaixo permite realizar as trocas onde
                 * os indices tem diferenca h
                 * */
                for (int j = i; j >=h && lessThan(array[j], array[j-h]) ; j-=h) {
                    swap(array, j, j-h);
                }
            }
            h/=3;
        }
    }

    private static void print(Comparable [] array) {
        for(Comparable a : array)
            System.out.printf("%s ", a);
        System.out.println("");
    }

    public static void main(String[] args) {
        Comparable [] S = GenerateSamples.sample(1, 15);
        print(S);
        sort(S);
        print(S);
    }

}
