package Service;

import Domain.MemberD;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String username = "test1";
        String password = "123456";
        MemberD md = MemberD.getInstance();
        String t = md.userLogin(username, password);
        System.out.println(t);
    }
}
