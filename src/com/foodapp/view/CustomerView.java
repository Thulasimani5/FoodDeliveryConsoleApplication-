package com.foodapp.view;

import com.foodapp.model.*;
import com.foodapp.repository.FoodStore;
import com.foodapp.repository.RestaurantStore;
import com.foodapp.service.FoodItemService;
import com.foodapp.service.OrderService;
import com.foodapp.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerView {
    FoodItemService foodItemService = new FoodItemService();
    FoodStore foodItemRepository = new FoodStore();
    RestaurantService res = new RestaurantService();
    OrderService orderService = new OrderService();
    private RestaurantStore restaurantRepository = new RestaurantStore();
    Scanner sc = new Scanner(System.in);

    public void customer_Menu(int customerId)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.BrowseRestaurant \n2.Orders\n3.Exit");
            System.out.println("Enter the Option : ");
            int auth = sc.nextInt();

            switch (auth) {
                case 1:
                    browseRes_menu(customerId);
                    break;
                case 2:
                    order_menu(customerId);
                    break;
                case 3:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }


    public void browseRes_menu(int customerId)
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
                            displayFood(item);
                        }
                        food_menu(res_id , customerId);
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
                            displayFood(item);
                        }
                        food_menu(res_id , customerId);
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

    public void food_menu(int restaurantId, int customerId)
    {
        System.out.println("1.Select Food \n2.Back to Restaurant");
        System.out.println("Enter the Option : ");
        int auth = sc.nextInt();

        switch (auth) {
            case 1:
                placeOrder(customerId, restaurantId);
                break;

            case 2:
                System.out.println("Exited");
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    public void placeOrder(int customerId, int restaurantId) {

        ArrayList<OrderItem> items = new ArrayList<>();

        boolean flag = true;

        while (flag) {

            System.out.print("Enter Food Id : ");
            int foodId = sc.nextInt();

            FoodItem foodItem = foodItemRepository.findById(foodId);

            if (foodItem == null) {
                System.out.println("Food Item Not Found");
                return;
            }

            System.out.print("Enter Quantity : ");
            int quantity = sc.nextInt();
            OrderItem item = orderService.createOrderItem(foodId,quantity);
            items.add(item);

            System.out.println("Continue Buying?");
            System.out.println("1.Yes");
            System.out.println("2.No");

            int option = sc.nextInt();

            if (option == 2) {

                System.out.println("Confirm Order");
                System.out.println("1.Yes");
                System.out.println("2.No");

                option = sc.nextInt();

                if (option == 1) {
                    flag = false;
                } else {
                    return;
                }
            }
        }

        String result = orderService.placeOrder(
                customerId,
                restaurantId,
                items
        );

        System.out.println(result);
    }

    public void order_menu(int customerId)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.Current Order \n2.Completed order\n3.Exit");
            System.out.println("Enter the Option : ");
            int auth = sc.nextInt();
            switch (auth) {
                case 1:
                    current_order(customerId);
                    break;
                case 2:
                    List<Order> orderHistory = orderService.getOrderHistory(customerId);
                    if (orderHistory.isEmpty()) {
                        System.out.println("No Order History  Found.");
                    } else {
                        for (Order order : orderHistory) {
                            orderService.displayOrders(order);
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

    public void current_order(int customerId)
    {
        List<Order> currentOrders = orderService.getCurrentOrders(customerId);
        if(currentOrders.isEmpty())
        {
            System.out.println("No Current Orders Found.");
        }
        else {
            for (Order order : currentOrders) {
                orderService.displayOrders(order);
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
                orderService.cancelOrder(id);
                break;
            case 2:
                System.out.println("Exited");
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    public void displayFood(FoodItem item)
    {
        System.out.println("Food Id      : " + item.getFoodItemId());
        System.out.println("Food Name    : " + item.getFoodName());
        System.out.println("Description  : " + item.getDescription());
        System.out.println("Price        : " + item.getPrice());
        System.out.println("Food Type    : " + item.getFoodType());
        System.out.println("Available    : " + item.getIsAvailable());
        System.out.println("Quantity     : " + item.getQuantity());
        System.out.println("Unit Type    : " + item.getQuantityType());
        System.out.println("-----------------------------------");
    }
}
