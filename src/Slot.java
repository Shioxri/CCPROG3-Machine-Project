import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> itemArrayList;

    private String assignedItemType;

    public Slot(String itemType) {
        this.assignedItemType = itemType;
        this.itemArrayList = new ArrayList<>();
    }

    public Slot() {
        this.assignedItemType = "NULL";
        this.itemArrayList = new ArrayList<>();
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }
    public String getAssignedItemType() {
        return assignedItemType;
    }
}
