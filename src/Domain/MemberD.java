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
        MemberDA mda = MemberDA.getInstance();
        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", username);
        Member m = mda.get(map);
        String msg = "";
        switch(m.getRole())
        {
            case "representative":

                break;
            case "referee":

                break;
            case "player":

                break;
            case "owner":
                Owner o = Owner.getOwner(m.getUserName());
                boolean success = o.login(username, password);
                msg = o.show(success, username);
                break;
            case "manager":

                break;
            case "fan":


                break;
            case "coach":

                break;
            default:
                msg = "user dont have any role";
        }

        return msg;
    }
}
