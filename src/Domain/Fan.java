package Domain;

import DataAccess.CoachDA;
import DataAccess.MemberDA;
import DataAccess.OwnerDA;

import java.util.HashMap;
import java.util.Map;

public class Fan extends Member {
    /**
     * constructor of  Fan class -extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     */
    public Fan(String username, String password, String name) {
        super(username, password, name);
    }

//    public Status signUpToFollowPages() {
//        return Status.Success;
//    }
//
//    public Status signUpToFollowGames(){
//        return Status.Success;
//    }
//
//    public void repotBug(String str){
//
//    }
//    public String watchHistory(){
//        return "pass";
//    }
//
//    public Status updateInfo(){
//        return Status.Success;
//    }
    /**
     * function login Checks if the user can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Checks whether the user is registered as a fan in the system
     * Return true if both conditions are met otherwise return false
     * @param password $password
     * @return boolean
     */
    public boolean login(String password) throws Exception {
        if (password == null) {
            throw new Exception("parameter is null");
        }
        return this.getPassword().equals(password);
    }

}