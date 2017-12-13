package project;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.*;
import java.util.StringTokenizer;

public class TestPermutation {

    static final String paths [] = {
         "D:\\algorithm\\part1\\weeks\\2\\queues\\distinct.txt"
        ,"D:\\algorithm\\part1\\weeks\\2\\queues\\duplicates.txt"
        ,"D:\\algorithm\\part1\\weeks\\2\\queues\\permutation5.txt"
    };

    public static void test1() {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int k = 0;
        File file = new File(paths[1]);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String in = null;
            while ( (in = reader.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(in, " ");
                k = StdRandom.uniform(tk.countTokens());
                while (tk.hasMoreTokens())
                    rq.enqueue(tk.nextToken());
            }

            if(!rq.isEmpty()) {
                for(int i=0; i<k; i++)
                    StdOut.println(rq.dequeue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int k = 0;
    }

    public static void transverse(String path) {
        File pathfile = new File(path);
        if(pathfile.listFiles() != null) {
            for(File file: pathfile.listFiles()) {
                if(file.isFile()) {
                    test2();
                }
                else {
                    transverse(file.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
        //transverse("D:\\algorithm\\part1\\weeks\\2\\queues\\");
        test1();
    }

}
