package Domain;

public class Coach extends Member
{
    String role;
    String training;

    public Coach(String username, String role, String training, String password, String name) {
        //need to get password ,name from db
        super(username, password, name);
        this.role = role;
        this.training = training;
    }

    public Status updateContent(){
        return Status.Success;
    }
    public Status updateInfo(){
        return Status.Success;
    }
}
