package Domain;

import DataAccess.OwnerDA;
import DataAccess.RefereeDA;

import java.util.HashMap;
import java.util.Map;

public class Referee extends Member
{
    String training;
    boolean IsMainReferee = false;

    /**
     * constructor of  Coach class - extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     * @param training $training
     */
    public Referee(String username, String password, String name, String training)
    {
        super(username, password, name, "referee");
        this.training = training;
    }


//    public Status updateInfo(){
//        return Status.Success;
//    }
//    public void viewGameAssignment()
//    {
//    }


    ///////////////////__Only_IF_IsMainReferee_///////////////////////
//    public Status updateEventLog(){
//
//        if (IsMainReferee)
//        {
//
//            return Status.Success;
//        }
//        return  Status.Failure;
//    }
//    public Status setGameSocore()
//    {
//        if (IsMainReferee)
//        {
//
//            return Status.Success;
//        }
//        return  Status.Failure;
//    }

    /**
     * function getTraining
     * @return $training type String
     */
    public String getTraining() {
        return training;
    }
    /**
     * function isMainReferee
     * @return $IsMainReferee type boolean
     */
    public boolean isMainReferee() {
        return IsMainReferee;
    }
    /**
     * function setMainReferee
     * @param mainReferee $mainReferee
     * @update $mainReferee type void
     */
    public void setMainReferee(boolean mainReferee) {
        IsMainReferee = mainReferee;
    }
    /**
     * function get Checks what type of field is the key and returns
     * the relevant field of the Referee accordingly
     * @param key $key
     * @return String key
     */
    public String get(String key)
    {
        if (key == null)
        {
            return null;
        }
        if(key.equals("userName"))
        {
            return this.getUserName();
        }
        else if(key.equals("password"))
        {
            return this.getPassword();
        }
        else if(key.equals("name"))
        {
            return this.getName();
        }
        else if(key.equals("training"))
        {
            return this.getTraining();
        }
        else
        {
            return null;
        }


//        switch(key)
//        {
//            case "userName":
//                return this.getUserName();
//
//            case "password":
//                return this.getPassword();
//
//            case "name":
//                return this.getName();
//
//            case "training":
//                return this.getTraining();
//        }
//        return null;
    }
    /**
     * function getRefFromDB return Referee object from the DB
     * the function find by username the Referee in DB and return object of hem.
     * @param username $username
     * @return Referee
     */
    public static Referee getRefFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        RefereeDA rda = RefereeDA.getInstance();
        return rda.get(map);
    }
    /**
     * function login Checks if the user can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Checks whether the user is registered as a Referee in the system
     * Return true if both conditions are met otherwise return false
     * @param username $username
     * @param password $password
     * @return boolean
     */
    public boolean login(String username, String password){
        if (username == null || password == null) {
            return false;
        }
        RefereeDA rda = RefereeDA.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        Referee ref = rda.get(map);
        if (ref == null) {
            return false;
        } else
        {
            return ref.getPassword().equals(password);
        }
    }

}
