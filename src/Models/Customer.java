package Models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private Address address;
    private List<Order> orders;

    public Customer(int userId,
                    String name,
                    String email,
                    long phoneNo,
                    String password,
                    Address address) {

        super(userId, name, email, phoneNo, password, Role.Customer);

        this.address = address;
        this.orders = new ArrayList<>();
    }
    public void addOrder(Order order)
    {
        orders.add(order);
    }
    public int getId()
    {
        return  super.user_id;
    }
    public List<Order> getOrders()
    {
        return orders;
    }
}