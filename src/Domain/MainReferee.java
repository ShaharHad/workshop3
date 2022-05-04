package Domain;

public class MainReferee extends Referee
{

    public MainReferee(String username, String password, String name, String training) {
        super(username, password, name, training);
    }

    public Status updateEventLog(){
        return Status.Success;
    }
    public Status setGameSocore(){
        return Status.Success;
    }

}
