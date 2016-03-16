package database;

//STEP 1. Import required packages
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    public DB() {
    }

    public static Connection getConnect() throws SQLException {
        
        Connection connection = null;
        
        try {
            String USERNAME = System.getEnv("OPENSHIFT_MYSQL_DB_USERNAME");
            String PASSWORD = System.getEnv("OPENSHIFT_MYSQL_DB_PASSWORD");
            String DB_NAME = System.getEnv("OPENSHIFT_APP_NAME");
            String FORNAME_URL = "com.mysql.jdbc.Driver";
            String URL = "jdbc:" + System.getEnv("OPENSHIFT_MYSQL_DB_URL") + DB_NAME;
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } //end main
        catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;

    }
}//end FirstExample
