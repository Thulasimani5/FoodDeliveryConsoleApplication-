package com.foodapp.service;

import com.foodapp.model.Order;
import com.foodapp.model.Order.OrderStatus;
import com.foodapp.repository.OrderStore;

import java.util.ArrayList;
import java.util.List;

public class OwnerOrderService {
    OrderStore orderRepository = new OrderStore();

    public List<Order> getPendingOrders(int restaurantId) {

        List<Order> pendingOrder = new ArrayList<>();
        for (Order order : orderRepository.getRestaurantOrders(restaurantId)) {
            if (order.getStatus().equals(OrderStatus.PENDING)) {
                pendingOrder.add(order);
            }
        }
        return pendingOrder;
    }

    public List<Order> getAcceptedOrders(int restaurantId) {
        List<Order> acceptedOrder = new ArrayList<>();
        for (Order order : orderRepository.getRestaurantOrders(restaurantId)) {
            if (order.getStatus().equals(OrderStatus.ACCEPTED)) {
                acceptedOrder.add(order);
            }
        }
        return acceptedOrder;
    }

    public List<Order> getCompletedOrders(int restaurantId) {
        List<Order> completedOrder = new ArrayList<>();
        for (Order order : orderRepository.getRestaurantOrders(restaurantId)) {
            if (order.getStatus().equals(OrderStatus.COMPLETED)) {
                completedOrder.add(order);
            }
        }
        return completedOrder;
    }
}
