package DataAccess;

import Domain.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import static DataAccess.DBConnector.getConnector;

public class PlayerDA implements DataAccess<Player>{
    private static final PlayerDA instance = new PlayerDA();

    //private constructor to avoid client applications to use constructor
    public static PlayerDA getInstance() { return instance; }
    /**
     * constructor of PlayerDA class
     */
    private PlayerDA() {}

    /**
     * function save: save a new player in DB
     * @param player $player
     * create a new instance of player in DB type void
     */
    @Override

    public void save(Player player) throws Exception {

    }

    /**
     * function update: update the player in DB
     * @param player $player
     * @param newParams $newParams
     * update DB of Player type void
     */
    @Override

    public void update(Player player, Map<String, String> newParams) throws Exception {

    }

    /**
     * function delete: delete the player from DB
     * @param player $player
     * delete Player from DB type void
     */
    @Override

    public void delete(Player player) throws Exception {

    }

    /**
     * function get : Connects to the database  and return player object from the DB
     * the function find by keyParams of the player in DB and return object of hem.
     * @param keyParams $keyParams
     * @return Player
     */
    @Override

    public Player get(Map<String, String> keyParams) {
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
        Player player = null;
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

            String query2 = "select * from javabase." + tableNames.players + " where userName = ?";
            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String teamNameRS = rs.getString("teamName");

                Date birthDateRS = rs.getDate("birthDate");
                String roleRS = rs.getString("role");

                player = new Player(userNameRS, member.getPassword(), member.getName(), birthDateRS, roleRS, teamNameRS);
            }
            preparedStmt.close();
            conn.close();
        }
        catch (SQLException e) {
            return null;
        }

        return player;
    }
}