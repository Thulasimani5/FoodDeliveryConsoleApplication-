package Repository;

import Models.Restaurant;

import java.util.List;

public class RestaurantRepository {

    private DataStore dataStore = DataStore.getInstance();

    public void addRestaurant(Restaurant restaurant) {
        dataStore.getRestaurants().add(restaurant);
    }

    public Restaurant findById(int restaurantId) {
        for (Restaurant restaurant : dataStore.getRestaurants()) {
            if (restaurant.getRestaurantId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }

    public List<Restaurant> getAllRestaurants() {
        return dataStore.getRestaurants();
    }

}