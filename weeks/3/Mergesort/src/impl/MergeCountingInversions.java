package impl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * https://www.hackerrank.com/challenges/ctci-merge-sort/problem
 * DONE
 * */

public class MergeCountingInversions {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    public static boolean lessThan(int a, int b) {
        return a < b;
    }

    public static long merge(int [] array, int [] aux, int lo, int mid, int hi) {
        long counter = 0;
        /**
         * Criando um array auxiliar a partir de um subarray de subproblema
         * do algoritmo mergesort
         * */
        for (int i = lo; i <= hi ; i++)
            aux[i] = array[i];
        /**
         * mid marcar o meio do array. o valor i serve
         * para iterar da esquerda ate o meio [lo, mid]
         * o valor j serve para iterar do meio ate o final
         * do subarray, [mid+1, hi]
         *
         *
         * */
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            /**
             * se i > mid quer dizer que todos os elementos
             * antes do meio do subarray sao menores que subarray[mid]
             * assim basta preencher o subarray a partir do meio pra frente
             * com os valores que restam no array auxiliar
             * */
            if(i>mid)
                array[k] = aux[j++];
            /**
             * Se j > hi
             * */
            else if(j>hi)
                array[k] = aux[i++];
            /**
             * Se temos um elemento do subarray que esta do lado direito do meio
             * que eh menor que um elemento do lado esquerdo, devemos fazer a troca
             * */
            else if(lessThan(aux[j], aux[i])) {
                array[k] = aux[j++];
                counter += (mid-i+1);
            }
            /**
             * Aqui os valores do lado esquerdo do subarray sao menores que
             * o valor subarray[mid] entao so preciso reposiciona-los
             */
            else {
                array[k] = aux[i++];
            }
        }
        return counter;
    }

    public static long countInversion(int [] array, int [] aux, int lo, int hi) {
        long acc = 0;
        if(hi<=lo)
            return acc;
        int mid = (hi - lo) / 2 + lo;
        acc += countInversion(array, aux, lo, mid);
        acc += countInversion(array, aux, mid+1, hi);
        acc += merge(array, aux, lo, mid, hi);
        return acc;
    }

    public static void main(String[] args) {
        try {
            int cases = Integer.parseInt(reader.readLine().trim());
            while (cases-->0) {
                int size = Integer.parseInt(reader.readLine().trim());
                StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
                int [] array = new int [size];
                int [] aux = new int [size];
                for (int i = 0; tk.hasMoreTokens() ; i++)
                    array[i] = Integer.parseInt(tk.nextToken());
                writer.printf("%d\n", countInversion(array, aux, 0, size-1));
            }
        } catch (Exception e) {}
    }
}
