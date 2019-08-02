package AI_BIN_Packing;

/**
 * Hello world!
 *
 */
public class App {
    // Returns number of bins required
    // using next fit online algorithm
    static int nextFit(int weight[], int n, int c) {

        // Initialize result (Count of bins) and remaining
        // capacity in current bin.
        int res = 0, bin_rem = c;

        // Place items one by one
        for (int i = 0; i < n; i++) {
            // If this item can't fit in current bin
            if (weight[i] > bin_rem) {
                res++; // Use a new bin
                bin_rem = c - weight[i];
            } else
                bin_rem -= weight[i];
        }
        return res;
    }

    // Count Time to solve problem
    public static probSol time(probSol dat) {
        long startTime = System.nanoTime();
        dat.setNumBins(nextFit(dat.getWeight(), dat.getN(), dat.getC()));
        long stopTime = System.nanoTime();
        dat.setDuration(stopTime - startTime);
        return dat;
    }

    // Driver program
    public static void main(String[] args) {
            util.findProblem();
    }
}