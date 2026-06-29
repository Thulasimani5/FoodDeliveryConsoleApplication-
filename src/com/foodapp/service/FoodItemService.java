package com.foodapp.service;

import com.foodapp.model.FoodItem;
import com.foodapp.model.FoodItem.FoodType;
import com.foodapp.model.FoodItem.UnitType;
import com.foodapp.model.Beverage;
import com.foodapp.model.Beverage.BeverageSize;
import com.foodapp.model.Restaurant;
import com.foodapp.model.Starter;
import com.foodapp.model.Starter.SpiceLevel;
import com.foodapp.model.MainCourse;
import com.foodapp.model.Dessert;
import com.foodapp.repository.FoodStore;
import com.foodapp.repository.RestaurantStore;

import java.util.ArrayList;
import java.util.List;

public class FoodItemService {


    private RestaurantStore restaurantRepository = new RestaurantStore();
    private FoodStore foodItemRepository = new FoodStore();

    public String addStarter(
            int restaurantId,
            String foodName,
            String description,
            int price,
            int quantity,
            FoodType foodType,
            UnitType unitType,
            SpiceLevel spiceLevel) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return "Restaurant Not Found";
        }

        int foodId = foodItemRepository.getFoodItems().size() + 1;

        Starter starter = new Starter(
                foodId,
                foodName,
                price,
                restaurantId,
                spiceLevel
        );

        starter.setDescription(description);
        starter.setFoodType(foodType);
        starter.setAvailable(true);
        starter.setQuantityType(unitType);
        starter.setQuantity(quantity);

        restaurant.addFoodItem(foodId);
        foodItemRepository.addFoodItem(starter);

        return "Starter Added Successfully";
    }

    public String addMainCourse(
            int restaurantId,
            String foodName,
            String description,
            int price,
            int quantity,
            FoodType foodType,
            UnitType unitType) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return "Restaurant Not Found";
        }

        int foodId = foodItemRepository.getFoodItems().size() + 1;

        MainCourse item = new MainCourse(
                foodId,
                foodName,
                price,
                restaurantId
        );

        item.setDescription(description);
        item.setFoodType(foodType);
        item.setAvailable(true);
        item.setQuantityType(unitType);
        item.setQuantity(quantity);

        restaurant.addFoodItem(foodId);
        foodItemRepository.addFoodItem(item);

        return "Main Course Added Successfully";
    }

    public String addBeverage(
            int restaurantId,
            String foodName,
            String description,
            int price,
            int quantity,
            FoodType foodType,
            UnitType unitType,
            BeverageSize size,
            boolean sugarFree,
            boolean cold) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return "Restaurant Not Found";
        }

        int foodId = foodItemRepository.getFoodItems().size() + 1;

        Beverage beverage = new Beverage(
                foodId,
                foodName,
                price,
                restaurantId,
                size
        );

        beverage.setDescription(description);
        beverage.setFoodType(foodType);
        beverage.setAvailable(true);
        beverage.setQuantityType(unitType);
        beverage.setQuantity(quantity);
        beverage.setSugarFree(sugarFree);
        beverage.setCold(cold);

        restaurant.addFoodItem(foodId);
        foodItemRepository.addFoodItem(beverage);

        return "Beverage Added Successfully";
    }

    public String addDessert(
            int restaurantId,
            String foodName,
            String description,
            int price,
            int quantity,
            FoodType foodType,
            UnitType unitType,
            boolean eggless,
            boolean containsNuts,
            boolean servedCold) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return "Restaurant Not Found";
        }

        int foodId = foodItemRepository.getFoodItems().size() + 1;

        Dessert dessert = new Dessert(
                foodId,
                foodName,
                price,
                restaurantId,
                eggless,
                containsNuts,
                servedCold
        );

        dessert.setDescription(description);
        dessert.setFoodType(foodType);
        dessert.setAvailable(true);
        dessert.setQuantityType(unitType);
        dessert.setQuantity(quantity);

        restaurant.addFoodItem(foodId);
        foodItemRepository.addFoodItem(dessert);

        return "Dessert Added Successfully";
    }

    private FoodItem getFoodItem(int restaurantId, int foodId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return null;
        }

        for (Integer id : restaurant.getRestaurantMenu()) {

            if (id == foodId) {
                return foodItemRepository.findById(id);
            }
        }

        return null;
    }

    public String updateFoodName(int restaurantId, int foodId, String name) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        item.setFoodName(name);

        return "Food Name Updated Successfully.";
    }

    public String updateDescription(int restaurantId, int foodId, String description) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        item.setDescription(description);

        return "Description Updated Successfully.";
    }

    public String updatePrice(int restaurantId, int foodId, int price) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        item.setPrice(price);

        return "Price Updated Successfully.";
    }

    public String updateQuantity(int restaurantId, int foodId, int quantity) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        item.setQuantity(quantity);

        return "Quantity Updated Successfully.";
    }

    public String updateFoodType(int restaurantId,
                                 int foodId,
                                 FoodType foodType) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        item.setFoodType(foodType);

        return "Food Type Updated Successfully.";
    }

    public String updateUnitType(int restaurantId,
                                 int foodId,
                                 UnitType unitType) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        item.setQuantityType(unitType);

        return "Quantity Type Updated Successfully.";
    }

    public String updateAvailability(int restaurantId,
                                     int foodId,
                                     boolean available) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        item.setAvailable(available);

        return "Availability Updated Successfully.";
    }

    public String updateStarter(int restaurantId,
                                int foodId,
                                SpiceLevel spiceLevel) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        if (!(item instanceof Starter))
            return "Selected item is not a Starter.";

        Starter starter = (Starter) item;

        starter.setSpiceLevel(spiceLevel);

        return "Starter Updated Successfully.";
    }

    public String updateBeverage(int restaurantId,
                                 int foodId,
                                 BeverageSize size,
                                 boolean sugarFree,
                                 boolean cold) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        if (!(item instanceof Beverage))
            return "Selected item is not a Beverage.";

        Beverage beverage = (Beverage) item;

        beverage.setSize(size);
        beverage.setSugarFree(sugarFree);
        beverage.setCold(cold);

        return "Beverage Updated Successfully.";
    }

    public String updateDessert(int restaurantId,
                                int foodId,
                                boolean eggless,
                                boolean containsNuts,
                                boolean servedCold) {

        FoodItem item = getFoodItem(restaurantId, foodId);

        if (item == null)
            return "Food Item Not Found.";

        if (!(item instanceof Dessert))
            return "Selected item is not a Dessert.";

        Dessert dessert = (Dessert) item;

        dessert.setEggless(eggless);
        dessert.setContainsNuts(containsNuts);
        dessert.setServedCold(servedCold);

        return "Dessert Updated Successfully.";
    }

    public String removeFoodItem(int restaurantId, int foodId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return "Restaurant Not Found.";
        }

        if (!restaurant.getRestaurantMenu().contains(foodId)) {
            return "Food Item Not Found.";
        }

        FoodItem item = foodItemRepository.findById(foodId);

        if (item == null) {
            return "Food Item Not Found.";
        }

        restaurant.getRestaurantMenu().remove(Integer.valueOf(foodId));
        foodItemRepository.removeFoodItem(item);

        return "Food Item Removed Successfully.";
    }

    public List<FoodItem> getFoodItems(int restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return new ArrayList<>();
        }

        List<FoodItem> foodItems = new ArrayList<>();

        for (Integer foodId : restaurant.getRestaurantMenu()) {

            FoodItem item = foodItemRepository.findById(foodId);

            if (item != null) {
                foodItems.add(item);
            }
        }

        return foodItems;
    }

}