package Domain;
import DataAccess.OwnerDA;
import jdk.jfr.Event;

import java.sql.SQLException;

public class Owner extends Member
{
    OwnerDA ODA = OwnerDA.getInstance();
    public Owner(String username, String password, String name)
    {
        super(username, password, name);
    }

    public Status assignManager(String username)
    {
        if (username == null)
        {
            return null;
        }
        try
        {
            ODA.save(username);
            ODA.update(username);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return Status.Success;
    }

    public Status assignPlayer(String username)
    {
        if (username == null)
        {
            return null;
        }
        try
        {
            ODA.update(username);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return Status.Success;
    }

    public Status assignCoach(String username)
    {
        if (username == null)
        {
            return null;
        }
        try
        {
            ODA.update(username);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return Status.Success;
    }

    public Status assignOwner(String username)
    {
        if (username == null)
        {
            return null;
        }
        ODA.update(username);
        return Status.Success;
    }




    public Status removeManager(String username)
    {
        if (username == null)
        {
            return null;
        }
        ODA.update(username);
        return Status.Success;
    }

    public Status removePlayer(String username)
    {
        if (username == null)
        {
            return null;
        }
        ODA.update(username);
        return Status.Success;
    }

    public Status removeCoach(String username)
    {
        if (username == null)
        {
            return null;
        }
        ODA.update(username);
        return Status.Success;
    }

    public Status removeOwner(String username)
    {
        if (username == null)
        {
            return null;
        }
        ODA.update(username);
        return Status.Success;
    }

}
