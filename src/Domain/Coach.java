package Domain;

import DataAccess.CoachDA;

import java.util.HashMap;
import java.util.Map;

public class Coach extends Member
{
    String roleInTeam;
    String training;
    String teamName;
    /**
     * constructor of  Coach class -extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     * @param roleInTeam $roleInTeam
     * @param training $training
     * @param teamName $teamName
     */
    public Coach(String username, String password, String name, String roleInTeam, String training, String teamName)
    {
        super(username, password, name, "coach");
        this.roleInTeam = roleInTeam;
        this.training = training;
        this.teamName = teamName;
    }
    /**
     * function getRoleInTeam
     * @return String $roleInTeam
     */
    public String getRoleInTeam() {
        return roleInTeam;
    }
    /**
     * function getTraining
     * @return String $training
     */
    public String getTraining() {
        return training;
    }
    /**
     * function getTeamName
     * @return String $teamName
     */
    public String getTeamName() {
        return teamName;
    }

//    public Status updateContent(){
//        return Status.Success;
//    }
//    public Status updateInfo(){
//        return Status.Success;
//    }
    /**
     * function login Checks if the user can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Checks whether the user is registered as a coach in the system
     * Return true if both conditions are met otherwise return false
     * @param username $username
     * @param password $password
     * @return boolean
     */

    public boolean login(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("One of the parameters is null");
        }
        CoachDA cda = CoachDA.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        Member member = cda.get(map);
        if (member == null) {
            throw new Exception("user doesn't exist");
        } else
        {
            return member.getPassword().equals(password);
        }
    }


    /**
     * function getCoachFromDB return choach object from the DB
     * the function find by username the choach in DB and return object of hem.
     * @param username $username
     * @return Coach
     */
    static Coach getCoachFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        CoachDA cda = CoachDA.getInstance();
        return cda.get(map);
    }

}
