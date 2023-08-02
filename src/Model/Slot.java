package Model;

import java.util.ArrayList;

/**
 * The Slot class represents a slot in the vending machine that can hold items.
 * Each slot can store multiple items of the same type with a specified quantity (itemStock).
 */
public class Slot implements Cloneable {

    /**
     * The list of items stored in this slot.
     */
    private ArrayList<Item> itemArrayList;

    /**
     * The type of item assigned to this slot.
     */
    private String assignedItemType;

    /**
     * The quantity or stock of items in this slot.
     */
    private int itemStock;

    /**
     * The price of the item assigned to this slot.
     */
    private int assignedItemPrice;

    /**
     * The calorie count of the item assigned to this slot.
     */
    private int assignedItemCals;

    /**
     * Constructs a new Slot object with the given item type, item stock, item price, and calorie count.
     *
     * @param itemType      The type of item to be assigned to this slot.
     * @param itemStock     The initial quantity of items in this slot.
     * @param itemPrice     The price of the item assigned to this slot.
     * @param itemCals      The calorie count of the item assigned to this slot.
     */
    public Slot(String itemType, int itemStock, int itemPrice, int itemCals) {
        this.assignedItemType = itemType;
        this.itemArrayList = new ArrayList<>();
        this.itemStock = itemStock;
        this.assignedItemPrice = itemPrice;
        this.assignedItemCals = itemCals;
    }

    /**
     * Clones the Slot object.
     *
     * @return A deep copy of the Slot object.
     */
    @Override
    public Slot clone() {
        try {
            return (Slot) super.clone();
        } catch (CloneNotSupportedException e) {
            // Handle the exception, if necessary
            return null;
        }
    }

    /**
     * Constructs a new Slot object with default values.
     * This constructor is used when no initial values are provided.
     * The assigned item type is set to "NULL," and the item stock, price, and calorie count are set to 0.
     */
    public Slot() {
        this.assignedItemType = "NULL";
        this.itemArrayList = new ArrayList<>();
        this.itemStock = 0;
        this.assignedItemCals = 0;
        this.assignedItemPrice = 0;
    }

    /**
     * Gets the list of items stored in this slot.
     *
     * @return The list of items stored in this slot.
     */
    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    /**
     * Sets the list of items stored in this slot.
     *
     * @param itemArrayList The new list of items to be stored in this slot.
     */
    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    /**
     * Gets the type of item assigned to this slot.
     *
     * @return The type of item assigned to this slot.
     */
    public String getAssignedItemType() {
        return assignedItemType;
    }

    /**
     * Gets the quantity or stock of items in this slot.
     *
     * @return The quantity or stock of items in this slot.
     */
    public int getItemStock() {
        return itemStock;
    }

    /**
     * Sets the quantity or stock of items in this slot.
     *
     * @param itemStock The new quantity or stock of items in this slot.
     */
    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    /**
     * Gets the price of the item assigned to this slot.
     *
     * @return The price of the item assigned to this slot.
     */
    public int getAssignedItemPrice() {
        return assignedItemPrice;
    }

    /**
     * Sets the price of the item assigned to this slot.
     *
     * @param assignedItemPrice The new price of the item assigned to this slot.
     */
    public void setAssignedItemPrice(int assignedItemPrice) {
        this.assignedItemPrice = assignedItemPrice;
    }

    /**
     * Gets the calorie count of the item assigned to this slot.
     *
     * @return The calorie count of the item assigned to this slot.
     */
    public int getAssignedItemCals() {
        return assignedItemCals;
    }
}
