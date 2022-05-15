package Domain;

public class Manager extends Member
{
    public Manager(String username) {
        //need to get the password and name from db
        super(username, password, name);
    }
}
