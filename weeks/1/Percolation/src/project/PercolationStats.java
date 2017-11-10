package project;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private Percolation percolation;
    private int sizeMatriz, trials;

    private double frac [];
    private double mean, stddev;

    public PercolationStats(int n, int trials) {
        this.sizeMatriz = n;
        this.trials = trials;
        percolation = new Percolation(n);
        frac    = new double[n];
        mean    = StdStats.mean(frac);
        stddev  = StdStats.stddev(frac);
    }


    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(trials);
    }




    public static void main(String[] args) {

    }

}
