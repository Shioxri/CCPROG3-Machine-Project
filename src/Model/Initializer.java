package Model;


import java.util.ArrayList;

public class Initializer {

    public void initializeItems(VendingMachine vendingMachine) {
        int numSlots = 8;
        int numItems = 10;

        // Create item types
        ArrayList<Item> itemTypes = new ArrayList<>();
        itemTypes.add(new Item("Orange Slices", 15, 42));
        itemTypes.add(new Item("Apple Slices", 20, 52));
        itemTypes.add(new Item("Banana", 10, 96));
        itemTypes.add(new Item("Mango", 30, 60));
        itemTypes.add(new Item("Blueberries", 25, 57));
        itemTypes.add(new Item("Strawberries", 35, 29));
        itemTypes.add(new Item("Grapes", 28, 69));
        itemTypes.add(new Item("Pineapple", 40, 50));

        // Create slots and add items
        for (int i = 0; i < numSlots; i++) {
            Slot slot = new Slot(itemTypes.get(i).getType(), numItems);
            ArrayList<Item> itemArrayList = slot.getItemArrayList();

            for (int j = 0; j < numItems; j++) {
                Item itemType = itemTypes.get(i);
                itemArrayList.add(new Item(itemType.getType(), itemType.getPrice(), itemType.getCalorie()));
            }

            vendingMachine.getSlotArrayList().add(slot);
        }

        // If the vending machine is a SpecialVendingMachine, add additional items for fruit shakes
        if (vendingMachine instanceof SpecialVendingMachine) {
            itemTypes.add(new Item("Water", 5, 0));
            itemTypes.add(new Item("Ice Shavings", 8, 0));
            itemTypes.add(new Item("Ice Cubes", 2, 0));
            itemTypes.add(new Item("Sugar Water", 10, 100));
            itemTypes.add(new Item("Regular Milk", 25, 120));
            itemTypes.add(new Item("Almond Milk", 30, 80));
            itemTypes.add(new Item("Soy Milk", 28, 90));
            itemTypes.add(new Item("Honey", 15, 60));

            for (int i = 0; i < numSlots; i++) {
                Slot slot = new Slot(itemTypes.get(i + numSlots).getType(), numItems);
                ArrayList<Item> itemArrayList = slot.getItemArrayList();

                for (int j = 0; j < numItems; j++) {
                    Item itemType = itemTypes.get(i + numSlots);
                    itemArrayList.add(new Item(itemType.getType(), itemType.getPrice(), itemType.getCalorie()));
                }

                vendingMachine.getSpecialSlots().add(slot);
            }
        }
    }
        public void initializeMoney(VendingMachine vendingMachine){
            vendingMachine.addStoredMoney(100, 5);
            vendingMachine.addStoredMoney(50, 10);
            vendingMachine.addStoredMoney(20, 20);
            vendingMachine.addStoredMoney(10, 50);
            vendingMachine.addStoredMoney(5, 70);
            vendingMachine.addStoredMoney(1, 100);

        }
    }

