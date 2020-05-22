package samples.behaviorsubtype;

class SubType<T> extends SuperType<T> {
    SubType() {
        super();
    }

    @Override
    protected T pop() {
        if (isEmpty())
            return null;

        T value = collection[0];
        remove();
        current--;
        if (current > 0 && current == collection.length / 4)
            resize(collection.length / 2);
        return value;
    }


    private void remove() {
        if (!isEmpty()) {
            T [] copy = (T[]) new Object[collection.length];
            System.arraycopy(collection, 1, copy, 0, current);
            collection = copy;
        }
    }
}
