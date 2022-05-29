package Domain;

import DataAccess.OwnerDA;

import java.util.HashMap;
import java.util.Map;

public class Owner extends Member
{
    String teamName;

    public Owner(String username, String password, String name, String teamName)
    {
        super(username, password, name, "owner");
        this.teamName = teamName;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String get(String key)
    {
        if (key == null)
            return null;
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
        else if(key.equals("teamName"))
        {
            return this.getTeamName();
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
//            case "teamName":
//                return this.getTeamName();
//            default:
//                return null;
//        }
    }

    public boolean login(String username, String password) throws Exception
    {
        if (username == null || password == null) {
            throw new Exception("One of the parameters is null");
        }
        OwnerDA oda = OwnerDA.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        Member member = oda.get(map);
        if (member == null) {
            throw new Exception("user not exist");
        } else
        {
            return member.getPassword().equals(password);
        }
    }


    static Owner getOwnerFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        OwnerDA oda = OwnerDA.getInstance();
        return oda.get(map);
    }

}


