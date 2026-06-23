package Services;

import Models.Order;
import Models.OrderStatus;
import Repository.DataStore;
import Repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OwnerOrderService {
    OrderRepository orderRepository = new OrderRepository();

    public List<Order> getPendingOrders(int restaurantId) {

        List<Order> pendingOrder = new ArrayList<>();
        for(Order orderObject : orderRepository.getRestaurantOrders(restaurantId))
        {
            if(orderObject.getStatus().equals(OrderStatus.PENDING ))
            {
                pendingOrder.add(orderObject);
            }
        }
        return pendingOrder;
    }

    public List<Order> getAcceptedOrders(int restaurantId) {
        List<Order> acceptedOrder = new ArrayList<>();
        for(Order orderObject : orderRepository.getRestaurantOrders(restaurantId))
        {
            if(orderObject.getStatus().equals(OrderStatus.ACCEPTED ))
            {
                acceptedOrder.add(orderObject);
            }
        }
        return acceptedOrder;
    }

    public List<Order> getCompletedOrders(int restaurantId) {
        List<Order> completedOrder = new ArrayList<>();
        for(Order orderObject : orderRepository.getRestaurantOrders(restaurantId))
        {
            if(orderObject.getStatus().equals(OrderStatus.COMPLETED ))
            {
                completedOrder.add(orderObject);
            }
        }
        return completedOrder;
    }
}
