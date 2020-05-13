import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private int top;
    private int bottom;
    private int openSites;
    private byte[] sites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufPerc;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        top = n * n;
        bottom = n * n + 1;
        openSites = 0;
        sites = new byte[n*n];
        uf = new WeightedQuickUnionUF(n*n + 2);
        ufPerc = new WeightedQuickUnionUF(n*n + 2);
    }

    private boolean isValid(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n)
            throw new IndexOutOfBoundsException();
        return true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isValid(row, col);
        if (isOpen(row, col))
            return ;

        int currentSite = convert2dTo1d(row, col);
        sites[currentSite] = 1;
        openSites++;

        if (row == 1 && !uf.connected(currentSite, top)) {
            uf.union(currentSite, top);
            ufPerc.union(currentSite, top);
        }

        if (row == n) {
            ufPerc.union(currentSite, bottom);
        }

        if (row < n && isOpen(row + 1, col)) {
            uf.union(currentSite, convert2dTo1d(row + 1, col));
            ufPerc.union(currentSite, convert2dTo1d(row + 1, col));
        }

        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(currentSite, convert2dTo1d(row - 1, col));
            ufPerc.union(currentSite, convert2dTo1d(row - 1, col));
        }

        if (col < n && isOpen(row, col + 1)) {
            uf.union(currentSite, convert2dTo1d(row, col + 1));
            ufPerc.union(currentSite, convert2dTo1d(row, col + 1));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(currentSite, convert2dTo1d(row, col - 1));
            ufPerc.union(currentSite, convert2dTo1d(row, col - 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isValid(row, col);
        return sites[convert2dTo1d(row, col)] == 1;
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

}