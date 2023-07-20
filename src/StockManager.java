public class StockManager {
    public Item dispenseSelectedItem(VendingMachine vendingMachine, int itemChoice) {
        // Retrieve the chosen item
        Item chosenItem = vendingMachine.getSelectedItem(itemChoice);
        vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().remove(0);
        // Shift the remaining items
        int itemListSize = vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().size();
        for (int i = 0; i < itemListSize - 1; i++) {
            vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().set
                    (i, vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().get(i + 1));
        }
        return chosenItem;
    }
}
