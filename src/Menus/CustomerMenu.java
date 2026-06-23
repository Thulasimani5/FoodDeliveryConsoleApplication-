package Menus;

import Models.Customer;
import Models.FoodItem;
import Models.Order;
import Models.OrderItem;
import Models.OrderStatus;
import Models.Restaurant;
import Repository.*;
import Services.Authentication;
import Services.OrderService;
import Services.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    OrderService ord = new OrderService();
    RestaurantService res = new RestaurantService();

    private DataStore dataStore = DataStore.getInstance();
    private RestaurantRepository restaurantRepository = new RestaurantRepository();
    private OrderRepository orderRepository = new OrderRepository();

    public void customer_Menu(Customer customer)
    {
        System.out.println("1.BrowseRestaurant \n2.Orders\n3.Exit");
        System.out.println("Enter the Option : ");
        Scanner sc = new Scanner(System.in);
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
        }
    }


    public void browseRes_menu(Customer customer)
    {
        if(res.getAllRestaurants() == null)
        {
            System.out.println("No restaurant Found");
        }
        else {
            for (Restaurant restaurant : res.getAllRestaurants()) {
                System.out.println("Restaurant Id   : " + restaurant.getRestaurantId());
                System.out.println("Restaurant Name : " + restaurant.getRestaurantName());
                System.out.println("Cuisine Type    : " + restaurant.getCuisineType());
                System.out.println("Opening Time    : " + restaurant.getOpeningTime());
                System.out.println("Closing Time    : " + restaurant.getClosingTime());

                System.out.println("-----------------------------------");
            }
            System.out.println("1.Select Restaurant \n2.Search Restaurant\n3.Exit");
            System.out.println("Enter the Option : ");
            Scanner sc = new Scanner(System.in);
            int auth = sc.nextInt();
            int res_id;
            switch (auth) {
                case 1:
                    System.out.println("Enter the Selected Restaurant Id : ");
                    res_id = sc.nextInt();
                    if(res.getRestaurantMenu(res_id) == null)
                    {
                        System.out.println("No Food items Found");
                    }
                    else {
                        for (FoodItem item : res.getRestaurantMenu(res_id)) {
                            System.out.println("Food Id      : " + item.getFoodItemId());
                            System.out.println("Food Name    : " + item.getFoodName());
                            System.out.println("Description  : " + item.getDescription());
                            System.out.println("Price        : " + item.getPrice());
                            System.out.println("Food Type    : " + item.getFoodType());
                            System.out.println("Available    : " + item.isAvailable());
                            System.out.println("Quantity     : " + item.getQuantity());
                            System.out.println("Unit Type    : " + item.getQuantityType());
                            System.out.println("-----------------------------------");
                        }
                        food_menu(res_id , customer);
                    }
                    break;
                case 2:
                    System.out.println("Enter the Search Restaurant Id : ");
                    res_id = sc.nextInt();
                    if(res.getRestaurantMenu(res_id) == null)
                    {
                        System.out.println("No Food items Found");
                    }
                    else {
                        for (FoodItem item : res.getRestaurantMenu(res_id)) {
                            System.out.println("Food Id      : " + item.getFoodItemId());
                            System.out.println("Food Name    : " + item.getFoodName());
                            System.out.println("Description  : " + item.getDescription());
                            System.out.println("Price        : " + item.getPrice());
                            System.out.println("Food Type    : " + item.getFoodType());
                            System.out.println("Available    : " + item.isAvailable());
                            System.out.println("Quantity     : " + item.getQuantity());
                            System.out.println("Unit Type    : " + item.getQuantityType());
                            System.out.println("-----------------------------------");
                        }
                        food_menu(res_id , customer);
                    }
                    break;
                case 3:
                    System.out.println("Exited");
                    break;
        }

        }

    }



    public void food_menu(int restaurantId, Customer customer)
    {
        Restaurant restaurant = restaurantRepository.findById(restaurantId);
        int totalFoodPrice=0;

        System.out.println("1.Select Food \n2.Back to Restaurant");
        System.out.println("Enter the Option : ");
        Scanner sc = new Scanner(System.in);
        int auth = sc.nextInt();

        switch (auth) {
            case 1:
                int quantity = 0;
                FoodItem selectedFood = null;
                List<OrderItem> items  = new ArrayList<>();
                boolean flag=true;
                while (flag) {
                    System.out.println("Enter the selected Food id :");
                    int foodId = sc.nextInt();
                    System.out.println("Enter the selected Food Quantity :");
                    quantity = sc.nextInt();

                    for (FoodItem item : dataStore.getFoodItems()) {
                        if (item.getFoodItemId() == foodId) {
                            selectedFood = item;
                            break;
                        }
                    }
                    if (selectedFood == null) {
                        System.out.println("Food Item Not Found");
                        return;
                    }

                    int foodPrice = selectedFood.getPrice() * quantity;
                    totalFoodPrice += foodPrice;

                    OrderItem orderItem = new OrderItem(selectedFood, quantity,foodPrice);
                    items.add(orderItem);
                    System.out.println("Continue Buying:\n1.Yes\n2.No");
                    int opt = sc.nextInt();
                    if(opt==2)
                    {
                        flag = false;
                        System.out.println("Confirm Order:\n1.Yes\n2.No");
                        opt = sc.nextInt();
                        if(opt==2)
                        {
                            return;
                        }
                    }
                }
                Order order = new Order(
                        dataStore.getOrders().size() + 1,
                        customer.getId(),
                        restaurantId,
                        totalFoodPrice,
                        40,
                        items,
                        OrderStatus.PENDING
                );

                orderRepository.addOrder(order);
                customer.addOrder(order);
                restaurant.addOrder(order);

                System.out.println("Order Placed Successfully");
                break;
            case 2:
                System.out.println("Exited");
                break;
        }
    }



    public void order_menu(Customer customer)
    {
        System.out.println("1.Current Order \n2.Completed order\n3.Exit");
        System.out.println("Enter the Option : ");
        Scanner sc = new Scanner(System.in);
        int auth = sc.nextInt();
        switch (auth) {
            case 1:
                current_order(customer);
                break;
            case 2:
                List<Order> orderHistory = ord.getOrderHistory(customer);
                if(orderHistory.isEmpty())
                {
                    System.out.println("No Order History  Found.");
                }
                else {
                    for (Order order : orderHistory) {
                        System.out.println("Order Id      : " + order.getOrderId());
                        System.out.println("Restaurant Id : " + order.getRestaurantId());
                        System.out.println("Status        : " + order.getOrderStatus());
                        System.out.println("Items:");
                        for (OrderItem item : order.getOrderedItems()) {
                            System.out.println(item.getOrderedFood().getFoodName() + " x " + item.getQuantity() + " = ₹" + item.getFoodPrice());
                        }
                        System.out.println("Food Price    : ₹" + order.getFoodPrice());
                        System.out.println("Delivery Fee  : ₹" + order.getDeliveryFee());
                        System.out.println("Total Amount  : ₹" + (order.getFoodPrice() + order.getDeliveryFee()));
                        System.out.println("-----------------------------------");
                    }
                }
                break;
            case 3:
                System.out.println("Exited");
                break;
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
                System.out.println("Order Id      : " + order.getOrderId());
                System.out.println("Restaurant Id : " + order.getRestaurantId());
                System.out.println("Status        : " + order.getOrderStatus());
                System.out.println("Items:");
                for (OrderItem item : order.getOrderedItems()) {
                    System.out.println(item.getOrderedFood().getFoodName() + " x " + item.getQuantity() + " = ₹" + item.getFoodPrice());
                }
                System.out.println("Food Price    : ₹" + order.getFoodPrice());
                System.out.println("Delivery Fee  : ₹" + order.getDeliveryFee());
                System.out.println("Total Amount  : ₹" + (order.getFoodPrice() + order.getDeliveryFee()));
                System.out.println("-----------------------------------");
            }
        }
        System.out.println("1.Cancel Order\n2.Exit");
        System.out.println("Enter the Option : ");
        Scanner sc = new Scanner(System.in);
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
        }
    }
}
