package impl;

public class ImplQueueWithLinkedList<T> {


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
        /**
         * Guardando a reference do um valor que foi inserido na fila.
         * Quando a fila esta vazia, as variaveries first e last aponto
         * para o mesmo endereço de memoria, na proximas insercoes
         * guardamos a referencia para o 'antigo ultimo' elemento inserido, criamos
         * um 'novo ultimo' e modificamos o 'antigo ultimo' assim a variavel 'first'
         * tambem sera modifcada, uma vez que ela ela aponta para o endereço de memoria
         * do 'antigo ultimo' elemento inserido
         *
         * Uma vez que 'first' aponta para um endereço na memoria que nao e nulo
         * toda vez que modificarmos 'oldest' modificamos 'first' tambem
         * */
        Node oldest = last;
        last = new Node(data);
        if(isEmpty())
            first = last;
        else
            oldest.next = last;
        size++;
    }

    public void dequeue() {
        /**
         * sobrescreve o primeiro da lista (FIFO)
         * 1 -> 2 -> 3 -> 4 dequeue
         * 2 -> 3 -> 4 dequeue
         * 3 -> 4 ... so on
         * */
        first = first.next;
        if(isEmpty())
            last = null;
        size--;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        ImplQueueWithLinkedList<Integer> qwll = new ImplQueueWithLinkedList<>();

        for(int i=0; i<10; i++)
            qwll.enqueue(i);

        while (!qwll.isEmpty())
            qwll.dequeue();
    }

}
