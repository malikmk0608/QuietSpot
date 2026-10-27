import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/quietspot";
        String username = "root";
        String password = "YOUR_DB_PASSWORD";

        // Check for local db.properties file (not tracked by Git)
        File propFile = new File("db.properties");
        if (propFile.exists()) {
            try (FileInputStream fis = new FileInputStream(propFile)) {
                Properties props = new Properties();
                props.load(fis);
                if (props.getProperty("db.url") != null) {
                    url = props.getProperty("db.url");
                }
                if (props.getProperty("db.user") != null) {
                    username = props.getProperty("db.user");
                }
                if (props.getProperty("db.password") != null) {
                    password = props.getProperty("db.password");
                }
            } catch (IOException e) {
                System.err.println("Warning: Could not read db.properties: " + e.getMessage());
            }
        } else {
            // Fall back to environment variables
            if (System.getenv("DB_URL") != null) {
                url = System.getenv("DB_URL");
            }
            if (System.getenv("DB_USER") != null) {
                username = System.getenv("DB_USER");
            }
            if (System.getenv("DB_PASSWORD") != null) {
                password = System.getenv("DB_PASSWORD");
            }
        }

        return DriverManager.getConnection(url, username, password);
    }
}
