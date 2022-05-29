package DataAccess;

import Domain.Member;
import Domain.Referee;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static DataAccess.DBConnector.getConnector;

public class RefereeDA implements DataAccess<Referee>
{
    private static final RefereeDA instance = new RefereeDA();

    //private constructor to avoid client applications to use constructor
    public static RefereeDA getInstance() { return instance; }
    private RefereeDA() {}

    @Override
    public void save(Referee referee) throws Exception
    {
        if (referee == null)
            throw new Exception("member is null");
        Connection conn;
        String userName = referee.getUserName();
        String password = referee.getPassword();
        String role = referee.getRole();
        String name = referee.getName();
        String training = referee.getTraining();

        try { conn = getConnector(); }
        catch(RuntimeException e) {throw new Exception(e.getMessage()); }
        String query = ("INSERT INTO "+ tableNames.members + "(userName ,password, role, name) VALUES (?,?,?,?)");
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, userName);
        preparedStmt.setString(2, password);
        preparedStmt.setString(3, role);
        preparedStmt.setString(4, name);
        preparedStmt.execute();

        String query2 = ("INSERT INTO "+ tableNames.referees + "(userName ,training, isMainReferee) VALUES (?,?, ?)");
        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
        preparedStmt2.setString(1, userName);
        preparedStmt2.setString(2, training);
        preparedStmt2.setBoolean(3, referee.isMainReferee());
        preparedStmt2.execute();
        conn.close();
    }

    @Override
    public void update(Referee referee, Map<String, String> newParams) throws Exception
    {
        if (referee == null || newParams.isEmpty())
            throw new Exception("one of the parameters is null");
        String userName = referee.getUserName();
        Map<String, String> keyParams = new HashMap<>();
        keyParams.put("userName", userName);
        Member memberDB = get(keyParams);
        if (memberDB == null)
            throw new Exception("member doesn't exist");
        Connection conn;
        try { conn = getConnector(); }
        catch(RuntimeException e) {throw new Exception(e.getMessage()); }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableNames.members);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<String> membersColNames = new ArrayList<>();

        // The column count starts from 1
        for (int i = 1; i <= columnCount; i++)
        {
            String name = rsmd.getColumnName(i);
            membersColNames.add(name);
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM " + tableNames.referees);
        rsmd = rs.getMetaData();
        columnCount = rsmd.getColumnCount();
        List<String> ownersColNames = new ArrayList<>();

        // The column count starts from 1
        for (int i = 1; i <= columnCount; i++)
        {
            String name = rsmd.getColumnName(i);
            ownersColNames.add(name);
        }

        for (String key : newParams.keySet())
        {
            if (membersColNames.contains(key))
            {
                String query = "UPDATE " + tableNames.members + " SET " + key + " = ? WHERE userName = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(2, memberDB.getUserName());
                preparedStmt.setString(1, newParams.get(key));
                preparedStmt.execute();
            }
            else if (ownersColNames.contains(key))
            {
                String query = "UPDATE " + tableNames.referees + " SET " + key + " = ? WHERE userName = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(2, memberDB.getUserName());
                preparedStmt.setString(1, newParams.get(key));
                preparedStmt.execute();
            }
        }
        conn.close();
    }

    @Override
    public void delete(Referee referee) throws Exception
    {
        if (referee == null)
            throw new Exception("referee is null");
        String userNameRS = referee.getUserName();
        String roleRS = referee.getClass().getSimpleName().toLowerCase(), table;
        Connection conn;
        try { conn = getConnector(); }
        catch(RuntimeException e) {throw new Exception(e.getMessage()); }
        table = getTableFromRole(roleRS);
        if (table != null)
        {
            //delete from role table
            String query = "DELETE FROM " + table + " WHERE userName = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, userNameRS);
            preparedStmt.execute();
        }
        else { throw new Exception("role is null"); }
        //delete from members table
        String query2 = "DELETE FROM " + tableNames.members + " WHERE userName = ?";
        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
        preparedStmt2.setString(1, userNameRS);
        preparedStmt2.execute();
        conn.close();
    }

    private String getTableFromRole(String roleRS)
    {
        for (tableNames table : tableNames.values())
        {
            if ((table.name().equals(roleRS + "s")) || table.name().equals(roleRS + "es"))
            {
                return table.name();
            }
        }
        return null;
    }

    @Override
    public Referee get(Map<String, String> keyParams)
    {
        for (String val : keyParams.values())
        {
            if (val == null)
                return null;
        }
        ResultSet rs;
        Connection conn;
        Member memberData = null;
        Referee referee = null;
        try
        {
            conn = getConnector();
            String query = "select * from javabase." + tableNames.members + " where userName = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String passwordRS = rs.getString("password");
                String roleRS = rs.getString("role");
                String nameRS = rs.getString("name");
                memberData = new MemberData(userNameRS, passwordRS, nameRS, roleRS);
            }
            preparedStmt.close();

            String query2 = "select * from javabase." + tableNames.referees + " where userName = ?";
            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String trainingRS = rs.getString("training");
                boolean isMainRefereeRS = rs.getBoolean("isMainReferee");
                referee = new Referee(userNameRS, memberData.getPassword(), memberData.getName(), trainingRS);
                referee.setMainReferee(isMainRefereeRS);
            }
            preparedStmt.close();
            conn.close();
        }
        catch (SQLException e) {
            return null;
        }

        return referee;
    }
}
