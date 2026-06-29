package com.foodapp.repository;

import com.foodapp.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

 class DataStore {

    private static final DataStore instance = new DataStore();

    private DataStore() {}

    public static DataStore getInstance() {
        return instance;
    }

    private final Map<String, Customer> customers = new HashMap<>();
    private final Map<String, RestaurantOwner> restaurantOwners = new HashMap<>();
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final Map<Integer, Order> orders = new HashMap<>();
    private final List<FoodItem> foodItems = new ArrayList<>();


    public Map<String, Customer> getCustomers() {
        return customers;
    }

    public Map<String, RestaurantOwner> getRestaurantOwners() {
        return restaurantOwners;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
}