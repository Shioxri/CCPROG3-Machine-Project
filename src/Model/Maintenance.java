package Model;

import java.util.ArrayList;

/**
 * The Maintenance class provides static methods for performing maintenance operations on the vending machine, such as restocking items,
 * updating item prices, collecting money, and generating sales reports.
 */
public class Maintenance {

    /**
     * Restocks the items in a specific slot of the vending machine.
     *
     * @param vendingMachine The vending machine to restock items in.
     * @param indexChoice    The index of the slot to restock.
     * @param isSpecialSlot  A boolean value indicating whether the slot is a special slot or not.
     */
    public static void restockItem(VendingMachine vendingMachine, int indexChoice, boolean isSpecialSlot) {
        vendingMachine.getStockManager().restockItems(vendingMachine, indexChoice, isSpecialSlot);
    }

    /**
     * Stocks new items in the vending machine.
     *
     * @param vendingMachine The vending machine to stock new items in.
     * @param newItem        The name of the new item to stock.
     * @param newPrice       The price of the new item.
     * @param newCals        The calorie count of the new item.
     */
    public static void stockNewItems(VendingMachine vendingMachine, String newItem, int newPrice, int newCals) {
        vendingMachine.getStockManager().stockNewItems(vendingMachine, newItem, newPrice, newCals);
    }

    /**
     * Updates the prices of items in the vending machine.
     *
     * @param vendingMachine The vending machine to update item prices in.
     * @param isSpecialSlot  A boolean value indicating whether the slot is a special slot or not.
     * @param userChoice     The index of the slot to update item prices.
     * @param newPrice       The new price to set for the items in the slot.
     */
    public static void updateItemPrices(VendingMachine vendingMachine, boolean isSpecialSlot, int userChoice, int newPrice) {
        vendingMachine.getStockManager().updateItemPrice(vendingMachine, isSpecialSlot, userChoice, newPrice);
    }

    /**
     * Collects the money from the vending machine and stores it in the admin money list.
     *
     * @param vendingMachine The vending machine to collect money from.
     */
    public static void collectMoney(VendingMachine vendingMachine) {
        vendingMachine.getMoneyManager().collectMoney();
    }

    /**
     * Replenishes the stored money in the vending machine with new denominations and quantities.
     *
     * @param vendingMachine The vending machine to replenish money in.
     * @param denomination   The denomination of the money to replenish.
     * @param quantity       The quantity of the money to replenish.
     */
    public static void replenishMoney(VendingMachine vendingMachine, int denomination, int quantity) {
        vendingMachine.getMoneyManager().addStoredMoney(denomination, quantity);
    }

    /**
     * Adds sold items to the sales records of the vending machine.
     *
     * @param vendingMachine The vending machine to add sold items to.
     * @param itemType       The type of item that was sold.
     */
    public static void addSoldItems(VendingMachine vendingMachine, String itemType) {
        vendingMachine.getRecorder().addSoldItems(itemType);
    }

    /**
     * Generates a sales report for the vending machine.
     *
     * @param vendingMachine The vending machine to generate the sales report for.
     * @return The sales report as a formatted string.
     */
    public static String getSalesReport(VendingMachine vendingMachine) {
        return vendingMachine.getRecorder().getSalesReport(vendingMachine);
    }

    /**
     * Adds all slots from the provided slotList to the starting inventory of the vending machine.
     *
     * @param vendingMachine The vending machine to add slots to the starting inventory.
     * @param slotList        The list of slots to add to the starting inventory.
     */
    public static void addAllToStartingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getStartingInventory().clear();
        vendingMachine.getRecorder().getStartingInventory().addAll(slotList);
    }

    /**
     * Adds all slots from the provided slotList to the previous starting inventory of the vending machine.
     *
     * @param vendingMachine The vending machine to add slots to the previous starting inventory.
     * @param slotList        The list of slots to add to the previous starting inventory.
     */
    public static void addAllToPrevStartingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getPrevStartingInventory().clear();
        vendingMachine.getRecorder().getPrevStartingInventory().addAll(slotList);
    }

    /**
     * Adds all slots from the provided slotList to the ending inventory of the vending machine.
     *
     * @param vendingMachine The vending machine to add slots to the ending inventory.
     * @param slotList        The list of slots to add to the ending inventory.
     */
    public static void addAllToEndingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getEndingInventory().clear();
        vendingMachine.getRecorder().getEndingInventory().addAll(slotList);
    }

    /**
     * Adds all slots from the provided slotList to the starting special inventory of the vending machine.
     *
     * @param vendingMachine The vending machine to add slots to the starting special inventory.
     * @param slotList        The list of slots to add to the starting special inventory.
     */
    public static void addAllToStartingSpecialInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getSpecialStartingInventory().clear();
        vendingMachine.getRecorder().getSpecialStartingInventory().addAll(slotList);
    }

    /**
     * Adds all slots from the provided slotList to the previous starting special inventory of the vending machine.
     *
     * @param vendingMachine The vending machine to add slots to the previous starting special inventory.
     * @param slotList        The list of slots to add to the previous starting special inventory.
     */
    public static void addAllToPrevStartingSpecialInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getSpecialPrevStartingInventory().clear();
        vendingMachine.getRecorder().getSpecialPrevStartingInventory().addAll(slotList);
    }

    /**
     * Adds all slots from the provided slotList to the ending special inventory of the vending machine.
     *
     * @param vendingMachine The vending machine to add slots to the ending special inventory.
     * @param slotList        The list of slots to add to the ending special inventory.
     */
    public static void addAllToEndingSpecialInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getSpecialEndingInventory().clear();
        vendingMachine.getRecorder().getSpecialEndingInventory().addAll(slotList);
    }

    /**
     * Creates a deep copy of the ArrayList of slots.
     *
     * @param original The original ArrayList of slots to be copied.
     * @return The deep copy of the original ArrayList of slots.
     */
    public static ArrayList<Slot> deepCopySlotArrayList(ArrayList<Slot> original) {
        ArrayList<Slot> copy = new ArrayList<>();
        for (Slot slot : original) {
            copy.add(slot.clone());
        }
        return copy;
    }
}

