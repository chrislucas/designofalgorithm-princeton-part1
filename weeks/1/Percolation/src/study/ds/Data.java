package study.ds;

public class Data<T> {
    private T value;
    /**
     * Esse objeto sera na estrutura de UnionFind
     * o atributo key representa o indice num vetor
     * onde uma determinada instancia de study.ds.Data esta
     * */
    private int key;
    public Data(T value, int key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", key, value.toString());
    }

    public int getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
