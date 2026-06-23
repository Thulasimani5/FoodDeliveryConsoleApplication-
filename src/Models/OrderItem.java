package Models;

public class OrderItem {
    private FoodItem ordered_food;
    private int quantity;
    private int price;
    public OrderItem(FoodItem orderedFood, int quantity,int price) {
        this.ordered_food = orderedFood;
        this.quantity = quantity;
        this.price=price;
    }
    public FoodItem getOrderedFood() {
        return ordered_food;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getFoodPrice() {
        return price ;
    }
}
