package Service;

import Domain.MemberD;

import java.util.Scanner;


public class Main
{
    public static void main(String[] args) throws Exception
    {
        String username;
        String password;
        MemberD md = MemberD.getInstance();
        Scanner input = new Scanner(System.in);
        System.out.println("Insert username");
        username = input.nextLine();
        System.out.println("Insert password");
        password = input.nextLine();
        String msg = md.userLogin(username, password);
        System.out.println(msg);

    }
}
