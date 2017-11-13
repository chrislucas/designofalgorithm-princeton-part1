package impl;

public class StackDoubling<T> {
    public T [] structure;
    public int sizeControl;

    public StackDoubling() {
        structure = (T[]) new Object[1];
        sizeControl = 1;
    }

    public void push(T data) {
        if(sizeControl == structure.length) {
            resize(sizeControl * 2);    // doubling
        }
        structure[sizeControl++] = data;
    }

    public T poll() {
        return structure[sizeControl];
    }

    /**
     * Forma eficiente de remover um item da pilha.
     * Estamos aqui tentando evitar um fenômeno denominado thrashing.
     *
     * A situação é a seguinte:
     *
     * Se o array chegar ao seu limite de capacidade, nós redimensionamo-lo
     * para o dobro de seu tamanho, o array começa com tamanho 1 e dobra toda vez que
     * chegamos ao seu limite. Assim concluimos que o array varia de tamanho na potênci de 2.
     *
     * Se o array estiver na metade do seu limite e for solicitado uma operação de pop
     * poderiamos diminuir pela metade o tamanho dele, porém se fizermos de forma seguida
     * push-pop, ficariamos dobrando e reduzindo pela metada o tamanho do array, essa operação
     * tornar-se-ia custosa. Para evitar isso só diminuiremo
     *
     * */
    public void pop() {
        structure[--sizeControl] = null;
        // so diminuiremos o tamanho do array quando ele tiver 1/4 da capacidade maxima que
        // ele atingira por ultimo
        if(sizeControl > 0 && sizeControl == structure.length/4)
            // redimensionar o array para a metade do seu tamanho
            // que ainda vai ser o dobro do tamanho em relação a quantidade de elementos
            // que ele tem armazenados
            resize(structure.length/2);
    }

    public boolean isEmpty() {
        return sizeControl == 0;
    }

    public void resize(int capacity) {
        T [] newArray = (T[]) new Object[capacity];
        for(int i=0; i<sizeControl; i++)
            newArray[i] = structure[i];
        structure = newArray;
    }

    public static void main(String[] args) {
        //StackDoubling<Integer> sd = new StackDoubling<Integer>();
        //StackDoubling sd = new StackDoubling();
        StackDoubling sd = new StackDoubling<Integer>();
        for(int i=0; i<1000; i++)
            sd.push(1);

        while (!sd.isEmpty())
            sd.pop();

        sd.push(1);
        sd.push(2);
        sd.pop();
        sd.push(3);
    }
}
