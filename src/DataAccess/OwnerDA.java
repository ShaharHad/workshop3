package DataAccess;

import Domain.Status;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import static DataAccess.DBConnector.getConnector;

public class OwnerDA implements DataAccess
{
    private static final OwnerDA instance = new OwnerDA();

    public static OwnerDA getInstance(){
        return instance;
    }

    public OwnerDA getOwnerDA()
    {
        return new OwnerDA();
    }


    //private constructor to avoid client applications to use constructor
    private OwnerDA()
    {

    }

    public void assignPlayer(String username, String role)
    {
        Connection conn;
        try
        {
            conn = getConnector();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO javabase.players (userName, teamName, birthDate, role)" +
                    "VALUES ("+username + ", 'Barcelona', '2000-01-01.', 'defense')");
            update(username, role, conn);
        }
        catch (SQLException e)
        {
            System.out.println("issue in assignPlayer function");
            System.out.println(e);
        }

    }

    public ResultSet getRecord(String username)
    {
        if (username == null)
        {
            return null;
        }
        Connection conn;
        try
        {
            conn = getConnector();
            String query = "SELECT userName, password, name FROM members";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);
            return rs;
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }


    public void save(String username)
    {
//        if (username == null)
//        {
//            return null;
//        }
        Connection conn;
        try
        {
            conn = getConnector();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO Customers " +
                    "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void delete(String username)
    {
//        if (username == null)
//        {
//            return null;
//        }
//        return Status.Failure;
    }


    public void update(String username, String role, Connection conn)
    {
        String query = "UPDATE javabase.members SET role=" + role + " WHERE userName=" + username;
        try
        {
            conn = getConnector();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        catch (SQLException e)
        {
            System.out.println("issue in assignPlayer function");

            System.out.println(e);
        }
    }

    public void delete()
    {

    }


    public void update(String username)
    {
        Connection conn;
        String table_name = "owner";
        String column_name = "";
        String query = "UPDATE ? SET Username=? WHERE 'somthing = ?'";
        try
        {
            conn = getConnector();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO Customers " +
                    "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}
