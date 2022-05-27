package Domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static DataAccess.DBConnector.getConnector;

public class Visitor
{
    public Status signUp(){
        return Status.Success;
    }
    public Status logIn(String username, String password){

        Connection conn;
        try
        {
            conn = getConnector();
            Statement st = conn.createStatement();

        }

        catch (SQLException e)
        {
            System.out.println(e);
        }



        return Status.Success;
    }

}

