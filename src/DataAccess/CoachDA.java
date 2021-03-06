package DataAccess;

import Domain.Coach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static DataAccess.DBConnector.getConnector;

public class CoachDA implements DataAccess<Coach> {
    private static final CoachDA instance = new CoachDA();

    //private constructor to avoid client applications to use constructor
    public static CoachDA getInstance() { return instance; }
    /**
     * constructor of CoachDA class
     */
    private CoachDA() {}

    @Override
    /*
      function save: save a new coach in DB
      @param coach $coach
     * @create a new instance of coach in DB type void
     */
    public void save(Coach coach) throws Exception {

    }

    @Override
    /*
      function update: update the coach in DB
      @param coach $coach
     * @param newParams $newParams
     * @updete DB of coach type void
     */
    public void update(Coach coach, Map<String, String> newParams) throws Exception {

    }

    @Override
    /*
     * function delete: delete the coach from DB
     * @param coach $coach
     * @delete coach from DB type void
     */
    public void delete(Coach coach) throws Exception {

    }

    @Override
    /*
     * function get Connects to the database  and return choach object from the DB
     * the function find by keyParams the choach in DB and return object of hem.
     * @param keyParams $keyParams
     * @return Coach
     */
    public Coach get(Map<String, String> keyParams) {
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
        Coach coach = null;
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

            String query2 = "select * from javabase." + tableNames.coaches + " where userName = ?";
            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String teamNameRS = rs.getString("teamName");
                String trainingRS = rs.getString("training");
                String roleInTeam = rs.getString("role");
                coach = new Coach(userNameRS, member.getPassword(), member.getName(),roleInTeam,trainingRS, teamNameRS);
            }
            preparedStmt.close();
            conn.close();
        }
        catch (SQLException e) {
            return null;
        }

        return coach;
    }

}
