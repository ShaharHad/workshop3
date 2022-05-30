package Domain;

import DataAccess.MemberDA;
import DataAccess.OwnerDA;

import java.util.HashMap;
import java.util.Map;

public class Representative extends Member
{
    //need to add role for this
    public Representative(String username, String password, String name) {
        super(username, password, name, "representative");
    }


//    public Status createGame(){
//        return Status.Success;
//    }
    public Status assignGame(){
        return Status.Success;
    }
    public Status assignRef(){
        return Status.Success;
    }
//    public Status setSocerPolicy(){
//        return Status.Success;
//    }
    public Status addRefereeTOGame(){
        return Status.Success;
    }


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
