package Domain;

import DataAccess.MemberDA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Representative extends Member
{
    /**
     * constructor of Representative class -extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     */
    //need to add role for this
    public Representative(String username, String password, String name) {
        super(username, password, name, "representative");
    }


//    public Status createGame(){
//        return Status.Success;
//    }
    /**
     * function assignGame
     * @return Status.Success type Status
     */
    public Status assignGame(){
        return Status.Success;
    }
    /**
 * function assignRef
 * @return Status.Success type Status
 */
    public Status assignRef(){
        return Status.Success;
    }

//    public Status setSocerPolicy(){
//        return Status.Success;
//    }

    /**
     * function addRefereeTOGame
     * @return Status.Success type Status
     */
    public Status addRefereeTOGame(){
        return Status.Success;
    }
    /**
     * function login Checks if the user can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Checks whether the user is registered as a owner in the system
     * Return true if both conditions are met otherwise return false
     * @param password $password
     * @return boolean
     */
    public boolean login(String password) throws Exception {
        if (password == null) {
            throw new Exception("One of the parameters is null");
        }
        return this.getPassword().equals(password);
    }

    static Representative getRepresentativeFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        MemberDA mda = MemberDA.getInstance();
        Member m = mda.get(map);
        return new Representative(m.getUserName(), m.getPassword(), m.getName());
    }


}
