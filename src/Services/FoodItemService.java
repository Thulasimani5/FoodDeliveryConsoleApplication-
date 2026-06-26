package Services;

import Models.FoodItem;
import Models.FoodType;
import Models.BeverageSize;
import Models.Restaurant;
import Models.Starter;
import Models.SpiceLevel;
import  Models.UnitType;
import Models.MainCourse;
import Models.Beverage;
import Models.Dessert;
import Repository.*;

import java.util.List;
import java.util.Scanner;

public class FoodItemService {

    Scanner sc = new Scanner(System.in);

    private DataStore dataStore = DataStore.getInstance();
    private RestaurantRepository restaurantRepository = new RestaurantRepository();
    private FoodItemRepository foodItemRepository = new FoodItemRepository();

    public void addFoodItem(  int restId) {
        Restaurant restaurant = restaurantRepository.findById(restId);
        if (restaurant == null) {
            System.out.println("Restaurant Not Found");
            return;
        }

        int foodId = dataStore.getFoodItems().size() + 1;

        System.out.print("Enter Food Name: ");
        String foodName = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        System.out.print("Enter Price: ");
        int price = sc.nextInt();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Food Type (VEG/NONVEG): ");
        FoodType foodType =
                FoodType.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Enter Quantity Type (PIECE/KG/GRAM/LITER): ");
        UnitType unitType =
                UnitType.valueOf(sc.nextLine().toUpperCase());

        boolean isAvailable = true;

        System.out.println("""
        1. Starter
        2. MainCourse
        3. Beverage
        4. Dessert
        """);

        System.out.print("Choose Category: ");
        int choice = sc.nextInt();
        sc.nextLine();

        FoodItem item = null;

        switch (choice) {

            case 1:

                System.out.print("Enter Spice Level (MILD/MEDIUM/SPICY/EXTRA_SPICY): ");
                SpiceLevel spiceLevel =
                        SpiceLevel.valueOf(sc.nextLine().toUpperCase());

                item = new Starter(
                        foodId,
                        foodName,
                        description,
                        price,
                        foodType,
                        isAvailable,
                        unitType,
                        quantity,
                        restaurant,
                        spiceLevel
                );
                break;

            case 2:

                System.out.print("Enter Number of Persons Served: ");
                int servesPersons = sc.nextInt();

                item = new MainCourse(
                        foodId,
                        foodName,
                        description,
                        price,
                        foodType,
                        isAvailable,
                        unitType,
                        quantity,
                        restaurant,
                        servesPersons
                );
                break;

            case 3:

                System.out.print("Enter Beverage Size (SMALL/MEDIUM/LARGE): ");
                BeverageSize size =
                        BeverageSize.valueOf(sc.nextLine().toUpperCase());

                System.out.print("Sugar Free? (true/false): ");
                boolean sugarFree = sc.nextBoolean();

                System.out.print("Cold Beverage? (true/false): ");
                boolean isCold = sc.nextBoolean();

                item = new Beverage(
                        foodId,
                        foodName,
                        description,
                        price,
                        foodType,
                        isAvailable,
                        unitType,
                        quantity,
                        restaurant,
                        size,
                        sugarFree,
                        isCold
                );
                break;

            case 4:

                System.out.print("Eggless? (true/false): ");
                boolean eggless = sc.nextBoolean();

                System.out.print("Contains Nuts? (true/false): ");
                boolean containsNuts = sc.nextBoolean();

                System.out.print("Served Cold? (true/false): ");
                boolean servedCold = sc.nextBoolean();

                item = new Dessert(
                        foodId,
                        foodName,
                        description,
                        price,
                        foodType,
                        isAvailable,
                        unitType,
                        quantity,
                        restaurant,
                        eggless,
                        containsNuts,
                        servedCold
                );
                break;

            default:
                System.out.println("Invalid Category");
                return;
        }

        restaurant.addFoodItem(item);
        foodItemRepository.addFoodItem(item);

        System.out.println("Food Item Added Successfully");


    }

    public void updateFoodItem(int foodId) {

        FoodItem item = foodItemRepository.findById(foodId);
        if (item == null) {
            System.out.println("Food Item Not Found.");
            return;
        }

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

            System.out.print("Choose Option : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter New Name : ");
                    item.setFoodName(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter New Description : ");
                    item.setDescription(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter New Price : ");
                    item.setPrice(sc.nextInt());
                    sc.nextLine();
                    break;

                case 4:
                    System.out.print("Enter New Quantity : ");
                    item.setQuantity(sc.nextInt());
                    sc.nextLine();
                    break;

                case 5:
                    System.out.print("Enter Food Type (VEG/NONVEG): ");
                    item.setFoodType(FoodType.valueOf(sc.nextLine().toUpperCase()));
                    break;

                case 6:
                    System.out.print("Enter Quantity Type (PIECE/KG/GRAM/LITER): ");
                    item.setQuantityType(UnitType.valueOf(sc.nextLine().toUpperCase()));
                    break;

                case 7:
                    System.out.print("Available (true/false): ");
                    item.setAvailable(sc.nextBoolean());
                    sc.nextLine();
                    break;

                case 8:
                    updateCategoryFields(item);
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
    public void removeFoodItem(int food_id ) {
        for (FoodItem item : dataStore.getFoodItems()) {

            if (item.getFoodItemId() == food_id) {
                item.getRestaurant().getRestaurantMenu().remove(item);
                foodItemRepository.removeFoodItem(item);
                System.out.println("Food Item Removed Successfully.");
                return ;
            }
            else
            {
                System.out.println("Food Item Not Found.");
            }
        }
    }

    public List<FoodItem> getFoodItems(int restaurantId) {
        return restaurantRepository.findById(restaurantId).getRestaurantMenu();
    }

    private void updateCategoryFields(FoodItem item) {

        if (item instanceof Starter starter) {

            System.out.println("""
                1. Spice Level
                2. Exit
                """);

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Spice Level (MILD/MEDIUM/SPICY/EXTRA_SPICY): ");
                starter.setSpiceLevel(SpiceLevel.valueOf(sc.nextLine().toUpperCase()));
            }
        }

        else if (item instanceof MainCourse mainCourse) {

            System.out.println("""
                1. Serves Persons
                2. Exit
                """);

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Number Of Persons Served : ");
                mainCourse.setServesPersons(sc.nextInt());
                sc.nextLine();
            }
        }

        else if (item instanceof Beverage beverage) {

            System.out.println("""
                1. Beverage Size
                2. Sugar Free
                3. Cold Beverage
                4. Exit
                """);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Size (SMALL/MEDIUM/LARGE): ");
                    beverage.setSize(BeverageSize.valueOf(sc.nextLine().toUpperCase()));
                    break;

                case 2:
                    System.out.print("Sugar Free (true/false): ");
                    beverage.setSugarFree(sc.nextBoolean());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.print("Cold Beverage (true/false): ");
                    beverage.setCold(sc.nextBoolean());
                    sc.nextLine();
                    break;
            }
        }

        else if (item instanceof Dessert dessert) {

            System.out.println("""
                1. Eggless
                2. Contains Nuts
                3. Served Cold
                4. Exit
                """);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Eggless (true/false): ");
                    dessert.setEggless(sc.nextBoolean());
                    sc.nextLine();
                    break;

                case 2:
                    System.out.print("Contains Nuts (true/false): ");
                    dessert.setContainsNuts(sc.nextBoolean());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.print("Served Cold (true/false): ");
                    dessert.setServedCold(sc.nextBoolean());
                    sc.nextLine();
                    break;
            }
        }
    }
    public void displayFood(FoodItem item)
    {
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