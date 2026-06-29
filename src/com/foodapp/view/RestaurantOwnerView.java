package com.foodapp.view;

import com.foodapp.model.*;
import com.foodapp.model.Beverage.BeverageSize;
import com.foodapp.model.FoodItem.FoodType;
import com.foodapp.model.FoodItem.UnitType;
import com.foodapp.model.Order.OrderStatus;
import com.foodapp.model.Starter.SpiceLevel;
import com.foodapp.repository.OrderStore;
import com.foodapp.service.FoodItemService;
import com.foodapp.service.OrderService;
import com.foodapp.service.OwnerOrderService;

import java.util.List;
import java.util.Scanner;

public class RestaurantOwnerView {
    Scanner scanner = new Scanner(System.in);

    private FoodItemService foodItemService = new FoodItemService();
    private OwnerOrderService ownerOrder = new OwnerOrderService();
    private OrderStore orderRepository = new OrderStore();
    private OrderService orderService = new OrderService();

    public void showRestaurantDashboard(int restaurantId) {
        boolean flag = true;
        while (flag) {
            System.out.println("1.Menu Management\n2. Order Management\n3.Exit");
            System.out.println("Enter the Option Number: ");
            int auth = scanner.nextInt();

            switch (auth) {
                case 1:
                    manageMenu(restaurantId);
                    break;
                case 2:
                    manageOrders(restaurantId);
                    break;
                case 3:
                    flag = false;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public void manageMenu(int restaurantId) {

        List<FoodItem> foodItems = foodItemService.getFoodItems(restaurantId);
        boolean flag = true;
        while (flag) {
            System.out.println("1. Add Food Item\n2.Update Food Item\n3.Remove Food Item\n4.View Food Items\n5.Exit");
            System.out.println("Enter the Option Number: ");
            int auth = scanner.nextInt();
            switch (auth) {
                case 1:
                    addFoodItem(restaurantId);
                    break;
                case 2:

                    if (foodItems.isEmpty()) {
                        System.out.println("No Food Items Found.");
                    } else {
                        for (FoodItem item : foodItems) {
                            displayFood(item);
                        }
                    }
                    updateFoodItem(restaurantId);
                    break;
                case 3:

                    if (foodItems.isEmpty()) {
                        System.out.println("No Food Items Found.");
                    } else {
                        for (FoodItem item : foodItems) {
                            displayFood(item);
                        }
                    }
                    removeFoodItem(restaurantId);
                    break;
                case 4:

                    if (foodItems.isEmpty()) {
                        System.out.println("No Food Items Found.");
                    } else {
                        for (FoodItem item : foodItems) {
                            displayFood(item);
                        }
                    }
                    break;
                case 5:
                    flag = false;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public void manageOrders(int restaurantId) {
        boolean flag = true;
        while (flag) {
            System.out.println("1.View Available Orders\n2. View Accepted Orders\n3.View Completed Orders \n4.Exit");
            System.out.println("Enter the Option Number: ");
            int auth = scanner.nextInt();

            switch (auth) {
                case 1:
                    List<Order> pendingOrders = ownerOrder.getPendingOrders(restaurantId);
                    if (pendingOrders.isEmpty()) {
                        System.out.println("No Pending Orders Found");
                        return;
                    }
                    System.out.println("\n===== Pending Orders =====");

                    for (Order order : pendingOrders) {
                        orderService.displayOrders(order);
                    }
                    processPendingOrders(restaurantId);
                    break;
                case 2:
                    List<Order> acceptedOrders = ownerOrder.getAcceptedOrders(restaurantId);
                    if (acceptedOrders.isEmpty()) {
                        System.out.println("No Accepted Orders Found");
                        return;
                    }

                    System.out.println("\n===== Accepted Orders =====");

                    for (Order order : acceptedOrders) {

                        orderService.displayOrders(order);
                    }
                    processAcceptedOrders(restaurantId);
                    break;
                case 3:
                    List<Order> completedOrders = ownerOrder.getCompletedOrders(restaurantId);
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
                    flag = false;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public void addFoodItem(int restaurantId) {

        scanner.nextLine();

        System.out.print("Enter Food Name: ");
        String foodName = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Price: ");
        int price = scanner.nextInt();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Food Type (VEG/NONVEG): ");
        FoodType foodType = FoodType.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter Quantity Type (PIECE/KG/GRAM/LITER): ");
        UnitType unitType = UnitType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("""
                1. Starter
                2. Main Course
                3. Beverage
                4. Dessert
                """);

        System.out.print("Choose Category: ");
        int category = scanner.nextInt();
        scanner.nextLine();

        String result;

        switch (category) {

            case 1:

                System.out.print("Enter Spice Level (MILD, MEDIUM, SPICY, EXTRA_SPICY): ");
                SpiceLevel spiceLevel =
                        SpiceLevel.valueOf(scanner.nextLine().toUpperCase());

                result = foodItemService.addStarter(
                        restaurantId,
                        foodName,
                        description,
                        price,
                        quantity,
                        foodType,
                        unitType,
                        spiceLevel
                );
                break;

            case 2:

                result = foodItemService.addMainCourse(
                        restaurantId,
                        foodName,
                        description,
                        price,
                        quantity,
                        foodType,
                        unitType
                );
                break;

            case 3:
                System.out.print("Enter Beverage Size (SMALL/MEDIUM/LARGE): ");
                BeverageSize size =
                        BeverageSize.valueOf(scanner.nextLine().toUpperCase());

                System.out.print("Sugar Free (true/false): ");
                boolean sugarFree = scanner.nextBoolean();

                System.out.print("Cold Beverage (true/false): ");
                boolean cold = scanner.nextBoolean();

                result = foodItemService.addBeverage(
                        restaurantId,
                        foodName,
                        description,
                        price,
                        quantity,
                        foodType,
                        unitType,
                        size,
                        sugarFree,
                        cold
                );
                break;

            case 4:

                System.out.print("Eggless (true/false): ");
                boolean eggless = scanner.nextBoolean();

                System.out.print("Contains Nuts (true/false): ");
                boolean nuts = scanner.nextBoolean();

                System.out.print("Served Cold (true/false): ");
                boolean servedCold = scanner.nextBoolean();

                result = foodItemService.addDessert(
                        restaurantId,
                        foodName,
                        description,
                        price,
                        quantity,
                        foodType,
                        unitType,
                        eggless,
                        nuts,
                        servedCold
                );
                break;

            default:
                System.out.println("Invalid Category");
                return;
        }

        System.out.println(result);
    }

    public void removeFoodItem(int restaurantId) {

        System.out.print("Enter Food Id: ");
        int foodId = scanner.nextInt();

        String result = foodItemService.removeFoodItem(restaurantId, foodId);

        System.out.println(result);
    }

    public void updateFoodItem(int restaurantId) {

        System.out.print("Enter Food Id: ");
        int foodId = scanner.nextInt();
        scanner.nextLine();

        while (true) {

            System.out.println("""
                    ===== UPDATE MENU =====
                    1. Food Name
                    2. Description
                    3. Price
                    4. Quantity
                    5. Food Type
                    6. Quantity Type
                    7. Availability
                    8. Category Specific Fields
                    9. Exit
                    """);

            System.out.print("Choose Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String result;

            switch (choice) {

                case 1:

                    System.out.print("Enter New Food Name: ");
                    result = foodItemService.updateFoodName(
                            restaurantId,
                            foodId,
                            scanner.nextLine()
                    );
                    break;

                case 2:

                    System.out.print("Enter New Description: ");
                    result = foodItemService.updateDescription(
                            restaurantId,
                            foodId,
                            scanner.nextLine()
                    );
                    break;

                case 3:

                    System.out.print("Enter New Price: ");
                    result = foodItemService.updatePrice(
                            restaurantId,
                            foodId,
                            scanner.nextInt()
                    );
                    scanner.nextLine();
                    break;

                case 4:

                    System.out.print("Enter New Quantity: ");
                    result = foodItemService.updateQuantity(
                            restaurantId,
                            foodId,
                            scanner.nextInt()
                    );
                    scanner.nextLine();
                    break;

                case 5:

                    System.out.print("Enter Food Type (VEG/NONVEG): ");
                    result = foodItemService.updateFoodType(
                            restaurantId,
                            foodId,
                            FoodType.valueOf(scanner.nextLine().toUpperCase())
                    );
                    break;

                case 6:

                    System.out.print("Enter Quantity Type (PIECE/KG/GRAM/LITER): ");
                    result = foodItemService.updateUnitType(
                            restaurantId,
                            foodId,
                            UnitType.valueOf(scanner.nextLine().toUpperCase())
                    );
                    break;

                case 7:

                    System.out.print("Available (true/false): ");
                    result = foodItemService.updateAvailability(
                            restaurantId,
                            foodId,
                            scanner.nextBoolean()
                    );
                    scanner.nextLine();
                    break;

                case 8:

                    System.out.println("""
                            1. Starter
                            2. Beverage
                            3. Dessert
                            """);

                    System.out.print("Choose Category: ");
                    int category = scanner.nextInt();
                    scanner.nextLine();

                    switch (category) {

                        case 1:

                            System.out.print("Enter Spice Level (MILD/MEDIUM/SPICY/EXTRA_SPICY): ");
                            SpiceLevel spiceLevel =
                                    SpiceLevel.valueOf(scanner.nextLine().toUpperCase());

                            result = foodItemService.updateStarter(
                                    restaurantId,
                                    foodId,
                                    spiceLevel
                            );
                            break;

                        case 2:

                            System.out.print("Enter Beverage Size (SMALL/MEDIUM/LARGE): ");
                            BeverageSize size =
                                    BeverageSize.valueOf(scanner.nextLine().toUpperCase());

                            System.out.print("Sugar Free (true/false): ");
                            boolean sugarFree = scanner.nextBoolean();

                            System.out.print("Cold Beverage (true/false): ");
                            boolean cold = scanner.nextBoolean();
                            scanner.nextLine();

                            result = foodItemService.updateBeverage(
                                    restaurantId,
                                    foodId,
                                    size,
                                    sugarFree,
                                    cold
                            );
                            break;

                        case 3:

                            System.out.print("Eggless (true/false): ");
                            boolean eggless = scanner.nextBoolean();

                            System.out.print("Contains Nuts (true/false): ");
                            boolean nuts = scanner.nextBoolean();

                            System.out.print("Served Cold (true/false): ");
                            boolean servedCold = scanner.nextBoolean();
                            scanner.nextLine();

                            result = foodItemService.updateDessert(
                                    restaurantId,
                                    foodId,
                                    eggless,
                                    nuts,
                                    servedCold
                            );
                            break;

                        default:
                            result = "Invalid Category";
                    }

                    break;

                case 9:
                    return;
                default:
                    result = "Invalid Choice";
            }

            System.out.println(result);
        }
    }

    public void processPendingOrders(int restaurantId) {

        while (true) {
            System.out.println("\nEnter Order ID to process (0 to Exit): ");
            int orderId = scanner.nextInt();

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

            int choice = scanner.nextInt();

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
            int orderId = scanner.nextInt();

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

            int choice = scanner.nextInt();

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

    public void displayFood(FoodItem item) {
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