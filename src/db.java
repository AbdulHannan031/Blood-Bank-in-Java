import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/blood_bank_managment_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection conn;

    Connection connection() throws SQLException {
        if(conn==null)
        {
            conn= DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
            return conn;
        }
        else {
            return conn;
        }
    }
}
