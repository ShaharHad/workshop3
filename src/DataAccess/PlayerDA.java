package DataAccess;

import Domain.Manager;
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
    private PlayerDA() {}
    @Override
    public void save(Player player) throws Exception {

    }

    @Override
    public void update(Player player, Map<String, String> newParams) throws Exception {

    }

    @Override
    public void delete(Player player) throws Exception {

    }

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

            String query2 = "select * from javabase." + tableNames.managers + " where userName = ?";
            preparedStmt = conn.prepareStatement(query2);
            preparedStmt.setString(1, keyParams.get("userName"));
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                String userNameRS = rs.getString("userName");
                String teamNameRS = rs.getString("teamName"); //???? need?

                Date birthDateRS = rs.getDate("birthDate");
                String roleRS = rs.getString("role");

                player = new Player(userNameRS, member.getPassword(), member.getName(),roleRS, birthDateRS);
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
