import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by jasonwalker on 11/23/16.
 */
public class Database {
    ResourceBundle config = ResourceBundle.getBundle("configuration");
    Connection conn;
    String url = "jdbc:mysql://zaphyrr.com:3306/classicmodels";
    String username = "root_remote";
    String password = config.getString("mysqlPass");

    public Database(){
        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

    }

    public Connection getConnection(){
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password)) {
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
