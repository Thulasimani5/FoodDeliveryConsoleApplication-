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

//    public void updateFoodItem(int food_id) {
//
//    }

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

}