import java.util.ArrayList;

public class VendingMachineInitializer {
        public void initializeItems(VendingMachine vendingMachine) {
            int numSlots = 8;
            int numItems = 10;

            // Create item types
            Item[] itemTypes;
/*  TODO: ADD FOR SPECIAL VENDING MACHINE DIFFERENT INITIALIZE
            if (vendingMachine instanceof SpecialVendingMachine) {
                itemTypes = new Item[] {
                };
            } else {

            }
 */
            itemTypes = new Item[] {
                    new Item("Orange Slices", 15, 42),
                    new Item("Apple Slices", 20, 52),
                    new Item("Banana", 10, 96),
                    new Item("Mango", 30, 60),
                    new Item("Blueberries", 25, 57),
                    new Item("Strawberries", 35, 29),
                    new Item("Grapes", 28, 69),
                    new Item("Pineapple", 40, 50)
            };

            // Create slots and add items
            for (int i = 0; i < numSlots; i++) {
                Slot slot = new Slot(itemTypes[i].getType(), numItems);
                ArrayList<Item> itemArrayList = slot.getItemArrayList();

                for (int j = 0; j < numItems; j++) {
                    itemArrayList.add(new Item(itemTypes[i].getType(), itemTypes[i].getPrice(), itemTypes[i].getCalorie()));
                }

                vendingMachine.getSlotArrayList().add(slot);
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

