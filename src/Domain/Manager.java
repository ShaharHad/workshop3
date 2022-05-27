package Domain;

public class Manager extends Member
{
    private String teamName;

    public Manager(String username, String password, String name,String role,String teamName) {
        super(username, password, name, role);
        this.teamName = teamName;
    }



}
