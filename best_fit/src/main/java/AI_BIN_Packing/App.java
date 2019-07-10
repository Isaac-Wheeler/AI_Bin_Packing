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
private static int bestfit(int[] weight, int n, int c)
{
	// Initialize result (Count of bins) 
	int res = 0;

	// Create an array to store remaining space in bins 
	// there can be at most n bins 
	int[] bin_rem = new int[n];

	// Place items one by one 
	for (int i = 0; i < n; i++)
	{
		// Find the best bin that ca\n accomodate 
		// weight[i] 
		int j;

		// Initialize minimum space left and index 
		// of best bin 
		int min = c + 1;
		int bi = 0;

		for (j = 0; j < res; j++)
		{
			if (bin_rem[j] >= weight[i] && bin_rem[j] - weight[i] < min)
			{
				bi = j;
				min = bin_rem[j] - weight[i];
			}
		}

		// If no bin could accommodate weight[i], 
		// create a new bin 
		if (min == c + 1)
		{
			bin_rem[res] = c - weight[i];
			res++;
		}
		else // Assign the item to best bin
		{
			bin_rem[bi] -= weight[i];
		}
	}
	return res;
}


    // Count Time to solve problem
    public static probSol bestfitTime(probSol dat) {
        long startTime = System.nanoTime();
        dat.setNumBins(bestfit(dat.getWeight(), dat.getN(), dat.getC()));
        long stopTime = System.nanoTime();
        dat.setDuration(stopTime - startTime);
        return dat;
    }

    private static final String SQL_SELECT = "SELECT problem, capacity, id FROM public.problem WHERE best_fit IS NULL";

    private static probSol findProblem(Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int weight[] = convert(resultSet.getString("problem"));
        probSol dat = new probSol(weight, resultSet.getInt("capacity"), resultSet.getInt("id"));
        return dat;
    }


    private static final String SQL_UPDATE = "UPDATE public.problem SET best_fit=?, best_fit_time=? WHERE id=?;";

    private static void updateProblem(Connection conn, probSol ps) throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
        preparedStatement.setInt(1, ps.getNumBins());
        preparedStatement.setLong(2, ps.getDuration());
        preparedStatement.setInt(3, ps.getId());
        preparedStatement.executeUpdate();
    }



    private static Connection sqlCon() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/AI_Bin_Problem", "postgres",
                "mage1996");
        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return conn;
    }

    // Driver program
public static void main(String[] args) {
    try{
    Connection conn;
    conn = sqlCon();
        while(true){
            try {
                probSol dat = findProblem(conn);
                bestfitTime(dat);
                System.out.print("Number of bins required in best Fit :");
                System.out.println(dat.numBins + " Time required to solve NanoSeconds :" + dat.duration);
                updateProblem(conn, dat);
            } catch (SQLException e) {
                e.printStackTrace();
                break;
            }
        }
    }catch (SQLException e) {
        e.printStackTrace();
    }
} 

private static int[] convert(String strArray) throws SQLException{
    String array[] = strArray.split(",");
    int w[] = new int[array.length];
    for (int i = 0; i < array.length; i++) {
        w[i] = Integer.parseInt(array[i]);
    }
    return w;
}

} 