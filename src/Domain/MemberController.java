package Domain;
import DataAccess.MemberDA;

import java.util.HashMap;
import java.util.Map;

/**
 * MemberController class is the class that Connects the domain layer to the service layer
 */
public class MemberController
{
    private static final MemberController instance = new MemberController();

    //private constructor to avoid client applications to use constructor
    public static MemberController getInstance() { return instance; }
    /**
     * constructor of  MemberController class
     */
    private MemberController() {}

    /**
     * function userLogin Checks if the user can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Check that the user is Member registered to the system
     * Return Success if both conditions are met otherwise return Failure
     * @param username $username
     * @param password $password
     * @return Status
     */

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
            /** We check what type of member the user is and whether he is registered */
            if (m.getRole().equals("representative"))
            {
                Representative rep = new Representative(m.getUserName(), m.getPassword(), m.getName());
                success = rep.login(password);
            }
            else if(m.getRole().equals("referee"))
            {
                Referee ref = Referee.getRefFromDB(m.getUserName());
                success = ref.login(username, password);
            }
            else if(m.getRole().equals("player"))
            {
                Player p = Player.getPlayerFromDB(m.getUserName());
                success = p.login(username, password);
            }

            else if(m.getRole().equals("owner"))
            {
                Owner o = Owner.getOwnerFromDB(m.getUserName());
                success = o.login(username, password);
            }
            else if(m.getRole().equals("manager"))
            {
                Manager manager = Manager.getManagerFromDB(m.getUserName());
                success = manager.login(username, password);
            }

            else if(m.getRole().equals("fan"))
            {
                Fan fan = new Fan(m.getUserName(), m.getPassword(), m.getName());
                success = fan.login(password);
            }

            else if(m.getRole().equals("coach"))
            {
                Coach coach = Coach.getCoachFromDB(m.getUserName());
                success = coach.login(username, password);
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
