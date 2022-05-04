package Domain;

public class Referee extends Member
{
    String training;

    public Referee(String username, String password, String name, String training) {
        super(username, password, name);
        this.training = training;
    }
    public Status updateInfo(){
        return Status.Success;
    }
    public void viewGameAssognment(){
    }

    public Status updateEventLog(){
        return Status.Success;
    }
}
