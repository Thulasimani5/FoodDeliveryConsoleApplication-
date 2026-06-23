package Menus;

import Models.FoodItem;
import Models.Order;
import Models.OrderStatus;
import Models.Role;
import Repository.OrderRepository;
import Services.Authentication;
import Services.FoodItemService;
import Services.OwnerOrderService;

import java.util.List;
import java.util.Scanner;

public class RestaurantOwnerMenu {
    Scanner sc = new Scanner(System.in);

    FoodItemService fd_obj = new FoodItemService();
    OwnerOrderService ownnerOrder = new OwnerOrderService();
    OrderRepository orderRepository = new OrderRepository();
    public void restaurantDashboardMenu(int res_id)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.Menu Management\n2. Order Management\n3.Exit");
            System.out.println("Enter the Option Number: ");
            Scanner sc = new Scanner(System.in);
            int auth = sc.nextInt();
            switch (auth) {
                case 1:
                    menu_management(res_id);
                    break;
                case 2:
                    order_management(res_id);
                    break;
                case 3:
                    flag= false;
                    System.out.println("Exited");
                    break;
            }
        }
    }
    public void menu_management(int res_id)
    {
        System.out.println("1. Add Food Item\n2.Update Food Item\n3.Remove Food Item\n4.View Food Items\n5.Exit");
        System.out.println("Enter the Option Number: ");
        int auth = sc.nextInt();
        switch (auth) {
            case 1:
                fd_obj.addFoodItem(res_id);
                break;
            case 2:
//                fd_obj.updateFoodItem(res_id);
                break;
            case 3:
                fd_obj.removeFoodItem(res_id);
                break;
            case 4:
                if(fd_obj.getFoodItems(res_id) == null)
                {
                    System.out.println("No Food items Found");
                }
                else {
                    for (FoodItem item : fd_obj.getFoodItems(res_id)) {
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
                }
                break;
            case 5:
                System.out.println("Exited");
                break;
        }
    }
    public void order_management(int res_id)
    {
        System.out.println("1.View Available Orders\n2. View Accepted Orders\n3.View Completed Orders \n4.Exit");
        System.out.println("Enter the Option Number: ");
        Scanner sc = new Scanner(System.in);
        int auth = sc.nextInt();
        switch (auth) {
            case 1:
                List<Order> pendingOrders = ownnerOrder.getPendingOrders(res_id);
                if (pendingOrders.isEmpty()) {
                    System.out.println("No Pending Orders Found");
                    return;
                }
                System.out.println("\n===== Pending Orders =====");

                for (Order order : pendingOrders) {

                    System.out.println("Order ID      : " + order.getOrderId());
                    System.out.println("Customer ID   : " + order.getCustomerId());
                    System.out.println("Restaurant ID : " + order.getRestaurantId());
                    System.out.println("Food Price    : " + order.getFoodPrice());
                    System.out.println("Delivery Fee  : " + order.getDeliveryFee());
                    System.out.println("Status        : " + order.getStatus());

                    System.out.println("----------------------------");
                }
                processPendingOrders(res_id);
                break;
            case 2:
                List<Order> acceptedOrders = ownnerOrder.getAcceptedOrders(res_id);
                if (acceptedOrders.isEmpty()) {
                    System.out.println("No Pending Orders Found");
                    return;
                }

                System.out.println("\n===== Pending Orders =====");

                for (Order order : acceptedOrders) {

                    System.out.println("Order ID      : " + order.getOrderId());
                    System.out.println("Customer ID   : " + order.getCustomerId());
                    System.out.println("Restaurant ID : " + order.getRestaurantId());
                    System.out.println("Food Price    : " + order.getFoodPrice());
                    System.out.println("Delivery Fee  : " + order.getDeliveryFee());
                    System.out.println("Status        : " + order.getStatus());

                    System.out.println("----------------------------");
                }
                processAcceptedOrders(res_id);
                break;
            case 3:
                List<Order> completedOrders = ownnerOrder.getCompletedOrders(res_id);
                if (completedOrders.isEmpty()) {
                    System.out.println("No Pending Orders Found");
                    return;
                }

                System.out.println("\n===== Pending Orders =====");

                for (Order order : completedOrders) {

                    System.out.println("Order ID      : " + order.getOrderId());
                    System.out.println("Customer ID   : " + order.getCustomerId());
                    System.out.println("Restaurant ID : " + order.getRestaurantId());
                    System.out.println("Food Price    : " + order.getFoodPrice());
                    System.out.println("Delivery Fee  : " + order.getDeliveryFee());
                    System.out.println("Status        : " + order.getStatus());

                    System.out.println("----------------------------");
                }
                break;
            case 4:
                System.out.println("Exited");
                break;
        }
    }
    public void processPendingOrders(int restaurantId) {

        while (true) {
            System.out.println("\nEnter Order ID to process (0 to Exit): ");
            int orderId = sc.nextInt();

            if (orderId == 0) {
                break;
            }

            Order order = orderRepository.findById(orderId);

            if (order == null
                    || order.getRestaurantId() != restaurantId
                    || order.getStatus() != OrderStatus.PENDING) {

                System.out.println("Invalid Order");
                continue;
            }

            System.out.println("\n1. Accept Order");
            System.out.println("2. Reject Order");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    order.setOrderStatus(OrderStatus.ACCEPTED);
                    System.out.println("Order Accepted Successfully");
                    break;

                case 2:
                    order.setOrderStatus(OrderStatus.REJECTED);
                    System.out.println("Order Rejected Successfully");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    public void processAcceptedOrders(int restaurantId) {

        while (true) {
            System.out.println("\nEnter Order ID to process (0 to Exit): ");
            int orderId = sc.nextInt();

            if (orderId == 0) {
                break;
            }

            Order order = orderRepository.findById(orderId);

            if (order == null
                    || order.getRestaurantId() != restaurantId
                    || order.getStatus() != OrderStatus.PENDING) {

                System.out.println("Invalid Order");
                continue;
            }

            System.out.println("\n1. Accept Order");
            System.out.println("2. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    order.setOrderStatus(OrderStatus.COMPLETED);
                    System.out.println("Order Completed Successfully");
                    break;
            }
        }
    }

}
