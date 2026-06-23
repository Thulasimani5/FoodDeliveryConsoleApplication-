package Models;

import java.util.List;

public class Order {
    private int order_id;
    private int customer_id;
    private int food_price;
    private int delivery_fee;
    private List<OrderItem> ordered_item;
    private  OrderStatus order_status;
    private  int restaurant_id;
    public Order(int orderId,
                 int customer,
                 int restaurant,
                 int foodPrice,
                 int deliveryFee,
                 List<OrderItem> orderedItems,
                 OrderStatus orderStatus) {

        this.order_id = orderId;
        this.customer_id = customer;
        this.restaurant_id = restaurant;
        this.food_price = foodPrice;
        this.delivery_fee = deliveryFee;
        this.ordered_item = orderedItems;
        this.order_status = orderStatus;
    }
    public OrderStatus getStatus()
    {
        return order_status;
    }
    public int getOrderId() {
        return order_id;
    }

    public int getFoodPrice() {
        return food_price;
    }

    public int getDeliveryFee() {
        return delivery_fee;
    }

    public List<OrderItem> getOrderedItems() {
        return ordered_item;
    }

    public OrderStatus getOrderStatus() {
        return order_status;
    }

    public int getRestaurantId() {
        return restaurant_id;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.order_status = orderStatus;
    }
    public  int getCustomerId()
    {
        return customer_id;
    }
}
