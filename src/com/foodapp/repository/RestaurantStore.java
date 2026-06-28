package com.foodapp.repository;

import com.foodapp.model.Restaurant;

import java.util.List;

public class RestaurantStore {

    private DataStore dataStore = DataStore.getInstance();

    public List<Restaurant> getRestaurants()
    {
        return dataStore.getRestaurants();
    }

    public void addRestaurant(Restaurant restaurant) {
        dataStore.getRestaurants().add(restaurant);
    }

    public Restaurant findById(int restaurantId) {
        for (Restaurant restaurant : dataStore.getRestaurants()) {
            if (restaurant.getRestaurantId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }

    public List<Restaurant> getAllRestaurants() {
        return dataStore.getRestaurants();
    }

}