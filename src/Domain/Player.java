package Domain;

import DataAccess.OwnerDA;
import DataAccess.PlayerDA;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Player extends Member
{
    Date birthDate;
    String roleInTeam;
    String teamName;
    /**
     * constructor of  Coach class -extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     * @param birthDate $birthDate
     * @param roleInTeam $roleInTeam
     * @param teamName $teamName
     */
    public Player(String username, String password, String name, Date birthDate, String roleInTeam, String teamName) {
        super(username, password, name, "player");
        this.birthDate = birthDate;
        this.roleInTeam = roleInTeam;
        this.teamName = teamName;
    }
    /**
     * function getTeamName
     * @return String this.teamName
     */
    public String getTeamName(){return this.teamName;}
    public String getRoleInTeam(){return this.roleInTeam;}

//    public Status updateContent(){
//        return Status.Success;
//    }
//    public Status updateInfo(){
//        return Status.Success;
//    }




    /**
     * function getPlayerFromDB return Player object from the DB
     * the function find by username the Player in DB and return object of hem.
     * @param username $username
     * @return Player
     */
    static Player getPlayerFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        PlayerDA pda = PlayerDA.getInstance();
        return pda.get(map);
    }
    /**
     * function login Checks if the user can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Checks whether the user is registered as a player in the system
     * Return true if both conditions are met otherwise return false
     * @param username $username
     * @param password $password
     * @return boolean
     */
    public boolean login(String username, String password) throws Exception{
        if (username == null || password == null) {
            throw new Exception("One of the parameters is null");
        }
        PlayerDA pda = PlayerDA.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        Player p = pda.get(map);
        if (p == null) {
            throw new Exception("user doesn't exist");
        }
        return p.getPassword().equals(password);
    }

}
