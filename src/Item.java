/**
 * Represents an item in the vending machine.
 */
public class Item {

    private String type;    // The type (or name) of the item
    private int price;      // The price of the item
    private int calorie;    // The calorie content of the item

    /**
     * Constructs an Item object with the specified type, price, and calorie content.
     * @param type    The type of the item.
     * @param price   The price of the item.
     * @param calorie The calorie content of the item.
     */
    public Item(String type, int price, int calorie) {
        this.type = type;
        this.price = price;
        this.calorie = calorie;
    }

    /**
     * Constructs an empty Item object with default values.
     * The default type is "NULL", price is 0, and calorie content is 0.
     */
    public Item() {
        this.type = "NULL";
        this.price = 0;
        this.calorie = 0;
    }

    /**
     * Returns the type of the item.
     * @return The type of the item.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the price of the item.
     * @return The price of the item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the calorie content of the item.
     * @return The calorie content of the item.
     */
    public int getCalorie() {
        return calorie;
    }
}
