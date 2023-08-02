package Model;


import java.util.ArrayList;
import java.util.Scanner;

public class Maintenance {
    public static void restockItem(VendingMachine vendingMachine, int indexChoice, boolean isSpecialSlot) {
        vendingMachine.getStockManager().restockItems(vendingMachine, indexChoice, isSpecialSlot);
    }

    public static void restockProcess(Scanner scanner, VendingMachine vendingMachine, boolean isSpecialSlot) {
        vendingMachine.getStockManager().restockProcess(scanner, vendingMachine, isSpecialSlot);
    }


    public static boolean isSameItemType(VendingMachine vendingMachine, String inputString) {
        return vendingMachine.getStockManager().isSameItemType(vendingMachine, inputString);
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

    public static void generateSalesReport(VendingMachine vendingMachine) {
        vendingMachine.getRecorder().generateSalesReport(vendingMachine);
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


    public static void setPricesProcess(Scanner scanner, VendingMachine vendingMachine, ArrayList<Slot> slotsToUse, boolean isSpecialSlot) {
        boolean isValidIndex = false;
        boolean isValidPrice;

        int stockChoice;
        vendingMachine.displayAllItems(slotsToUse);
        do {
            System.out.print("Please provide the index of the item for which you would like to set the price: ");
            stockChoice = scanner.nextInt();
            scanner.nextLine();
            if (stockChoice < 1 || stockChoice > slotsToUse.size()) { // checking if input fits the range
                System.out.print("Invalid Input, please try again");
            } else
                isValidIndex = true;
        } while (!isValidIndex);

        System.out.println("Item Selected: " + vendingMachine.getSelectedItem(stockChoice - 1, isSpecialSlot).getType()); // -1 since the display shows a +1 of the indices

        int newPrice = 0;
        do {
            System.out.print("Please provide the new price of the item: ");
            try {
                newPrice = scanner.nextInt();
                scanner.nextLine();
                if (newPrice < 0) {
                    System.out.println("Invalid input. Price cannot be negative.");
                    isValidPrice = false;
                } else {
                    isValidPrice = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for price. Please enter a valid integer.");
                isValidPrice = false;
            }
        } while (!isValidPrice);
        updateItemPrices(vendingMachine, isSpecialSlot,stockChoice - 1, newPrice);

    }
}

