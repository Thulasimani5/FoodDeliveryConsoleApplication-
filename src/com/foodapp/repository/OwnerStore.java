package com.foodapp.repository;

import com.foodapp.model.Customer;
import com.foodapp.model.RestaurantOwner;

import java.util.ArrayList;
import java.util.List;

public class OwnerStore {

    private DataStore dataStore = DataStore.getInstance();

    public void addOwner(RestaurantOwner owner) {
        dataStore.getRestaurantOwners().put(owner.getEmail(), owner);
    }

    public List<RestaurantOwner> getRestaurantOwners()
    {
        return new ArrayList<>(dataStore.getRestaurantOwners().values());
    }

    public RestaurantOwner findByEmail(String email) {
        return dataStore.getRestaurantOwners().get(email);
    }
}