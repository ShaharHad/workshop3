package Domain;

import DataAccess.OwnerDA;

import java.util.HashMap;
import java.util.Map;

public class Fan extends Member {
    public Fan(String username, String password, String name) {
        super(username, password, name);
    }

    public Status signUpToFollowPages() {
        return Status.Success;
    }

    public Status signUpToFollowGames(){
        return Status.Success;
    }

    public void repotBug(String str){

    }
    public String watchHistory(){
        return "pass";
    }

    public Status updateInfo(){
        return Status.Success;
    }

    public boolean login(String password) throws Exception {
        if (password == null) {
            throw new Exception("One of the parameters is null");
        }
        return this.getPassword().equals(password);
    }
}