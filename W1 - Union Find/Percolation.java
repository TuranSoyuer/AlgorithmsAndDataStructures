import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/** Monte Carlo Simulation*/
public class Percolation {
    private final int n;
    private final int sitesCnt;
    private final int virTop;
    private final int virBot;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufForIsFull;
    private int[] grid;
    private int openCnt;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than zero");
        this.n      = n;
        sitesCnt    = n*n;
        openCnt     = 0;
        grid        = new int[sitesCnt];
        uf          = new WeightedQuickUnionUF(sitesCnt + 2);   // last 2 for virtual top & virtual bottom
        ufForIsFull = new WeightedQuickUnionUF(sitesCnt + 1);   // last 1 for virtual top
        for (int i = 0; i < sitesCnt; i++)
            grid[i] = 0;

        virTop = sitesCnt;
        virBot = sitesCnt + 1;
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row < 1 || row > n)
            throw new IllegalArgumentException("row " + row + " is not between 1 and " + n);
        if (col < 1 || col > n)
            throw new IllegalArgumentException("col " + col + " is not between 1 and " + n);
        int i = toInd(row, col);

        if (grid[i] == 0) {
            grid[i] = 1;
            openCnt++;

            if (row == 1)            // Connect to virtual top
            {
                uf.union(i, virTop);
                ufForIsFull.union(i, virTop);
//                            System.out.println("Union -> " + row + "," + col + " & virtTop" );
            }

            if (row == n)            // Connect to bottom top
            {
                uf.union(i, virBot);
//                            System.out.println("Union -> " + row + "," + col + " & virtBottom" );
            }

            int left = i - 1;
            int right = i + 1;
            int top = i - n;
            int bottom = i + n;


            if (col > 1 && left >= 0 && isOpen(left)) {
                uf.union(i, left);
                ufForIsFull.union(i,left);
//                            System.out.println("Union -> " + row + "," + col + " & " + toRow(left) + "," + toCol(left) );
            }

            if (col < n && right < sitesCnt && isOpen(right)) {
                uf.union(i, right);
                ufForIsFull.union(i, right);
//                            System.out.println("Union -> " + row + "," + col + " & " + toRow(right) + "," + toCol(right) );
            }

            if (row > 1 && top >= 0 && isOpen(top)) {
                uf.union(i, top);
                ufForIsFull.union(i, top);
//                            System.out.println("Union -> " + row + "," + col + " & " + toRow(top) + "," + toCol(top) );
            }

            if (row < n && bottom < sitesCnt && isOpen(bottom)) {
                uf.union(i, bottom);
                ufForIsFull.union(i, bottom);
//                            System.out.println("Union -> " + row + "," + col + " & " + toRow(bottom) + "," + toCol(bottom) );
            }
        }
    }

    private boolean isOpen(int i)
    {
        int row = toRow(i);
        int col = toCol(i);
        return isOpen(row, col);
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (row < 1 || row > n)
            throw new IllegalArgumentException("row " + row + " is not between 1 and " + n);
        if (col < 1 || col > n)
            throw new IllegalArgumentException("col " + col + " is not between 1 and " + n);
        if (grid[toInd(row, col)] == 1)
            return true;
        return false;
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row < 1 || row > n)
            throw new IllegalArgumentException("row " + row + " is not between 1 and " + n);
        if (col < 1 || col > n)
            throw new IllegalArgumentException("col " + col + " is not between 1 and " + n);
        int i = toInd(row, col);
        if (i == virTop)
            return false;

        return ufForIsFull.connected(i, virTop);
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return openCnt;
    }

    public boolean percolates()              // does the system percolate?
    {
        return uf.connected(virTop, virBot);
    }

    private int toInd(int row, int col)
    {
        return (row-1) * n + (col-1);
    }

    private int toRow(int i)
    {
        return i / n + 1;
    }

    private int toCol(int i)
    {
        return i % n + 1;
    }

    public static void main(String[] args)
    {
        /*int n = StdIn.readInt();

        Percolation perc = new Percolation(n);


        while (StdIn.hasNextLine())
        {
            int row = StdIn.readInt();

            if (row == -1)
                break;

            int col = StdIn.readInt();


            if (!perc.isOpen(row, col))
                perc.open(row, col);
            System.out.println(row + " " + col + " is Full:" + perc.isFull(row, col));
            System.out.println();
        }*/

//        System.out.println("isFull:" + perc.isFull(6,6));
    }
}
