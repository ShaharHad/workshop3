package Domain;

abstract class Member
{
    String userName;
    String password;
    String name;

    public Member(String username, String password, String name)
    {
        this.userName = username;
        this.password = password;
        this.name = name;
    }
}
