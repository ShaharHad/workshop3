package Service;

import Domain.Game;
import Domain.MemberController;
import Domain.RepresentativeController;
import Domain.Status;

import java.sql.Date;
import java.util.HashMap;
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
        Game gameDB = rpc.searchGame(game.getHomeGroup(), game.getGuestGroup(), game.getField(), game.getDate());
        Map<String, String> newParams = new HashMap<>();
        newParams.put("date", date.toString());
        return rpc.updateGame(gameDB, newParams);
    }
}
