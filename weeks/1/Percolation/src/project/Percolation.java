package project;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF wqu;
    private int qSites,  virtualTop = 0, virtualBottom, openSites = 0;

    private static final int pos [][] = {
         {0,-1} // left
        ,{-1,0} // top
        ,{0,1}  // right
        ,{1,0}  // bottom
    };

    public Percolation(int s) {
        wqu         = new WeightedQuickUnionUF(s);
        qSites      = s;
        virtualTop  = s*s+1;
    }

    /**
     * Esse metodo auxilia converter um indice de uma matriz (i, j) para
     * um vetor[i]
     *
     * A matemática é feita baseada numa matriz MxN onde o primeito
     * indice é (1,1) e o ultimo (M, N)
     * Exemplo
     * o indice (1,1) na matriz deveria ser (1) no vetor
     *
     * o indice (3,4) numa matriz (4,4) dever ser o 12 num
     * vetor de 16 posicoes
     *
     * Formula (linha - 1) * qColunas + coluna
     * Num exemplo cuja matriz varia de (0,0) a (M, N)
     * a formula é a mesma
     * */

    private boolean isValid(int i, int j) {
        return i > 0 && i < qSites && j > 0 && j < qSites;
    }

    private int getIndex(int i, int j) {
        return (i-1) * qSites + j;
    }

    public void open(int p, int q) {

    }

    public boolean isOpen(int p, int q) {
        return false;
    }

    public boolean isFull(int p, int q) {
        return false;
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return wqu.connected(virtualBottom, virtualTop);
    }

    public static void main(String[] args) { }

}
