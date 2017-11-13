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
        Node current = new Node(data);
        if(isEmpty()) {
            last = current;
            first = last;
        }
        else {
            Node oldest = last;
            oldest.next = current;
        }
    }

    public void dequeue() {
        first = first.next;
        if(isEmpty())
            last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        QueueWithLinkedList qwll = new QueueWithLinkedList();

        for(int i=0; i<10; i++)
            qwll.enqueue(i);

        //while (!qwll.isEmpty())
            //qwll.dequeue();
    }

}
