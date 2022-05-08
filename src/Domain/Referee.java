package Domain;

public class Referee extends Member
{
    String training;
    boolean IsMainReferee=false;
    boolean Is_free=false;

    public void setMainReferee(boolean mainReferee) {
        IsMainReferee = mainReferee;
    }

    public void setIs_free(boolean is_free) {
        Is_free = is_free;
    }

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
