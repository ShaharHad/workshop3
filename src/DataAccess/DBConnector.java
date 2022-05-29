package DataAccess;
import java.sql.*;


class DBConnector
{
    public static final String URL = "jdbc:mysql://localhost:3306/javabase";
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
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e) { throw new RuntimeException("Error connecting to the database", e); }
    }
}


