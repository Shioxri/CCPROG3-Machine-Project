package Model;

import java.util.ArrayList;

public class TransactionManager {
    public static final int VALID_TRANSACTION = 0;
    public static final int INVALID_INTEGER_INPUT = 727;
    public static final int INVALID_ITEM_CHOICE = 1;
    public static final int OUT_OF_STOCK = 2;
    public static final int INSUFFICIENT_BALANCE = 3;
    public static final int INSUFFICIENT_CHANGE = 4;

    public int checkInputValidity(VendingMachine vendingMachine, int itemChoice, boolean isSpecialSlot) {
        int itemPrice;
        int totalUserMoney;

        if (!isValidInteger(itemChoice)) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return INVALID_INTEGER_INPUT;
        }

        ArrayList<Slot> slots = isSpecialSlot ? vendingMachine.getSpecialSlots() : vendingMachine.getSlotArrayList();

        if (itemChoice >= 1 && itemChoice <= slots.size()) {
            // Check if the chosen item is available and in stock
            Slot selectedSlot = slots.get(itemChoice - 1);
            if (selectedSlot.getItemArrayList().isEmpty()) {
                System.out.println("Chosen item is not available due to being out of stock.");
                return OUT_OF_STOCK;
            }

            // Get the item price and user's total balance
            itemPrice = selectedSlot.getItemArrayList().get(0).getPrice(); // Assuming all items in the slot have the same price
            totalUserMoney = vendingMachine.getUserBalance();

            if (itemPrice > totalUserMoney) {
                // If user's balance does not meet the item price requirements
                System.out.println("Chosen item is not available due to insufficient balance.");
                return INSUFFICIENT_BALANCE;
            }

            // Check if there's enough change in the machine
            if (!vendingMachine.getMoneyManager().canReturnChange(itemPrice)) {
                System.out.println("Insufficient change in the machine. Transaction canceled.");
                // Return user's money and clear user paid money
                vendingMachine.getMoneyManager().returnMoney(vendingMachine.getMoneyManager().getTempMoneyFromUser());
                vendingMachine.getMoneyManager().clearUserPaidMoney();
                return INSUFFICIENT_CHANGE;
            }

            // If all conditions are met, return true for a valid transaction
            return VALID_TRANSACTION;
        } else {
            System.out.println("Invalid input. Please try again.");
            return INVALID_ITEM_CHOICE;
        }
    }


    public void confirmTransaction(VendingMachine vendingMachine, int itemChoice)
    {
        int totalUserMoney = vendingMachine.getUserBalance();
        int change = totalUserMoney - vendingMachine.getSelectedSlot(itemChoice, false).getAssignedItemPrice();

        System.out.println("[Transaction Successful]");
        System.out.println("SELECTED ITEM: " + vendingMachine.getSelectedSlot(itemChoice, false).getAssignedItemType());
        System.out.println("CHANGE: ₱" + change);
        vendingMachine.getMoneyManager().depositMoney();
        vendingMachine.getMoneyManager().returnChange(change);
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
