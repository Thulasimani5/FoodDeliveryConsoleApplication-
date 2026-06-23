package Menus;

import Models.Role;
import Services.Authentication;

import java.util.Scanner;

public class AuthenticationMenu {

    Authentication obj = new Authentication();

    public void signupMenu()
    {
        boolean flag = true;
        while(flag) {
            System.out.println("1.SignIn\n2.SignUp\n3.Exit");
            System.out.println("Enter the Authentication Option : ");
            Scanner sc = new Scanner(System.in);
            int auth = sc.nextInt();
            switch (auth) {
                case 2:
                    obj.signUp();
                    break;
                case 1:
                    obj.login();
                    break;
                case 3:
                    System.out.println("Exited");
                    flag=false;
                    break;
            }
        }
    }
}
