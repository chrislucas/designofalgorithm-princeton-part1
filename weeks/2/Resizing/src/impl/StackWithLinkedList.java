package impl;

public class StackWithLinkedList<T> {

    public class Node {
        T data;
        Node next;
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
        @Override
        public String toString() {
            return String.format("%s", data);
        }
    }

    private Node first;
    private int size;

    StackWithLinkedList() {
        this.size = 0;
    }

    public void push(T data) {
        if(isEmpty()) {
            this.first = new Node(data);
        }
        else {
            Node oldest  = first;
            first = new Node(data);
            first.next = oldest;
        }
        size++;
    }

    public void pop() {
        first = first.next;
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        StackWithLinkedList<Integer> swld = new StackWithLinkedList<>();
        for(int i=0; i<10; i++)
            swld.push(i);

        while (!swld.isEmpty())
            swld.pop();
    }

}
