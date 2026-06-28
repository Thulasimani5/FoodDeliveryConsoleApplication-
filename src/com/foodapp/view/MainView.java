package com.foodapp.view;

import com.foodapp.model.Role;

import java.util.Scanner;

public class MainView {
    public void show()
    {
        boolean flag = true;
        while(flag) {
            AuthenticationView authenticationView = new AuthenticationView();
            System.out.println("Roles :\n1.User\n2.Restaurant owner\n3.Exit");
            System.out.println("Enter the role : ");
            Scanner sc = new Scanner(System.in);
            int role = sc.nextInt();
            switch (role) {
                case 1:
                     authenticationView.authenticationMenu(Role.Customer);
                case 2:
                    authenticationView.authenticationMenu(Role.RestaurantOwner);
                case 3:
                    flag=false;
                    System.out.println("Exited");
                default:
                    System.out.println("Invalid Role");
            }
        }
    }
}
