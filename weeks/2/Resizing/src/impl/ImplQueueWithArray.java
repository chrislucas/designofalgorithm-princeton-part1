package impl;

import it.ArrayIterable;
import it.ArrayIterator;

import java.util.Iterator;

public class ImplQueueWithArray<T> implements ArrayIterator<T>, ArrayIterable<T> {

    private static ImplQueueWithArray<Integer> qwa;
    public class Node {
        T data;
    }

    public T[] array;
    public int head, tail, controlSize, idx;

    public ImplQueueWithArray() {
        array = (T[]) new Object[2];
        idx = controlSize = 0;
    }

    public void enqueue(T data) {
        if(controlSize == array.length)
            resize(array.length*2);
        array[tail++] = data;
        if(tail == array.length)
            tail = 0;
        controlSize++;
    }

    public void dequeue() {
        if(!isEmpty()) {
            array[head++] = null;
            controlSize--;
            if(head == array.length)
                head = 0;
            if(controlSize > 0 && controlSize == array.length / 4)
                resize(array.length/2);
        }
        return;
    }

    public T peek() {
        return isEmpty() ? null : array[head];
    }

    public boolean isEmpty() {
        return controlSize == 0; //array[0] == null;
    }

    public void resize(int capacity) {
        T [] newArray = (T[]) new Object[capacity];
        for (int i = 0; i<controlSize ; i++)
            newArray[i] = array[ (head+i) % array.length];
        array = newArray;
        head = 0;
        tail = controlSize;
    }


    public void test1() {
        for(int i=0; i<=(1<<5); i++)
            qwa.enqueue(i);

        qwa.enqueue(33);
        while (!qwa.isEmpty()) {
            System.out.println(qwa.peek());
            qwa.dequeue();
        }
    }

    public void test2() {
        qwa.enqueue(2);
        qwa.enqueue(7);
        qwa.enqueue(4);
        qwa.enqueue(13);
        qwa.dequeue();
        qwa.enqueue(14);
        qwa.enqueue(15);
        dequeue();
    }

    public void test3() {
        for(int i=0; i<=(1<<5); i++)
            qwa.enqueue(i);
/*
        usando iterator
        while (hasNext())
            System.out.println(next());
*/
        // iterable nos permite usar a sintaxe foreach
        for (Iterator<T> it = iterator(); it.hasNext(); ) {
            T i = it.next();
            System.out.println(i);
        }

        while (!qwa.isEmpty()) {
            qwa.dequeue();
        }
    }

    public static void main(String[] args) {
        qwa = new ImplQueueWithArray<>();
        qwa.test3();
    }

    @Override
    public boolean hasNext() {
        return idx < controlSize;
    }

    @Override
    public T next() {
        T data = array[(idx+head)%array.length];
        idx++;
        return data;
    }

    @Override
    public void remove() {

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return idx < controlSize;
            }

            @Override
            public T next() {
                T data = array[(idx+head)%array.length];
                idx++;
                return data;
            }
        };
    }
}
