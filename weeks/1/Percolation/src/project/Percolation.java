package project;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF wqu;
    private int qSites
            ,  virtualTop = 0
            , virtualBottom, qOpenSites = 0;
    private boolean openSites [];

    private static final int pos [][] = {
         {0,-1} // left
        ,{-1,0} // top
        ,{0,1}  // right
        ,{1,0}  // bottom
    };

    public Percolation(int s) {
        if(s <= 0)
            throw new IllegalArgumentException();
        wqu             = new WeightedQuickUnionUF(s*s+2);
        qSites          = s;
        virtualBottom   = s*s+1;
        openSites       = new boolean[s*s+2];
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
        return i > 0 && i <= qSites && j > 0 && j <= qSites;
    }

    private int getIndex(int i, int j) {
        return (i-1) * qSites + j;
    }

    public void open(int p, int q) {
        if(!isValid(p, q))
            throw new IllegalArgumentException();
        if(!isOpen(p, q)) {
            int idx = getIndex(p, q);
            markOpenSite(idx);
            if(p == 1) {
                wqu.union(virtualTop, idx);
            }

            else if(p == qSites) {
                wqu.union(virtualBottom, idx);
            }

            for(int i=0; i<pos.length; i++) {
                int np = pos[i][0] + p;
                int nq = pos[i][1] + q;
                if(isValid(np, nq) && isOpen(np, nq)) {
                    wqu.union(getIndex(np, nq), idx);
                }
            }
        }
    }

    private void markOpenSite(int idx) {
        openSites[idx] = true;
        qOpenSites++;
    }

    public boolean isOpen(int p, int q) {
        if(!isValid(p, q))
            throw new IllegalArgumentException();
        return openSites[getIndex(p, q)];
    }

    public boolean isFull(int p, int q) {
        if(!isValid(p, q))
            throw new IllegalArgumentException();
        if(!isOpen(p, q))
            return false;
        return wqu.connected(getIndex(p, q), 0);
    }

    public int numberOfOpenSites() {
        return qOpenSites ;
    }

    public boolean percolates() {
        return wqu.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) { }

}
