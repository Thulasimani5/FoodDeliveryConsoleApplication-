package com.foodapp.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private int restaurant_id;
    private String restaurant_name;
    private Address restaurant_address;
    private List<Integer> menu;
    private RestaurantType cuisineType;
    private List<Integer> orderId;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private RestaurantOwner restaurantOwner;

    public Restaurant(int restaurantId,
                      String restaurantName,
                      Address address,
                      RestaurantOwner restaurantOwner) {

        this.restaurant_id = restaurantId;
        this.restaurant_name = restaurantName;
        this.restaurant_address = address;
        this.restaurantOwner = restaurantOwner;

        this.menu = new ArrayList<>();
        this.orderId = new ArrayList<>();
    }

    public int getRestaurantId() {
        return restaurant_id;
    }

    public String getRestaurantName() {
        return restaurant_name;
    }

    public Address getRestaurantAddress() {
        return restaurant_address;
    }

    public RestaurantType getCuisineType() {
        return cuisineType;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public RestaurantOwner getRestaurantOwner() {
        return restaurantOwner;
    }

    public List<Integer> getRestaurantMenu() {
        return menu;
    }

    public List<Integer> getOrderIds() {
        return orderId;
    }

    public void addFoodItem(int item) {
        menu.add(item);
    }

    public void addOrder(int orderId) {
        this.orderId.add(orderId);
    }

    public void setCuisineType(RestaurantType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurant_name = restaurantName;
    }

    public void setRestaurantAddress(Address restaurantAddress) {
        this.restaurant_address = restaurantAddress;
    }

    public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }
}