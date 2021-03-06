package DataAccess;

import Domain.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static DataAccess.DBConnector.getConnector;

public class ManagerDA implements DataAccess<Manager>
{
    private static final ManagerDA instance = new ManagerDA();

    //private constructor to avoid client applications to use constructor
    public static ManagerDA getInstance() { return instance; }
    /**
     * constructor of ManagerDA class
     */
    private ManagerDA() {}

    /**
     * function save: save a new game in DB
     * @param manager $manager
     * create a new instance of manager in DB type void
     */
    @Override

    public void save(Manager manager) throws Exception {

    }

    /**
     * function get : Connects to the database  and return Manager object from the DB
     * the function find by keyParams the Manager in DB and return object of hem.
     * @param keyParams $keyParams
     * @return Manager
     */
    @Override

    public Manager get(Map<String, String> keyParams) {
        if (keyParams.isEmpty())
            return null;
        for (String val : keyParams.values())
        {
            if (val == null)
                return null;
        }
        ResultSet rs;
        Connection conn;
        MemberData member = null;
        Manager manager = null;
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

            String query2 = "select * from javabase." + tableNames.managers + " where userName = ?";
            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String teamNameRS = rs.getString("teamName");
                manager = new Manager(userNameRS, member.getPassword(), member.getName(), teamNameRS);
            }
            preparedStmt.close();
            conn.close();
        }
        catch (SQLException e) {
            return null;
        }

        return manager;
    }

    /**
     * function update: update the manager in DB
     * @param manager $manager
     * @param newParams $newParams
     * update DB of manager type void
     */
    @Override

    public void update(Manager manager, Map<String, String> newParams) throws Exception {

    }

    /**
     * function delete: delete the manager from DB
     * @param manager $manager
     * delete Manager from DB type void
     */
    @Override

    public void delete(Manager manager) throws Exception {

    }
}