import java.util.ArrayList;

public class DisplayManager {
    public void displayAllItems(VendingMachine vendingMachine) {
        ArrayList<Slot> slots = vendingMachine.getSlotArrayList();

        int maxItemNameLength = 0;
        for (Slot slot : slots) {
            for (Item item : slot.getItemArrayList()) {
                int itemNameLength = item.getType().length();
                if (itemNameLength > maxItemNameLength) {
                    maxItemNameLength = itemNameLength;
                }
            }
        }

        System.out.println("-----------------------------------------");
        System.out.println("|   Item  |  Index | Stock |");
        System.out.println("-----------------------------------------");

        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            String slotType = slot.getAssignedItemType();
            int stock = slot.getItemStock();

            String itemName = slotType + " ".repeat(maxItemNameLength - slotType.length());
            System.out.printf("| %-7s| %-6d| %-6d|\n", itemName, i + 1, stock);
        }

        System.out.println("-----------------------------------------");
    }
}
