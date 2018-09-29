import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFCONST = 1.96;
    private final int t;
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("invalid n or trial count");

        t = trials;
        double[] perTrsh = new double[trials];
        for (int i = 0; i < trials; i++)
        {
            Percolation p = new Percolation(n);
            while (!p.percolates())
            {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if (!p.isOpen(row, col))
                    p.open(row, col);
            }
            perTrsh[i] = (double) p.numberOfOpenSites() / (double) (n*n);
        }
        mean    = StdStats.mean(perTrsh);
        stddev  = StdStats.stddev(perTrsh);

    }

    public double mean()                          // sample mean of percolation threshold
    {
        return mean;
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return stddev;
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - ((CONFCONST * stddev()) / Math.sqrt(t));
    }

    public double confidenceHi()                  // high endpoint of 95% confidence
    {
        return mean() + ((CONFCONST * stddev()) / Math.sqrt(t));
    }

    public static void main(String[] args)        // test client (described below)
    {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        System.out.println("mean                    = "  + ps.mean());
        System.out.println("stddev                  = "  + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
