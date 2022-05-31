package DataAccess;


import Domain.Member;
import Domain.Owner;

import java.sql.*;
import java.util.*;

import static DataAccess.DBConnector.getConnector;

public class OwnerDA implements DataAccess<Owner>
{
    private static final OwnerDA instance = new OwnerDA();

    //private constructor to avoid client applications to use constructor
    public static OwnerDA getInstance() { return instance; }
    /**
     * constructor of OwnerDA class
     */
    private OwnerDA() {}

    /**
     * function save: save a new owner in DB
     * @param owner $owner
     * create a new instance of owner in DB type void
     */
    @Override

    public void save(Owner owner) throws Exception
    {
        if (owner == null)
            throw new Exception("member is null");
        Connection conn;
        String userName = owner.getUserName();
        String password = owner.getPassword();
        String role = owner.getRole();
        String name = owner.getName();
        String teamName = owner.getTeamName();
        try { conn = getConnector(); }
        catch(RuntimeException e) {throw new Exception(e.getMessage()); }
        String query = ("INSERT INTO "+ tableNames.members + "(userName ,password, role, name) VALUES (?,?,?,?)");
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, userName);
        preparedStmt.setString(2, password);
        preparedStmt.setString(3, role);
        preparedStmt.setString(4, name);
        preparedStmt.execute();

        String query2 = ("INSERT INTO "+ tableNames.owners + "(userName ,teamName) VALUES (?,?)");
        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
        preparedStmt2.setString(1, userName);
        preparedStmt2.setString(2, teamName);
        preparedStmt2.execute();
        conn.close();
    }

    /**
     * function update: update the owner in DB
     * @param owner $owner
     * @param newParams $newParams
     * update DB of Owner type void
     */
    @Override

    public void update(Owner owner, Map<String, String> newParams) throws Exception
    {
        if (owner == null || newParams.isEmpty())
            throw new Exception("one of the parameters is null");
        String userName = owner.getUserName();
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
        rs = stmt.executeQuery("SELECT * FROM " + tableNames.owners);
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
                String query = "UPDATE " + tableNames.owners + " SET " + key + " = ? WHERE userName = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(2, memberDB.getUserName());
                preparedStmt.setString(1, newParams.get(key));
                preparedStmt.execute();
            }
            conn.close();
        }
    }

    /**
     * function delete: delete the owner from DB
     * @param owner $owner
     * delete Owner from DB type void
     */
    @Override

    public void delete(Owner owner) throws Exception
    {
        if (owner == null)
            throw new Exception("owner is null");
        String userNameRS = owner.getUserName();
        String roleRS = owner.getClass().getSimpleName().toLowerCase(), table;
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
     * function getTableFromRole : get roleRS and Returns the name of relevant member table
     * @param roleRS $roleRS
     * @return String
     */
    private String getTableFromRole(String roleRS)
    {
        if (roleRS == null)
            return null;
        for (tableNames table : tableNames.values())
        {
            if ((table.name().equals(roleRS + "s")) || table.name().equals(roleRS + "es"))
            {
                return table.name();
            }
        }
        return null;
    }

    /**
     * function get : Connects to the database  and return Owner object from the DB
     * the function find by keyParams of the Owner in DB and return object of hem.
     * @param keyParams $keyParams
     * @return Owner
     */
    @Override

    public Owner get(Map<String, String> keyParams)
    {
        ResultSet rs;
        Connection conn;
        Member member = null;
        Owner owner = null;
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

            String query2 = "select * from javabase." + tableNames.owners + " where userName = ?";
            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String teamNameRS = rs.getString("teamName");
                owner = new Owner(userNameRS, member.getPassword(), member.getName(), teamNameRS);
            }
            preparedStmt.close();
            conn.close();
        }
        catch (SQLException e) {
            return null;
        }

        return owner;
    }
}
