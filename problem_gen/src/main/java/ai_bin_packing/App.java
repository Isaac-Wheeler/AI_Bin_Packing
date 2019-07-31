package ai_bin_packing;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import java.sql.*;

public class App {

    private static int problemsCount;

    private static final int MAX_CAP = 10;

    // TODO: link to sql database

    private static int randomNum(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static int genProblems(Connection conn) {
        for (int i = 0; i < problemsCount; i++) {
            int numWieghts = randomNum(1, 10);
            int cap = randomNum(1, MAX_CAP);
            ArrayList<Integer> wieghts = new ArrayList<Integer>();

            for (int j = 0; j < numWieghts; j++) {
                wieghts.add(randomNum(1, cap));
            }
                try{
                    insert(wieghts, cap, conn);
                }catch (SQLException e) {
                    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return 0;
    }

    private static void insert(ArrayList<Integer> wieghts, int capacity, Connection conn) throws SQLException{     
        String SQL = "INSERT INTO problem (problem, capacity) VALUES (?, ?)";
        PreparedStatement pstmn = conn.prepareStatement(SQL);     
        StringBuilder arrayJson = new StringBuilder();
        for(int i: wieghts){
            arrayJson.append(i + ",");
        }
        pstmn.setString(1, arrayJson.toString());
        pstmn.setInt(2, capacity);
        pstmn.executeQuery();
    }

    private static Connection sqlCon() throws SQLException {
        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://127.0.0.1:5432/AI_Bin_Problem", "postgres", "mage1996");

        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }

        return conn;
    }


    public static void main( String[] args )
    {
        if (args.length != 0){
            problemsCount = Integer.parseInt(args[0]);
        }else{
            System.out.println("did not provide arguments, please provide number of problems to generate");
            System.exit(0);
        }
        try (Connection conn = sqlCon()){
            if(genProblems(conn) != 0){
                System.out.println("error in generating problems");
                System.exit(0);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
            e.printStackTrace();
            }
    }
}
