package DataAccess;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;

import static DataAccess.DBConnector.getConnector;

public class CoachDA implements DataAccess
{
    public void save(String username , String password) throws Exception
    {
        if (username==null || password==null) throw new Exception("username name or password is null");
        else{
            Connection conn;
            try
            {
                conn = getConnector();
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO Couch (username , password) " +
                        "VALUES (@username,@password)");
            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }



    }

    public void update()
    {

    }

    public void delete(String username)throws Exception
    {
        if (username==null ) throw new Exception("username name is null");
        else
        {
            Connection con = null;
            String query = "";
            ResultSet rs;
            try {
                con = getConnector();
                Statement stmt = con.createStatement();
                query = "DELETE from dbo.DB WHERE USER_NAME = @username";

                stmt.executeQuery(query);

            } catch (SQLException throwables) {
                throwables.printStackTrace();


            }

        }


    }


    public Boolean IsExist(String user_name){
        if (user_name==null)
            return null;
        Connection con = null;
        String query = "";
        ResultSet rs;
        try {
            con = getConnector();
            Statement stmt = con.createStatement();
            query = "SELECT USER_NAME from dbo.DB WHERE USER_NAME = @username";

            rs = stmt.executeQuery(query);
            if (rs!=null)
                return true;
            return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;

        }
    }

}
