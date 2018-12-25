package impl;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private class HeapIterator implements Iterator<Key> {

        private MaxPQ<Key> maxPQ;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Key next() {
            return null;
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

    public MaxPQ(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
    }

    public MaxPQ(int capacity, Comparator<Key> comparator) {
        this.keys = (Key[]) new Comparable[capacity];
        this.comparator = comparator;
    }

    public MaxPQ(Key [] keys, Comparator<Key> comparator) {
        this.keys = keys;
        this.controlSize = keys.length;
        this.comparator = comparator;
        for (int i = 0; i < controlSize ; i++)
            this.keys[i] = keys[i];

        for (int i = controlSize/2; i >= 1 ; i--)
            heapify(i);
    }

    public MaxPQ(Key [] keys) {
        this.keys = keys;
        this.controlSize = keys.length;
        System.arraycopy(keys, 0, this.keys, 0, controlSize);
        for (int i = controlSize/2; i >= 1 ; i--)
            heapify(i);
    }

    public void insert(Key key) { }

    public Key delMax() { return null; }

    public boolean isEmpty() { return controlSize == 0; }

    public Key max() { return null;}

    public int size() { return  0;}

    private void exch( int i, int j) {
        Key aux = keys[i];
        keys[i] = keys[j];
        keys[j] = aux;
    }


    public void heapify(int node) {
        topDownheapify(node);
    }

    /**
     * a partir de um no 'folha' corrigimos a estrutura heap
     * subindo para o no 'raiz'
     * */
    private void bottomUpheapify(int node) {
        // se no pai for menor que o no folha
        while (node > 1 && lessThan(node/2, node)) {
            // colocar o no folha como no pai
            exch(node, node/2);
            node /= 2;
        }
    }

    /**
     * A partir de um no raiz corrigimos a estrutura heap
     * descendo para os nós 'folhas'
     * */
    private void topDownheapify(int node) {
        while (2*node <= controlSize) {
            int leaf = 2*node; // no esquerdo
            //Vamos comparar o maior valor (no esquerdo|direito) com o valor do no raiz
            // comparamos o no da esquerda com o da direita
            // se o no da esquerda for menor
            if (leaf < controlSize && lessThan(leaf, leaf+1))
                // , vamos usar o no da para verificar a necessidade de consertar a estrutura heap
                leaf++;
            // se o no filho folha for menor que o raiz, a estrutura esta correta
            if(lessThan(leaf, node))
                break;
            // o maior valor torna-se o no pai
            exch(leaf, node);
            node = leaf;
        }
    }

    private  boolean lessThan(int p, int q) {
        return comparator == null ? keys[p].compareTo(keys[q]) < 0 :  comparator.compare(keys[p], keys[q]) < 0;
    }

    private boolean isMaxHeap() {
        return isMaxHeap(0);
    }

    private boolean isMaxHeap(int k) {
        if(k > controlSize)
            return true;
        int l = 2*k;
        int r = 2*k+1;
        if( (l < controlSize && lessThan(k, l)) || (r < controlSize && lessThan(k, r)))
            return false;
        boolean p = isMaxHeap(l);
        boolean q = isMaxHeap(r);
        return p && q;
    }

    @NotNull
    @Override
    public Iterator<Key> iterator() {
        return new Iterator<Key>() {

            int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < controlSize;
            }

            @Override
            public Key next() {
                return keys[idx++];
            }
        };
    }

    private static void printIterator(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void testPQString() {
        String [] data = {"P", "r", "i", "o", "r"
                , "i", "t", "y", "Q", "u", "e", "u", "e"};
        MaxPQ<String> maxPQ = new MaxPQ<>(data);
        printIterator(maxPQ.iterator());
    }

    private static void testPQInteger() {
        Integer [] data = {6, 4, 5, 3, 2, 0, 1};
        MaxPQ<Integer> maxPQ = new MaxPQ<>(data);
        printIterator(maxPQ.iterator());
    }

    public static void main(String[] args) {
        testPQInteger();
    }
}
