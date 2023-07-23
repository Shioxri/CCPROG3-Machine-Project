package Model;


import java.util.ArrayList;

public class StockManager {
    public Item dispenseSelectedItem(VendingMachine vendingMachine, int itemChoice) {
        // Retrieve the chosen item
        Item chosenItem = vendingMachine.getSelectedItem(itemChoice);
        vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().remove(0);
        // Shift the remaining items
        int itemListSize = vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().size();
        for (int i = 0; i < itemListSize - 1; i++) {
            vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().set
                    (i, vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().get(i + 1));
        }
        return chosenItem;
    }

    public void restockItems(VendingMachine vendingMachine, int indexChoice) { // -1 the indexChoice

        int maxItemsPerSlot = 10; // Actual maximum capacity of the slots
        int currentItemsCount = vendingMachine.getSelectedSlot(indexChoice).getItemArrayList().size();
        int numFreeSlotSpaces = maxItemsPerSlot - currentItemsCount;

        if (numFreeSlotSpaces > 0) { // if there are still empty spaces available in the slot
            //get the attributes of the item
            String itemType = vendingMachine.getSelectedItem(indexChoice).getType();
            int price = vendingMachine.getSelectedItem(indexChoice).getPrice();
            int calories = vendingMachine.getSelectedItem(indexChoice).getCalorie();

            if (numFreeSlotSpaces <= 5) { // There must be at most 5 items in the slot to be able to refill, else, it still doesn't need restocking
                System.out.println("There are already enough items in this slot. Must be 5 or less to refill");
            } else {
                for (int i = 0; i < numFreeSlotSpaces; i++) {
                    Item newItem = new Item(itemType, price, calories); // Create a new item with the same attributes
                    vendingMachine.getSelectedSlot(indexChoice).getItemArrayList().add(newItem); // Add the item to the slot
                }
                System.out.println("Successfully restocked " + indexChoice + " items of type: " + itemType);

            }
        } else {
            System.out.println("This slot is already fully stocked.");
        }
    }

    public boolean isSameItemType(VendingMachine vendingMachine, String inputString) {
        // Check the input if an item of the same type already exists
        int slotSize = vendingMachine.getSlotArrayList().size();
        for (int i = 0; i < slotSize; i++) {
            if (vendingMachine.getSelectedSlot(i).getAssignedItemType().equalsIgnoreCase(inputString)) {
                System.out.println("An item of the same type already exists. Please enter a different item.");
                return true;
            }
        }
        return false;
    }


    public void stockNewItems(VendingMachine vendingMachine, String newItem, int newPrice, int newCals) {
        vendingMachine.getSlotArrayList().add(new Slot(newItem, 10));
        int lastIndex = vendingMachine.getSlotArrayList().size() - 1;
        for (int i = 0; i < 10; i++) {
            vendingMachine.getSelectedSlot(lastIndex).getItemArrayList().add(new Item(newItem, newPrice, newCals));
        }
    }


    public void updateItemPrice(VendingMachine vendingMachine, int slotIndex, int newPrice) {
        ArrayList<Item> itemArrayList = vendingMachine.getSelectedSlot(slotIndex).getItemArrayList();
        for (Item item : itemArrayList) {
            item.setPrice(newPrice);
        }
        System.out.println("Successfully updated the price of all items in slot " + (slotIndex + 1) + " to " + newPrice);
    }





}
