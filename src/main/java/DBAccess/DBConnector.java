package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    
    private static final String URL = "jdbc:mysql://localhost:3306/lego";
    private static final String USERNAME = "connect";
    private static final String PASSWORD = "connect";
    
    private static Connection singleton;
    
    public static void setConnection( Connection con ) {
        singleton = con;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        if(singleton == null){
            Class.forName("com.mysql.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        
        return singleton;
    }

}
