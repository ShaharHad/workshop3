package Service;

import Domain.MemberD;

import java.util.Scanner;


public class Main
{
    public static void main(String[] args) throws Exception
    {
        String username;
        String password;
        MemberD md;
        Scanner input;
        // to stop the loop insert "stop" in username or password and the loop will stop
        while(true)
        {
            try
            {
                md = MemberD.getInstance();
                input = new Scanner(System.in);
                System.out.println("Insert username");
                username = input.nextLine();
                System.out.println("Insert password");
                password = input.nextLine();
                if(username.equals("stop") || password.equals("stop"))
                {
                    break;
                }
                String msg = md.userLogin(username, password);
                System.out.println(msg);
                System.out.println();
            }
            catch (Exception e)
            {
                System.out.println("user or password not match");
                System.out.println();
            }

        }

    }
}
