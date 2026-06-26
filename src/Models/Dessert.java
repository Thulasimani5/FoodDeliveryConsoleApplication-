package Models;

public class Dessert extends FoodItem {

    private boolean eggless;
    private boolean containsNuts;
    private boolean servedCold;

    public Dessert(int foodId, String foodName, String description,
                   int price, FoodType foodType,
                   boolean isAvailable, UnitType quantityType,
                   int quantity, Restaurant restaurant,
                   boolean eggless,
                   boolean containsNuts,
                   boolean servedCold) {

        super(foodId, foodName, description, price,
                foodType, isAvailable,
                quantityType, quantity, restaurant);

        this.eggless = eggless;
        this.containsNuts = containsNuts;
        this.servedCold = servedCold;
    }
    public void setEggless(boolean eggless) {
        this.eggless = eggless;
    }

    public void setContainsNuts(boolean containsNuts) {
        this.containsNuts = containsNuts;
    }

    public void setServedCold(boolean servedCold) {
        this.servedCold = servedCold;
    }
}