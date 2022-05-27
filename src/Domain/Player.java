package Domain;

import java.util.ArrayList;
import java.util.Date;

public class Player extends Member
{
    Date birthDate;
    String role;

    public Player(String username, String password, String name,String role, Date birthDate) {
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
