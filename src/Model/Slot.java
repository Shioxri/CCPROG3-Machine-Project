package Model;

import Model.Item;

import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> itemArrayList;
    private String assignedItemType;
    private int itemStock; // The itemStock or quantity of items in the slot

    public Slot(String itemType, int itemStock) {
        this.assignedItemType = itemType;
        this.itemArrayList = new ArrayList<>();
        this.itemStock = itemStock;
    }

    public Slot() {
        this.assignedItemType = "NULL";
        this.itemArrayList = new ArrayList<>();
        this.itemStock = 0;
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
