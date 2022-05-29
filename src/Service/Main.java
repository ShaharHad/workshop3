package Service;

import Domain.MemberController;

import java.util.Scanner;


public class Main
{
    public static void main(String[] args) throws Exception {
        String username;
        String password;
        MemberController md;
        Scanner input;
        // to stop the loop insert "stop" in username or password and the loop will stop
        try {
            md = MemberController.getInstance();
            input = new Scanner(System.in);
            System.out.println("Insert username");
            username = input.nextLine();
            System.out.println("Insert password");
            password = input.nextLine();
            System.out.println();
        } catch (Exception e) {
            System.out.println("user or password not match");
            System.out.println();
        }
    }
}
