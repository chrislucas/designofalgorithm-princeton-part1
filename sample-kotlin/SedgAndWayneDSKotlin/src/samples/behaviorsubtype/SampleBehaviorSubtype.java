package samples.behaviorsubtype;


public class SampleBehaviorSubtype {

    public static void main(String[] args) {
        /*
         * Aqui temos um exemplo de Behaviroal Subtype.
         *
         * Ambas as classes encapsulam um Array generico. A forma de adicao de elementos
         * eh igual, entretanto a forma de remocao difere. A SUPERCLASSE implementa o comportamento
         * FIFO e a sub classe o comportamento LIFO.
         *
         * O que ocorrera abaixo eh o seguinte:
         * criamos um objeto do tipo da subclasse e o referenciamos como a superclasse. Espera-se que
         * o comportamento seja da Super classe mas o metodo que sera executado (pop()) eh o da subclasse
         *
         * */
        SuperType<String> struct = new SuperType<>();
        struct.add("I");
        struct.add("am");
        struct.add("a");
        struct.add("stupid");
        struct.add("student");

        while (!struct.isEmpty()) {
            System.out.println(struct.pop());
        }
    }
}
