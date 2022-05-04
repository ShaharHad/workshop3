package Domain;
import DataAccess.DatabaseFuctions;

public class Owner extends Member
{
    public Owner(String username, String password, String name)
    {
        super(username, password, name);
    }

    public Status assignManager(String username)
    {
        DatabaseFuctions.update("Manager");
        return Status.Success;
    }

    public Status assignCoach(String username)
    {
        DatabaseFuctions.update("Coach");
        return Status.Success;
    }

    public Status assignPlayer(String username)
    {
        DatabaseFuctions.update("Player");
        return Status.Success;
    }
}
