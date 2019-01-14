package impl;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private class HeapIterator implements Iterator<Key> {

        private MaxPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) {
                copy = new MaxPQ<>(size());
            }
            else {
                copy = new MaxPQ<>(size(), comparator);
            }

            for (int i = 1; i <= controlSize; i++) {
                copy.insert(keys[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return ! copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy.delMax();
        }

        @Override
        public void remove() {}
    }

    private Key [] keys;
    private int controlSize = 0;

    private Comparator<Key> comparator;

    public MaxPQ() {
        this(1);
    }

    private MaxPQ(int capacity) {
        this.keys = (Key[]) new Comparable[capacity + 1];
    }

    public MaxPQ(int capacity, Comparator<Key> comparator) {
        this(capacity);
        this.comparator = comparator;
    }

    public MaxPQ(Key [] keys, Comparator<Key> comparator) {
        this(keys);
        this.comparator = comparator;
    }

    MaxPQ(Key[] keys) {
        this.controlSize = keys.length;
        this.keys = (Key []) new Object[keys.length + 1];
        System.arraycopy(keys, 1, this.keys, 0, controlSize);
        for (int i = controlSize/2; i >= 1 ; i--)
            bottomUpHeapify(i);
    }

    private void insert(Key key) {
        if (controlSize == keys.length - 1)
            resize(keys.length * 2);
        keys[++controlSize] = key;
        bottomUpHeapify(controlSize);
    }

    private Key delMax() {
        if (isEmpty())
            throw new NoSuchElementException("PQ vazia");
        Key max = keys[1];
        swap(1, controlSize--);
        topDownHeapify(1);
        keys[controlSize+1] = null;
        if (controlSize > 0 && (controlSize == (keys.length-1)/4))
            resize(keys.length/2);
        return max;
    }

    private void resize(int capacity) {
        Key [] temp = (Key []) new Object[capacity];
        System.arraycopy(keys, 1, temp, 1, controlSize);
        keys = temp;
    }


    private boolean isEmpty() { return controlSize == 0; }

    public Key max() throws Exception {
        if (isEmpty())
            throw new Exception("PQ vazia");
        return keys[0];
    }

    public int size() { return controlSize;}

    private void swap(int i, int j) {
        Key aux = keys[i];
        keys[i] = keys[j];
        keys[j] = aux;
    }


    public void heapify(int node) {
        topDownHeapify(node);
    }

    /**
     * a partir de um no 'folha' corrigimos a estrutura heap
     * subindo para o no 'raiz''- 'swim
     * */
    private void bottomUpHeapify(int node) {
        // se no pai for menor que o no folha
        while (node > 1 && lessThan(node/2, node)) {
            // colocar o no folha como no pai
            swap(node, node/2);
            node /= 2;
        }
    }

    /**
     * A partir de um no raiz corrigimos a estrutura heap
     * descendo para os nós 'folhas' - sink'
     * */
    private void topDownHeapify(int parent) {
        while (2*parent+1 < controlSize) {
            int leaf = 2*parent; // no esquerdo
            // Vamos comparar o maior valor (no esquerdo | direito) com o valor do no raiz
            // comparamos o no da esquerda com o da direita
            // se o no da esquerda for menor
            if (leaf < controlSize && lessThan(leaf, leaf+1))
                // , vamos usar o no da direita para verificar a necessidade de consertar a estrutura heap
                leaf++;
            // se o no filho-folha for menor que o raiz, a estrutura esta correta
            if(lessThan(leaf, parent))
                break;
            // o maior valor torna-se o no pai
            swap(leaf, parent);
            parent = leaf;
        }
    }

    private  boolean lessThan(int p, int q) {
        return comparator == null ? keys[p].compareTo(keys[q]) < 0 :  comparator.compare(keys[p], keys[q]) < 0;
    }

    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if(k > controlSize)
            return true;
        int l = 2*k;
        int r = 2*k+1;
        if( (l <= controlSize && lessThan(k, l)) || (r <= controlSize && lessThan(k, r)))
            return false;
        boolean p = isMaxHeap(l);
        boolean q = isMaxHeap(r);
        return p && q;
    }

    @NotNull
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }
}
