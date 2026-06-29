package com.foodapp.view;

import com.foodapp.model.User.Role;

import java.util.Scanner;

public class MainView {
    public void show()
    {
        boolean flag = true;
        while(flag) {
            AuthenticationView authenticationView = new AuthenticationView();
            System.out.println("Roles :\n1.User\n2.Restaurant owner\n3.Exit");
            System.out.println("Enter the role : ");
            Scanner scanner = new Scanner(System.in);
            int role = scanner.nextInt();
            switch (role) {
                case 1:
                     authenticationView.authenticationMenu(Role.Customer);
                     break;
                case 2:
                    authenticationView.authenticationMenu(Role.RestaurantOwner);
                    break;
                case 3:
                    flag=false;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Role");
            }
        }
    }
}
