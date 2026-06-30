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
    Scanner scanner = new Scanner(System.in);

    public void showCustomerMenu(String email)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.BrowseRestaurant \n2.Orders\n3.Exit");
            System.out.println("Enter the Option : ");
            int auth = scanner.nextInt();

            switch (auth) {
                case 1:
                    browseRestaurantMenu(email);
                    break;
                case 2:
                    showOrderMenu(email);
                    break;
                case 3:
                    System.out.println("Exited");
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }


    public void browseRestaurantMenu(String email)
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
            int auth = scanner.nextInt();
            int restaurantId;

            switch (auth) {
                case 1:
                    System.out.println("Enter the Selected Restaurant Id : ");
                    restaurantId = scanner.nextInt();
                    if(res.getRestaurantMenu(restaurantId).isEmpty())
                    {
                        System.out.println("No Food items Found");
                    }
                    else {
                        for (FoodItem item : res.getRestaurantMenu(restaurantId)) {
                            displayFood(item);
                        }
                        showFoodMenu(restaurantId , email);
                    }
                    break;
                case 2:
                    System.out.println("Enter the Search Restaurant Id : ");
                    restaurantId = scanner.nextInt();
                    if(res.getRestaurantMenu(restaurantId).isEmpty())
                    {
                        System.out.println("No Food items Found");
                    }
                    else {
                        for (FoodItem item : res.getRestaurantMenu(restaurantId)) {
                            displayFood(item);
                        }
                        showFoodMenu(restaurantId , email);
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

    public void showFoodMenu(int restaurantId, String email)
    {
        System.out.println("1.Select Food \n2.Back to Restaurant");
        System.out.println("Enter the Option : ");
        int auth = scanner.nextInt();

        switch (auth) {
            case 1:
                placeOrder(email, restaurantId);
                break;

            case 2:
                System.out.println("Exited");
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    public void placeOrder(String email, int restaurantId) {

        ArrayList<Integer> items = new ArrayList<>();

        boolean flag = true;

        while (flag) {

            System.out.print("Enter Food Id : ");
            int foodId = scanner.nextInt();

            FoodItem foodItem = foodItemRepository.findById(foodId);

            if (foodItem == null) {
                System.out.println("Food Item Not Found");
                return;
            }

            System.out.print("Enter Quantity : ");
            int quantity = scanner.nextInt();
            int item = orderService.createOrderItem(foodId,quantity);
            if(item ==0 )
            {
                System.out.println("Food item not Found");
            }
            items.add(item);

            System.out.println("Continue Buying?");
            System.out.println("1.Yes");
            System.out.println("2.No");

            int option = scanner.nextInt();

            if (option == 2) {

                System.out.println("Confirm Order");
                System.out.println("1.Yes");
                System.out.println("2.No");

                option = scanner.nextInt();

                if (option == 1) {
                    flag = false;
                } else {
                    return;
                }
            }
        }

        String result = orderService.placeOrder(
                email,
                restaurantId,
                items
        );

        System.out.println(result);
    }

    public void showOrderMenu(String email)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.Current Order \n2.Completed order\n3.Exit");
            System.out.println("Enter the Option : ");
            int auth = scanner.nextInt();
            switch (auth) {
                case 1:
                    showCurrentOrder(email);
                    break;
                case 2:
                    List<Order> orderHistory = orderService.getOrderHistory(email);
                    if (orderHistory.isEmpty()) {
                        System.out.println("No Order History  Found.");
                    } else {
                        for (Order order : orderHistory) {
                            orderService.displayOrders(order);
                        }
                    }
                    break;
                case 3:
                    flag= false;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public void showCurrentOrder(String email)
    {
        List<Order> currentOrders = orderService.getCurrentOrders(email);
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
        int auth = scanner.nextInt();
        switch (auth) {
            case 1:
                int id;
                System.out.println("Enter the Order Id : ");
                id = scanner.nextInt();
                orderService.cancelOrder(id);
                System.out.println("Order Cancelled Successfully");
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
