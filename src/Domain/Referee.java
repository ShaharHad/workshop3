package Domain;

public class Referee extends Member
{
    String training;
    boolean IsMainReferee=false;
    public Referee(String username, String password, String name, String training) {
        super(username, password, name);
        this.training = training;
    }
    public Status updateInfo(){
        return Status.Success;
    }
    public void viewGameAssognment(){
    }


    ///////////////////__Only_IF_IsMainReferee_///////////////////////
    public Status updateEventLog(){

        if (IsMainReferee)
        {

            return Status.Success;
        }
        return  Status.Failure;
    }
    public Status setGameSocore(){
        if (IsMainReferee)
        {

            return Status.Success;
        }
        return  Status.Failure;
    }
}
