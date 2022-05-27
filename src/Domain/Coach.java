package Domain;

public class Coach extends Member
{
    String role;
    String training;
    String userName;



    public Coach(String username, String password, String name, String role, String training)
    {
        super(username, password, name);
        this.role = role;
        this.training = training;
    }
    public Coach(String userName, String training,String role,String teamName)
    {
        this.role = role;
        this.training = training;
    }

    public Status updateContent(){
        return Status.Success;
    }
    public Status updateInfo(){
        return Status.Success;
    }
    public Status Delete(){
        return Status.Success;
    }

}
