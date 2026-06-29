package com.foodapp.view;

import com.foodapp.model.Restaurant.RestaurantType;
import com.foodapp.model.User.Role;
import com.foodapp.service.AuthenticationService;

import java.time.LocalTime;
import java.util.Scanner;

public class AuthenticationView {

    private AuthenticationService authenticationService = new AuthenticationService();
    private Scanner scanner = new Scanner(System.in);
    CustomerView customerView = new CustomerView();
    RestaurantOwnerView restaurantOwnerView = new RestaurantOwnerView();

    public void authenticationMenu(Role role) {

            System.out.println("\n========== Authentication ==========");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter Choice : ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    login(role);
                    break;

                case 2:
                    signUp(role);
                    break;

                case 3:
                    System.out.println("Exited Successfully.");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

    }

    public void signUp(Role role) {

        System.out.println("\n========== SIGN UP ==========");

        System.out.print("Enter Name : ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Phone Number : ");
        String phone = scanner.nextLine();
        if (!authenticationService.isValidPhone(phone)) {
            System.out.println("Phone number must contain exactly 10 digits.");
            return;}
        if (authenticationService.phoneExists(phone)) {
            System.out.println("Phone number already registered.");
            return;}

        System.out.print("Enter Email : ");
        String email = scanner.nextLine();
        if (!authenticationService.isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;}
        if (authenticationService.emailExists(email)) {
            System.out.println("Email already exists.");
            return;}

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();
        if (!authenticationService.isValidPassword(password)) {
            System.out.println("Password must contain:");
            System.out.println("- At least 8 characters");
            System.out.println("- One uppercase letter");
            System.out.println("- One lowercase letter");
            System.out.println("- One digit");
            System.out.println("- One special character");
            return;
        }
        System.out.print("Confirm Password : ");
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }
        String hashedPassword = authenticationService.hashPassword(password);

        if (role == Role.Customer) {

            System.out.print("Building No : ");
            String building = scanner.nextLine();

            System.out.print("Street : ");
            String street = scanner.nextLine();

            System.out.print("Area : ");
            String area = scanner.nextLine();

            System.out.print("City : ");
            String city = scanner.nextLine();

            System.out.print("State : ");
            String state = scanner.nextLine();

            System.out.print("Pin Code : ");
            String pinCode = scanner.nextLine();

            if (authenticationService.isBlank(building)
                    || authenticationService.isBlank(street)
                    || authenticationService.isBlank(area)
                    || authenticationService.isBlank(city)
                    || authenticationService.isBlank(state)
                    || authenticationService.isBlank(pinCode)) {

                System.out.println("Address fields cannot be empty.");
                return;
            }

             authenticationService.customerSignup(
                    name,
                    phone,
                    email,
                    hashedPassword,
                    building,
                    street,
                    area,
                    city,
                    state,
                    pinCode
            );
            customerView.showCustomerMenu(email);

        } else {

            System.out.print("Restaurant Name : ");
            String restaurantName = scanner.nextLine();

            System.out.print("Building No : ");
            String building = scanner.nextLine();

            System.out.print("Street : ");
            String street = scanner.nextLine();

            System.out.print("Area : ");
            String area = scanner.nextLine();

            System.out.print("City : ");
            String city = scanner.nextLine();

            System.out.print("State : ");
            String state = scanner.nextLine();

            System.out.print("Pin Code : ");
            String pinCode = scanner.nextLine();

            if (authenticationService.isBlank(building)
                    || authenticationService.isBlank(street)
                    || authenticationService.isBlank(area)
                    || authenticationService.isBlank(city)
                    || authenticationService.isBlank(state)
                    || authenticationService.isBlank(pinCode)) {

                System.out.println("Address fields cannot be empty.");
                return;
            }

            System.out.print("Cuisine Type (INDIAN/ITALIAN/CHINESE/MEXICAN/AMERICAN/JAPANESE/FAST_FOOD/BAKERY): ");
            String cuisine = scanner.nextLine();
            RestaurantType cuisineType;

            try {
                cuisineType = RestaurantType.valueOf(cuisine);
            }
            catch (Exception e) {
                System.out.println("Invalid Cuisine Type.");
                return;
            }

            LocalTime openingTime;
            LocalTime closingTime;

            try {

                System.out.print("Enter Opening Time (HH:mm): ");
                openingTime = LocalTime.parse(scanner.nextLine());

                System.out.print("Enter Closing Time (HH:mm): ");
                closingTime = LocalTime.parse(scanner.nextLine());

            }
            catch (Exception e) {
                System.out.println("Invalid Time Format.");
                return;
            }

            if (openingTime.isAfter(closingTime)) {
                System.out.println("Opening time cannot be after closing time.");
                return;
            }

            int restaurantId = authenticationService.ownerSignup(
                    name,
                    phone,
                    email,
                    hashedPassword,
                    restaurantName,
                    building,
                    street,
                    area,
                    city,
                    state,
                    pinCode,
                    cuisineType,
                    openingTime,
                    closingTime
            );
            restaurantOwnerView.showRestaurantDashboard(restaurantId);
        }
    }

    public void login(Role role) {

        System.out.println("\n========== LOGIN ==========");

        System.out.print("Enter Email : ");
        String email = scanner.nextLine();

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();

        password = authenticationService.hashPassword(password);

        if (role == Role.Customer) {

            int status = authenticationService.customerLogin(email, password);

            if (status == 1) {
                System.out.println("Login Successful");
                customerView.showCustomerMenu(email);
            } else if (status == -1) {
                System.out.println("Wrong Password");
            } else {
                System.out.println("Email Not Found");
            }

        }
        else {

            int restaurantId = authenticationService.ownerLogin(email, password);

            if (restaurantId > 0) {
                System.out.println("Login Successful");
                restaurantOwnerView.showRestaurantDashboard(restaurantId);
            } else if (restaurantId == -1) {
                System.out.println("Wrong Password");
            } else {
                System.out.println("Email Not Found");
            }
        }
    }
}