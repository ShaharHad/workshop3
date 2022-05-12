package DataAccess;

import Domain.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import static DataAccess.DBConnector.getConnector;

public class VisitorDA implements DataAccess {

    public void save(String username , String password) {
        Connection conn;
        try {
            conn = getConnector();
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO Customers " +
                    "VALUES (@username, @password)");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void save(String username) {

    }
    @Override
    public void update(){}
    @Override
    public void delete(){}

    public boolean IsExist(String user_name){
        Connection con = null;
        String query = "";
        ResultSet rs;
        try (Statement stmt = con.createStatement()) {
            con = getConnector();
            query = "SELECT USER_NAME ,PASSWORD from dbo.DB WHERE USER_NAME = @username AND PASSWORD = @password";

            rs = stmt.executeQuery(query);

            if (rs.next()) {
                //String U_n = rs.getString("USER_NAME");
                //String p = rs.getString("PASSWORD");
                return true;

            }
            else
                return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;

        }
    }

    };
}