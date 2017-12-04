package exercises;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffling {

    public static void swap(Comparable [] array, int i, int j) {
        Comparable a = array[i];
        array[i] = array[j];
        array[j] = a;
    }


    /**
     * Ordenando um array de forma aleatorio em tempo linear
     * */
    public void shuffleArray(Comparable [] array) {
        int limit = array.length;
        for (int i = 0; i <limit ; i++) {
            int r = StdRandom.uniform(i+1);
            swap(array,i, r);
        }
        print(array);
    }

    public void print(Comparable [] comparables) {
        for(Comparable comparable : comparables)
            System.out.printf("%s ", comparable);
        System.out.println("");
    }

    public static void main(String[] args) {
        String [] names = {"Christoffer", "Lucas", "Fernandes", "Santos"};
        Shuffling shuffling = new Shuffling();
        shuffling.print(names);
        System.out.println("");
        for (int i = 0; i < names.length; i++)
            shuffling.shuffleArray(names);

    }

}
