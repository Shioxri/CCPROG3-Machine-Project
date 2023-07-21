import Model.Maintenance;
import Model.VendingMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mainMenuChoice;
        do {
            System.out.println("Main Menu");
            System.out.println("[1] Create Vending Machine");
            System.out.println("[2] Exit");
            System.out.print("Enter your choice: ");
            mainMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (mainMenuChoice) {
                case 1:
                    createVendingMachineMenu(scanner);
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (mainMenuChoice != 2);

        scanner.close();
    }

    /**
     * Create Vending Machine Menu
     *
     * @param scanner for scanning user input
     */
    public static void createVendingMachineMenu(Scanner scanner) {
        int vendingMachineChoice;
        boolean isDone = false;
        do {

            System.out.println("Created a Vending Machine!");
            System.out.println("[1] Create Regular Vending Machine");
            System.out.println("[2] Create Special Vending Machine");
            System.out.println("[3] Back");
            System.out.print("Enter your choice: ");
            vendingMachineChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (vendingMachineChoice) {
                case 1:
                    VendingMachine vendingMachine = new VendingMachine(); //instantiate from the vending machine class
                    Maintenance maintenance = new Maintenance();
                    isDone = createRegularVendMachineMenu(scanner, vendingMachine, maintenance);
                    break;
                case 2:
                    System.out.println("Work in progress...");
                    break;
                case 3:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (vendingMachineChoice != 3 && !isDone);
    }

    /**
     * Menu for a regular vending machine
     *
     * @param scanner        for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     * @return boolean
     */
    public static boolean createRegularVendMachineMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        int regularVMChoice;
        do {
            System.out.println("╔═══════════════════╗");
            System.out.println("║  VENDING MACHINE  ║");
            System.out.println("╚═══════════════════╝");
            System.out.println("[1] User Menu");
            System.out.println("[2] Maintenance Menu");
            System.out.println("[3] Exit");
            System.out.println("(Exiting will discard the current vending machine)");
            System.out.print("Enter your choice: ");
            regularVMChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (regularVMChoice) {
                case 1:
                    createUserMenu(scanner, vendingMachine);
                    break;
                case 2:
                    if (checkPassword(scanner)) {
                        createMaintenanceMenu(scanner, vendingMachine, maintenance);
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (regularVMChoice != 3);
        return true;
    }

    /**
     * Menu for users
     *
     * @param scanner        for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     */
    public static void createUserMenu(Scanner scanner, VendingMachine vendingMachine) {
        int userMenuChoice;

        do {

            System.out.println("╔═══════════════════════╗");
            System.out.println("║    GREETINGS! USER    ║");
            System.out.println("╚═══════════════════════╝");
            vendingMachine.displayAllItems();
            System.out.println("========User Menu========");
            System.out.println("[1] Display Specific Item Information");
            System.out.println("[2] Buy Item");
            System.out.println("[3] Go back");
            System.out.print("Enter your choice: ");
            userMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character


            switch (userMenuChoice) {
                case 1: {
                    System.out.print("Input Slot Index of chosen item: ");
                    int chosenSlotIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    vendingMachine.displaySpecificItem(chosenSlotIndex - 1);

                    break;
                }

                case 2: {
                    insertMoney(scanner, vendingMachine);
                    break;
                }

                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (userMenuChoice != 3);


    }

    /**
     * method for choosing what type of denomination to insert
     * @param scanner for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     */
    public static void insertMoney(Scanner scanner, VendingMachine vendingMachine) {
        int quantity;
        boolean isDone = false;

        do {
            System.out.println("[Insert Cash - Denomination]");
            System.out.println("[1] 100");
            System.out.println("[2] 50");
            System.out.println("[3] 20");
            System.out.println("[4] 10");
            System.out.println("[5] 5");
            System.out.println("[6] 1");
            System.out.print("Enter your choice: ");
            int cashDenomination = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer input

            switch (cashDenomination) {
                case 1:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.addTempPaidMoney(100, quantity);
                    break;
                case 2:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.addTempPaidMoney(50, quantity);
                    break;
                case 3:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.addTempPaidMoney(20, quantity);
                    break;
                case 4:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.addTempPaidMoney(10, quantity);
                    break;
                case 5:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.addTempPaidMoney(5, quantity);
                    break;
                case 6:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.addTempPaidMoney(1, quantity);
                    break;
                default:
                    System.out.println("INVALID INPUT!");
                    break;
            }

            boolean isValidInput = false;
            do {
                System.out.print("Do you want to continue inserting money? [Y]es or [N]o: ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Y")) {
                    isValidInput = true;
                } else if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Going to the buy item menu...");
                    isValidInput = true;
                    isDone = true;
                    buyItemMenu(scanner, vendingMachine);
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            } while (!isValidInput);
        } while (!isDone);
    }



    public static void buyItemMenu(Scanner scanner, VendingMachine vendingMachine)
    {
        int choice;
        String stringChoice;
        boolean isValidInput = false;
        boolean isDone = false;
        do {
            vendingMachine.displayAvailableItems();
                System.out.print("Input Choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading the integer input
                if(vendingMachine.checkInputValidity(choice))
                {
                    vendingMachine.confirmTransaction(choice-1);
                    do {
                        if (vendingMachine.getUserBalance() < 10) {
                            try {
                                System.out.print("Press [0] to go back: ");
                                choice = scanner.nextInt();
                                scanner.nextLine();

                                if (choice == 0) {
                                    vendingMachine.getMoneyManager().clearUserPaidMoney(); // Clear the user's paid money
                                } else {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scanner.nextLine(); // Clear the input buffer
                            }
                        }
                        else
                        {
                            System.out.print("Do you want to continue buying with your remaining balance ₱"+vendingMachine.getUserBalance()+"? [Y]es or [N]o: ");
                            stringChoice = scanner.nextLine();

                            if (stringChoice.equalsIgnoreCase("Y")) {
                                isValidInput = true;
                            } else if (stringChoice.equalsIgnoreCase("N")) {
                                System.out.println("Exiting...");
                                isValidInput = true;
                                isDone = true;
                                vendingMachine.getMoneyManager().clearUserPaidMoney();
                            } else {
                                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                            }
                        }

                    } while (!isValidInput);
                }
        }while(!isDone);
    }



    /**
     * Menu for Maintenance/Admin
     *
     * @param scanner        for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     */
    public static void createMaintenanceMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {

        int maintenanceMenuChoice;


        do {
            System.out.println("╔════════════════════════╗");
            System.out.println("║    GREETINGS! ADMIN    ║");
            System.out.println("╚════════════════════════╝");
            System.out.println("========Maintenance Menu========");
            System.out.println("[1] Restock Items");
            System.out.println("[2] Stock New Items");
            System.out.println("[3] Set Item Prices");
            System.out.println("[4] Collect Money");
            System.out.println("[5] Replenish Change Money");
            System.out.println("[6] Print Machine Summary");
            System.out.println("[0] Go Back");
            System.out.print("Enter choice: ");
            maintenanceMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (maintenanceMenuChoice) {
                case 1:
                    restockItems(scanner, vendingMachine, maintenance);
                    break;
                case 2:
                    stockNewItems(scanner, vendingMachine, maintenance);
                    break;
                case 3:
                    setPriceForSelectedItemType(scanner, vendingMachine, maintenance);
                    break;
                case 4:
                    collectMachineMoney(scanner, vendingMachine, maintenance);
                    break;
                case 5:
                    replenishMachineMoney(scanner, vendingMachine, maintenance);
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Exiting Maintenance Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (maintenanceMenuChoice != 0);
    }

    /**
     * Method to ask and check a user's input for a password to enter the Admin Menu
     *
     * @param scanner for scanning user input
     */
    public static boolean checkPassword(Scanner scanner) {
        String password;
        String toTryAgain;
        boolean isCorrect = false;
        String defaultPassword = "password"; // default admin password

        do {
            System.out.print("Input admin password: ");
            password = scanner.nextLine();

            if (defaultPassword.equals(password)) {
                isCorrect = true;
                System.out.println("Password correct! Access granted.");
            } else {
                System.out.println("Wrong Password!");

                do {
                    System.out.print("Do you want to try again? [Y]es or [N]o: ");
                    toTryAgain = scanner.nextLine();

                    if (toTryAgain.equalsIgnoreCase("Y")) {
                        break; // go back to start of outer loop
                    } else if (toTryAgain.equalsIgnoreCase("N")) {
                        System.out.println("Exiting...");
                        return false; // return false if user chooses not to try again
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                } while (!toTryAgain.equalsIgnoreCase("Y") && !toTryAgain.equalsIgnoreCase("N"));
            }
        } while (!isCorrect); // while password isn't correct

        return true; // return true if the password is correct or the user chooses to try again
    }


    public static void restockItems(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        int indexChoice;
        boolean isDone = false;
        boolean isCorrect = false;
        do {
            for (int i = 0; i < vendingMachine.getSlotArrayList().size(); i++) {
                System.out.println("[" + (i + 1) + "] " + vendingMachine.getSelectedItem(i).getType()
                        +" -- -- Stock: "+vendingMachine.getSelectedSlot(i).getItemStock());
            }
            System.out.print("Enter the index of the item to be restocked: ");
            indexChoice = scanner.nextInt();
            scanner.nextLine();
            maintenance.restockItem(vendingMachine, indexChoice-1);

            do {
                System.out.print("Do you want to continue restocking items? [Y]es or [N]o: ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Y")) {
                    isCorrect = true;
                } else if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Going back to maintenance menu...");
                    isCorrect = true;
                    isDone = true;
                } else {
                    System.out.println("Invalid input. Please only enter 'Y' or 'N'.");
                }
            }while(!isCorrect);

        } while (!isDone);
    }

    public static void stockNewItems(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        boolean isDone = false;
        boolean isCorrect = false;
        do {
            System.out.println("What type of item would you like to add to the vending machine?");
            String newItem = scanner.nextLine();

            if (maintenance.isSameItemType(vendingMachine, newItem)) {
                System.out.println("An item of the same type already exists. Please enter a different item.");
                return;
            }
            else {
                int newPrice;
                while (true) {
                    System.out.println("Please enter its price:"); // Set Price of the inputted item
                    try {
                        newPrice = scanner.nextInt();
                        scanner.nextLine();
                        break; // Break out of the loop if valid input is received
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for price. Please enter a valid integer.");
                        scanner.nextLine();
                    }
                }

                int newCals;
                while (true) {
                    System.out.println("Please enter its calorie count:"); // Set the calories of the inputted item
                    try {
                        newCals = scanner.nextInt();
                        scanner.nextLine();
                        break; // Break out of the loop if valid input is received
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for calorie count. Please enter a valid integer.");
                        scanner.nextLine();
                    }
                }

                maintenance.stockNewItems(vendingMachine, newItem, newPrice, newCals);
                System.out.println("Successfully stocked up on new item: " + newItem);
            }

            do {
                System.out.print("Do you want to continue adding items? [Y]es or [N]o: ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Y")) {
                    isCorrect = true;
                } else if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Going back to maintenance menu...");
                    isCorrect = true;
                    isDone = true;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }while(!isCorrect);

        } while (!isDone);
    }

    /**
     * Method for manually setting the price of each item (by choosing which one)
     * @param scanner for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     */
    public static void setPriceForSelectedItemType(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        boolean isDone = false;
        boolean isValidIndex = false;
        boolean isValidPrice;
        boolean isCorrect = false;
        int stockChoice;
        do {
            vendingMachine.displayAllItems();
            do{
                System.out.print("Please provide the index of the item for which you would like to set the price: ");
                stockChoice = scanner.nextInt();
                scanner.nextLine();
                if (stockChoice < 1 || stockChoice > vendingMachine.getSlotArrayList().size()) { // checking if input fits the range
                    System.out.print("Invalid Input, please try again");
                }
                else
                    isValidIndex = true;
            }while(!isValidIndex);

            System.out.println("Item Selected: " + vendingMachine.getSelectedItem(stockChoice-1).getType()); // -1 since the display shows a +1 of the indices

            int newPrice=0;
            do {
                System.out.print("Please provide the new price of the item: ");
                try {
                    newPrice = scanner.nextInt();
                    scanner.nextLine();
                    if (newPrice < 0) {
                        System.out.println("Invalid input. Price cannot be negative.");
                        isValidPrice = false;
                    } else {
                        isValidPrice = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for price. Please enter a valid integer.");
                    isValidPrice = false;
                }
            } while (!isValidPrice);


            maintenance.updateItemPrices(vendingMachine,stockChoice-1, newPrice);

            do {
                System.out.print("Do you want to continue setting prices for items? [Y]es or [N]o: ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("Y")) {
                    isCorrect = true;

                } else if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Going back to maintenance menu...");
                    isCorrect = true;
                    isDone = true;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }while(!isCorrect);

        } while (!isDone);

    }

    public static void collectMachineMoney(Scanner scanner,VendingMachine vendingMachine, Maintenance maintenance)
    {
        boolean isDone = false;
        int totalMoneyGenerated = vendingMachine.getMoneyManager().getTotalStoredMoney();
        maintenance.collectMoney(vendingMachine); // empties the denomination array list of the machine and sends it to the admins
        System.out.println("Successfully collected ₱"+totalMoneyGenerated+" from the vending machine!");
        do {
            System.out.println("Enter [0] to go back: ");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("Exiting...");
                isDone = true;
            }
        } while (!isDone);
    }

    public static void replenishMachineMoney(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        boolean isDone = false;
        int denomination, quantity;

        while (true) {
            try {
                System.out.print("Enter your denomination: ");
                denomination = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (denomination <= 0) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                    continue;
                }
                break; // Break out of the loop if valid input is received
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        while (true) {
            try {
                System.out.print("Enter quantity: ");
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (quantity <= 0) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                    continue;
                }
                break; // Break out of the loop if valid input is received
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        maintenance.replenishMoney(vendingMachine, denomination, quantity); // adds the denomination x (quantity) amount of times
        System.out.println("Successfully replenished ₱" + (denomination * quantity) + " in denominations of ₱" + denomination + ".");

        do {
            System.out.println("Enter [0] to go back: ");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("Exiting...");
                isDone = true;
            }
        } while (!isDone);
    }

}