package impl;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;

public class MinPQ<K extends Comparable<K>> implements Iterable<K> {

    private K [] keys;
    private int controlSize = 0;

    private Comparator<K> comparator;

    public MinPQ() {
        this(1);
    }

    private MinPQ(int controlSize) {
        this.controlSize = controlSize;
        this.keys = (K[]) new Comparable[controlSize];
    }


    public MinPQ(int controlSize, Comparator<K> comparator) {
        this(controlSize);
        this.comparator = comparator;
    }

    public MinPQ(K [] keys, Comparator<K> comparator) {
        this.comparator = comparator;
        this.controlSize = keys.length;
        System.arraycopy(keys, 0, this.keys, 0, controlSize);
        for (int i = controlSize/2; i>1 ; i--)
            heapify(i);
    }

    private boolean greaterThan(int p, int q) {
        return comparator == null
                ? keys[p].compareTo(keys[q]) > 0 : comparator.compare(keys[p], keys[q]) > 0;
    }


    private void exch(int p, int q) {
        K aux = keys[p];
        keys[p] = keys[q];
        keys[q] = aux;
    }

    private void heapify(int i) {
        topDown(i);
    }

    // from leaf to root
    private void bottomUp(int k) {
        // se o no pai for maior que o no folha
        while(k > 1 && greaterThan(k/2, k)) {
            // colocar o no folha como no pai (min)
            exch(k, k/2);
            k/=2;
        }
    }

    // from root to leaf
    private void topDown(int k) {
        while (2*k <= controlSize) {
            int leaf = 2*k;
            //Vamos comparar o menor valor com o valor guardado no no raiz
            // se o no da esquerda for maior do que o da direita
            if (leaf < controlSize && greaterThan(leaf, leaf+1))
                leaf++; //usamos o no da direita para compara com o no raiz
            // se o no raiz nao for maior que as folhas, a estrutura heap esta correta
            if ( ! greaterThan(k, leaf))
                break;
            // o menor valor torna-se o no pai
            exch(k, leaf);
            k = leaf;
        }
    }

    public boolean isMinHeap(int k) {
        if (k > controlSize)
            return true;
        int l = 2*k;
        int r = 2*k+1;

        if ( (l < controlSize && greaterThan(k, l)) || (r < controlSize && greaterThan(k, r)))
            return false;

        boolean p = isMinHeap(l);
        boolean q = isMinHeap(r);
        return p && q;
    }

    @NotNull
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public K next() {
                return null;
            }
        };
    }

    public static void main(String[] args) {

    }
}
