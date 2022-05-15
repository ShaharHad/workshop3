//package DataAccess;

package DataAccess;
        import Domain.Table;

        import java.io.*;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.lang.*;

        import static DataAccess.DBConnector.getConnector;

public class  GameDA implements DataAccess
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
                st.executeUpdate("INSERT INTO "+ Table.coaches+ "(username , password) " +
                        "VALUES (@username,@password)");
            }
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }



    }

    public void update(String username, String password) throws Exception
    {
        if (username==null ) throw new Exception("username is null");
        else{
            if (password==null ) throw new Exception("password is null");
            else{

                if (IsExist(username))
                {
                    Connection con = getConnector();
                    //UPDATE table_name
                    //SET column1 = value1, column2 = value2, ...
                    //WHERE condition;
                    String query="UPDATE " + Table.coaches + "SET password=WHERE userName = @username";
                    Statement stmt = con.createStatement();
                    stmt.executeQuery(query);
                }
            }
        }
    }

    public void delete(String username)throws Exception
    {
        if (username==null ) throw new Exception("username is null");
        else
        {
            Connection con = null;
            String query = "";

            try {
                con = getConnector();
                Statement stmt = con.createStatement();
                query = "DELETE from" + Table.coaches + "WHERE userName = @username";

                stmt.executeQuery(query);

            } catch (SQLException throwables) {
                throwables.printStackTrace();


            }

        }


    }


    public Boolean IsExist(String username){
        if (username==null)
            return null;
        Connection con = null;
        String query = "";
        ResultSet rs;
        try {
            con = getConnector();
            Statement stmt = con.createStatement();
            query = "SELECT userName from " + Table.coaches + "WHERE userName = @username";

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

