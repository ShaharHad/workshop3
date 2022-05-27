package Service;

import Domain.MemberD;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // test1 - fan
        String username = "test1";
        String password = "test1";
        MemberD md = MemberD.getInstance();
        String t = md.userLogin(username, password);
        System.out.println(t);
        System.out.println();


        // test2 - coach
        username = "test2";
        password = "test2";
        t = md.userLogin(username, password);
        System.out.println(t);
        System.out.println();


        // test3 - player
        username = "test3";
        password = "test3";
        t = md.userLogin(username, password);
        System.out.println(t);
        System.out.println();



        // test4 - manager
        username = "test4";
        password = "test4";
        t = md.userLogin(username, password);
        System.out.println(t);
        System.out.println();



        // test5 - owner
        username = "test5";
        password = "test5";
        t = md.userLogin(username, password);
        System.out.println(t);
        System.out.println();


        // test6 - representative
        username = "test6";
        password = "test6";
        t = md.userLogin(username, password);
        System.out.println(t);
        System.out.println();


        // test7 - referee
        username = "test7";
        password = "test7";
        t = md.userLogin(username, password);
        System.out.println(t);
        System.out.println();

    }
}
