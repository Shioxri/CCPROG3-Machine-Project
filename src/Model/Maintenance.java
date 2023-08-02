package Model;


import java.util.ArrayList;

public class Maintenance {
    public static void restockItem(VendingMachine vendingMachine, int indexChoice, boolean isSpecialSlot) {
        vendingMachine.getStockManager().restockItems(vendingMachine, indexChoice, isSpecialSlot);
    }

    public static void stockNewItems(VendingMachine vendingMachine, String newItem, int newPrice, int newCals) {
        vendingMachine.getStockManager().stockNewItems(vendingMachine, newItem, newPrice, newCals);
    }

    public static void updateItemPrices(VendingMachine vendingMachine, boolean isSpecialSlot, int userChoice, int newPrice) {
        vendingMachine.getStockManager().updateItemPrice(vendingMachine, isSpecialSlot, userChoice, newPrice);
    }

    public static void collectMoney(VendingMachine vendingMachine) {
        vendingMachine.getMoneyManager().collectMoney();
    }

    public static void replenishMoney(VendingMachine vendingMachine, int denomination, int quantity) {
        vendingMachine.getMoneyManager().addStoredMoney(denomination, quantity);
    }

    public static void addSoldItems(VendingMachine vendingMachine, String itemType) {
        vendingMachine.getRecorder().addSoldItems(itemType);
    }
    
    public static String getSalesReport(VendingMachine vendingMachine) {
        return vendingMachine.getRecorder().getSalesReport(vendingMachine);
    }

    public static void addAllToStartingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getStartingInventory().clear();
        vendingMachine.getRecorder().getStartingInventory().addAll(slotList);
    }

    public static void addAllToPrevStartingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getPrevStartingInventory().clear();
        vendingMachine.getRecorder().getPrevStartingInventory().addAll(slotList);
    }

    public static void addAllToEndingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getEndingInventory().clear();
        vendingMachine.getRecorder().getEndingInventory().addAll(slotList);
    }

    public static void addAllToStartingSpecialInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getSpecialStartingInventory().clear();
        vendingMachine.getRecorder().getSpecialStartingInventory().addAll(slotList);
    }

    public static void addAllToPrevStartingSpecialInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getSpecialPrevStartingInventory().clear();
        vendingMachine.getRecorder().getSpecialPrevStartingInventory().addAll(slotList);
    }

    public static void addAllToEndingSpecialInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getSpecialEndingInventory().clear();
        vendingMachine.getRecorder().getSpecialEndingInventory().addAll(slotList);
    }

    public static ArrayList<Slot> deepCopySlotArrayList(ArrayList<Slot> original) {
        ArrayList<Slot> copy = new ArrayList<>();
        for (Slot slot : original) {
            copy.add(slot.clone());
        }
        return copy;
    }


}

