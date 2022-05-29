package Domain;
import DataAccess.MemberDA;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MemberD
{
    private static final MemberD instance = new MemberD();

    //private constructor to avoid client applications to use constructor
    public static MemberD getInstance() { return instance; }
    private MemberD() {}


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
            String msg = "";
            boolean success;
            if (m.getRole().equals("representative"))
            {
                Representative rep = new Representative(m.getUserName(), m.getPassword(), m.getName());
                success = rep.login(rep.getPassword());
                msg = rep.show(success, username, rep.getRole());
            }
            else if(m.getRole().equals("referee"))
            {
                Referee ref = Referee.getRefFromDB(m.getUserName());
                success = ref.login(username, password);
                msg = ref.show(success, username, ref.getRole());
            }
            else if(m.getRole().equals("player"))
            {
                Player p = Player.getPlayerFromDB(m.getUserName());
                success = p.login(username, password);
                msg = p.show(success, username, p.getRole());
            }

            else if(m.getRole().equals("owner"))
            {
                Owner o = Owner.getOwnerFromDB(m.getUserName());
                success = o.login(username, password);
                msg = o.show(success, username, o.getRole());
            }

            else if(m.getRole().equals("manager"))
            {
                Manager manager = Manager.getManagerFromDB(m.getUserName());
                success = manager.login(username, password);
                msg = manager.show(success, username, manager.getRole());
            }

            else if(m.getRole().equals("fan"))
            {
                Fan fan = new Fan(m.getUserName(), m.getPassword(), m.getName());
                success = fan.login(password);
                msg = fan.show(success, username, fan.getRole());
            }

            else if(m.getRole().equals("coach"))
            {
                Coach coach = Coach.getCoachFromDB(m.getUserName());
                success = coach.login(username, password);
                msg = coach.show(success, username, coach.getRole());
            }





//            switch(m.getRole())
//            {
//                case "representative":
//                    Representative rep = new Representative(m.getUserName(), m.getPassword(), m.getName());
//                    success = rep.login(rep.getPassword());
//                    msg = rep.show(success, username, rep.getRole());
//                    break;
//                case "referee":
//                    Referee ref = Referee.getRefFromDB(m.getUserName());
//                    success = ref.login(username, password);
//                    msg = ref.show(success, username, ref.getRole());
//                    break;
//                case "player":
//                    Player p = Player.getPlayerFromDB(m.getUserName());
//                    success = p.login(username, password);
//                    msg = p.show(success, username, p.getRole());
//                    break;
//                case "owner":
//                    Owner o = Owner.getOwnerFromDB(m.getUserName());
//                    success = o.login(username, password);
//                    msg = o.show(success, username, o.getRole());
//                    break;
//                case "manager":
//                    Manager manager = Manager.getManagerFromDB(m.getUserName());
//                    success = manager.login(username, password);
//                    msg = manager.show(success, username, manager.getRole());
//                    break;
//                case "fan":
//                    Fan fan = new Fan(m.getUserName(), m.getPassword(), m.getName());
//                    success = fan.login(password);
//                    msg = fan.show(success, username, fan.getRole());
//                    break;
//                case "coach":
//                    Coach coach = Coach.getCoachFromDB(m.getUserName());
//                    success = coach.login(username, password);
//                    msg = coach.show(success, username, coach.getRole());
//                    break;
//                default:
//                    msg = "user don't have any role";
//            }

            return msg;
        }
        catch (Exception e)
        {
            throw new Exception("login issue");
        }

    }
}
