package Model;

import java.util.Scanner;

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
        int change = totalUserMoney - vendingMachine.getSelectedItem(itemChoice, false).getPrice();

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
}
