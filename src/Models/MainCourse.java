package Models;

public class MainCourse extends FoodItem {

    private int servesPersons;

    public MainCourse(int foodId, String foodName, String description,
                      int price, FoodType foodType,
                      boolean isAvailable, UnitType quantityType,
                      int quantity, Restaurant restaurant,
                      int servesPersons) {

        super(foodId, foodName, description, price,
                foodType, isAvailable,
                quantityType, quantity, restaurant);

        this.servesPersons = servesPersons;
    }
    public void setServesPersons(int servesPersons) {
        this.servesPersons = servesPersons;
    }
}