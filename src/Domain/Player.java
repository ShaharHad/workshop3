package Domain;

import java.util.ArrayList;
import java.util.Date;

public class Player extends Member
{
    Date birthDate;
    String role;

    public Player(String username, Date birthDate, String role, String password, String name) {
        //need toget the password and name from db
        super(username, password, name);
        this.birthDate = birthDate;
        this.role = role;
    }
    public Status updateContent(){
        return Status.Success;
    }
    public Status updateInfo(){
        return Status.Success;
    }

}
