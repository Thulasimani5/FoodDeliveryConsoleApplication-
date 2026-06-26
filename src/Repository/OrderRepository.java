package Repository;

import Models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private DataStore dataStore = DataStore.getInstance();

    public void addOrder(Order order) {
        dataStore.getOrders().add(order);
    }

    public List<Order> getCustomerOrders(int customerId) {

        List<Order> customerOrders = new ArrayList<>();

        for (Order order : dataStore.getOrders()) {
            if (order.getCustomerId() == customerId) {
                customerOrders.add(order);
            }
        }

        return customerOrders;
    }

    public List<Order> getRestaurantOrders(int restaurantId) {

        List<Order> restaurantOrders = new ArrayList<>();

        for (Order order : dataStore.getOrders()) {
            if (order.getRestaurantId() == restaurantId) {
                restaurantOrders.add(order);
            }
        }
        return restaurantOrders;
    }
    public Order findById(int orderId) {

        for (Order order : dataStore.getOrders()) {

            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }
}