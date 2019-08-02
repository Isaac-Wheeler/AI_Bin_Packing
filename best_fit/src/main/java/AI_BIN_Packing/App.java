package AI_BIN_Packing;

import java.sql.*;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
	// Returns number of bins required using best fit
	// online algorithm
	private static int bestfit(int[] weight, int n, int c) {
		// Initialize result (Count of bins)
		int res = 0;

		// Create an array to store remaining space in bins
		// there can be at most n bins
		int[] bin_rem = new int[n];

		// Place items one by one
		for (int i = 0; i < n; i++) {
			// Find the best bin that ca\n accomodate
			// weight[i]
			int j;

			// Initialize minimum space left and index
			// of best bin
			int min = c + 1;
			int bi = 0;

			for (j = 0; j < res; j++) {
				if (bin_rem[j] >= weight[i] && bin_rem[j] - weight[i] < min) {
					bi = j;
					min = bin_rem[j] - weight[i];
				}
			}

			// If no bin could accommodate weight[i],
			// create a new bin
			if (min == c + 1) {
				bin_rem[res] = c - weight[i];
				res++;
			} else // Assign the item to best bin
			{
				bin_rem[bi] -= weight[i];
			}
		}
		return res;
	}

	// Count Time to solve problem
	public static probSol time(probSol dat) {
		long startTime = System.nanoTime();
		dat.setNumBins(bestfit(dat.getWeight(), dat.getN(), dat.getC()));
		long stopTime = System.nanoTime();
		dat.setDuration(stopTime - startTime);
		return dat;
	}

	public static void main(String[] args) {
		util.findProblem();
	}

}