package Domain;

public abstract class Member
{
    private String userName;
    private String password;
    private String name;
    private String role;
    /**
     * constructor of  Member - abstract class
     * @param username $username
     * @param password $password
     * @param name $name
     * @param role $role
     */
    public Member(String username, String password, String name, String role)
    {
        this.userName = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }
    /**
     * constructor of  Member - abstract class
     * @param username $username
     * @param password $password
     * @param name $name
     */
    public Member(String username, String password, String name)
    {
        this.userName = username;
        this.password = password;
        this.name = name;
        this.role = "fan";
    }
    /**
     * function getUserName
     * @return this.userName type String
     */
    public String getUserName()
    {
        return this.userName;
    }
    /**
     * function getPassword
     * @return password type String
     */
    public String getPassword() {
        return password;
    }
    /**
     * function getName
     * @return name type String
     */
    public String getName() {
        return name;
    }
    /**
     * function getRole
     * @return role type String
     */
    public String getRole() {
        return role;
    }
    /**
     * function show print a massage to Member whether he was able to connect as required or not
     * @param username $username
     * @param success $success
     * @param role $role
     * @return role type String
     */
    public String show(String username, boolean success,  String role)
    {
        if(success)
        {
            return username + " success to login as " + role;
        }
        return "Username or Password is incorrect";
    }


}
