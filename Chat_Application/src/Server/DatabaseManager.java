package Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://127.0.0.1:3306/sigmachat";
        String username = "root";
        String password = "new_password";

        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Connection successful
            System.out.println("Connection succeeded");

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            // JDBC driver not found
            System.out.println("JDBC driver not found");
        } catch (SQLException e) {
            // Connection failed
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
