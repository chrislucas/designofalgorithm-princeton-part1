package samples.behaviorsubtype;

import java.util.Arrays;

class SuperType<T> {
    T[] collection;
    int current = 0;

    boolean isEmpty() {
        return current == 0;
    }

    SuperType() {
        this.collection = (T[]) new Object[2];
    }

    // simples adicao
    void add(T data) {
        if (current == collection.length)
            resize(collection.length * 2);
        collection[current++] = data;
    }

    protected T pop()  {

        if (isEmpty())
            return null;

        T value = collection[--current];

        if (current > 0 && current == collection.length / 4)
            resize(collection.length / 2);

        return value;
    }


    private void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

     void resize(int newSize) {
        if (!isEmpty()) {
            T [] copy = (T[]) new Object[newSize];
            System.arraycopy(collection, 0, copy, 0, current);
            collection = copy;
        }
    }
}
