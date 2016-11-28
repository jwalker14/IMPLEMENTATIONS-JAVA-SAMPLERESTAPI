import org.json.JSONArray;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by jasonwalker on 11/23/16.
 */
public class ConnectionManager {
    private static ResourceBundle config = ResourceBundle.getBundle("configuration");
    private static String url = "jdbc:mysql://zaphyrr.com:3306/classicmodels";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root_remote";
    private static String password = config.getString("mysqlPass");
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }

    public ResultSet runQuery(String sqlQuery){
        try{
            PreparedStatement ps = getConnection().prepareStatement(sqlQuery);
            return ps.executeQuery();
        }
        catch(Exception e){
            return null;
        }
    }

    public int executeUpdate(String sql){
        try{
            PreparedStatement ps = getConnection().prepareStatement(sql);
            return ps.executeUpdate(sql);
        }
        catch(Exception e){
            return 0;
        }
    }
}
