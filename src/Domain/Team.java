package Domain;

import java.util.ArrayList;

public class Team
{
    ArrayList<Player> players;
    ArrayList<Coach> coaches;
    ArrayList<Manager> managers;
    ArrayList<Owner> owners;
    String teamName;
    String field;
    /**
     * constructor of  Coach class
     * @param owner $owner
     * @param teamName $teamName
     * @param field $field
     */
    public Team(Owner owner, String teamName, String field) throws Exception
    {
        if (owner == null || teamName == null || field == null)
            throw new Exception("one of the params is null!");
        this.teamName = teamName;
        this.field = field;
        this.owners = new ArrayList<>();
        this.owners.add(owner);
        this.players = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.managers = new ArrayList<>();
    }
    /**
     * function getTeamName
     * @return $teamName type String
     */
    public String getTeamName() {
        return teamName;
    }
}
