package Services;

import Menus.CustomerMenu;
import Menus.MainMenu;
import Menus.RestaurantOwnerMenu;
import Models.*;
import Repository.CustomerRepository;
import Repository.DataStore;
import Repository.RestaurantOwnerRepository;
import Repository.RestaurantRepository;

import java.time.LocalTime;
import java.util.Scanner;

public class Authentication {

    private DataStore dataStore = DataStore.getInstance();
    private CustomerRepository customerRepository = new CustomerRepository();
    private RestaurantOwnerRepository ownerRepository = new RestaurantOwnerRepository();
    private RestaurantRepository restaurantRepository = new RestaurantRepository();

    Scanner sc = new Scanner(System.in);

    CustomerMenu cusmen_obj = new CustomerMenu();
    RestaurantOwnerMenu remen_obj = new RestaurantOwnerMenu();
    MainMenu mainmenu_obj = new MainMenu();


    public void signUp() {
        String name,email,password;
        int id;
        long phone_no;

        System.out.println("Enter name :");
        name =sc.nextLine();

        System.out.println("Enter phone_no :");
        phone_no = sc.nextLong();
        sc.nextLine();

        System.out.println("Enter email :");
        email = sc.nextLine();

        System.out.println("Enter password :");
        password = sc.nextLine();

        Role role = mainmenu_obj.show();

        if(role.equals(Role.Customer)) {
            id = dataStore.getCustomers().size() + 1;

            System.out.print("Enter Building No: ");
            String buildingNo = sc.nextLine();

            System.out.print("Enter Street: ");
            String street = sc.nextLine();

            System.out.print("Enter Area: ");
            String area = sc.nextLine();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter State: ");
            String state = sc.nextLine();

            int add_id = dataStore.getAddresses().size() + 1;

            System.out.print("Enter Pin Code: ");
            String pinCode = sc.nextLine();

            Address address = new Address(
                    add_id,
                    buildingNo,
                    street,
                    area,
                    city,
                    state,
                    pinCode
            );

            Customer c1 = new Customer(
                    id,
                    name,
                    email,
                    phone_no,
                    password,
                    address
            );

            customerRepository.addCustomer(c1);
            System.out.println(" Customer SignUp Successful");
            cusmen_obj.customer_Menu(c1);
        }
        else
        {
            id = dataStore.getRestaurantOwners().size()+1;
            int restaurant_id = dataStore.getRestaurants().size()+1;

            System.out.print("Enter Restaurant Name: ");
            String restaurant_name = sc.nextLine();

            System.out.print("Enter Restaurant Building No: ");
            String buildingNo = sc.nextLine();

            System.out.print("Enter Restaurant Street: ");
            String street = sc.nextLine();

            System.out.print("Enter Restaurant Area: ");
            String area = sc.nextLine();

            System.out.print("Enter Restaurant City: ");
            String city = sc.nextLine();

            System.out.print("Enter Restaurant State: ");
            String state = sc.nextLine();

            int add_id = dataStore.getAddresses().size() + 1;

            System.out.print("Enter Pin Code: ");
            String pinCode = sc.nextLine();

            System.out.print("Enter CuisineType: ");
            String type = sc.nextLine().toUpperCase();
            RestaurantType cuisineType = RestaurantType.valueOf(type);

            System.out.print("Enter Opening time (HH:mm): ");
            String t1 = sc.nextLine();
            LocalTime openingTime = LocalTime.parse(t1);

            System.out.print("Enter time (HH:mm): ");
            String t2 = sc.nextLine();
            LocalTime closingTime = LocalTime.parse(t2);

            Address address = new Address(
                    add_id,
                    buildingNo,
                    street,
                    area,
                    city,
                    state,
                    pinCode
            );
            RestaurantOwner owner = new RestaurantOwner(
                    id,
                    name,
                    email,
                    phone_no,
                    password
            );
            Restaurant restaurantObj = new Restaurant(
                    restaurant_id,
                    restaurant_name,
                    address,
                    cuisineType,
                    openingTime,
                    closingTime,
                    owner
            );

            restaurantRepository.addRestaurant(restaurantObj);
            ownerRepository.addOwner(owner);
            owner.setRestaurant(restaurantObj);
            System.out.println("Restaurant Owner Signup Successful");
            remen_obj.restaurantDashboardMenu(restaurant_id);
        }
    };



    public void login() {
        String email, password;

        Role role = mainmenu_obj.show();

        System.out.print("Enter Email: ");
        email = sc.nextLine();

        System.out.print("Enter Password: ");
        password = sc.nextLine();
        int flag=0;
        if(role.equals(Role.Customer)) {
            for (Customer c : dataStore.getCustomers()) {
                if (c.getEmail().equals(email)
                        && c.getPassword().equals(password))
                {
                    System.out.println("Login Successful");
                    flag = 1;
                    cusmen_obj.customer_Menu(c);
                    break;
                }
                else if(c.getEmail().equals(email)
                        && ! c.getPassword().equals(password)){
                    System.out.println("Wrong Password");
                    System.out.println("Login Failed");
                    break;
                }
            }
            if(flag == 0)
            {
                System.out.println("No Email found");
                System.out.println("Login Failed");
            }
        }
        else if (role.equals(Role.RestaurantOwner))
        {
            for (RestaurantOwner c : dataStore.getRestaurantOwners()) {
                if (c.getEmail().equals(email)
                        && c.getPassword().equals(password)) {
                    System.out.println("Login Successful");
                    remen_obj.restaurantDashboardMenu(c.getRestaurant().getRestaurantId());
                } else {
                    System.out.println("Login Failed");
                }
            }
        }
    }

    }

