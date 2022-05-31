package Domain;

import DataAccess.OwnerDA;
import DataAccess.RefereeDA;

import java.util.HashMap;
import java.util.Map;

public class Referee extends Member
{
    String training;
    boolean IsMainReferee = false;


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


    public String getTraining() {
        return training;
    }

    public boolean isMainReferee() {
        return IsMainReferee;
    }

    public void setMainReferee(boolean mainReferee) {
        IsMainReferee = mainReferee;
    }

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

    public static Referee getRefFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        RefereeDA rda = RefereeDA.getInstance();
        return rda.get(map);
    }

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
