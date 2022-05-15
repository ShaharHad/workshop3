package DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import static DataAccess.DBConnector.getConnector;

public class VisitorDA implements DataAccess {
    public VisitorDA() { }

    public void save(String username , String password) throws Exception {
        if (username==null || password==null) throw new Exception("username name or password is null");
        else {
            Connection conn;
            String query = "";
            try {
                conn = getConnector();
                Statement st = conn.createStatement();
                query = "INSERT INTO Customers (username,password) VALUES (@username, @password)";
                st.executeUpdate(query);
                //INSERT INTO table_name (column1, column2, column3, ...)
                //VALUES (value1, value2, value3, ...);
                //st.executeUpdate("INSERT INTO Customers " +"(username,password)"+
                //        "VALUES (@username, @password)");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    @Override
    public void save(String username) {

    }
    @Override
    public void update(){ }

    @Override
    public void delete(String username)throws Exception {
        if (username==null ) throw new Exception("username name is null");
        else{
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

    public Boolean IsExist(String username){
        if (username==null)
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

            //if (rs.next()) {
                //String U_n = rs.getString("USER_NAME");
                //String p = rs.getString("PASSWORD");
                //return true;
            //}
            //else
             //   return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;

        }
    }

    }
