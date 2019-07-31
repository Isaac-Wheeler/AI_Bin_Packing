package ai_bin_packing;

import java.sql.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class App 
{


    private static final String SQL_SELECT = "SELECT * FROM public.problem WHERE best_fit IS NOT NULL OR next_fit IS NOT NULL OR first_fit IS NOT NULL";

    private static StringBuilder csv;

    private static boolean checkVaild(probSol row){
        int lowestTime = Integer.MAX_VALUE;
        int numBins = 0;
        if(row.getBf_time() < lowestTime){
            lowestTime = row.getBf_time();
            numBins = row.getBest_fit();
        }
        if(row.getFf_time() < lowestTime){
            lowestTime = row.getFf_time();
            numBins = row.getFirst_fit();
        }
        if(row.getNf_time() < lowestTime){
            lowestTime = row.getNf_time();
            numBins = row.getNext_fit();
        }
        row.setNumBins(numBins);
        int weigth[] = row.getWeigth();
        int n = row.getN();
        int c = row.getC();
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += weigth[i];
        }
        int cap = numBins*c;
        if(cap < total){
            return false;
        }
        return true;
   }


    private static void getProblems(Connection conn) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next() != false){
            int weigth[] = convert(resultSet.getString("problem"));
            //probSol row = new probSol(weight, resultSet.getInt("capacity"), resultSet.getInt("id"));
            probSol row = new probSol(0, 0, weigth, resultSet.getInt("capacity"), resultSet.getInt("id"), 
            resultSet.getInt("next_fit"), resultSet.getInt("first_fit"), resultSet.getInt("best_fit"), 
            resultSet.getInt("next_fit_time"), resultSet.getInt("first_fit_time"), resultSet.getInt("best_fit_time"));

            if(checkVaild(row)){
                csv.append(row.toString());
            }
        }
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

    private static int[] convert(String strArray) throws SQLException{
        String array[] = strArray.split(",");
        int w[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            w[i] = Integer.parseInt(array[i]);
        }
        return w;
    }


    public static void main( String[] args )
    {

        csv = new StringBuilder();

        csv.append("Number of Bins, Weight, capacity \n");

        try {
            Connection conn = sqlCon();
            getProblems(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter("traing.csv");
            fileWriter.write(csv.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}