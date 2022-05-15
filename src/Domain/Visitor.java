package Domain;

import java.sql.Connection;
import java.sql.DriverManager;

public class Visitor
{
    public Status signUp(String user_name){
        if (user_name==null)
            return null;
        String url="";
        Connection conn;
        try{
            conn = DriverManager.getConnection(url, "root", "root");
        }
        catch ()
        return Status.Success;
    }
    public Status logIn(){
        return Status.Success;
    }

}

