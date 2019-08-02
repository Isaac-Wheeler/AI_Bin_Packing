package AI_BIN_Packing;

import java.sql.*;

public class util {

    private static final String FIT_TYPE = "first";

    private static final String SQL_SELECT = "SELECT problem, capacity, id FROM public.problem WHERE "+ FIT_TYPE + "_fit IS NULL";
    private static final String SQL_UPDATE = "UPDATE public.problem SET "+ FIT_TYPE + "_fit=?, "+ FIT_TYPE + "_fit_time=? WHERE id=?;";

    public static void findProblem(){
        try {
            Connection conn = sqlCon();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() != false) {
                int weight[] = convert(resultSet.getString("problem"));
                probSol dat = new probSol(weight, resultSet.getInt("capacity"), resultSet.getInt("id"));
                dat = App.time(dat);
                updateProblem(conn, dat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProblem(Connection conn, probSol ps) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
        preparedStatement.setInt(1, ps.getNumBins());
        preparedStatement.setLong(2, ps.getDuration());
        preparedStatement.setInt(3, ps.getId());
        preparedStatement.executeUpdate();
    }

    private static Connection sqlCon() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/AI_Bin_Problem", "postgres",
                "mage1996");
        if (conn == null) {
            System.out.println("Failed to make connection!");
            System.exit(1);
        }
        return conn;
    }

    private static int[] convert(String strArray) throws SQLException {
        String array[] = strArray.split(",");
        int w[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            w[i] = Integer.parseInt(array[i]);
        }
        return w;
    }

}