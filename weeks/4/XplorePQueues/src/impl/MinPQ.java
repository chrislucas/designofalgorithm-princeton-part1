package impl;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<K extends Comparable<K>> implements Iterable<K> {

    private class HeapIterator implements Iterator<K> {
        private MinPQ<K> copy;
        HeapIterator() {
            if (comparator == null)
                copy = new MinPQ<>(size());
            else
                copy = new MinPQ<>(size(), comparator);
            for (int i = 1; i <= controlSize ; i++) {
                copy.insert(keys[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public K next() {
            if (!hasNext())
                throw  new NoSuchElementException();
            return copy.delMin();
        }
    }

    private K [] keys;
    private int controlSize = 0;

    private Comparator<K> comparator;

    public MinPQ() {
        this(1);
    }

    private MinPQ(int capacity) {
        this.keys = (K[]) new Comparable[capacity+1];
    }

    private MinPQ(int capacity, Comparator<K> comparator) {
        this(capacity);
        this.comparator = comparator;
    }

    public MinPQ(K [] keys) {
        this.keys = (K []) new Object[keys.length+1];
        this.controlSize = keys.length;
        System.arraycopy(keys, 1, this.keys, 0, controlSize);
        for (int i = controlSize/2; i>1 ; i--)
            bottomUp(i);
    }

    public MinPQ(K [] keys, Comparator<K> comparator) {
        this(keys);
        this.comparator = comparator;
    }

    private int size() {
        return controlSize;
    }

    private K min() {
        if (isEmpty())
            throw new NoSuchElementException();
        return keys[1];
    }

    private void insert(K key) {
        if (controlSize == keys.length - 1)
            resize(keys.length*2);
        keys[++controlSize] = key;
        bottomUp(controlSize);
    }

    private K delMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        K min = keys[1];
        swap(1, controlSize--);
        topDown(1);
        keys[controlSize+1] = null;
        if ( (controlSize>0) && (controlSize == (keys.length-1)/4))
            resize(keys.length/2);
        return min;
    }

    private boolean isEmpty() {
        return controlSize == 0;
    }


    private void resize(int capacity) {
        K [] temp = (K []) new Object[capacity];
        System.arraycopy(this.keys, 1, temp, 1, controlSize);
        this.keys = temp;
    }


    private boolean greaterThan(int p, int q) {
        return comparator == null ? keys[p].compareTo(keys[q]) > 0 : comparator.compare(keys[p], keys[q]) > 0;
    }


    private void swap(int p, int q) {
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
            swap(k, k/2);
            k/=2;
        }
    }

    // from root to leaf
    private void topDown(int parent) {
        while (2*parent+1 < controlSize) {
            int c = 2*parent+1;
            //Vamos comparar o menor valor com o valor guardado no no raiz
            // se o no da esquerda for maior do que o da direita
            if (c < controlSize && greaterThan(c, c+1))
                c++; //usamos o no da direita para compara com o no raiz
            // se o no raiz nao for maior que as folhas, a estrutura heap esta correta
            if ( ! greaterThan(parent, c))
                break;
            // o menor valor torna-se o no pai
            swap(parent, c);
            parent = c;
        }
    }

    private boolean isMinHeap(int k) {
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
        return new HeapIterator();
    }
}
