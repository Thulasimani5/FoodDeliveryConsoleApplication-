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
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.println("Enter phone_no :");
        phone_no = sc.nextLong();
        sc.nextLine();
        if (!isValidPhone(phone_no)) {
            System.out.println("Phone number must contain exactly 10 digits.");
            return;
        }
        if (phoneExists(phone_no)) {
            System.out.println("Phone number already registered.");
            return;
        }

        System.out.println("Enter email :");
        email = sc.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return;
        }

        if (emailExists(email)) {
            System.out.println("Email already exists.");
            return;
        }

        System.out.println("Enter password :");
        password = sc.nextLine();
        if (!isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters.");
            return;
        }

        System.out.print("Confirm Password : ");
        String confirmPassword = sc.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }
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
            if (isBlank(buildingNo)
                    || isBlank(street)
                    || isBlank(area)
                    || isBlank(city)
                    || isBlank(state)
                    || isBlank(pinCode)) {

                System.out.println("Address fields cannot be empty.");
                return;
            }
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
            if (isBlank(buildingNo)
                    || isBlank(street)
                    || isBlank(area)
                    || isBlank(city)
                    || isBlank(state)
                    || isBlank(pinCode)) {

                System.out.println("Address fields cannot be empty.");
                return;
            }
            System.out.print("Enter CuisineType: ");
            String type = sc.nextLine().toUpperCase();
            RestaurantType cuisineType;

            try {
                cuisineType = RestaurantType.valueOf(type);
            }
            catch (Exception e) {
                System.out.println("Invalid Cuisine Type.");
                return;
            }

            LocalTime openingTime;
            LocalTime closingTime;

            try {

                System.out.print("Enter Opening Time (HH:mm): ");
                openingTime = LocalTime.parse(sc.nextLine());

                System.out.print("Enter Closing Time (HH:mm): ");
                closingTime = LocalTime.parse(sc.nextLine());

            }
            catch (Exception e) {
                System.out.println("Invalid Time Format.");
                return;
            }

            if (openingTime.isAfter(closingTime)) {
                System.out.println("Opening time cannot be after closing time.");
                return;
            }

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

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidPhone(Long phone) {
        int count=0;
        while(phone!=0)
        {
            phone/=10;
            count++;
        }
        return (count == 10) ;
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean emailExists(String email) {

        for (Customer c : dataStore.getCustomers()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        for (RestaurantOwner o : dataStore.getRestaurantOwners()) {
            if (o.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    private boolean phoneExists(long phoneNo) {

        for (Customer c : dataStore.getCustomers()) {
            if (c.getPhoneNo() == phoneNo) {
                return true;
            }
        }

        for (RestaurantOwner o : dataStore.getRestaurantOwners()) {
            if (o.getPhoneNo() == phoneNo) {
                return true;
            }
        }

        return false;
    }

    }

