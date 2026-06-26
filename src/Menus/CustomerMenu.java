package Menus;

import Models.Customer;
import Models.FoodItem;
import Models.Order;
import Models.OrderItem;
import Models.OrderStatus;
import Models.Restaurant;
import Repository.*;
import Services.FoodItemService;
import Services.OrderService;
import Services.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    OrderService ord = new OrderService();
    FoodItemService foodItemService = new FoodItemService();
    RestaurantService res = new RestaurantService();
    private RestaurantRepository restaurantRepository = new RestaurantRepository();
    Scanner sc = new Scanner(System.in);

    public void customer_Menu(Customer customer)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.BrowseRestaurant \n2.Orders\n3.Exit");
            System.out.println("Enter the Option : ");
            int auth = sc.nextInt();

            switch (auth) {
                case 1:
                    browseRes_menu(customer);
                    break;
                case 2:
                    order_menu(customer);
                    break;
                case 3:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }


    public void browseRes_menu(Customer customer)
    {
        if(restaurantRepository.getAllRestaurants().isEmpty())
        {
            System.out.println("No restaurant Found");
        }
        else {
            for (Restaurant restaurant : restaurantRepository.getAllRestaurants()) {
                System.out.println("Restaurant Id   : " + restaurant.getRestaurantId());
                System.out.println("Restaurant Name : " + restaurant.getRestaurantName());
                System.out.println("Cuisine Type    : " + restaurant.getCuisineType());
                System.out.println("Opening Time    : " + restaurant.getOpeningTime());
                System.out.println("Closing Time    : " + restaurant.getClosingTime());

                System.out.println("-----------------------------------");
            }
            System.out.println("1.Select Restaurant \n2.Search Restaurant\n3.Exit");
            System.out.println("Enter the Option : ");
            int auth = sc.nextInt();
            int res_id;

            switch (auth) {
                case 1:
                    System.out.println("Enter the Selected Restaurant Id : ");
                    res_id = sc.nextInt();
                    if(res.getRestaurantMenu(res_id).isEmpty())
                    {
                        System.out.println("No Food items Found");
                    }
                    else {
                        for (FoodItem item : res.getRestaurantMenu(res_id)) {
                            foodItemService.displayFood(item);
                        }
                        food_menu(res_id , customer);
                    }
                    break;
                case 2:
                    System.out.println("Enter the Search Restaurant Id : ");
                    res_id = sc.nextInt();
                    if(res.getRestaurantMenu(res_id).isEmpty())
                    {
                        System.out.println("No Food items Found");
                    }
                    else {
                        for (FoodItem item : res.getRestaurantMenu(res_id)) {
                            foodItemService.displayFood(item);
                        }
                        food_menu(res_id , customer);
                    }
                    break;
                case 3:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }

    }

    public void food_menu(int restaurantId, Customer customer)
    {
        System.out.println("1.Select Food \n2.Back to Restaurant");
        System.out.println("Enter the Option : ");
        int auth = sc.nextInt();

        switch (auth) {
            case 1:
                ord.placeOrder(customer, restaurantId);
                break;

            case 2:
                System.out.println("Exited");
                break;
            default:
                System.out.println("Invalid Option");
        }
    }



    public void order_menu(Customer customer)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.Current Order \n2.Completed order\n3.Exit");
            System.out.println("Enter the Option : ");
            int auth = sc.nextInt();
            switch (auth) {
                case 1:
                    current_order(customer);
                    break;
                case 2:
                    List<Order> orderHistory = ord.getOrderHistory(customer);
                    if (orderHistory.isEmpty()) {
                        System.out.println("No Order History  Found.");
                    } else {
                        for (Order order : orderHistory) {
                            ord.displayOrders(order);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public void current_order(Customer customer)
    {
        List<Order> currentOrders = ord.getCurrentOrders(customer);
        if(currentOrders.isEmpty())
        {
            System.out.println("No Current Orders Found.");
        }
        else {
            for (Order order : currentOrders) {
                ord.displayOrders(order);
            }
        }
        System.out.println("1.Cancel Order\n2.Exit");
        System.out.println("Enter the Option : ");
        int auth = sc.nextInt();
        switch (auth) {
            case 1:
                int id;
                System.out.println("Enter the Order Id : ");
                id = sc.nextInt();
                ord.cancelOrder(id);
                break;
            case 2:
                System.out.println("Exited");
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

}
