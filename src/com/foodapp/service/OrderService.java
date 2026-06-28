package com.foodapp.service;

import com.foodapp.repository.CustomerStore;
import com.foodapp.repository.FoodStore;
import com.foodapp.repository.OrderStore;
import com.foodapp.repository.RestaurantStore;
import com.foodapp.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    RestaurantStore restaurantRepository = new RestaurantStore();
    FoodStore foodItemRepository= new FoodStore();
    OrderStore orderRepository = new OrderStore();
    CustomerStore customerRepository = new CustomerStore();

    public OrderItem createOrderItem(int foodId, int quantity) {

        FoodItem foodItem = foodItemRepository.findById(foodId);

        if (foodItem == null) {
            return null;
        }

        return new OrderItem(foodItem, quantity);
    }

    public String placeOrder(
            int customerId,
            int restaurantId,
            ArrayList<OrderItem> items) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant == null) {
            return "Restaurant Not Found";
        }

        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            return "Customer Not Found";
        }

        int totalPrice = 0;

        for (OrderItem item : items) {
            totalPrice += item.getOrderedFood().getPrice() * item.getQuantity();
        }

        int orderId = orderRepository.getOrders().size() + 1;

        Order order = new Order(
                orderId,
                customerId,
                restaurantId,
                items
        );

        order.setFoodPrice(totalPrice);
        order.setDeliveryFee(40);
        order.setOrderStatus(OrderStatus.PENDING);

        orderRepository.addOrder(order);

        customer.addOrder(orderId);
        restaurant.addOrder(orderId);

        return "Order Placed Successfully";
    }

    public List<Order> getCurrentOrders(int customerId) {

        List<Order> currentOrders = new ArrayList<>();

        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            return currentOrders;
        }

        for (int orderId : customer.getOrders()) {

            Order order = orderRepository.findById(orderId);

            if (order != null &&
                    (order.getStatus() == OrderStatus.PENDING ||
                            order.getStatus() == OrderStatus.ACCEPTED)) {

                currentOrders.add(order);
            }
        }

        return currentOrders;
    }

    public List<Order> getOrderHistory(int customerId) {

        List<Order> orderHistory = new ArrayList<>();

        Customer customer = customerRepository.findById(customerId);

        if (customer == null) {
            return orderHistory;
        }

        for (int orderId : customer.getOrders()) {

            Order order = orderRepository.findById(orderId);

            if (order != null &&
                    (order.getStatus() == OrderStatus.CANCELLED
                            || order.getStatus() == OrderStatus.COMPLETED
                            || order.getStatus() == OrderStatus.REJECTED)) {

                orderHistory.add(order);
            }
        }

        return orderHistory;
    }

    public String cancelOrder(int orderId) {

        Order order = orderRepository.findById(orderId);

        if (order == null) {
            return "Order Not Found.";
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            return "Only pending orders can be cancelled.";
        }

        order.setOrderStatus(OrderStatus.CANCELLED);

        return "Order Cancelled Successfully.";
    }

    public void displayOrders(Order order)
    {
        System.out.println("Order Id      : " + order.getOrderId());
        System.out.println("Customer ID   : " + order.getCustomerId());
        System.out.println("Restaurant Id : " + order.getRestaurantId());
        System.out.println("Status        : " + order.getOrderStatus());
        System.out.println("Items:");
        for (OrderItem item : order.getOrderedItems()) {
            System.out.println(item.getOrderedFood().getFoodName() + " x " + item.getQuantity() + " = ₹" + item.getOrderedFood().getPrice());
        }
        System.out.println("Food Price    : ₹" + order.getFoodPrice());
        System.out.println("Delivery Fee  : ₹" + order.getDeliveryFee());
        System.out.println("Total Amount  : ₹" + (order.getFoodPrice() + order.getDeliveryFee()));
        System.out.println("-----------------------------------");
    }
}