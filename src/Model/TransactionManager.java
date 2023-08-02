package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class TransactionManager {
    public static final int VALID_TRANSACTION = 0;
    public static final int INVALID_INTEGER_INPUT = 9;
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


    public boolean insertMoneyProcess(Scanner scanner, VendingMachine vendingMachine)
    {
        int quantity;
            System.out.println("[Insert Cash - Denomination]");
            System.out.println("[1] ₱100");
            System.out.println("[2] ₱50");
            System.out.println("[3] ₱20");
            System.out.println("[4] ₱10");
            System.out.println("[5] ₱5");
            System.out.println("[6] ₱1");
            System.out.println("[0] Exit");
            System.out.print("Enter your choice: ");
            int cashDenomination = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer input

            switch (cashDenomination) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.addTempPaidMoney(getDenominationValue(cashDenomination), quantity);
                    break;
                case 0:
                    System.out.println("Exiting money insertion...");
                    return false;
                default:
                    System.out.println("INVALID INPUT!");
                    break;
            }
            return true;
    }

    private static int getDenominationValue(int cashDenomination) {
        switch (cashDenomination) {
            case 1:
                return 100;
            case 2:
                return 50;
            case 3:
                return 20;
            case 4:
                return 10;
            case 5:
                return 5;
            case 6:
                return 1;
            default:
                return 0;
        }
    }


    public void confirmSpecialTransaction(ArrayList<Item> selectedItems, double totalPrice, int totalCals,
                                          double totalUserMoney, double change,
                                          ArrayList<Item> selectedFruits, ArrayList<Item> selectedLiquids,
                                          Item selectedIce, Item selectedAddOns) {
        System.out.println("[Transaction Successful]");
        System.out.println("\n*** Receipt ***");
        for (Item selectedItem : selectedItems) {
            System.out.println(selectedItem.getType() + " - ₱" + selectedItem.getPrice());
        }
        System.out.println("Total Price: ₱" + totalPrice);
        System.out.println("Total Calories: " + totalCals + " cals");
        System.out.println();
        System.out.println("Inserted Money: ₱" + totalUserMoney);
        System.out.println("CHANGE: ₱" + change);

        System.out.println("\nPreparing your custom fruit shake...");
        System.out.print("Blending Fruits: ");
        for (Item item : selectedFruits) {
            System.out.print(item.getType() + " ");
        }
        System.out.println();
        System.out.print("Pouring Liquids: ");
        for (Item liquid : selectedLiquids) {
            System.out.print(liquid.getType() + " ");
        }
        System.out.println();
        if (selectedIce != null) {
            System.out.println("Putting " + selectedIce.getType());
        }
        if (selectedAddOns != null) {
            System.out.println("Topping with " + selectedAddOns.getType());
        }
        System.out.println("Order Complete! Enjoy your customized fruit shake!");
        System.out.println("\nPress [0] to go back");
    }
}
