package Model;


import java.util.ArrayList;

public class StockManager {
    public Item dispenseSelectedItem(VendingMachine vendingMachine, int itemChoice, boolean isSpecialSlot) {
        // Retrieve the chosen item
        Item chosenItem = vendingMachine.getSelectedItem(itemChoice, isSpecialSlot);
        vendingMachine.getSelectedSlot(itemChoice, isSpecialSlot).getItemArrayList().remove(0);
        // Shift the remaining items
        int itemListSize = vendingMachine.getSelectedSlot(itemChoice, isSpecialSlot).getItemArrayList().size();
        for (int i = 0; i < itemListSize - 1; i++) {
            vendingMachine.getSelectedSlot(itemChoice, isSpecialSlot).getItemArrayList().set
                    (i, vendingMachine.getSelectedSlot(itemChoice, isSpecialSlot).getItemArrayList().get(i + 1));
        }
        // Update the item list size of the slot
        int newSize = vendingMachine.getSelectedSlot(itemChoice, isSpecialSlot).getItemArrayList().size();
        vendingMachine.getSelectedSlot(itemChoice, isSpecialSlot).setItemStock(newSize);
        return chosenItem;
    }

    public void restockItems(VendingMachine vendingMachine, int indexChoice, boolean isSpecialSlot) { // -1 the indexChoice

        int maxItemsPerSlot = 10; // Actual maximum capacity of the slots
        int currentItemsCount = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getItemArrayList().size();
        int numFreeSlotSpaces = maxItemsPerSlot - currentItemsCount;

        if (numFreeSlotSpaces > 0) { // if there are still empty spaces available in the slot
            //get the attributes of the item
            String itemType = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getAssignedItemType();
            int price = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getAssignedItemPrice();
            int calories = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getAssignedItemCals();

            if (numFreeSlotSpaces <= 5) { // There must be at most 5 items in the slot to be able to refill, else, it still doesn't need restocking
                System.out.println("There are already enough items in this slot. Must be 5 or less to refill");
            } else {
                for (int i = 0; i < numFreeSlotSpaces; i++) {
                    Item newItem = new Item(itemType, price, calories); // Create a new item with the same attributes
                    vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getItemArrayList().add(newItem); // Add the item to the slot
                }
                System.out.println("Successfully restocked " + numFreeSlotSpaces + " items of type: " + itemType);
                vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).setItemStock(maxItemsPerSlot);
            }
        } else {
            System.out.println("This slot is already fully stocked.");
        }
    }

    public void stockNewItems(VendingMachine vendingMachine, String newItem, int newPrice, int newCals) {
        vendingMachine.getSlotArrayList().add(new Slot(newItem, 10, newPrice, newCals));
        int lastIndex = vendingMachine.getSlotArrayList().size() - 1;
        for (int i = 0; i < 10; i++) {
            vendingMachine.getSelectedSlot(lastIndex, false).getItemArrayList().add(new Item(newItem, newPrice, newCals));
        }
    }


    public void updateItemPrice(VendingMachine vendingMachine, boolean isSpecialSlot, int slotIndex, int newPrice) {
        ArrayList<Item> itemArrayList = vendingMachine.getSelectedSlot(slotIndex, isSpecialSlot).getItemArrayList();
        for (Item item : itemArrayList) {
            item.setPrice(newPrice);
        }
        vendingMachine.getSelectedSlot(slotIndex, isSpecialSlot).setAssignedItemPrice(newPrice);
        System.out.println("Successfully updated the price of all items in slot " + (slotIndex + 1) + " to " + newPrice);
    }


    public void restoreOriginalSlotContents(VendingMachine vendingMachine, ArrayList<Slot> originalSlots, ArrayList<Slot> originalSpecialSlots) {
        // Restore the original contents of regular slots
        for (int i = 0; i < originalSlots.size(); i++) {
            Slot originalSlot = originalSlots.get(i);
            Slot currentSlot = vendingMachine.getSlotArrayList().get(i);
            currentSlot.setItemArrayList(originalSlot.getItemArrayList());
            currentSlot.setItemStock(originalSlot.getItemArrayList().size());
        }

        // Restore the original contents of special slots
        for (int i = 0; i < originalSpecialSlots.size(); i++) {
            Slot originalSlot = originalSpecialSlots.get(i);
            Slot currentSlot = vendingMachine.getSpecialSlots().get(i);
            currentSlot.setItemArrayList(originalSlot.getItemArrayList());
            currentSlot.setItemStock(originalSlot.getItemArrayList().size());
        }
    }


}

