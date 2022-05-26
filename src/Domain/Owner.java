package Domain;

public class Owner extends Member
{
    String teamName;

    public Owner(String username, String password, String name, String teamName)
    {
        super(username, password, name, "owner");
        this.teamName = teamName;
    }

    public String getTeamName() {
        return this.teamName;
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

            case "teamName":
                return this.getTeamName();
        }
        return null;
    }
}


