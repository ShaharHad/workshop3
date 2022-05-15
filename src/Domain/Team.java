package Domain;

import java.util.ArrayList;

public class Team
{
    ArrayList<Player> players;
    ArrayList<Coach> coaches;
    ArrayList<Manager> managers;
    ArrayList<Owner> owners;

    public Team(Owner owner) throws Exception
    {
//        this.players = new ArrayList<>();
//        this.coaches = new ArrayList<>();
//        this.managers = new ArrayList<>();
        if (owner == null) { throw new Exception("owner is null!"); }
        this.owners.add(owner);
    }
}
