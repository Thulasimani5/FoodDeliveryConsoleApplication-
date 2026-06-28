package com.foodapp.repository;

import com.foodapp.model.FoodItem;

import java.util.List;

public class FoodStore {

    private DataStore dataStore = DataStore.getInstance();

    public void addFoodItem(FoodItem foodItem) {
        dataStore.getFoodItems().add(foodItem);
    }

    public List<FoodItem> getFoodItems()
    {
        return dataStore.getFoodItems();
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

}