package project;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF wqu;
    private int qSites;

    private static final int path [][] = {};

    public Percolation(int s) {
        wqu = new WeightedQuickUnionUF(s);
    }

    public static void main(String[] args) {
    }

}
