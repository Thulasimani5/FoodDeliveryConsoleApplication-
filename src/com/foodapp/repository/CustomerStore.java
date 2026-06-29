package com.foodapp.repository;

import com.foodapp.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerStore {

    private DataStore dataStore = DataStore.getInstance();

    public void addCustomer(Customer customer) {
        dataStore.getCustomers().put(customer.getEmail(), customer);
    }

    public List<Customer> getCustomers()
    {
        return new ArrayList<>(dataStore.getCustomers().values());
    }

    public Customer findByEmail(String email) {
        return dataStore.getCustomers().get(email);
    }

}