package com.foodapp.service;

import com.foodapp.model.FoodItem;
import com.foodapp.model.Restaurant;
import com.foodapp.repository.FoodStore;
import com.foodapp.repository.RestaurantStore;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {

    private RestaurantStore restaurantRepository = new RestaurantStore();
    private FoodStore foodItemRepository = new FoodStore();

    public List<FoodItem> getRestaurantMenu(int restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return new ArrayList<>();
        }

        List<FoodItem> menu = new ArrayList<>();

        for (Integer foodId : restaurant.getRestaurantMenu()) {

            FoodItem item = foodItemRepository.findById(foodId);

            if (item != null) {
                menu.add(item);
            }
        }

        return menu;
    }
}