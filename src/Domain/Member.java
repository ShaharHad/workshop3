package Domain;

import DataAccess.MemberDA;

import java.util.HashMap;
import java.util.Map;

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

    public String show(boolean success, String username, String role)
    {
        if(success)
        {
            return username + " success to login as " + role;
        }
        return "Username or Password is incorrect";
    }


}
