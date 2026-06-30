package com.foodapp.repository;

import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderStore {

    private DataStore dataStore = DataStore.getInstance();

    public void addOrder(Order order) {
        dataStore.getOrders().put(order.getOrderId(), order);
    }

    public List<Order> getOrders()
    {
        return new ArrayList<>(dataStore.getOrders().values());
    }

    public List<Order> getRestaurantOrders(int restaurantId) {

        List<Order> restaurantOrders = new ArrayList<>();

        for (Order order : dataStore.getOrders().values()) {
            if (order.getRestaurantId() == restaurantId) {
                restaurantOrders.add(order);
            }
        }
        return restaurantOrders;
    }

    public Order findById(int orderId) {
        return dataStore.getOrders().get(orderId);
    }

    public void addOrderItem(OrderItem orderItem) {
        dataStore.getOrderItems().add(orderItem);
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(dataStore.getOrderItems());
    }

   public OrderItem getOrderItemById(int id)
   {
       for(OrderItem orderItem : getOrderItems())
       {
           if(orderItem.getOrderId() == id)
           {
               return orderItem;
           }
       }
       return  null;
   }
}
