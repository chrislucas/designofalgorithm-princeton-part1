package impl;

public class QueueWithLinkedList<T> {


    public class Node {
        T data;
        Node next;
        public Node (T data) { this.data = data; }
        @Override
        public String toString() {
            return String.format("%s", data.toString());
        }
    }

    private Node first, last;
    private int size = 0;

    public void enqueue(T data) {
        Node oldest = last;
        last = new Node(data);
        if(isEmpty()) {
            first = last;
        }
        else {
            oldest.next = last;
        }
        size++;
    }

    public void dequeue() {
        first = first.next; // sobrescreve o primeiro da lista (FIFO)
        if(isEmpty())
            last = null;
        size--;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        QueueWithLinkedList qwll = new QueueWithLinkedList();

        for(int i=0; i<10; i++)
            qwll.enqueue(i);

        while (!qwll.isEmpty())
            qwll.dequeue();
    }

}
