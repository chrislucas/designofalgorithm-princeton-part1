package project;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] data;
    private int controlSize, head, tail;
    public RandomizedQueue() {
        data = (Item[]) new Object[1];
        tail = head = controlSize = 0;
    }

    public boolean isEmpty() {
        return controlSize == 0;
    }

    public int size() {
        return controlSize;
    }

    public void enqueue(Item item) {
        if(item == null)
            throw new IllegalArgumentException();
        if(controlSize == data.length)
            resize(data.length * 2);
        data[tail++] = item;
        /**
         * caso especial: Apos algumas delecoes a variavel que cuida do fim da
         * lista pode aumentar mais rapido do que a que controla a quantidade de elementos
         * nela e assim ultrapassar o tamanho maximo atual da estrutura de dados. Como
         * nessa estrutura os elementos sao renovidos a partir da variavel que cuida do comeco
         * da fila ('head') e sempre que necessario redimensiona-mos o array, os primeiros indices
         * ficam vagos, assim podemos zerar a variavel que cuida do fim da lista, usa-la
         * quando quisermos adicionar itens na lista, que quando chegar o momento, o algoritmo
         * ira ajustar o tamanho da estrutura de dados, redefinir o valor da variavel que cuisa
         * do final da fila e posicionar os elementos que estavam no começo da fila nesse caso
         * especial para o final dela.
         * */
        if(tail == data.length)
            tail = 0;
        controlSize++;
    }

    private void resize(int capacity) {
        Item [] items = (Item[]) new Object[capacity];
        int size = data.length;
        /**
         * Loop que vai de 0 ate o numero de itens atualmente
         * na estrutura para refazer a estrutura com o novo tamano
         * */
        for(int idx=0; idx<controlSize; idx++)
            /**
             * Caso interessante: Adicione um numero de elementos
             * do tipo 2^m, remova alguns elementos e depois adicione
             * uma quantidade 2^m, onde m > n. I que ocorrera eh que
             * a veriavel que cuida do começo da fila ('head') mais a quantidade
             * de elementos que tem na fila ('controle') ira passar do tamanho
             * limite da estrutura de dados atual ('data'), por isso usamos o operador
             * modulo ('%') pois os elementos que estao numa posicao cujo valor 'head' +
             * 'quantidade de itens' > size estao exatamente no comeco da lista e precisam
             * ser reajustados para o final dela. Como o loop varia 0 <= idx < controlSize
             * a instrucao (head+idx)%size garante que os elementos serao postos nos devidos
             * lugares.
             * */
            items[idx] = data[(head+idx)%size];
        data = items;
        head = 0;
        tail = controlSize;
    }

    /**
     *         Item item = data[head];
     *         data[head++] = null;
     *         if(head == data.length)
     *         head = 0;
     *         Essa implementacao me pareceu a mais correta pois ao remover
     *         itens do meio da fila
     * */
    private Item dequeue2() {
        if(isEmpty())
            throw new NoSuchElementException();
        int indexRandom = controlSize > 1 ? getRandomIndex() : 0;
        controlSize--;
        if(indexRandom == head) {
            head++;
            if(head == data.length)
                head = 0;
        }
        Item item = data[indexRandom];
        data[indexRandom] = null;
        for(int idx=indexRandom; idx<controlSize; idx++) {
            data[idx] = data[idx+1];
            data[idx+1] = null;
        }
        tail = controlSize;
        if(controlSize > 0 && controlSize == data.length / 4)
            resize(data.length/2);
        return item;
    }

    public Item dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();
        int indexRandom = controlSize > 1 ? getRandomIndex() : tail;
        controlSize--;
        if(indexRandom == head) {
            head++;
            if(head == data.length)
                head = 0;
        }
        Item item = null;
        do {
            item = data[indexRandom];
            indexRandom = controlSize > 1 ? getRandomIndex() : tail;
        } while (item == null);
        data[indexRandom] = data[tail];
        data[tail--] = null;
        tail = controlSize;
        if(controlSize > 0 && controlSize == data.length / 4)
            resize(data.length/2);
        return item;
    }

    private int getRandomIndex() {
       //return StdRandom.uniform(0, controlSize);
       //return StdRandom.uniform(head == 0 ? 1 : head, tail == 0 ? 2 : tail);
        int min = Math.min(head == 0 ? 1 : head, tail == 0 ? 2 : tail);
        return StdRandom.uniform(min, controlSize+1);
    }

    private int getRandomIndex(int p, int q) {
        return StdRandom.uniform(p, q);
    }

    public Item sample() {
        if(isEmpty())
            throw new NoSuchElementException();
        Item sampl  = null;
        int attempts = 0;
        while (sampl == null || attempts < controlSize) {
            sampl = data[getRandomIndex()];
            attempts++;
        }
        return sampl;
    }

    private int [] shuffle() {
        int [] indexes = new int[controlSize];
        for(int i=0; i<controlSize; i++) {
            indexes[i] = i;
        }
        for(int i=0; i<controlSize; i++) {
            int r = StdRandom.uniform(i+1);
            int aux = indexes[r];
            indexes[r] = indexes[i];
            indexes[i] = aux;
        }
        return indexes;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int indexes [] = shuffle();
            private int  size = data.length, counter = 0;
            @Override
            public boolean hasNext() {
                return counter < controlSize;
            }

            @Override
            public Item next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                return data[((indexes[counter++])+head)%size];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static void test() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for(int i=0; i<(1<<4)+1; i++)
            queue.enqueue(i);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        for (int i=7; i<(1<<6)+1; i++)
            queue.enqueue(32*i);
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void test1() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("Maria");
        queue.enqueue("Marta");
        queue.enqueue("Joana");
        queue.enqueue("Estela");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("Juliana");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("Adriana");
        queue.enqueue("Amanda");
        queue.enqueue("Milena");
        queue.dequeue();
        while (!queue.isEmpty())
            System.out.println(queue.dequeue());
        queue.enqueue("Claudia");
        queue.enqueue("Beatriz");
        queue.enqueue("Juliana");
        queue.enqueue("Denise");
        queue.enqueue("Antonia");
        queue.enqueue("Bianca");
        while (!queue.isEmpty())
            System.out.println(queue.dequeue());
    }

    public static void main(String[] args) {
        //test();
        test1();
    }
}
