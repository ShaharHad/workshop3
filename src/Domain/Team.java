package Domain;

import java.util.ArrayList;

public class Team
{
    ArrayList<Player> players;
    ArrayList<Coach> coaches;
    ArrayList<Manager> managers;
    ArrayList<Owner> owners;
    String teamName;

    public Team(Owner owner, String teamName) throws Exception
    {
        if (owner == null) { throw new Exception("owner is null!"); }
        if (teamName == null)  { throw new Exception("teamName is null!"); }
        this.teamName = teamName;
        this.owners = new ArrayList<>();
        this.owners.add(owner);
        this.players = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }
}
