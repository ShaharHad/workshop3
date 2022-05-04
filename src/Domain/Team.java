package Domain;

import java.util.ArrayList;

public class Team
{
    ArrayList<Player> players;
    ArrayList<Coach> coaches;
    ArrayList<Manager> managers;
    Owner owner;

    public Team(ArrayList<Player> players, ArrayList<Coach> coaches, ArrayList<Manager> managers, Owner owner) {
        this.players = players;
        this.coaches = coaches;
        this.managers = managers;
        this.owner = owner;
    }
}
