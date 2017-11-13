package project;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private Percolation percolation;
    private int trials;
    private double frac [];
    private double mean, stddev;

    public PercolationStats(int n, int trials) {
        if(n < 1 || trials < 1)
            throw new IllegalArgumentException();
        this.trials = trials;
        frac = new double[trials];
        for (int i = 0; i < trials ; i++) {
            percolation = new Percolation(n);
            long accumulate = 0;
            while (!percolation.percolates()) {
                int p = StdRandom.uniform(1, n+1);
                int q = StdRandom.uniform(1, n+1);
                if (!percolation.isOpen(p, q)) {
                    percolation.open(p, q);
                    accumulate++;
                }
            }
            frac[i] = (double) accumulate/(n*n);
        }
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
        return mean - (1.96 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean + (1.96 * stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        //int n = StdRandom.uniform(30, 100);
        //int trials = StdRandom.uniform(30, 1000);
        PercolationStats percolationStats = new PercolationStats(200,100);
        StdOut.printf("mean %f\n", percolationStats.mean);
        StdOut.printf("stddev %f\n", percolationStats.stddev);
        double lo = percolationStats.confidenceLo();
        double hi = percolationStats.confidenceHi();
        StdOut.printf("95%% confidence interval [%f, %f]\n", lo, hi);
    }

}
