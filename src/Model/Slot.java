package Model;


import java.util.ArrayList;

public class Slot implements Cloneable {
    private ArrayList<Item> itemArrayList;
    private String assignedItemType;
    private int itemStock; // The itemStock or quantity of items in the slot
    private int assignedItemPrice;

    public int getAssignedItemCals() {
        return assignedItemCals;
    }
    private int assignedItemCals;

    public int getAssignedItemPrice() {
        return assignedItemPrice;
    }

    public void setAssignedItemPrice(int assignedItemPrice) {
        this.assignedItemPrice = assignedItemPrice;
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public Slot(String itemType, int itemStock, int itemPrice, int itemCals) {
        this.assignedItemType = itemType;
        this.itemArrayList = new ArrayList<>();
        this.itemStock = itemStock;
        this.assignedItemPrice = itemPrice;
        this.assignedItemCals = itemCals;
    }

    @Override
    public Slot clone() {
        try {
            return (Slot) super.clone();
        } catch (CloneNotSupportedException e) {
            // Handle the exception, if necessary
            return null;
        }
    }

    public Slot() {
        this.assignedItemType = "NULL";
        this.itemArrayList = new ArrayList<>();
        this.itemStock = 0;
        this.assignedItemCals = 0;
        this.assignedItemPrice = 0;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public String getAssignedItemType() {
        return assignedItemType;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }
}
