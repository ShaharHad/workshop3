package Domain;

public class Representative extends Member
{

    public Representative(String username, String password, String name) {
        super(username, password, name);
    }

    public Status createGame(){
        return Status.Success;
    }
    public Status assignGame(){
        return Status.Success;
    }

    public Status assignRef(){

        return Status.Success;
    }
    public Status setSocerPolicy(){
        return Status.Success;
    }
    public Status addRefereeTOGame(){
        return Status.Success;
    }
}
