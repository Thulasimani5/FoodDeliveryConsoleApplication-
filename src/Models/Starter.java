package Models;

public class Starter extends FoodItem {

    private SpiceLevel spiceLevel;

    public Starter(int foodId, String foodName, String description,
                   int price, FoodType foodType,
                   boolean isAvailable, UnitType quantityType,
                   int quantity, Restaurant restaurant,
                   SpiceLevel spiceLevel) {

        super(foodId, foodName, description, price,
                foodType, isAvailable,
                quantityType, quantity, restaurant);

        this.spiceLevel = spiceLevel;
    }
    public void setSpiceLevel(SpiceLevel spiceLevel) {
        this.spiceLevel = spiceLevel;
    }
}