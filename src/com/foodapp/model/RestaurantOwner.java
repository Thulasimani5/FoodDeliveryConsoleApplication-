package com.foodapp.model;

public class RestaurantOwner extends User {

    private Restaurant restaurant;

    public RestaurantOwner(int userId,
                           String email,
                           String password) {

        super(userId,  email, password, Role.RestaurantOwner);
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public int getId()
    {
        return  super.userId;
    }
}