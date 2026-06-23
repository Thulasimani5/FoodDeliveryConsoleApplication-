package Repository;

import Models.RestaurantOwner;

import java.util.List;

public class RestaurantOwnerRepository {

    private DataStore dataStore = DataStore.getInstance();

    public void addOwner(RestaurantOwner owner) {
        dataStore.getRestaurantOwners().add(owner);
    }

}