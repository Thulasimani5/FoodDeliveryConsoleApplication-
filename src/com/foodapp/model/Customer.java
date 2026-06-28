package com.foodapp.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private Address address;
    private List<Integer> orderIds;

    public Customer(int userId,
                    String email,
                    String password) {

        super(userId, email, password, Role.Customer);

        this.orderIds = new ArrayList<>();
    }

    public void addOrder(int orderId) {
        orderIds.add(orderId);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public List<Integer> getOrders() {
        return orderIds;
    }
}