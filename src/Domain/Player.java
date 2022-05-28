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

    public Player(String username, String password, String name, Date birthDate, String roleInTeam, String teamName) {
        super(username, password, name, "player");
        this.birthDate = birthDate;
        this.roleInTeam = roleInTeam;
        this.teamName = teamName;
    }

    public String getTeamName(){return this.teamName;}
    public String getRoleInTeam(){return this.roleInTeam;}

    public Status updateContent(){
        return Status.Success;
    }
    public Status updateInfo(){
        return Status.Success;
    }

    static Player getPlayerFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        PlayerDA pda = PlayerDA.getInstance();
        return pda.get(map);
    }

    public boolean login(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("One of the parameters is null");
        }
        PlayerDA pda = PlayerDA.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        Player p = pda.get(map);
        if (p == null) {
            throw new Exception("user not exist");
        } else
        {
            return p.getPassword().equals(password);
        }
    }

}
