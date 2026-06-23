package Models;

public class RestaurantOwner extends User {

    private Restaurant restaurant;

    public RestaurantOwner(int userId,
                           String name,
                           String email,
                           long phoneNo,
                           String password) {

        super(userId, name, email, phoneNo, password, Role.RestaurantOwner);
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public int getId()
    {
        return  super.user_id;
    }
}