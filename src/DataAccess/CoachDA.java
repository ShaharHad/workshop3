package DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static DataAccess.DBConnector.getConnector;

public class CoachDA implements DataAccess
{
    public void save(String username)
    {
        Connection conn;
        try
        {
            conn = getConnector();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO coaches " +
                    "VALUES (1001, 'Simpson', 'Mr.', 'Springfield', 2001)");
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }

    }

    public void update()
    {

    }

    public void delete()
    {

    }
}
