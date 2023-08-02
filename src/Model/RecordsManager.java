package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The RecordsManager class keeps track of sales records and inventory information for the vending machine.
 */
public class RecordsManager {

    // HashMaps to store the quantity and prices of sold items
    private HashMap<String, Integer> itemsQuantities;
    private HashMap<String, Integer> itemsPrices;

    // Lists to store starting, previous starting, and ending inventories for regular and special slots
    private ArrayList<Slot> startingInventory;
    private ArrayList<Slot> prevStartingInventory;
    private ArrayList<Slot> endingInventory;
    private ArrayList<Slot> specialStartingInventory;
    private ArrayList<Slot> specialPrevStartingInventory;
    private ArrayList<Slot> specialEndingInventory;

    /**
     * Constructs a new RecordsManager object with empty data.
     */
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

    /**
     * Records the sale of an item by incrementing its quantity in the itemsQuantities HashMap.
     *
     * @param itemType The type of item sold.
     */
    public void addSoldItems(String itemType) {
        if (itemsQuantities.containsKey(itemType)) {
            int quantitySold = itemsQuantities.get(itemType);
            itemsQuantities.put(itemType, quantitySold + 1);
        } else {
            itemsQuantities.put(itemType, 1);
        }
    }

    /**
     * Generates a sales report based on the vending machine's sales and inventory data.
     *
     * @param vendingMachine The VendingMachine instance for which to generate the sales report.
     * @return The sales report as a string.
     */
    public String getSalesReport(VendingMachine vendingMachine) {
        StringBuilder reportBuilder = new StringBuilder();

        // Calculate total sales for each item type
        for (Slot slot : vendingMachine.getSlotArrayList()) {
            String itemTypeKey = slot.getAssignedItemType();
            if (itemsQuantities.containsKey(itemTypeKey)) {
                int itemPrice = itemsQuantities.get(itemTypeKey) * slot.getAssignedItemPrice();
                itemsPrices.put(itemTypeKey, itemPrice);
            }
        }

        // Calculate total sales for special items if vendingMachine is a SpecialVendingMachine
        if (vendingMachine instanceof SpecialVendingMachine) {
            for (Slot slot : vendingMachine.getSpecialSlots()) {
                String itemTypeKey = slot.getAssignedItemType();
                if (itemsQuantities.containsKey(itemTypeKey)) {
                    int itemPrice = itemsQuantities.get(itemTypeKey) * slot.getAssignedItemPrice();
                    itemsPrices.put(itemTypeKey, itemPrice);
                }
            }
        }

        // Prepare and build the sales report
        if (itemsQuantities.isEmpty()) {
            reportBuilder.append("No sales recorded.");
        } else {
            reportBuilder.append("Sales Report:\n");

            int totalSalesAmount = 0;

            // Loop through each item type and calculate the sales quantity and total price
            for (String itemType : itemsQuantities.keySet()) {
                int quantitySold = itemsQuantities.getOrDefault(itemType, 0);
                int totalPrice = itemsPrices.getOrDefault(itemType, 0);
                totalSalesAmount += totalPrice;

                reportBuilder.append(itemType).append(":\n")
                        .append(quantitySold).append(" Item/s Sold\n")
                        .append("Sales: ₱").append(totalPrice).append("\n\n");
            }

            reportBuilder.append("Total Sales: ₱").append(totalSalesAmount);

            // Add admin collected money information to the report
            if (vendingMachine.getAdminCollectedMoney() > 0) {
                reportBuilder.append("\nTotal Collected Money: ₱").append(vendingMachine.getAdminCollectedMoney());
            } else {
                reportBuilder.append("\nAdmin has not claimed any money from the machine yet.");
            }

            // Add starting and ending inventory information to the report
            reportBuilder.append("\n\nStarting Inventory since previous restocking: ");
            for (Slot slot : prevStartingInventory) {
                reportBuilder.append("\n")
                        .append(slot.getAssignedItemType()).append(": [Stock: ").append(slot.getItemStock()).append("]");
            }
            if (vendingMachine instanceof SpecialVendingMachine) {
                for (Slot slot : specialPrevStartingInventory) {
                    reportBuilder.append("\n")
                            .append(slot.getAssignedItemType()).append(": [Stock: ").append(slot.getItemStock()).append("]");
                }
            }

            // Add ending inventory information to the report if available
            if (vendingMachine.getEndingInventory().isEmpty() &&
                    (vendingMachine instanceof SpecialVendingMachine && vendingMachine.getSpecialEndingInventory().isEmpty())) {
                reportBuilder.append("\n\nEnding Inventory is not available. No previous restocking recorded.");
            } else {
                reportBuilder.append("\n\nEnding Inventory since previous restocking: ");
                for (Slot slot : endingInventory) {
                    reportBuilder.append("\n")
                            .append(slot.getAssignedItemType()).append(": [Stock: ").append(slot.getItemStock()).append("]");
                }
                if (vendingMachine instanceof SpecialVendingMachine) {
                    for (Slot slot : specialEndingInventory) {
                        reportBuilder.append("\n")
                                .append(slot.getAssignedItemType()).append(": [Stock: ").append(slot.getItemStock()).append("]");
                    }
                }
            }
        }
        return reportBuilder.toString();
    }

    // Getters for the inventory lists
    public ArrayList<Slot> getStartingInventory() {
        return startingInventory;
    }

    public ArrayList<Slot> getPrevStartingInventory() {
        return prevStartingInventory;
    }

    public ArrayList<Slot> getEndingInventory() {
        return endingInventory;
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
