package Menus;

import Models.FoodItem;
import Models.Order;
import Models.OrderStatus;
import Models.Role;
import Repository.OrderRepository;
import Services.Authentication;
import Services.FoodItemService;
import Services.OrderService;
import Services.OwnerOrderService;

import java.util.List;
import java.util.Scanner;

public class RestaurantOwnerMenu {
    Scanner sc = new Scanner(System.in);

    private FoodItemService fd_obj = new FoodItemService();
    private OwnerOrderService ownerOrder = new OwnerOrderService();
    private OrderRepository orderRepository = new OrderRepository();
    private OrderService orderService = new OrderService();

    public void restaurantDashboardMenu(int res_id)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.Menu Management\n2. Order Management\n3.Exit");
            System.out.println("Enter the Option Number: ");
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
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
    public void menu_management(int res_id)
    {
        boolean flag = true;
        while(flag) {
            System.out.println("1. Add Food Item\n2.Update Food Item\n3.Remove Food Item\n4.View Food Items\n5.Exit");
            System.out.println("Enter the Option Number: ");
            int auth = sc.nextInt();
            switch (auth) {
                case 1:
                    fd_obj.addFoodItem(res_id);
                    break;
                case 2:
                    fd_obj.updateFoodItem(res_id);
                    break;
                case 3:
                    fd_obj.removeFoodItem(res_id);
                    break;
                case 4:
                    if (fd_obj.getFoodItems(res_id) == null) {
                        System.out.println("No Food items Found");
                    } else {
                        for (FoodItem item : fd_obj.getFoodItems(res_id)) {
                            fd_obj.displayFood(item);
                        }
                    }
                    break;
                case 5:
                    flag=false;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
    public void order_management(int res_id)
    {
        boolean flag=true;
        while(flag) {
            System.out.println("1.View Available Orders\n2. View Accepted Orders\n3.View Completed Orders \n4.Exit");
            System.out.println("Enter the Option Number: ");
            int auth = sc.nextInt();

            switch (auth) {
                case 1:
                    List<Order> pendingOrders = ownerOrder.getPendingOrders(res_id);
                    if (pendingOrders.isEmpty()) {
                        System.out.println("No Pending Orders Found");
                        return;
                    }
                    System.out.println("\n===== Pending Orders =====");

                    for (Order order : pendingOrders) {
                        orderService.displayOrders(order);
                    }
                    processPendingOrders(res_id);
                    break;
                case 2:
                    List<Order> acceptedOrders = ownerOrder.getAcceptedOrders(res_id);
                    if (acceptedOrders.isEmpty()) {
                        System.out.println("No Accepted Orders Found");
                        return;
                    }

                    System.out.println("\n===== Accepted Orders =====");

                    for (Order order : acceptedOrders) {

                        orderService.displayOrders(order);
                    }
                    processAcceptedOrders(res_id);
                    break;
                case 3:
                    List<Order> completedOrders = ownerOrder.getCompletedOrders(res_id);
                    if (completedOrders.isEmpty()) {
                        System.out.println("No Completed Orders Found");
                        return;
                    }

                    System.out.println("\n===== Completed Orders =====");

                    for (Order order : completedOrders) {

                        orderService.displayOrders(order);
                    }
                    break;
                case 4:
                    flag=false;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
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
                    || order.getStatus() != OrderStatus.ACCEPTED) {

                System.out.println("Invalid Order");
                continue;
            }

            System.out.println("\n1. Complete Order");
            System.out.println("2. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    order.setOrderStatus(OrderStatus.COMPLETED);
                    System.out.println("Order Completed Successfully");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
}
