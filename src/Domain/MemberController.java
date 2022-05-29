package Domain;
import DataAccess.MemberDA;

import java.util.HashMap;
import java.util.Map;

public class MemberController
{
    private static final MemberController instance = new MemberController();

    //private constructor to avoid client applications to use constructor
    public static MemberController getInstance() { return instance; }
    private MemberController() {}


    public String userLogin(String username, String password) throws Exception
    {
        if(username == null || password == null)
        {
            throw new Exception("One of the parameters is null");
        }
        try
        {
            MemberDA mda = MemberDA.getInstance();
            Map<String, String> map = new HashMap<>();
            map.put("userName", username);
            Member m = mda.get(map);
            String msg;
            boolean success;
            switch(m.getRole())
            {
                case "representative":
                    Representative rep = new Representative(m.getUserName(), m.getPassword(), m.getName());
                    success = rep.login(rep.getPassword());
                    msg = rep.show(success, username, rep.getRole());
                    break;
                case "referee":
                    Referee ref = Referee.getRefFromDB(m.getUserName());
                    success = ref.login(username, password);
                    msg = ref.show(success, username, ref.getRole());
                    break;
                case "player":
                    Player p = Player.getPlayerFromDB(m.getUserName());
                    success = p.login(username, password);
                    msg = p.show(success, username, p.getRole());
                    break;
                case "owner":
                    Owner o = Owner.getOwnerFromDB(m.getUserName());
                    success = o.login(username, password);
                    msg = o.show(success, username, o.getRole());
                    break;
                case "manager":
                    Manager manager = Manager.getManagerFromDB(m.getUserName());
                    success = manager.login(username, password);
                    msg = manager.show(success, username, manager.getRole());
                    break;
                case "fan":
                    Fan fan = new Fan(m.getUserName(), m.getPassword(), m.getName());
                    success = fan.login(password);
                    msg = fan.show(success, username, fan.getRole());
                    break;
                case "coach":
                    Coach coach = Coach.getCoachFromDB(m.getUserName());
                    success = coach.login(username, password);
                    msg = coach.show(success, username, coach.getRole());
                    break;
                default:
                    msg = "user dont have any role";
            }

            return msg;
        }
        catch (Exception e)
        {
            throw new Exception("login issue");
        }

    }
}
