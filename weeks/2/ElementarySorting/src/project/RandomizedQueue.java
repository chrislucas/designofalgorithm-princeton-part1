package project;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int controlSize, head, tail;

    Item [] data;

    public RandomizedQueue() {
        data = (Item[]) new Object[2];
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
        if(tail == data.length)
            tail = 0;
        controlSize++;
    }

    private void resize(int size) {
        Item [] items = (Item[]) new Object[size];
        for(int idx=0; idx<data.length; idx++)
            items[idx] = data[(head+idx)%data.length];
        data = items;
        head = 0;
        tail = controlSize;
    }

    public Item dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();
        Item item = data[head];
        data[head++] = null;
        controlSize--;
        if(head == data.length)
            head = 0;
        if(controlSize > 0 && controlSize == data.length / 4)
            resize(data.length/2);
        return item;
    }

    private int getRandomIndex() {
       return StdRandom.uniform(0, data.length-1);
    }

    public Item sample() {
        if(isEmpty())
            throw new NoSuchElementException();
        return data[getRandomIndex()];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int index = 0;
            private int size = data.length;
            @Override
            public boolean hasNext() {
                return index < controlSize;
            }

            @Override
            public Item next() {
                return data[((index++)+head)%size];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void test() {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("Maria");
        queue.enqueue("Marta");
        queue.enqueue("Joana");
        queue.enqueue("Estela");
        queue.enqueue("Gertrudes");
        queue.enqueue("Penelope");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    public static void main(String[] args) {
        test();
    }
}
