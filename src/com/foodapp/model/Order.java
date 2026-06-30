package com.foodapp.model;

import java.util.List;

public class Order {

    public enum OrderStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        COMPLETED,
        CANCELLED
    }

    private int orderId;
    private String customerEmail;
    private int foodPrice;
    private int deliveryFee;
    private List<Integer> orderedItems;
    private OrderStatus orderStatus;
    private int restaurantId;

    public Order(int orderId,
                 String customerEmail,
                 int restaurantId,
                 List<Integer> orderedItems) {

        this.orderId = orderId;
        this.customerEmail = customerEmail;
        this.restaurantId = restaurantId;
        this.orderedItems = orderedItems;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public List<Integer> getOrderedItems() {
        return orderedItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
