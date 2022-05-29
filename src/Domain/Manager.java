package Domain;

import DataAccess.ManagerDA;
import DataAccess.OwnerDA;

import java.util.HashMap;
import java.util.Map;

public class Manager extends Member
{
    String teamName;
    public Manager(String username, String password, String name, String teamName) {
        super(username, password, name, "manager");
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    static Manager getManagerFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        ManagerDA mda = ManagerDA.getInstance();
        return mda.get(map);
    }

    public boolean login(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("One of the parameters is null");
        }
        ManagerDA mda = ManagerDA.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        Manager manager = mda.get(map);
        if (manager == null) {
            throw new Exception("user not exist");
        } else
        {
            return manager.getPassword().equals(password);
        }
    }

}
