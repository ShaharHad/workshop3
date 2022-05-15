package Domain;


import DataAccess.VisitorDA;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import static DataAccess.VisitorDA.*;

public class Visitor extends Member {
    VisitorDA V_IDA=null;
    public Visitor(String username, String password, String name) {
        super(username, password, name);
        VisitorDA V_IDA=new VisitorDA();
    }

    public Status signUp(String username, String password) {
        try {
            V_IDA.save(username ,password);
            return Status.Success;
        }
        catch (Exception e)
        {
            return Status.Failure;
        }
    }

    public Status logIn(String username, String password) {
        if (V_IDA.IsExist(username))
            return Status.Success;
        else
            return Status.Failure;
    }


}