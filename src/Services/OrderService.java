package Services;

import Models.Customer;
import Models.OrderItem;
import Models.Order;
import Models.OrderStatus;
import Repository.DataStore;
import Repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private DataStore dataStore = DataStore.getInstance();

    public void placeOrder(List<OrderItem> order_items) {

    }

    public List<Order> getCurrentOrders( Customer customer) {
        List<Order> currentOrder = new ArrayList<>();
        for(Order ord : customer.getOrders())
        {
            if(ord.getStatus().equals(OrderStatus.PENDING ) || ord.getStatus().equals(OrderStatus.ACCEPTED ) )
            {
                currentOrder.add(ord);
            }
        }
        return currentOrder;
    }

    public List<Order> getOrderHistory( Customer customer) {
        List<Order> oldOrder = new ArrayList<>();
        for(Order ord : customer.getOrders())
        {
            if(ord.getStatus().equals(OrderStatus.CANCELLED ) || ord.getStatus().equals(OrderStatus.COMPLETED ) || ord.getStatus().equals(OrderStatus.REJECTED ) )
            {
                oldOrder.add(ord);
            }
        }
        return oldOrder;
    }

    public void cancelOrder(int orderId) {

        for (Order order : dataStore.getOrders()) {

            if (order.getOrderId() == orderId) {

                if (order.getStatus() == OrderStatus.PENDING) {

                    order.setOrderStatus(OrderStatus.CANCELLED);
                    System.out.println("Order Cancelled Successfully.");
                }
                else {
                    System.out.println("Only pending orders can be cancelled.");
                }

                return;
            }
        }

        System.out.println("Order Not Found.");
    }
}