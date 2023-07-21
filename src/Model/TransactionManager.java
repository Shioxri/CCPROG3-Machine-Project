package Model;

import Model.Item;
import Model.Slot;
import Model.VendingMachine;

public class TransactionManager {
    public boolean checkInputValidity(VendingMachine vendingMachine, int itemChoice) {
        int itemPrice;
        int totalUserMoney;

        if (!isValidInteger(itemChoice)) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return false;
        }

        if (itemChoice == 0) { // If user cancels the transaction
            // Return user's money and clear user paid money
            vendingMachine.getMoneyManager().returnMoney(vendingMachine.getMoneyManager().getTempMoneyFromUser());
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            System.out.println("Going back to the user menu...");
            return false;
        } else if (itemChoice > 0 && itemChoice <= vendingMachine.getSlotArrayList().size()) {
            // Check if the chosen item is available and in stock
            Slot selectedSlot = vendingMachine.getSlotArrayList().get(itemChoice - 1);
            if (selectedSlot.getItemArrayList().isEmpty()) {
                System.out.println("Chosen item is not available due to being out of stock.");
                return false;
            }

            // Get the item price and user's total balance
            itemPrice = selectedSlot.getItemArrayList().get(0).getPrice(); // Assuming all items in the slot have the same price
            totalUserMoney = vendingMachine.getUserBalance();

            if (itemPrice > totalUserMoney) {
                // If user's balance does not meet the item price requirements
                System.out.println("Chosen item is not available due to insufficient balance.");
                return false;
            }

            // Check if there's enough change in the machine
            if (!vendingMachine.getMoneyManager().canReturnChange(itemPrice)) {
                System.out.println("Insufficient change in the machine. Transaction canceled.");
                // Return user's money and clear user paid money
                vendingMachine.getMoneyManager().returnMoney(vendingMachine.getMoneyManager().getTempMoneyFromUser());
                vendingMachine.getMoneyManager().clearUserPaidMoney();
                return false;
            }

            // If all conditions are met, return true for a valid transaction
            return true;
        } else {
            System.out.println("Invalid input. Please try again.");
            return false;
        }
    }


    public void confirmTransaction(VendingMachine vendingMachine, int itemChoice)
    {
        int totalUserMoney = vendingMachine.getUserBalance();

        System.out.println("[Transaction Successful]");
        System.out.println("SELECTED ITEM: " + vendingMachine.getSelectedSlot(itemChoice).getAssignedItemType());
        System.out.println("CHANGE: ₱" + (totalUserMoney - vendingMachine.getSelectedItem(itemChoice).getPrice()));
        vendingMachine.getMoneyManager().clearUserPaidMoney();
        //TODO: THIS

        //vendingMachine.addItemToRecord(vendingMachine.dispenseSelectedItem(itemChoice - 1));
        Item newItem = vendingMachine.dispenseSelectedItem(itemChoice);
        System.out.println(newItem.getType()+" <- Dispensed");
        int newSize = vendingMachine.getSelectedSlot(itemChoice).getItemArrayList().size();
        vendingMachine.getSelectedSlot(itemChoice).setItemStock(newSize);


    }

    private boolean isValidInteger(int input) {
        try {
            Integer.parseInt(String.valueOf(input));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
