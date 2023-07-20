public class TransactionManager {
    public void checkInputValidity(VendingMachine vendingMachine, int itemChoice)
    {
        int itemPrice = vendingMachine.getSelectedItem(itemChoice-1).getPrice();
        if (itemChoice == 0) { // If user cancels the transaction
            // Return user's money and remove it from storedMoney
            vendingMachine.getMoneyManager().returnMoney(vendingMachine.getMoneyManager().getTempMoneyFromUser());
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            System.out.println("Going back to the user menu...");
        }

        if (itemChoice < 0 || itemChoice > vendingMachine.getSlotArrayList().size()) {
            System.out.println("Invalid input. Please try again.");
        }

        if (itemChoice > 0 && itemChoice <= vendingMachine.getSlotArrayList().size()) {
            if (itemPrice > totalUserMoney) {
                //if user's balance does not meet the item price requirements
                System.out.println("Chosen item is not available due to insufficient balance.");
                continue;
            } else if (vendingMachine.getSlotArrayList().get(itemChoice - 1).getItemArrayList().size() <= 0) {
                //if chosen item is out of stock
                System.out.println("Chosen item is not available due to being out of stock.");
                continue;
            }

        }
        // Check if there's enough change in the machine
        if (!vendingMachine.getMoney().returnChange(vendingMachine.getItemDetails().getPrice(itemChoice - 1))) {
            System.out.println("Insufficient change in the machine. Transaction canceled.");
            // Return user's money and remove it from storedMoney
            vendingMachine.getMoney().returnMoney(userMoney);
            continue; // Go back to the start of the buyItem loop
        }
        else
        {
            System.out.println("[Transaction Successful]");
            System.out.println("SELECTED ITEM: " + vendingMachine.getSlotArrayList().get(itemChoice - 1).getItemType());
            System.out.println("CHANGE: ₱" + (totalUserMoney - vendingMachine.getItemDetails().getPrice(itemChoice - 1)));
            vendingMachine.addItemToRecord(vendingMachine.dispenseSelectedItem(itemChoice - 1));
            vendingMachine.getMoney().clearUserPaidMoney();
        }
    }

}
