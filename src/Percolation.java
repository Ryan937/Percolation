import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private int top;
    private int bottom;
    private int openSites;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufPerc;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n*n + 2);
        ufPerc = new WeightedQuickUnionUF(n*n + 2);
        top = n * n;
        bottom = n * n + 1;
        openSites = 0;
    }

    private boolean isValid(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isValid(row, col);
        if (isOpen(row, col))
            return ;

        int i = row - 1;
        int j = col - 1;
        int currentSite = convert2dTo1d(row, col);

        
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isValid(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isValid(row, col);
        return uf.connected(top, convert2dTo1d(row, col));
    }

    private int convert2dTo1d(int i, int j) {
        return n * (i - 1) + j - 1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufPerc.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args) {

    }

}