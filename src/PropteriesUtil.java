import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropteriesUtil {

    public Properties loadProperties() {
        Properties p = new Properties();

        try {
            p.load(new FileInputStream("src\\Settings.properties."));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Connection createConnectionFromProperty(Properties p) throws SQLException {
        Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

        return con;
    }
}
