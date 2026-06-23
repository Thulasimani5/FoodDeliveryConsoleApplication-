package Models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant  {
    private int restaurant_id;
    private  String restaurant_name;
    private Address restaurant_address;
    private List<FoodItem> menu;
    private RestaurantType cuisineType;
    private List<Order> orders ;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private RestaurantOwner restaurantOwner;
    public Restaurant(int restaurantId,
                      String restaurantName,
                      Address address,
                      RestaurantType cuisineType,
                      LocalTime openingTime,
                      LocalTime closingTime,
                      RestaurantOwner restaurantOwner)
    {
        this.restaurantOwner=restaurantOwner;
        this.restaurant_id = restaurantId;
        this.restaurant_name = restaurantName;
        this.restaurant_address = address;
        this.cuisineType = cuisineType;
        this.openingTime = openingTime;
        this.closingTime = closingTime;

        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
    }
    public int getRestaurantId() {
        return restaurant_id;
    }

    public List<FoodItem>  getRestaurantMenu() {
        return menu;
    }

    public void addFoodItem(FoodItem item) {
        menu.add(item);
    }
    public String getRestaurantName() {
        return restaurant_name;
    }

    public RestaurantType getCuisineType() {
        return cuisineType;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public Address getRestaurantAddress() {
        return restaurant_address;
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }
}
