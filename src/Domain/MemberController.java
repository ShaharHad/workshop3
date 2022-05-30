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


    public Status userLogin(String username, String password)
    {
        if(username == null || password == null)
        {
            return Status.Failure;
        }
        try
        {
            MemberDA mda = MemberDA.getInstance();
            Map<String, String> map = new HashMap<>();
            map.put("userName", username);
            Member m = mda.get(map);
            boolean success = false;
            switch (m.getRole()) {
                case "representative":
                    Representative rep = new Representative(m.getUserName(), m.getPassword(), m.getName());
                    success = rep.login(password);
                    break;
                case "referee":
                    Referee ref = Referee.getRefFromDB(m.getUserName());
                    success = ref.login(username, password);
                    break;
                case "player":
                    Player p = Player.getPlayerFromDB(m.getUserName());
                    success = p.login(username, password);
                    break;
                case "owner":
                    Owner o = Owner.getOwnerFromDB(m.getUserName());
                    success = o.login(username, password);
                    break;
                case "manager":
                    Manager manager = Manager.getManagerFromDB(m.getUserName());
                    success = manager.login(username, password);
                    break;
                case "fan":
                    Fan fan = new Fan(m.getUserName(), m.getPassword(), m.getName());
                    success = fan.login(password);
                    break;
                case "coach":
                    Coach coach = Coach.getCoachFromDB(m.getUserName());
                    success = coach.login(username, password);
                    break;
            }
            if(success)
            {
                return Status.Success;
            }
            return Status.Failure;
        }
        catch (Exception e)
        {
            return Status.Failure;
        }
    }
}
