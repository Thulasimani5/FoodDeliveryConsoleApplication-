package com.foodapp.repository;

import com.foodapp.model.RestaurantOwner;

import java.util.List;

public class OwnerStore {

    private DataStore dataStore = DataStore.getInstance();

    public void addOwner(RestaurantOwner owner) {
        dataStore.getRestaurantOwners().add(owner);
    }
    public List<RestaurantOwner> getRestaurantOwners()
    {
        return dataStore.getRestaurantOwners();
    }

}