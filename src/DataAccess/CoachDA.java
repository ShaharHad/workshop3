//package DataAccess;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.lang.*;
//
//import static DataAccess.DBConnector.getConnector;
//
//public class CoachDA implements DataAccess
//{
//    public void save(String[] params) throws Exception
//    {
//        for (int i = 0; i < params.length; i++)
//        {
//            if (params[i] == null)
//                throw new Exception("one of the parameters is null");
//        }
//        Connection conn;
//        try
//        {
//            conn = getConnector();
//            Statement st = conn.createStatement();
//            st.executeUpdate("INSERT INTO "+ tableNames.coaches+ "(userName, teamName , role, training)" +
//                    "VALUES (@params[0], @params[1], @params[2], @params[3])");
//        }
//        catch (SQLException e)
//        {
//            System.out.println(e);
//        }
//    }
//
//    public void update(String[] params) throws Exception
//    {
//        for (int i = 0; i < params.length; i++)
//        {
//            if (params[i] == null)
//                throw new Exception("one of the parameters is null");
//        }
//        if ()
//        {
//            Connection con = getConnector();
//            //UPDATE table_name
//            //SET column1 = value1, column2 = value2, ...
//            //WHERE condition;
//            String query="UPDATE " + tableNames.coaches + "SET password=WHERE userName = @username";
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(query);
//        }
//
//
//    }
//
//    public void delete(String username)throws Exception
//    {
//        if (username==null ) throw new Exception("username is null");
//        else
//        {
//            Connection con = null;
//            String query = "";
//            try {
//                con = getConnector();
//                Statement stmt = con.createStatement();
//                query = "DELETE from" + tableNames.coaches + "WHERE userName = @username";
//
//                stmt.executeQuery(query);
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//
//
//            }
//
//        }
//
//
//    }
//
//    @Override
//    public ResultSet get(String userName) throws Exception
//    {
//        return null;
//    }
//
//
//
//
//}