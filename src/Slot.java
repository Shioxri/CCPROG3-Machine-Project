import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> itemArrayList;

    private String assignedItemType;

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    public Slot(String itemType, int price, int calories) {
        this.assignedItemType = itemType;
        this.itemArrayList = new ArrayList<>();
        initializeItemArrayList(itemType, price, calories);
    }

    public Slot() {
        this.assignedItemType = "NULL";
        this.itemArrayList = new ArrayList<>();
        initializeItemArrayList();
    }

    // Internal method to initialize itemArrayList with default items
    private void initializeItemArrayList() {
        int initialNumItems = 10;
        for (int i = 0; i < initialNumItems; i++) {
            itemArrayList.add(new Item());
        }
    }

    // Internal method to initialize itemArrayList with items of specific type, price, and calories
    private void initializeItemArrayList(String itemType, int price, int calories) {
        int initialNumItems = 10;
        for (int i = 0; i < initialNumItems; i++) {
            itemArrayList.add(new Item(itemType, price, calories));
        }
    }

    public String getAssignedItemType() {
        return assignedItemType;
    }
}
