package Model;

import java.util.ArrayList;

/**
 * The TransactionManager class handles the validation and confirmation of transactions in the vending machine.
 */
public class TransactionManager {
    public static final int VALID_TRANSACTION = 0;
    public static final int INVALID_INTEGER_INPUT = 727;
    public static final int INVALID_ITEM_CHOICE = 1;
    public static final int OUT_OF_STOCK = 2;
    public static final int INSUFFICIENT_BALANCE = 3;
    public static final int INSUFFICIENT_CHANGE = 4;

    /**
     * Checks the input validity for a given item choice in the vending machine.
     *
     * @param vendingMachine The vending machine instance.
     * @param itemChoice     The user's item choice (slot index).
     * @param isSpecialSlot  A boolean indicating whether the selected slot is a special slot.
     * @return An integer representing the result of the input validity check (constants defined in this class).
     */
    public int checkInputValidity(VendingMachine vendingMachine, int itemChoice, boolean isSpecialSlot) {
        int itemPrice;
        int totalUserMoney;

        if (!isValidInteger(itemChoice)) {
            return INVALID_INTEGER_INPUT;
        }

        ArrayList<Slot> slots = isSpecialSlot ? vendingMachine.getSpecialSlots() : vendingMachine.getSlotArrayList();

        if (itemChoice >= 1 && itemChoice <= slots.size()) {
            // Check if the chosen item is available and in stock
            Slot selectedSlot = slots.get(itemChoice - 1);
            if (selectedSlot.getItemArrayList().isEmpty()) {
                return OUT_OF_STOCK;
            }

            // Get the item price and user's total balance
            itemPrice = selectedSlot.getItemArrayList().get(0).getPrice(); // Assuming all items in the slot have the same price
            totalUserMoney = vendingMachine.getUserBalance();

            if (itemPrice > totalUserMoney) {
                // If user's balance does not meet the item price requirements
                return INSUFFICIENT_BALANCE;
            }

            // Check if there's enough change in the machine
            if (!vendingMachine.getMoneyManager().canReturnChange(itemPrice)) {
                // Return user's money and clear user paid money
                vendingMachine.getMoneyManager().returnMoney(vendingMachine.getMoneyManager().getTempMoneyFromUser());
                vendingMachine.getMoneyManager().clearUserPaidMoney();
                return INSUFFICIENT_CHANGE;
            }

            // If all conditions are met, return true for a valid transaction
            return VALID_TRANSACTION;
        } else {
            return INVALID_ITEM_CHOICE;
        }
    }

    /**
     * Confirms the successful transaction by updating the vending machine with the transaction details.
     *
     * @param vendingMachine The vending machine instance.
     * @param itemChoice     The user's item choice (slot index).
     */
    public void confirmTransaction(VendingMachine vendingMachine, int itemChoice) {
        int totalUserMoney = vendingMachine.getUserBalance();
        int change = totalUserMoney - vendingMachine.getSelectedSlot(itemChoice, false).getAssignedItemPrice();
        vendingMachine.getMoneyManager().depositMoney();
        vendingMachine.getMoneyManager().returnChange(change);
    }

    /**
     * Checks if the input is a valid integer.
     *
     * @param input The input to check.
     * @return True if the input is a valid integer, otherwise false.
     */
    private boolean isValidInteger(int input) {
        try {
            Integer.parseInt(String.valueOf(input));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

