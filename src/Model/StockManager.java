package Model;


import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Scanner;

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
            String itemType = vendingMachine.getSelectedItem(indexChoice, isSpecialSlot).getType();
            int price = vendingMachine.getSelectedItem(indexChoice, isSpecialSlot).getPrice();
            int calories = vendingMachine.getSelectedItem(indexChoice, isSpecialSlot).getCalorie();

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

    public boolean isSameItemType(VendingMachine vendingMachine, String inputString) {
        // Check the input if an item of the same type already exists
        int slotSize = vendingMachine.getSlotArrayList().size();
        for (int i = 0; i < slotSize; i++) {
            if (vendingMachine.getSelectedSlot(i, false).getAssignedItemType().equalsIgnoreCase(inputString)) {
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
            vendingMachine.getSelectedSlot(lastIndex, false).getItemArrayList().add(new Item(newItem, newPrice, newCals));
        }
    }


    public void updateItemPrice(VendingMachine vendingMachine, boolean isSpecialSlot, int slotIndex, int newPrice) {
        ArrayList<Item> itemArrayList = vendingMachine.getSelectedSlot(slotIndex, isSpecialSlot).getItemArrayList();
        for (Item item : itemArrayList) {
            item.setPrice(newPrice);
        }
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


    public void restockProcess(Scanner scanner, VendingMachine vendingMachine, boolean isSpecialSlot)
    {
        int indexChoice;
        boolean isDone = false;
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> specialEndingInventoryCopy = null;
        if(vendingMachine instanceof SpecialVendingMachine)
        {
            specialEndingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        }
        ArrayList<? extends Slot> slotsToUse = isSpecialSlot ? vendingMachine.getSpecialSlots() : vendingMachine.getSlotArrayList();
        do {
            System.out.println("Restocking is only allowed for items with a stock count below 5.");
            System.out.println();
            for (int i = 0; i < slotsToUse.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + vendingMachine.getSelectedSlot(i, isSpecialSlot).getAssignedItemType()
                        + " -- Stock: " + vendingMachine.getSelectedSlot(i, isSpecialSlot).getItemStock());
            }

            System.out.println("[0] Press 0 to go back");
            System.out.print("Enter the index of the item to be restocked: ");
            indexChoice = scanner.nextInt();
            scanner.nextLine();

            if (indexChoice == 0) {
                System.out.println("Going back to maintenance menu...");
                isDone = true;
            } else if (indexChoice >= 1 && indexChoice <= slotsToUse.size()) {
                Maintenance.restockItem(vendingMachine, indexChoice - 1, isSpecialSlot);
                boolean isValidInput = false;
                do {
                    System.out.print("Do you want to continue restocking items? [Y]es or [N]o: ");
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("Y")) {
                        isValidInput = true;
                    } else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Going back to previous menu...");
                        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
                        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
                        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
                        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
                        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);

                        if(vendingMachine instanceof SpecialVendingMachine)
                        {
                            ArrayList<Slot> specialPrevStartingInventory = Maintenance.deepCopySlotArrayList(vendingMachine.getPrevStartingInventory());
                            Maintenance.addAllToPrevStartingSpecialInventory (vendingMachine, specialPrevStartingInventory);
                            Maintenance.addAllToEndingSpecialInventory (vendingMachine, specialEndingInventoryCopy);
                            ArrayList<Slot> specialStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
                            Maintenance.addAllToStartingSpecialInventory(vendingMachine, specialStartingInventoryCopy);
                        }
                        isValidInput = true;
                        isDone = true;
                    } else {
                        System.out.println("Invalid input. Please only enter 'Y' or 'N'.");
                    }
                } while (!isValidInput);
            } else {
                System.out.println("Invalid index choice. Please select a valid option or press 0 to go back.");
            }
        } while (!isDone);
    }
}

