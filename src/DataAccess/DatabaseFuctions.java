package DataAccess;
import java.sql.*;


class DBConnector
{
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    private static final DBConnector instance = new DBConnector();

    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){ return instance; }

    private DBConnector() { }

    public static Connection getConnector()
    {
        String url = "";
        try
        {
            return DriverManager.getConnection(url, "root", "root");
        }
        catch (SQLException e) { throw new RuntimeException(); }
    }




}
