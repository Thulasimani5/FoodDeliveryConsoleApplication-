package Models;

public class Beverage extends FoodItem {

    private BeverageSize size;
    private boolean sugarFree;
    private boolean isCold;

    public Beverage(int foodId, String foodName, String description,
                    int price, FoodType foodType,
                    boolean isAvailable, UnitType quantityType,
                    int quantity, Restaurant restaurant,
                    BeverageSize size,
                    boolean sugarFree,
                    boolean isCold) {

        super(foodId, foodName, description, price,
                foodType, isAvailable,
                quantityType, quantity, restaurant);

        this.size = size;
        this.sugarFree = sugarFree;
        this.isCold = isCold;
    }
}