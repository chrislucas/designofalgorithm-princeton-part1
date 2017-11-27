package impl;

public class ImplStackDoubling<T> {
    public T [] structure;
    public int controlSize;

    public ImplStackDoubling() {
        structure = (T[]) new Object[1];
        /**
         * Dizemos que o valor controlSize comeca com 1 pois
         * quando inserimos um valor numa pilha vazia ou numa pilha com
         * sua capacidade no limite, dobramos
         * o tamanho da capacidade da pilha, se o valor de 'controlSize'
         * fosse zero nao seria possivel usar essa estrategia
         * */
        controlSize = 1;
    }

    public void push(T data) {
        if(controlSize == structure.length) {
            resize(controlSize * 2);    // doubling
        }
        structure[controlSize++] = data;
    }

    public T poll() {
        return isEmpty() ? null : structure[controlSize];
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
        if(!isEmpty())  {
            structure[--controlSize] = null;
            // so diminuiremos o tamanho do array quando ele tiver 1/4 da capacidade maxima que
            // ele atingira por ultimo
            if(controlSize == structure.length/4)
                // redimensionar o array para a metade do seu tamanho
                // que ainda vai ser o dobro do tamanho em relação a quantidade de elementos
                // que ele tem armazenados
                resize(structure.length/2);
        }
    }

    public boolean isEmpty() {
        return structure[0] == null; //|| controlSize == 0;
    }

    public void resize(int capacity) {
        T [] newArray = (T[]) new Object[capacity];
        for(int i = 0; i< controlSize; i++)
            newArray[i] = structure[i];
        structure = newArray;
    }

    public static void main(String[] args) {
        //ImplStackDoubling<Integer> sd = new ImplStackDoubling<Integer>();
        //ImplStackDoubling sd = new ImplStackDoubling();
        ImplStackDoubling<Integer> sd = new ImplStackDoubling<Integer>();
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
