package Domain;

import DataAccess.GameDA;
import DataAccess.RefereeDA;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepresentativeController
{
    private static final RepresentativeController instance = new RepresentativeController();
    GameDA gda = GameDA.getInstance();
    RefereeDA rda = RefereeDA.getInstance();

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
        if (gameDB.getReferee1() != null)
            System.out.println("\tReferee1: " + gameDB.getReferee1().getUserName());
        else
            System.out.println("\tReferee1: Not Set");
        if (gameDB.getReferee2() != null)
            System.out.println("\tReferee2: " + gameDB.getReferee2().getUserName());
        else
            System.out.println("\tReferee2: Not Set");
        if (gameDB.getMainReferee() != null)
            System.out.println("\tMain Referee: " + gameDB.getMainReferee().getUserName());
        else
            System.out.println("\tMain Referee: Not Set");

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

//    public Status assignRefToGame(Team homeTeam, Team guestTeam, String fieldName, Date date) throws Exception
//    {
//        if (homeTeam == null || guestTeam == null || fieldName == null || date == null )
//        {
//            throw new Exception("at least one of the parameters is null");
//        }
//        RepresentativeController rc = RepresentativeController.getInstance();
//        Game game = rc.searchGame(homeTeam, guestTeam, fieldName, date);
//        List<Referee> =
//        return Status.Success;
//    }

    public List<Referee> searchAvailableReferee(Date date)
    {
        if(date == null)
        {
            return null;
        }
        List<Referee> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("date", date.toString());
        try
        {
           list = rda.getAllAvailableReferee(map);
        }
        catch (Exception e)
        {
            return null;
        }
        return list;
    }

    public Status assignRefsToGame(Game gameDB, List<Referee> list)
    {
        if (gameDB == null || list == null)
        {
            return Status.Failure;
        }
        Map<String, String> newParam = new HashMap<>();
        if(gameDB.getReferee1() == null)
        {
            newParam.put("referee1UN", list.get(0).getUserName());
            gameDB.setReferee1(list.get(0));
            list.remove(0);
        }
        if(gameDB.getReferee2() == null)
        {
            newParam.put("referee2UN", list.get(0).getUserName());
            gameDB.setReferee2(list.get(0));
            list.remove(0);
        }
        if(gameDB.getMainReferee() == null)
        {
            newParam.put("mainRefereeUN", list.get(0).getUserName());
            gameDB.setMainReferee(list.get(0));
            Map<String, String> m = new HashMap<>();
            m.put("isMAinReferee", "1");
            try
            {
                rda.update(list.get(0), m);
            }
            catch (Exception e)
            {
                return Status.Failure;
            }
            list.remove(0);
        }
        if(newParam.size() == 0)
        {
            return Status.Failure;
        }
        this.updateGame(gameDB, newParam);
        return Status.Success;
    }

    public Status removeRefFromGame(Game game, Map<String, String> removeParams)
    {
        Map<String, String> refereeParam = new HashMap<>();
        Map<String, String> gameParam = new HashMap<>();
        String refereeUserName = removeParams.get(removeParams.keySet().toArray()[0]);
        refereeParam.put("userName", refereeUserName);
        Referee referee = rda.get(refereeParam);
        if(removeParams.containsKey("mainRefereeUN"))
        {
            Map<String, String> m = new HashMap<>();
            m.put("isMAinReferee", "0");
            referee.setMainReferee(false);
            try
            {
                rda.update(referee, m);
            }
            catch (Exception e)
            {
                return Status.Failure;
            }
            gameParam.put("mainRefereeUN", null);
        }
        else if(removeParams.containsKey("referee1UN"))
        {
            gameParam.put("referee1UN", null);
        }
        else if(removeParams.containsKey("referee2UN"))
        {
            gameParam.put("referee2UN", null);
        }
        try
        {
            gda.update(game, gameParam);
        }
        catch (Exception e)
        {
            return Status.Failure;
        }
        return Status.Success;
    }

}
