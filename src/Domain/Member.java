package Domain;

public abstract class Member
{
    private String userName;
    private String password;
    private String name;
    private String role;

    public Member(String username, String password, String name, String role)
    {
        this.userName = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Member(String username, String password, String name)
    {
        this.userName = username;
        this.password = password;
        this.name = name;
        this.role = "fan";
    }

    protected Member() {
    }

    public String getUserName()
    {
        return this.userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }


}
