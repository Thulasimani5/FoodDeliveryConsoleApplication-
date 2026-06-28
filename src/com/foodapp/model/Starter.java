package com.foodapp.model;

public class Starter extends FoodItem {

    private SpiceLevel spiceLevel;

    public Starter(int foodId, String foodName,
                   int price,int restaurantId,
                   SpiceLevel spiceLevel) {

        super(foodId, foodName, price, restaurantId);

        this.spiceLevel = spiceLevel;
    }

    public SpiceLevel getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(SpiceLevel spiceLevel) {
        this.spiceLevel = spiceLevel;
    }
}