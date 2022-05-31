package DataAccess;
import java.sql.*;

//**Connector class of the database, A department responsible for connecting to the data structure*/
class DBConnector
{
    public static final String URL = "jdbc:mysql://localhost:3306/javabase";
    public static final String USER = "root";
    public static final String PASSWORD = "MySQLroot369";

    private static final DBConnector instance = new DBConnector();

    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){ return instance; }
    /**
     * constructor of  DBConnector class
     */
    private DBConnector() { }
    /**
     * function getConnector
     * @return Connection to database type Connection
     */
    public static Connection getConnector()
    {
        try
        {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e) { throw new RuntimeException("Error connecting to the database", e); }
    }
}


