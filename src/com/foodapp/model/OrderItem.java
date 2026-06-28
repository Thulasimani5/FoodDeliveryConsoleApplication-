package com.foodapp.model;

public class OrderItem {
    private FoodItem ordered_food;
    private int quantity;
    public OrderItem(FoodItem orderedFood, int quantity) {
        this.ordered_food = orderedFood;
        this.quantity = quantity;
    }
    public FoodItem getOrderedFood() {
        return ordered_food;
    }

    public int getQuantity() {
        return quantity;
    }

}
