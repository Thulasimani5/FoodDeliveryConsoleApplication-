package Menus;

import Models.Role;
import Services.Authentication;

import java.util.Scanner;

public class MainMenu {
    public Role show()
    {
        System.out.println("Roles :\n1.User\n2.Restaurant owner\n3.Exit");
        System.out.println("Enter the role : ");
        Scanner sc = new Scanner(System.in);
        int role = sc.nextInt();
        switch (role) {
            case 1:
                return Role.Customer;
            case 2:
                return Role.RestaurantOwner;
            default:
                System.out.println("Invalid Role");
                return null;
        }
    }
}
