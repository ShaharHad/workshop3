package DataAccess;
import java.sql.*;


public class DatabaseFuctions
{
    public static Connection getConnector()
    {
        String url = "";
        Connection conn;
        try
        {
            conn = DriverManager.getConnection(url, "root", "root");
            return conn;
        }
        catch (SQLException e)
        {
            throw new RuntimeException();
        }
    }

    public static void save(Connection conn, String username)
    {
        try
        {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO Customers " +
                    "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }

    }

    public static void update(String role)
    {

    }

    public static void delete()
    {

    }

}
