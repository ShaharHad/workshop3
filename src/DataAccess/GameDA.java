package DataAccess;

import Domain.Game;
import Domain.Owner;
import Domain.Referee;
import Domain.Team;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static DataAccess.DBConnector.getConnector;

public class GameDA implements DataAccess<Game>
{

    private static final GameDA instance = new GameDA();

    //private constructor to avoid client applications to use constructor
    public static GameDA getInstance() { return instance; }
    private GameDA() {}

    @Override
    public void save(Game game) throws Exception
    {
        if (game == null || game.getHomeGroup() == null || game.getGuestGroup() == null)
            throw new Exception("game is null");
        Connection conn;
        String homeTeam = game.getHomeGroup().getTeamName();
        String guestTeam = game.getGuestGroup().getTeamName();
        Date date = game.getDate();
        int eventLogID = game.getEventLog().getID();
        String field = game.getField();
        int seasonID = game.getSeasonID();
        int leagueID = game.getSeasonID();

        try
        {
            conn = getConnector();
            String query = ("INSERT INTO "+ tableNames.games+ "(homeTeam ,guestTeam, fieldName, seasonID," +
                    "leagueID, date, eventLogID) VALUES (?,?,?,?,?,?,?)");
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, homeTeam);
            preparedStmt.setString(2, guestTeam);
            preparedStmt.setString(3, field);
            preparedStmt.setInt(4, seasonID);
            preparedStmt.setInt(5, leagueID);
            preparedStmt.setDate(6, date);
            preparedStmt.setInt(7, eventLogID);
            preparedStmt.execute();
            conn.close();
        }
        catch (SQLException e)
        {
            throw new Exception("Error connecting to the database");
        }
    }

    @Override
    public void update(Game game, Map<String, String> newParams) throws Exception
    {
        if (game == null || newParams.isEmpty())
            throw new Exception("one of the parameters is null");
        Map<String, String> keyParams = new HashMap<>();
        keyParams.put("homeTeam", game.getHomeGroup().getTeamName());
        keyParams.put("guestTeam", game.getGuestGroup().getTeamName());
        keyParams.put("fieldName", game.getField());
        keyParams.put("date", game.getDate().toString());
        Game gameDB = get(keyParams);
        if (gameDB == null) { System.out.println("game doesn't exist"); }
        Connection conn;
        java.sql.Date sqlDate = null;
        try {
            conn = getConnector();
            for (String key : newParams.keySet()) {
                String query = "UPDATE " + tableNames.games + " SET " + key + " = ? WHERE homeTeam = ?" +
                        "and guestTeam = ? and fieldName = ? and date = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                if (key.equals("date")) {
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        java.util.Date utilDate = format.parse(newParams.get(key));
                        sqlDate = new java.sql.Date(utilDate.getTime());
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    preparedStmt.setDate(1, sqlDate);
                    preparedStmt.setString(2, keyParams.get("homeTeam"));
                    preparedStmt.setString(3, keyParams.get("guestTeam"));
                    preparedStmt.setString(4, keyParams.get("fieldName"));
                    preparedStmt.setDate(5, game.getDate());
                    preparedStmt.execute();
                    game.setDate(sqlDate);
                } else {
                    preparedStmt.setString(1, newParams.get(key));
                    preparedStmt.setString(2, keyParams.get("homeTeam"));
                    preparedStmt.setString(3, keyParams.get("guestTeam"));
                    preparedStmt.setString(4, keyParams.get("fieldName"));
                    preparedStmt.setDate(5, game.getDate());
                    preparedStmt.execute();
                }

            }
            conn.close();
        }
        catch (SQLException e)
        {
            throw new Exception("Error connecting to the database");
        }
    }

    @Override
    public void delete(Game game) throws Exception
    {
        if (game == null)
            throw new Exception("game is null");
        String homeTeamName = game.getHomeGroup().getTeamName();
        String guestTeamName = game.getGuestGroup().getTeamName();
        String fieldName = game.getField();
        Date date = game.getDate();
        Connection conn;
        try
        {
            conn = getConnector();
            //delete from games table
            String query2 = "DELETE FROM " + tableNames.games + " WHERE homeTeam = ?" +
                    "and guestTeam = ? and fieldName = ? and date = ?";
            PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
            preparedStmt2.setString(1, homeTeamName);
            preparedStmt2.setString(2, guestTeamName);
            preparedStmt2.setString(3, fieldName);
            preparedStmt2.setDate(4, date);
            preparedStmt2.execute();
            conn.close();
        }
        catch (Exception e) { System.out.println("problem in delete function"); }
    }


    @Override
    public Game get(Map<String, String> keyParams)
    {
        ResultSet rs;
        Connection conn;
        Game game;
        conn = getConnector();
        String queryGames = "select * from javabase." + tableNames.games + " where homeTeam = ? and " +
                "guestTeam = ? and fieldName = ? and date = ?";
        String queryTeams = "select * from javabase." + tableNames.teams + " where teamName = ?";
        String queryOwners = "select * from javabase." + tableNames.owners + " where userName = ?";
        String queryMembers = "select * from javabase." + tableNames.members + " where userName = ?";

        PreparedStatement preparedStmt;
        String homeTeamName = keyParams.get("homeTeam"), homeTeamRS = "";
        String guestTeamName = keyParams.get("guestTeam"), guestTeamRS = "";
        String fieldName = keyParams.get("fieldName"), fieldRS = "", homeFieldRS = "";
        String date = keyParams.get("date");
        int seasonIDRS = 0, leagueIDRS = 0, hourRS = 0, eventLogIDRS = 0;
        Team homeTeam, guestTeam;
        Owner owner;
        MemberData member = null;
        Date dateRS = null;
        String ownerName = "", userNameRS = "";
        try
        {
            preparedStmt = conn.prepareStatement(queryGames);
            preparedStmt.setString(1, homeTeamName);
            preparedStmt.setString(2, guestTeamName);
            preparedStmt.setString(3, fieldName);
            preparedStmt.setString(4, date);
            rs = preparedStmt.executeQuery();

            while(rs.next())
            {
                homeTeamRS = rs.getString("homeTeam");
                guestTeamRS = rs.getString("guestTeam");
                fieldRS = rs.getString("fieldName");
                seasonIDRS = rs.getInt("seasonID");
                leagueIDRS = rs.getInt("leagueID");
                dateRS = rs.getDate("date");
                eventLogIDRS = rs.getInt("eventLogID");
                hourRS = rs.getInt("hour");
            }
            preparedStmt.close();

            preparedStmt = conn.prepareStatement(queryTeams);
            preparedStmt.setString(1, homeTeamRS);
            rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                homeFieldRS = rs.getString("homeField");
                ownerName = rs.getString("ownerUserName");
            }
            preparedStmt.close();

            preparedStmt = conn.prepareStatement(queryOwners);
            preparedStmt.setString(1, ownerName);
            rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                userNameRS = rs.getString("userName");
            }
            preparedStmt.close();

            preparedStmt = conn.prepareStatement(queryMembers);
            preparedStmt.setString(1, userNameRS);
            rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                userNameRS = rs.getString("userName");
                String passwordRS = rs.getString("password");
                String roleRS = rs.getString("role");
                String nameRS = rs.getString("name");
                member = new MemberData(userNameRS, passwordRS, nameRS, roleRS);
            }
            preparedStmt.close();

            owner = new Owner(member.getUserName(), member.getPassword(), member.getName(), homeTeamRS);
            homeTeam = new Team(owner, homeTeamRS, homeFieldRS);

            preparedStmt = conn.prepareStatement(queryTeams);
            preparedStmt.setString(1, guestTeamRS);
            rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                homeFieldRS = rs.getString("homeField");
                ownerName = rs.getString("ownerUserName");
            }
            preparedStmt.close();

            preparedStmt = conn.prepareStatement(queryOwners);
            preparedStmt.setString(1, ownerName);
            rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                userNameRS = rs.getString("userName");
            }
            preparedStmt.close();

            preparedStmt = conn.prepareStatement(queryMembers);
            preparedStmt.setString(1, userNameRS);
            rs = preparedStmt.executeQuery();
            while(rs.next())
            {
                userNameRS = rs.getString("userName");
                String passwordRS = rs.getString("password");
                String roleRS = rs.getString("role");
                String nameRS = rs.getString("name");
                member = new MemberData(userNameRS, passwordRS, roleRS, nameRS);
            }
            preparedStmt.close();

            owner = new Owner(member.getUserName(), member.getPassword(), member.getName(), homeTeamRS);
            guestTeam = new Team(owner, guestTeamRS, homeFieldRS);

            game = new Game(guestTeam, homeTeam);
            game.setField(fieldRS);
            game.setSeasonID(seasonIDRS);
            game.setLeagueID(leagueIDRS);
            game.setDate(dateRS);
            game.setHour(hourRS);
            game.setEventLogID(eventLogIDRS);

            conn.close();
        }
        catch (Exception e) {
            return null;
        }

        return game;
    }
}