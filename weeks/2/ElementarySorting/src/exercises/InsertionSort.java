package exercises;

import java.util.Random;


/**
 *
 * O algoritmo InsertionSort leva um tempo linear (O(m))
 * para ordenar arrays parcialmente ordenados
 * */
public class InsertionSort {

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


    /**
     * Ponto interessante: SelectionSort Vs InsertionSort
     *
     * QUando caimos no caso do array estar ordenado
     * de forma ascendente e se for assim que o queremos,
     * o algorimto InsertionSor leva vantagem sobre o SelectionSort
     * Pois faz menos comparacoes e trocas, fazendo exatamente N-1 comparacoes
     * e nenhuma troca, diferente do SelectionSort que fara N^2 comparacoes
     *
     * Porem no caso do array estar em ordem decrescente, o algormto InsertionSort
     * faz muito mais trocas do que o SelectionSort, pois o segundo loop
     * percorre o array da direita para esquerda, e acaba trocando o elemento
     * mais a esquerda N-1 vezes, e isso vai ocorrer para os N elementos
     * enquanto o algoritmo SelectionSort fara N-1 comparacoes porem 1 troca
     *
     * */
    public static void sort(Comparable [] array) {
        int limit = array.length;
        for (int i = 0; i <limit-1 ; i++) {
            for (int j = i+1; j > 0; j--) {
                if(lessThan(array[j], array[j-1]))
                    swap(array, j, j-1);
                else
                    break;
            }
        }
    }

    public static void print(Comparable [] array) {
        for(Comparable a : array)
            System.out.printf("%s ", a);
        System.out.println("");
    }


    public static void main(String[] args) {
        Comparable [] array = sample(1,100);
        print(array);
        sort(array);
        print(array);
    }

}
