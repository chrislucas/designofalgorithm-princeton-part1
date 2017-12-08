package project;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>  {
    private int controlSize = 0;
    public class Node {
        Item data;
        Node next, parent;
        public Node(Item data) { this.data = data; }
        @Override
        public String toString() {
            return String.format("Data %s, Next: %s Parent"
                , data
                , next != null ? next.toString() : "Null"
                , parent != null ? parent.toString() : "Null"
            );
        }
    }

    public Node first, last;

    public Deque() {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return controlSize;
    }

    /**
     * O metodo addFirst insere o item na ponta da estrutura Deque como se fosse
     * uma pilha
     * */
    public void addFirst(Item item) {
        if(item == null)
            throw new IllegalArgumentException();
        if(isEmpty()) {
            first = new Node(item);
        }
        else {
            Node oldFirst = first;
            first = new Node(item);
            oldFirst.parent = first;
            first.next = oldFirst;
        }
        if(last == null)
            last = first;
        controlSize++;
    }

    /**
     * O metodo addLast insere o item no final da estrutura Deque como se fosse
     * uma fila
     *
     * */
    public void addLast(Item item) {
        if(item == null)
            throw new IllegalArgumentException();
        Node old = last;
        last = new Node(item);
        if (isEmpty()) {
            first = last;
        }
        else {
            last.parent = old;
            old.next = last;
        }
        controlSize++;
    }

    public Item removeFirst() {
        if(isEmpty())
            throw new NoSuchElementException();
        Item data = first.data;
        first = first.next;
        if(isEmpty())
            last = null;
        controlSize--;
        return data;
    }

    /**
     * Remover o ultimo
     * */
    public Item removeLast() {
        if(isEmpty())
            throw new NoSuchElementException();
        Item data = last.data;
        last = last.parent;
        if(last == null)
            first = null;
        else
            last.next = null;
        controlSize--;
        return data;
    }

    @Override
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            private Node firstNode = first;

            @Override
            public boolean hasNext() {
                return firstNode != null;
            }

            @Override
            public Item next() {
                if(firstNode == null)
                    throw new NoSuchElementException();
                Item data = firstNode.data;
                firstNode = firstNode.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static void test() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(13);
        deque.addFirst(10);
        deque.addLast(15);
        deque.addLast(-32);
        deque.addFirst(-64);
        deque.addFirst(-132);
        System.out.println(deque.removeLast());     // -32
        System.out.println(deque.removeFirst());    // -132
        while (!deque.isEmpty()) {
            System.out.println(deque.removeLast());
        }
    }
    private static void test2() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("Jenifer");
        deque.addFirst("Amanda");
        deque.addFirst("Priscila");
        deque.addFirst("Paula");
        deque.addFirst("Mariana");
        deque.addFirst("Letícia");
        deque.addFirst("Veronica");
        deque.addLast("Marta");
        deque.addLast("Gerusa");
        deque.addLast("Penelope");
        deque.addFirst("Vanessa");
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());;
        }
    }
    private static void test3() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("Talita");
        deque.addLast("Maria");
        deque.addFirst("Roberta");
        deque.addLast("Joana");
        deque.addFirst("Miriam");
        deque.addLast("Marta");
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
    }
    private static void test4() {
        Deque<String> deque = new Deque<>();
        deque.addFirst("Talita");
        deque.addLast("Maria");
        deque.addFirst("Roberta");
        deque.addLast("Joana");
        deque.addFirst("Miriam");
        deque.addLast("Marta");
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        test4();
    }
}
