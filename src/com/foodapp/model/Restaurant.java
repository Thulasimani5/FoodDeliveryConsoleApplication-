package com.foodapp.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    public enum RestaurantType {
        INDIAN,
        ITALIAN,
        CHINESE,
        MEXICAN,
        AMERICAN,
        JAPANESE,
        FAST_FOOD,
        BAKERY
    }

    private int restaurantId;
    private String restaurantName;
    private Address restaurantAddress;
    private List<Integer> menu;
    private RestaurantType cuisineType;
    private List<Integer> orderId;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private int restaurantOwnerId;

    public Restaurant(int restaurantId,
                      String restaurantName,
                      Address address,
                      RestaurantOwner restaurantOwner) {

        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = address;
        this.restaurantOwnerId = restaurantOwner.getId();

        this.menu = new ArrayList<>();
        this.orderId = new ArrayList<>();
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Address getRestaurantAddress() {
        return restaurantAddress;
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

    public int getRestaurantOwnerId() {
        return restaurantOwnerId;
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
        this.restaurantName = restaurantName;
    }

    public void setRestaurantAddress(Address restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public void setRestaurantOwnerId(int restaurantOwnerId) {
        this.restaurantOwnerId = restaurantOwnerId;
    }
}