package Model;

import java.util.HashMap;

public class RecordsManager {

    private HashMap<String, Integer> itemsQuantities;
    private HashMap<String, Integer> itemsPrices;

    public RecordsManager() {
        itemsQuantities = new HashMap<>();
        itemsPrices = new HashMap<>();
    }


    public void addSoldItems(String itemType) {
        if (itemsQuantities.containsKey(itemType)) {
            int quantitySold = itemsQuantities.get(itemType);
            itemsQuantities.put(itemType, quantitySold + 1);
        } else {
            itemsQuantities.put(itemType, 1);
        }
    }

    public HashMap<String, Integer> getItemsQuantities() {
        return itemsQuantities;
    }


    public void generateSalesReport(VendingMachine vendingMachine) {
        for (Slot slot : vendingMachine.getSlotArrayList()) {
            String itemTypeKey = slot.getAssignedItemType();

            if (itemsQuantities.containsKey(itemTypeKey)) {
                int itemPrice = itemsQuantities.get(itemTypeKey) * slot.getItemArrayList().get(0).getPrice();
                itemsPrices.put(itemTypeKey, itemPrice);

            }
        }
        System.out.println("Sales Report:");

        for (String itemType : itemsQuantities.keySet()) {
            int quantitySold = itemsQuantities.getOrDefault(itemType, 0);
            int totalPrice = itemsPrices.getOrDefault(itemType, 0);

            System.out.println(itemType + " - Sold: " + quantitySold + " - Sales: ₱" + totalPrice);
        }
    }

    public HashMap<String, Integer> getPricesList() {
        return itemsPrices;
    }
}
