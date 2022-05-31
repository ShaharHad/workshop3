package Service;

import Domain.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ucFunctions
{
    MemberController mbc = MemberController.getInstance();
    RepresentativeController rpc = RepresentativeController.getInstance();

    //login procedure
    public Status userLogin(String userName, String password)
    {
        return mbc.userLogin(userName, password);
    }

    //schedule games
    public Status scheduleGame(Game game, Date date)
    {
        String stringDate = date.toString();
        Game gameDB = rpc.searchGame(game.getHomeGroup(), game.getGuestGroup(), game.getField(), game.getDate());
        Map<String, String> newParams = new HashMap<>();
        newParams.put("date", stringDate);
        return rpc.updateGame(gameDB, newParams);
    }

    public Status assignRef(Team homeTeam, Team guestTeam, String fieldName, Date date)
    {
        Game gameDB = rpc.searchGame(homeTeam, guestTeam, fieldName, date);
        List<Referee> list = rpc.searchAvailableReferee(date);
        return rpc.assignRefsToGame(gameDB, list);
    }
    // key is the column name of the referee section in games in database
    public Status removeRef(Team homeTeam, Team guestTeam, String fieldName, Date date, Map<String, String> removeParam)
    {
        Game gameDB = rpc.searchGame(homeTeam, guestTeam, fieldName, date);
        return rpc.removeRefFromGame(gameDB, removeParam);
    }

}
