package Domain;

import DataAccess.GameDA;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class RepresentativeController
{
    private static final RepresentativeController instance = new RepresentativeController();
    GameDA gda = GameDA.getInstance();

    //private constructor to avoid client applications to use constructor
    public static RepresentativeController getInstance() { return instance; }
    private RepresentativeController() {}

    public Game searchGame(Team homeTeam, Team guestTeam, String fieldName, Date date)
    {
        Map<String, String> keyParams = new HashMap<>();
        keyParams.put("guestTeam", guestTeam.getTeamName());
        keyParams.put("homeTeam", homeTeam.getTeamName());
        keyParams.put("fieldName", fieldName);
        keyParams.put("date", date.toString());
        Game gameDB = gda.get(keyParams);
        if (gameDB != null)
        {
            showGameDetails(gameDB);
            return gameDB;
        }
        else
            return null;
    }

    private void showGameDetails(Game gameDB)
    {
        System.out.println("Game Details:");
        System.out.println("\tHome Team: " + gameDB.getHomeGroup().getTeamName());
        System.out.println("\tGuest Team: " + gameDB.getGuestGroup().getTeamName());
        System.out.println("\tField Name: " + gameDB.getField());
        System.out.println("\tDate: " + gameDB.getDate().toString());
        if (gameDB.getHour() != 0)
            System.out.println("\tHour: " + gameDB.getHour());
        else
            System.out.println("\tHour: Not Set");
    }


    public Status updateGame(Game game, Map<String, String> newParams)
    {
        try {
            gda.update(game, newParams);
        } catch (Exception e) {
            return Status.Failure;
        }
        return Status.Success;
    }

}
