package Services;

import Models.*;
import Repository.DataStore;
import Repository.FoodItemRepository;
import Repository.OrderRepository;
import Repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    private DataStore dataStore = DataStore.getInstance();
    RestaurantRepository restaurantRepository = new RestaurantRepository();
    FoodItemRepository foodItemRepository= new FoodItemRepository();
    OrderRepository orderRepository = new OrderRepository();
    Scanner sc = new Scanner(System.in);

    public void placeOrder(Customer customer, int restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant == null) {
            System.out.println("Restaurant Not Found");
            return;
        }

        int totalFoodPrice = 0;
        List<OrderItem> items = new ArrayList<>();
        boolean flag = true;

        while (flag) {

            System.out.println("Enter the selected Food Id :");
            int foodId = sc.nextInt();

            System.out.println("Enter the selected Food Quantity :");
            int quantity = sc.nextInt();

            FoodItem selectedFood = foodItemRepository.findById(foodId);

            if (selectedFood == null) {
                System.out.println("Food Item Not Found");
                return;
            }

            int foodPrice = selectedFood.getPrice() * quantity;
            totalFoodPrice += foodPrice;

            OrderItem orderItem = new OrderItem(selectedFood, quantity, foodPrice);
            items.add(orderItem);

            System.out.println("Continue Buying:\n1.Yes\n2.No");
            int opt = sc.nextInt();
            if (opt == 2) {
                flag = false;

                System.out.println("Confirm Order:\n1.Yes\n2.No");
                opt = sc.nextInt();

                if (opt == 2) {
                    return;
                }
            }
        }

        Order order = new Order(
                dataStore.getOrders().size() + 1,
                customer.getId(),
                restaurantId,
                totalFoodPrice,
                40,
                items,
                OrderStatus.PENDING
        );

        orderRepository.addOrder(order);
        customer.addOrder(order);
        restaurant.addOrder(order);

        System.out.println("Order Placed Successfully");
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
    public void displayOrders(Order order)
    {
        System.out.println("Order Id      : " + order.getOrderId());
        System.out.println("Customer ID   : " + order.getCustomerId());
        System.out.println("Restaurant Id : " + order.getRestaurantId());
        System.out.println("Status        : " + order.getOrderStatus());
        System.out.println("Items:");
        for (OrderItem item : order.getOrderedItems()) {
            System.out.println(item.getOrderedFood().getFoodName() + " x " + item.getQuantity() + " = ₹" + item.getFoodPrice());
        }
        System.out.println("Food Price    : ₹" + order.getFoodPrice());
        System.out.println("Delivery Fee  : ₹" + order.getDeliveryFee());
        System.out.println("Total Amount  : ₹" + (order.getFoodPrice() + order.getDeliveryFee()));
        System.out.println("-----------------------------------");
    }
}