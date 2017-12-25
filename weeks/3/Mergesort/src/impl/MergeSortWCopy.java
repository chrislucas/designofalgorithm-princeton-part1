package impl;

/**
 * Versao do algoritmo que nao precisa fazer uma copia do array original
 * na hora de unir os subarrays ordenados
 *
 * */
public class MergeSortWCopy {


    public static boolean lessThan(Comparable [] array, int i, int j) {
        return array[i].compareTo(array[j]) < 0;
    }

    public static long merge(Comparable [] dat, Comparable[] cpy, int lo, int mid, int hi) {
        long acc = 0;
        int i = lo, j = mid+1;
        for(int k=lo; k<=hi; k++) {
            if(i>mid)   // elementos antes do meio do subarray ja estao ordenados
                cpy[k] = dat[j++];
            else if(j > hi)
                cpy[k] = dat[i++];
            // se o elemento do lado direito do meio do subarray for menor que o do lado esquerdo
            else if(lessThan(dat, j, i)) {
                cpy[k] = dat[j++];  // adicione o elemento do lado esquerdo
                acc += (mid - i + 1);
            }
            else
                cpy[k] = dat[i++];   // senao basta pegar o elemento do lado esquerdo do subarray
        }
        return acc;
    }

    public static long sort(Comparable [] dat, Comparable [] cpy, int lo, int hi) {
        long acc = 0;
        if(lo>=hi)
            return acc;
        int mid = (hi - lo) / 2 + lo;
        acc += sort(cpy, dat, lo, mid);
        acc += sort(cpy, dat, mid+1, hi);
        if(lessThan(dat, mid, mid+1)) {
            System.arraycopy(dat, lo, cpy, lo, hi - lo + 1);
            return acc;
        }
        acc += merge(dat, cpy, lo, mid, hi);
        return acc;
    }

    public static void print(Comparable [] array) {
        for (Comparable c: array)
            System.out.printf("%s ", c.toString());
        System.out.println("");
    }

    public static void main(String[] args) {
        Integer [][] dat = {
            {4,5,2,3,-1,20,1,0,-4}
        };
        int idx = 0;
        print(dat[idx]);
        Integer [] cpy = dat[idx].clone();
        sort(dat[idx], cpy, 0, dat[idx].length-1);
        print(dat[idx]);
    }

}
