package com.foodapp.model;

public class Beverage extends FoodItem {

    private BeverageSize size;
    private boolean sugarFree;
    private boolean isCold;

    public Beverage(int foodId, String foodName, int price,
                    int restaurantId, BeverageSize size) {

        super(foodId, foodName, price, restaurantId);

        this.size = size;
    }

    public BeverageSize getSize() {
        return size;
    }

    public void setSize(BeverageSize size) {
        this.size = size;
    }

    public boolean isSugarFree() {
        return sugarFree;
    }

    public void setSugarFree(boolean sugarFree) {
        this.sugarFree = sugarFree;
    }

    public boolean isCold() {
        return isCold;
    }

    public void setCold(boolean cold) {
        this.isCold = cold;
    }
}