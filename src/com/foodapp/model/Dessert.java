package com.foodapp.model;

public class Dessert extends FoodItem {

    private boolean eggless;
    private boolean containsNuts;
    private boolean servedCold;

    public Dessert(int foodId, String foodName,
                   int price,int restaurantId,
                   boolean eggless,
                   boolean containsNuts,
                   boolean servedCold) {

        super(foodId, foodName, price, restaurantId);

        this.eggless = eggless;
        this.containsNuts = containsNuts;
        this.servedCold = servedCold;
    }

    public boolean isContainsNuts() {
        return containsNuts;
    }

    public boolean isEggless() {
        return eggless;
    }

    public boolean isServedCold() {
        return servedCold;
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