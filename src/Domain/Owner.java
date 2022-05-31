package Domain;

import DataAccess.OwnerDA;

import java.util.HashMap;
import java.util.Map;

public class Owner extends Member
{
    String teamName;
    /**
     * constructor of Owner class -extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     * @param teamName $teamName
     */
    public Owner(String username, String password, String name, String teamName)
    {
        super(username, password, name, "owner");
        this.teamName = teamName;
    }
    /**
     * function getTeamName
     * @return String $this.teamName
     */
    public String getTeamName() {
        return this.teamName;
    }
    /**
     * function get Checks what type of field is the key and returns
     * the relevant field of the owner accordingly
     * @param key $key
     * @return String key
     */
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

    }
    /**
     * function login Checks if the user can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Checks whether the user is registered as a owner in the system
     * Return true if both conditions are met otherwise return false
     * @param username $username
     * @param password $password
     * @return boolean
     */
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
            throw new Exception("user doesn't exist");
        } else
        {
            return member.getPassword().equals(password);
        }
    }

    /**
     * function getOwnerFromDB return Owner object from the DB
     * the function find by username the Owner in DB and return object of hem.
     * @param username $username
     * @return Owner
     */
    static Owner getOwnerFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        OwnerDA oda = OwnerDA.getInstance();
        return oda.get(map);
    }

}


