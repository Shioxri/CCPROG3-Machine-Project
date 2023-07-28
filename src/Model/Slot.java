package Model;


import java.util.ArrayList;

public class Slot implements Cloneable {
    private ArrayList<Item> itemArrayList;
    private String assignedItemType;
    private int itemStock; // The itemStock or quantity of items in the slot

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public Slot(String itemType, int itemStock) {
        this.assignedItemType = itemType;
        this.itemArrayList = new ArrayList<>();
        this.itemStock = itemStock;
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
