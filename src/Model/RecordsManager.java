package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class RecordsManager {

    private HashMap<String, Integer> itemsQuantities;
    private HashMap<String, Integer> itemsPrices;

    private ArrayList<Slot> startingInventory;
    private ArrayList<Slot> prevStartingInventory;
    private ArrayList<Slot> endingInventory;
    private ArrayList<Slot> specialStartingInventory;
    private ArrayList<Slot> specialPrevStartingInventory;
    private ArrayList<Slot> specialEndingInventory;

    public RecordsManager() {
        itemsQuantities = new HashMap<>();
        itemsPrices = new HashMap<>();
        startingInventory = new ArrayList<>();
        endingInventory = new ArrayList<>();
        prevStartingInventory = new ArrayList<>();
        specialStartingInventory = new ArrayList<>();
        specialEndingInventory = new ArrayList<>();
        specialPrevStartingInventory = new ArrayList<>();
  }


    public void addSoldItems(String itemType) {
        if (itemsQuantities.containsKey(itemType)) {
            int quantitySold = itemsQuantities.get(itemType);
            itemsQuantities.put(itemType, quantitySold + 1);
        } else {
            itemsQuantities.put(itemType, 1);
        }
    }


    public void generateSalesReport(VendingMachine vendingMachine) {
        for (Slot slot : vendingMachine.getSlotArrayList()) {
            String itemTypeKey = slot.getAssignedItemType();

            if (itemsQuantities.containsKey(itemTypeKey)) {
                int itemPrice = itemsQuantities.get(itemTypeKey) * slot.getItemArrayList().get(0).getPrice();
                itemsPrices.put(itemTypeKey, itemPrice);
            }
        }

        if(vendingMachine instanceof SpecialVendingMachine)
        {
            for (Slot slot : vendingMachine.getSpecialSlots()) {
                String itemTypeKey = slot.getAssignedItemType();

                if (itemsQuantities.containsKey(itemTypeKey)) {
                    int itemPrice = itemsQuantities.get(itemTypeKey) * slot.getItemArrayList().get(0).getPrice();
                    itemsPrices.put(itemTypeKey, itemPrice);
                }
            }
        }

        if (itemsQuantities.isEmpty()) {
            System.out.println("No sales recorded.");
        } else {
            System.out.println("Sales Report:");

            int totalSalesAmount = 0;

            for (String itemType : itemsQuantities.keySet()) {
                int quantitySold = itemsQuantities.getOrDefault(itemType, 0);
                int totalPrice = itemsPrices.getOrDefault(itemType, 0);
                totalSalesAmount += totalPrice;

                System.out.println(itemType + " - # of Items Sold: " + quantitySold + " - Sales: ₱" + totalPrice);
            }

            System.out.println("Total Sales: ₱" + totalSalesAmount);
            if(vendingMachine.getAdminCollectedMoney()>0)
            {
                System.out.println("Total Collected Money: ₱"+vendingMachine.getAdminCollectedMoney());
            }
            else
            {
                System.out.println("Admin has not claimed any money from the machine yet.");
            }
        }
    }

    public String getSalesReport(VendingMachine vendingMachine) {
        StringBuilder reportBuilder = new StringBuilder();

        HashMap<String, Integer> itemsQuantities = new HashMap<>();
        HashMap<String, Integer> itemsPrices = new HashMap<>();

        for (Slot slot : vendingMachine.getSlotArrayList()) {
            String itemTypeKey = slot.getAssignedItemType();

            if (itemsQuantities.containsKey(itemTypeKey)) {
                int itemPrice = itemsQuantities.get(itemTypeKey) * slot.getItemArrayList().get(0).getPrice();
                itemsPrices.put(itemTypeKey, itemPrice);
            }
        }

        if (vendingMachine instanceof SpecialVendingMachine) {
            for (Slot slot : vendingMachine.getSpecialSlots()) {
                String itemTypeKey = slot.getAssignedItemType();

                if (itemsQuantities.containsKey(itemTypeKey)) {
                    int itemPrice = itemsQuantities.get(itemTypeKey) * slot.getItemArrayList().get(0).getPrice();
                    itemsPrices.put(itemTypeKey, itemPrice);
                }
            }
        }

        if (itemsQuantities.isEmpty()) {
            reportBuilder.append("No sales recorded.");
        } else {
            reportBuilder.append("Sales Report:\n");

            int totalSalesAmount = 0;

            for (String itemType : itemsQuantities.keySet()) {
                int quantitySold = itemsQuantities.getOrDefault(itemType, 0);
                int totalPrice = itemsPrices.getOrDefault(itemType, 0);
                totalSalesAmount += totalPrice;

                reportBuilder.append(itemType)
                        .append(" - # of Items Sold: ")
                        .append(quantitySold)
                        .append(" - Sales: ₱")
                        .append(totalPrice)
                        .append("\n");
            }

            reportBuilder.append("Total Sales: ₱").append(totalSalesAmount);

            if (vendingMachine.getAdminCollectedMoney() > 0) {
                reportBuilder.append("\nTotal Collected Money: ₱").append(vendingMachine.getAdminCollectedMoney());
            } else {
                reportBuilder.append("\nAdmin has not claimed any money from the machine yet.");
            }
        }

        return reportBuilder.toString();
    }

    public ArrayList<Slot> getStartingInventory() {
        return startingInventory;
    }

    public ArrayList<Slot> getPrevStartingInventory() {
        return prevStartingInventory;
    }

    public ArrayList<Slot> getEndingInventory() {
        return endingInventory;
    }

    public HashMap<String, Integer> getPricesList() {
        return itemsPrices;
    }

    public void clearItemsQuantities() {
        itemsQuantities.clear();
    }

    public HashMap<String, Integer> getItemsQuantities() {
        return itemsQuantities;
    }
    public void clearItemsPrices() {
        itemsPrices.clear();
    }

    public ArrayList<Slot> getSpecialStartingInventory() {
        return specialStartingInventory;
    }

    public ArrayList<Slot> getSpecialPrevStartingInventory() {
        return specialPrevStartingInventory;
    }

    public ArrayList<Slot> getSpecialEndingInventory() {
        return specialEndingInventory;
    }
}
