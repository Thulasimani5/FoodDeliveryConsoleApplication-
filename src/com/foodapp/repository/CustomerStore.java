package com.foodapp.repository;

import com.foodapp.model.Customer;

import java.util.List;

public class CustomerStore {

    private DataStore dataStore = DataStore.getInstance();

    public void addCustomer(Customer customer) {
        dataStore.getCustomers().add(customer);
    }

    public List<Customer> getCustomers()
    {
        return dataStore.getCustomers();
    }

    public Customer findByEmail(String email) {
        for (Customer customer : dataStore.getCustomers()) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findById(int customerId) {
        for (Customer customer : dataStore.getCustomers()) {
            if (customer.getUser_id() == customerId) {
                return customer;
            }
        }
        return null;
    }
}