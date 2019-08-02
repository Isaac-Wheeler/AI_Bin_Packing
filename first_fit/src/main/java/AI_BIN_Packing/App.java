package AI_BIN_Packing;

import java.sql.*;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    // Returns number of bins required
    // using first fit online algorithm
    static int firstfit(int weight[], int n, int c) {
        // Initialize result (Count of bins)
        int res = 0;

        // Create an array to store remaining space in bins
        // there can be at most n bins
        int[] bin_rem = new int[n];

        // Place items one by one
        for (int i = 0; i < n; i++) {
            // Find the first bin that can accommodate
            // weight[i]
            int j;
            for (j = 0; j < res; j++) {
                if (bin_rem[j] >= weight[i]) {
                    bin_rem[j] = bin_rem[j] - weight[i];
                    break;
                }
            }

            // If no bin could accommodate weight[i]
            if (j == res) {
                bin_rem[res] = c - weight[i];
                res++;
            }
        }
        return res;

    }

    // Count Time to solve problem
    public static probSol time(probSol dat) {
        long startTime = System.nanoTime();
        dat.setNumBins(firstfit(dat.getWeight(), dat.getN(), dat.getC()));
        long stopTime = System.nanoTime();
        dat.setDuration(stopTime - startTime);
        return dat;
    }

    // Driver program
    public static void main(String[] args) {
        util.findProblem();
    }

}