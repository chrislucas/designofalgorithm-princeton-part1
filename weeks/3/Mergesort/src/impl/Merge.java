package impl;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class Merge {
    
    public static boolean lessThan(Comparable<Comparable> a, Comparable<Comparable> b) {
        return a.compareTo(b) < 0;
    }

    public static long merge(Comparable [] data, Comparable[] aux
            , int lo, int mid, int hi) {
        long counter = 0;
        for (int i = lo; i <=hi ; i++)
            aux[i] = data[i];
        int i = lo              // subarray da esquerda
                , j = mid+1;    // subarray da direita
        /**
         * O vetor data de i ate mid estao os valores menores
         * de mid+1 ate hi estao os valores maiores
         * */
        for (int k = lo; k<=hi ; k++) {
            /**
             * i > mid  significa que estamos olhando para elementos
             * a direita do meio do array, que devem ser sempre maiores
             * que data[mid]
             * Dessa forma, se i > mid so precisamos adicionar os elementos
             * que estao a direita da metade do array, ao array original (merge)
             * */
            if(i>mid)
                data[k] = aux[j++];
            /**
             * j eh iniciado com o valor do indice mid+1 o que quer dizer
             * que data[j] esta alem do meio do subarray [lo,hi]
             * Se j > hi quer dizer que todos os valores apos o meio do subarray[lo,hi]
             * sao maiores ou iguais a subarray[mid], dessa forma basta adicionar ao array
             * original os valores anteriores a ao indice 'mid'
             * */
            else if(j > hi)
                data[k] = aux[i++];
            /**
             * Se o valor de aux[j] que esta a direita do elemento no meio
             * do subarray. Se aux[j] > aux[i]
             * */
            else if(lessThan(aux[j], aux[i])) {
                data[k] = aux[j++];
                /**
                 *
                 * */
                counter+= (mid - i + 1);
            }
            /**
             * se  aux[j] > aux[i] entao so precisamos adicionar
             * o valor na posicao i do array copia
             * */
            else
                data[k] = aux[i++];
        }
        return counter;
    }

    public static long sort(Comparable [] data, Comparable [] aux, int lo, int hi) {
        long acc = 0;
        if(hi <= lo)
            return acc;
        int mid = (hi - lo) / 2 + lo;
        acc  = sort(data, aux, lo, mid);
        acc += sort(data, aux, mid+1, hi);
        acc += merge(data, aux, lo, mid, hi);
        return acc;
    }

    public static Integer [] sample (int q) {
        Integer [] ints = new Integer[q];
        for(int i=0; i<q; i++)
            ints[i] = i;
        for(int i=0; i<q; i++) {
            int rand = StdRandom.uniform(i, q);
            Integer aux = ints[rand];
            ints[rand] = ints[i];
            ints[i] = aux;
        }
        return ints;
    }

    private static void print(Integer [] ints) {
        for(Integer i : ints)
            System.out.printf("%d ", i);
        System.out.println("");
    }

    public static void main(String[] args) {
        //Integer [] ints = sample(30);
        // {4,3,2,1};//{4, 3, 2, 1};
        Integer [][] ints = new Integer[][] {
             {4,3,2,1}
            ,{1,5,4,8,10,2,6,9,3,7}
            ,{2,1,3,1,2}
        };
        int idx = 1;
        print(ints[idx]);
        long inversions = sort(ints[idx], new Comparable[ints[idx].length], 0, ints[idx].length-1);
        System.out.printf("Numero de inversoes %d\n", inversions);
        print(ints[idx]);
    }
}