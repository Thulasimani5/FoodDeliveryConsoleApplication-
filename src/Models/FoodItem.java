package Models;

public abstract class FoodItem {
    protected int foodId;
    protected String foodName;
    protected String description;
    protected int price;
    protected FoodType foodType;
    protected boolean isAvailable;
    protected UnitType quantityType;
    protected int quantity;
    protected Restaurant restaurant;

    public FoodItem(int foodId, String foodName, String description,
                    int price, FoodType foodType,
                    boolean isAvailable, UnitType quantityType,
                    int quantity, Restaurant restaurant) {

        this.foodId = foodId;
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.foodType = foodType;
        this.isAvailable = isAvailable;
        this.quantityType = quantityType;
        this.quantity = quantity;
        this.restaurant = restaurant;
    }
    public Restaurant getRestaurant()
    {
        return restaurant;
    }
    public int getFoodItemId()
    {
        return foodId;
    }
    public String getFoodName() {
        return foodName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public UnitType getQuantityType() {
        return quantityType;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRestaurantId() {
        return restaurant.getRestaurantId();
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setQuantityType(UnitType quantityType) {
        this.quantityType = quantityType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}