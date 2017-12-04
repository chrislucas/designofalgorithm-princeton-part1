package exercises;

import java.util.Random;

public class GenerateSamples {


    public static Comparable [] sample(int p, int q) {
        Random random = new Random();
        int limit = Math.abs(p-q)+1;
        int min = Math.min(p, q);
        Comparable [] array = new Integer[limit];
        for(int i=0; i<limit; i++) {
            array[i] = random.nextInt(limit) + min + 1;
        }
        return array;
    }

}
