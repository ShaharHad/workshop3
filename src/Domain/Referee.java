package Domain;

public class Referee extends Member
{
    String training;
    boolean IsMainReferee = false;


    public Referee(String username, String password, String name, String training)
    {
        super(username, password, name, "referee");
        this.training = training;
    }

    public Status updateInfo(){
        return Status.Success;
    }
    public void viewGameAssignment()
    {
    }


    ///////////////////__Only_IF_IsMainReferee_///////////////////////
    public Status updateEventLog(){

        if (IsMainReferee)
        {

            return Status.Success;
        }
        return  Status.Failure;
    }
    public Status setGameSocore()
    {
        if (IsMainReferee)
        {

            return Status.Success;
        }
        return  Status.Failure;
    }


    public String getTraining() {
        return training;
    }

    public boolean isMainReferee() {
        return IsMainReferee;
    }

    public void setMainReferee(boolean mainReferee) {
        IsMainReferee = mainReferee;
    }

    public String get(String key)
    {
        switch(key)
        {
            case "userName":
                return this.getUserName();

            case "password":
                return this.getPassword();

            case "name":
                return this.getName();

            case "training":
                return this.getTraining();
        }
        return null;
    }
}
