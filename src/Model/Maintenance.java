package Model;


import java.util.ArrayList;

public class Maintenance {
    public void restockItem(VendingMachine vendingMachine, int indexChoice)
    {
        vendingMachine.getStockManager().restockItems(vendingMachine,indexChoice);
    }

    public boolean isSameItemType(VendingMachine vendingMachine, String inputString) {
        return vendingMachine.getStockManager().isSameItemType(vendingMachine, inputString);
    }

    public void stockNewItems(VendingMachine vendingMachine, String newItem, int newPrice, int newCals) {
        vendingMachine.getStockManager().stockNewItems(vendingMachine,newItem,newPrice,newCals);
    }

    public void updateItemPrices(VendingMachine vendingMachine, int userChoice, int newPrice)
    {
        vendingMachine.getStockManager().updateItemPrice(vendingMachine, userChoice, newPrice);
    }

    public void collectMoney(VendingMachine vendingMachine)
    {
        vendingMachine.getMoneyManager().collectMoney();
    }

    public void replenishMoney(VendingMachine vendingMachine, int denomination, int quantity)
    {
        vendingMachine.getMoneyManager().addStoredMoney(denomination, quantity);
    }

    public void addSoldItems(VendingMachine vendingMachine, String itemType)
    {
        vendingMachine.getRecorder().addSoldItems(itemType);
    }

    public void generateSalesReport(VendingMachine vendingMachine)
    {
        vendingMachine.getRecorder().generateSalesReport(vendingMachine);
    }

    public void addAllToStartingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getStartingInventory().clear();
        vendingMachine.getRecorder().getStartingInventory().addAll(slotList);
    }
    public void addAllToPrevStartingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getPrevStartingInventory().clear();
        vendingMachine.getRecorder().getPrevStartingInventory().addAll(slotList);
    }

    public void addAllToEndingInventory(VendingMachine vendingMachine, ArrayList<Slot> slotList) {
        vendingMachine.getRecorder().getEndingInventory().clear();
        vendingMachine.getRecorder().getEndingInventory().addAll(slotList);
    }

    public ArrayList<Slot> deepCopySlotArrayList(ArrayList<Slot> original) {
        ArrayList<Slot> copy = new ArrayList<>();
        for (Slot slot : original) {
            copy.add(slot.clone());
        }
        return copy;
    }


}
