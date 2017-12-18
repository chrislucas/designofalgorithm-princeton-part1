package impl;

/**
 * Essa versao nao copia os dados do array original para um array auxiliar
 * */
public class Mergesort {

    private static final int THRESHOLD_SIZE = 7;

    public static boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static long merge(Comparable[] data, Comparable [] aux, int lo, int mid, int hi) {
        long counter = 0;
        // isso divide o subarray em 2 partes
        int i = lo  // a partir do comeco do subarray
                , j = mid+1;    // a partir do meio do subarray
        for (int k = lo; k <= hi ; k++) {
            /**
             * Se o indice i passou do meio do array, todos os elementos
             * a esquerda estao ordenados
             * */
            if(i > mid) {
                aux[k] = data[j++];     // adicione os elementos do lado direito ao vetor
            }
            /**
             * Se o indice j for maior que hi, os elementos da direita ja estao ordenados
             * */
            else if(j > hi) {
                aux[k] = data[i++];     // adicione os elementos do lado esquerdo do subvetor
            }
            /**
             * se um elemento a direita do meio do subarray for
             * menor que um elemento a esquerda, faça a inversao
             * */
            if(lessThan(data[j], data[i])) {
                aux[k] = data[j++];
                counter += mid - i + 1;
            }
            /**
             * No ultimo caso, os elementos estao ordenados e so precisamos preencher
             * o vetor da esquerda para direita
             * */
            else {
                aux[k] = data[i++];
            }
        }
        return counter;
    }

    public static void swap(Comparable [] data, int i, int j) {
        Comparable aux = data[i];
        data[i] = data[j];
        data[j] = aux;
    }

    public static void insertionSortIncrease(Comparable [] data, int lo, int hi) {
        for (int i = lo; i <= hi ; i++) {
            for (int j = i; j > lo && lessThan(data[j], data[j-1]) ; j--) {
                swap(data, j, j-1);
            }
        }
    }

    public static long sort(Comparable [] data, Comparable [] aux, int lo, int hi) {
        long counter = 0;
        if(lo+THRESHOLD_SIZE>=hi) {
            insertionSortIncrease(aux, lo, hi);
            return counter;
        }
        int mid = (hi - lo) / 2 + lo;
        counter += sort(aux, data, lo, mid);
        counter += sort(aux, data, mid+1, hi);
        if(lessThan(data[mid], data[mid+1])) {
            System.arraycopy(data, lo, aux, lo, hi-lo+1);
            return counter;
        }
        counter += merge(data, aux, lo, mid, hi);
        return counter;
    }

    private static void print(Comparable [] data) {
        for(Comparable i : data)
            System.out.printf("%s ", i);
        System.out.println("");
    }

    public static void testMergeSort(Comparable [] data) {
        print(data);
        long inversions = sort(data, new Comparable[data.length]
                , 0, data.length-1);
        System.out.printf("Numero de inversoes %d\n", inversions);
        print(data);
    }

    public static void testInsertionSort(Comparable [] data) {
        print(data);
        insertionSortIncrease(data, 0, data.length-1);
        print(data);
    }

    public static void main(String[] args) {
        Integer [][] ints = new Integer[][] {
             {4,3,2,1}
            ,{1,5,4,8,10,2,6,9,3,7}
            ,{2,1,3,1,2}
        };
        int idx = 1;
        testInsertionSort(ints[idx]);
    }
}
