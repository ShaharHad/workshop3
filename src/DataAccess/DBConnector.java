package DataAccess;
import java.sql.*;
import java.sql.DriverManager;


class DBConnector
{
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/javabase";
    public static final String USER = "root";
    public static final String PASSWORD = "MySQLroot369";

    private static final DBConnector instance = new DBConnector();

    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){ return instance; }

    private DBConnector() { }

    public static Connection getConnector()
    {
        try
        {
            System.out.println("database connected!");
            Connection conn =  DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        }
        catch (SQLException e) { throw new RuntimeException("Error connecting to the database", e); }
    }

}
