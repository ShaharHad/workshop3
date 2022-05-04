package Domain;
import DataAccess.DatabaseFuctions;

import java.sql.Connection;

public class Owner extends Member
{
    public Owner(String username, String password, String name)
    {
        super(username, password, name);
    }

    public Status assignManager(String username)
    {
        Connection conn = DatabaseFuctions.getConnector();
        return Status.Success;
    }
}
