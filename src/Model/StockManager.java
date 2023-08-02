package Model;

import java.util.ArrayList;

/**
 * The StockManager class handles the management of items in the vending machine's slots.
 */
public class StockManager {
    /**
     * Dispenses the selected item from the vending machine's slot.
     *
     * @param vendingMachine The vending machine instance.
     * @param itemChoice     The user's item choice (slot index).
     * @param isSpecialSlot  A boolean indicating whether the selected slot is a special slot.
     * @return The item that has been dispensed.
     */
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

    /**
     * Restocks the items in the vending machine's slot.
     *
     * @param vendingMachine The vending machine instance.
     * @param indexChoice    The index of the slot to be restocked.
     * @param isSpecialSlot  A boolean indicating whether the selected slot is a special slot.
     */
    public void restockItems(VendingMachine vendingMachine, int indexChoice, boolean isSpecialSlot) {
        int maxItemsPerSlot = 10; // Actual maximum capacity of the slots
        int currentItemsCount = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getItemArrayList().size();
        int numFreeSlotSpaces = maxItemsPerSlot - currentItemsCount;

        if (numFreeSlotSpaces > 0) { // if there are still empty spaces available in the slot
            // Get the attributes of the item
            String itemType = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getAssignedItemType();
            int price = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getAssignedItemPrice();
            int calories = vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getAssignedItemCals();

            if (!(numFreeSlotSpaces <= 5)) { // There must be at most 5 items in the slot to be able to refill, else, it still doesn't need restocking
                for (int i = 0; i < numFreeSlotSpaces; i++) {
                    Item newItem = new Item(itemType, price, calories); // Create a new item with the same attributes
                    vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).getItemArrayList().add(newItem); // Add the item to the slot
                }
                vendingMachine.getSelectedSlot(indexChoice, isSpecialSlot).setItemStock(maxItemsPerSlot);
            }
        }
    }

    /**
     * Stocks new items in the vending machine's slot.
     *
     * @param vendingMachine The vending machine instance.
     * @param newItem        The type of the new item to be stocked.
     * @param newPrice       The price of the new item.
     * @param newCals        The calories of the new item.
     */
    public void stockNewItems(VendingMachine vendingMachine, String newItem, int newPrice, int newCals) {
        vendingMachine.getSlotArrayList().add(new Slot(newItem, 10, newPrice, newCals));
        int lastIndex = vendingMachine.getSlotArrayList().size() - 1;
        for (int i = 0; i < 10; i++) {
            vendingMachine.getSelectedSlot(lastIndex, false).getItemArrayList().add(new Item(newItem, newPrice, newCals));
        }
    }

    /**
     * Updates the price of all items in a vending machine's slot.
     *
     * @param vendingMachine The vending machine instance.
     * @param isSpecialSlot   A boolean indicating whether the selected slot is a special slot.
     * @param slotIndex       The index of the slot to be updated.
     * @param newPrice        The new price to be set for all items in the slot.
     */
    public void updateItemPrice(VendingMachine vendingMachine, boolean isSpecialSlot, int slotIndex, int newPrice) {
        ArrayList<Item> itemArrayList = vendingMachine.getSelectedSlot(slotIndex, isSpecialSlot).getItemArrayList();
        for (Item item : itemArrayList) {
            item.setPrice(newPrice);
        }
        vendingMachine.getSelectedSlot(slotIndex, isSpecialSlot).setAssignedItemPrice(newPrice);
    }

    /**
     * Restores the original contents of the vending machine's slots.
     *
     * @param vendingMachine      The vending machine instance.
     * @param originalSlots       The list of original regular slots.
     * @param originalSpecialSlots The list of original special slots.
     */
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
