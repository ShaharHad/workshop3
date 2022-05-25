package DataAccess;

import Domain.Member;
import Domain.Owner;
import Domain.Status;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static DataAccess.DBConnector.getConnector;

public class OwnerDA implements DataAccess<Owner>
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


    private String getTableFromRole(String roleRS) throws Exception
    {
        if (roleRS == null)
            throw new Exception("role is null");
        else
        {
            for (tableNames table : tableNames.values())
            {
                if ((table.name().equals(roleRS + "s")) || table.name().equals(roleRS + "es"))
                {
                    return table.name();
                }
            }
        }
        return null;
    }

    // no need this function for owner
    @Override
    public void save(Owner owner) throws Exception
    {
        if (owner == null)
            throw new Exception("member is null");
        Connection conn;
        String userName = owner.getUserName();
        String teamName = owner.getTeamName();
        try
        {
            conn = getConnector();
            String query = ("INSERT INTO "+ tableNames.owners+ "(userName ,teamName) VALUES (?,?)");
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, userName);
            preparedStmt.setString(2, teamName);
            preparedStmt.execute();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    // update the members table
    public void update(Owner owner, Map<String, String> newParams) throws Exception
    {
        if (owner == null || newParams.isEmpty())
            throw new Exception("one of the parameters is null");
        String userName = owner.getUserName();
        Member memberDB = get(userName);
        if (memberDB == null) { System.out.println("member doesn't exist"); }
        Connection conn;
        try
        {
            conn = getConnector();
            for (String key : newParams.keySet())
            {
                String query = "UPDATE " + tableNames.members + " SET " + key + " = ? WHERE userName = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(2, memberDB.getUserName());
                preparedStmt.setString(1, newParams.get(key));
                preparedStmt.execute();
                conn.close();
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Owner owner) throws Exception
    {
        if (owner == null)
            throw new Exception("userName is null");
        String userNameRS = owner.getUserName();
        String roleRS = owner.getClass().getSimpleName(), table;
        Connection conn;
        conn = getConnector();
        try
        {
            table = getTableFromRole(roleRS);
            if (table != null)
            {
                //delete from role table
                String query = "DELETE FROM javabase." + table + " WHERE userName = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, userNameRS);
                preparedStmt.execute();
            }
            conn.close();
        }
        catch (Exception e) { System.out.println("problem in delete function");}
    }

    @Override
    public Member get(String userName)
    {
        if (userName == null)
            return null;
        ResultSet rs;
        Connection conn;
        Member member = null;
        conn = getConnector();
        String query = "select * from javabase." + tableNames.members + " where userName = ?";
        PreparedStatement preparedStmt;
        try
        {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, userName);
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String passwordRS = rs.getString("password");
                String roleRS = rs.getString("role");
                String nameRS = rs.getString("name");
                member = new MemberData(userNameRS, passwordRS, roleRS, nameRS);
            }
            preparedStmt.close();
            conn.close();
        }
        catch (SQLException e) {
            return null;
        }
        return member;
    }


}
