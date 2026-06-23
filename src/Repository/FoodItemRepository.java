package Repository;

import Models.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodItemRepository {

    private DataStore dataStore = DataStore.getInstance();

    public void addFoodItem(FoodItem foodItem) {
        dataStore.getFoodItems().add(foodItem);
    }

    public void removeFoodItem(FoodItem food) {

        if (food != null) {
            dataStore.getFoodItems().remove(food);
        }
    }

    public FoodItem findById(int foodId) {
        for (FoodItem foodItem : dataStore.getFoodItems()) {
            if (foodItem.getFoodItemId() == foodId) {
                return foodItem;
            }
        }
        return null;
    }

    public List<FoodItem> getByRestaurantId(int restaurantId) {

        List<FoodItem> restaurantFoods = new ArrayList<>();

        for (FoodItem foodItem : dataStore.getFoodItems()) {
            if (foodItem.getRestaurantId() == restaurantId) {
                restaurantFoods.add(foodItem);
            }
        }

        return restaurantFoods;
    }
}