package Domain;

import DataAccess.CoachDA;

import java.util.HashMap;
import java.util.Map;

public class Coach extends Member
{
    String roleInTeam;
    String training;
    String teamName;

    public Coach(String username, String password, String name, String roleInTeam, String training, String teamName)
    {
        super(username, password, name, "coach");
        this.roleInTeam = roleInTeam;
        this.training = training;
        this.teamName = teamName;
    }

    public String getRoleInTeam() {
        return roleInTeam;
    }

    public String getTraining() {
        return training;
    }

    public String getTeamName() {
        return teamName;
    }

//    public Status updateContent(){
//        return Status.Success;
//    }
//    public Status updateInfo(){
//        return Status.Success;
//    }

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



    static Coach getCoachFromDB(String username)
    {
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        CoachDA cda = CoachDA.getInstance();
        return cda.get(map);
    }

}
