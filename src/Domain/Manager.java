package Domain;

import DataAccess.ManagerDA;
import DataAccess.OwnerDA;

import java.util.HashMap;
import java.util.Map;

public class Manager extends Member
{
    String teamName;
    /**
     * constructor of  Manager class - extends Member
     * @param username $username
     * @param password $password
     * @param name $name
     * @param teamName $teamName
     */
    public Manager(String username, String password, String name, String teamName) {
        super(username, password, name, "manager");
        this.teamName = teamName;
    }
    /**
     * function getTeamName
     * @return teamName of Manager type String
     */
    public String getTeamName() {
        return teamName;
    }
    /**
     * function getManagerFromDB return Manager object from the DB
     * the function find by username the Manager in DB and return object of hem.
     * @param username $username
     * @return Manager
     */
    static Manager getManagerFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        ManagerDA mda = ManagerDA.getInstance();
        return mda.get(map);
    }

    /**
     * function login Checks if the Manager can make a connection it's mean:
     * 1. Check the correctness of username and password
     * 2. Checks whether the user is registered as a Manager in the system
     * Return true if both conditions are met otherwise return false
     * @param username $username
     * @param password $password
     * @return boolean
     */
    public boolean login(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("One of the parameters is null");
        }
        ManagerDA mda = ManagerDA.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        Manager manager = mda.get(map);
        if (manager == null) {
            throw new Exception("user doesn't exist");
        } else
        {
            return manager.getPassword().equals(password);
        }
    }

}
