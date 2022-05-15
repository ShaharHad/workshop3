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


    public Status update(String username)
    {
        if (username == null)
        {
            return null;
        }
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
        return Status.Failure;
    }

    public void delete()
    {

    }
}
