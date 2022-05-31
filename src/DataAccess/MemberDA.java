package DataAccess;


import Domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static DataAccess.DBConnector.getConnector;

public class MemberDA implements DataAccess<Member>
{

    private static final MemberDA instance = new MemberDA();

    //private constructor to avoid client applications to use constructor
    public static MemberDA getInstance() { return instance; }
    /**
     * constructor of MemberDA class
     */
    private MemberDA() {}

    @Override
    /**
     * function save: save a new member in DB
     * @param member $member
     * @create a new instance of member in DB type void
     */
    public void save(Member member) throws Exception
    {
        if (member == null)
            throw new Exception("member is null");
        Connection conn;
        String userName = member.getUserName();
        String password = member.getPassword();
        String role = member.getRole();
        String name =member.getName();
        try { conn = getConnector(); }
        catch(RuntimeException e) {throw new Exception(e.getMessage()); }
        String query = ("INSERT INTO "+ tableNames.members+ "(userName ,password, role, name) VALUES (?,?,?,?)");
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, userName);
        preparedStmt.setString(2, password);
        preparedStmt.setString(3, role);
        preparedStmt.setString(4, name);
        preparedStmt.execute();
        conn.close();

    }

    @Override
    /**
     * function update: update the manager in DB
     * @param member $member
     * @param newParams $newParams
     * @updete DB of Member type void
     */
    public void update(Member member, Map<String, String> newParams) throws Exception
    {
        if (member == null || newParams.isEmpty())
            throw new Exception("one of the parameters is null");
        String userName = member.getUserName();
        Map<String, String> keyParams = new HashMap<>();
        keyParams.put("userName", userName);
        Member memberDB = get(keyParams);
        if (memberDB == null)
            throw new Exception("member doesn't exist");
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
    /**
     * function delete: delete the member from DB
     * @param member $member
     * @delete Member from DB type void
     */
    public void delete(Member member) throws Exception
    {
        if (member == null)
            throw new Exception("member is null");
        String userNameRS = member.getUserName();
        String roleRS = member.getRole(), table;
        Connection conn;
        conn = getConnector();
        try
        {
            table = getTableFromRole(roleRS);
            if (table != null)
            {
                //delete from role table
                String query = "DELETE FROM " + table + " WHERE userName = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, userNameRS);
                preparedStmt.execute();
            }

            //delete from members table
            String query2 = "DELETE FROM " + tableNames.members + " WHERE userName = ?";
            PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
            preparedStmt2.setString(1, userNameRS);
            preparedStmt2.execute();
            conn.close();
        }
        catch (Exception e) { System.out.println("problem in delete function"); }
    }
    /**
     * function getTableFromRole : get roleRS and Returns the name of the relevant member table
     * @param roleRS $roleRS
     * @return String
     */
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

//    public List getDataFromRoleTable(String username, String role) throws Exception
//    {
//        if(username == null || role == null)
//        {
//
//        }
//    }

    @Override
    /**
     * function get : Connects to the database  and return Member object from the DB
     * the function find by keyParams of the Member in DB and return object of hem.
     * @param keyParams $keyParams
     * @return Member
     */
    public Member get(Map<String, String> keyParams)
    {
        if (keyParams.isEmpty())
            return null;
        for (String val : keyParams.values())
        {
            if (val == null)
                return null;
        }
        ResultSet rs;
        Connection conn;
        Member member = null;
        conn = getConnector();
        String query = "select * from javabase." + tableNames.members + " where userName = ?";
        PreparedStatement preparedStmt;
        try
        {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String passwordRS = rs.getString("password");
                String roleRS = rs.getString("role");
                String nameRS = rs.getString("name");
                member = new MemberData(userNameRS, passwordRS, nameRS, roleRS);
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