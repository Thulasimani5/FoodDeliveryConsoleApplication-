package Repository;

import Models.*;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static final DataStore instance = new DataStore();

    private DataStore() {}

    public static DataStore getInstance() {
        return instance;
    }

    private final List<Customer> customers = new ArrayList<>();
    private final List<RestaurantOwner> restaurantOwners = new ArrayList<>();
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<Address> addresses = new ArrayList<>();
    private final List<FoodItem> foodItems = new ArrayList<>();


    public List<Customer> getCustomers() {
        return customers;
    }

    public List<RestaurantOwner> getRestaurantOwners() {
        return restaurantOwners;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
}