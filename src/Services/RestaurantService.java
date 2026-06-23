package Services;

import Models.FoodItem;
import Models.Restaurant;
import Repository.CustomerRepository;
import Repository.DataStore;
import Repository.RestaurantOwnerRepository;
import Repository.RestaurantRepository;

import java.util.List;

public class RestaurantService {

    private DataStore dataStore = DataStore.getInstance();


    public List<Restaurant> getAllRestaurants() {
        return dataStore.getRestaurants();
    }

    public List<FoodItem> getRestaurantMenu(int restaurantId) {
        for (Restaurant item : dataStore.getRestaurants()) {

            if (item.getRestaurantId() == restaurantId) {
                return item.getRestaurantMenu();
            }
        }
        return null;
    }

}