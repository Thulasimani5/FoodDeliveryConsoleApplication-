package com.foodapp.model;

public class OrderItem {
    private int orderId;
    private FoodItem ordered_food;
    private int quantity;
    public OrderItem(int orderId , FoodItem orderedFood, int quantity) {
        this.orderId = orderId;
        this.ordered_food = orderedFood;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }
    public FoodItem getOrderedFood() {
        return ordered_food;
    }

    public int getQuantity() {
        return quantity;
    }

}
