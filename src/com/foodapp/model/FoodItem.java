package com.foodapp.model;

public abstract class FoodItem {

    protected int foodId;
    protected String foodName;
    protected String description;
    protected int price;
    protected FoodType foodType;
    protected boolean isAvailable;
    protected UnitType quantityType;
    protected int quantity;
    protected int restaurantId;

    public FoodItem(int foodId, String foodName, int price, int restaurantId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public int getFoodItemId() {
        return foodId;
    }

    public void setFoodItemId(int foodId) {
        this.foodId = foodId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public UnitType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(UnitType quantityType) {
        this.quantityType = quantityType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}