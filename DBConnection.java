import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
 // Update these credentials based on your local MySQL setup
 private static final String URL = "jdbc:mysql://localhost:3306/newspaper_db";
 private static final String USER = "root"; 
 private static final String PASS = "Shyama@2026"; 
 public static Connection getConnection() throws SQLException {
 return DriverManager.getConnection(URL, USER, PASS);
 }
}
