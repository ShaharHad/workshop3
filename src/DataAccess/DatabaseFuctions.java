package DataAccess;
import java.sql.*;


public class DatabaseFuctions
{
    public static Connection getConnector()
    {
        String url = "";
        try
        {
            Connection conn = DriverManager.getConnection(url, "root", "root");
            return conn;
        }
        catch (SQLException e)
        {
            throw new RuntimeException();
        }
    }

    public void save(Connection conn, String username)
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

    public void update()
    {

    }

    public void delete()
    {

    }

}
