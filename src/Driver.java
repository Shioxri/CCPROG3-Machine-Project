import Model.*;

import java.util.ArrayList;
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
        VendingMachine vendingMachine;
        Maintenance maintenance;
        ArrayList<Slot> startingInventoryCopy;
        ArrayList<Slot> prevStartingInventoryCopy;
        do {
            System.out.println("[1] Create Regular Vending Machine");
            System.out.println("[2] Create Special Vending Machine");
            System.out.println("[0] Back");
            System.out.print("Enter your choice: ");
            vendingMachineChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (vendingMachineChoice) {
                case 1:
                    vendingMachine = new VendingMachine(); //instantiate from the vending machine class
                    maintenance = new Maintenance();
                    startingInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
                    prevStartingInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());

                    maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
                    maintenance.addAllToPrevStartingInventory(vendingMachine, prevStartingInventoryCopy);

                    isDone = createVendingMachineMenu(scanner, vendingMachine, maintenance);
                    break;
                case 2:
                    vendingMachine = new SpecialVendingMachine(); //instantiate from the vending machine class
                    maintenance = new Maintenance();

                    startingInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
                    prevStartingInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
                    maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
                    maintenance.addAllToPrevStartingInventory(vendingMachine, prevStartingInventoryCopy);

                    ArrayList<Slot> startingSpecialInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
                    ArrayList<Slot> prevStartingSpecialInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
                    maintenance.addAllToStartingSpecialInventory(vendingMachine, startingSpecialInventoryCopy);
                    maintenance.addAllToPrevStartingSpecialInventory(vendingMachine, prevStartingSpecialInventoryCopy);

                    isDone = createVendingMachineMenu(scanner, vendingMachine, maintenance);
                    System.out.println("Work in progress...");
                    break;
                case 0:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (vendingMachineChoice != 0 && !isDone);
    }

    /**
     * Menu for a regular vending machine
     *
     * @param scanner        for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     * @return boolean
     */
    public static boolean createVendingMachineMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        int regularVMChoice;
        do {
            System.out.println("╔═══════════════════╗");
            System.out.println("║  VENDING MACHINE  ║");
            System.out.println("╚═══════════════════╝");
            System.out.println("[1] User Menu");
            System.out.println("[2] Maintenance Menu");
            System.out.println("[0] Exit");
            System.out.println("(Exiting will discard the current vending machine)");
            System.out.print("Enter your choice: ");
            regularVMChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (regularVMChoice) {
                case 1:
                    createUserMenu(scanner, vendingMachine, maintenance);
                    break;
                case 2:
                    if (checkPassword(scanner)) {
                        createMaintenanceMenu(scanner, vendingMachine, maintenance);
                    } else {
                        continue;
                    }
                    break;
                case 0:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (regularVMChoice != 0);
        return true;
    }

    /**
     * Menu for users
     *
     * @param scanner        for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     */
    public static void createUserMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        int userMenuChoice;

        do {
            System.out.println("╔═══════════════════════╗");
            System.out.println("║    GREETINGS! USER    ║");
            System.out.println("╚═══════════════════════╝");
            vendingMachine.displayAllItems(vendingMachine.getSlotArrayList());
            System.out.println("========User Menu========");
            System.out.println("[1] Display Specific Item Information");
            System.out.println("[2] Buy Item");

            // Check if vendingMachine is an instance of SpecialVendingMachine
            if (vendingMachine instanceof SpecialVendingMachine) {
                System.out.println("[3] Buy Customized Item (Fruit Shake)"); // Include case specific to SpecialVendingMachine
            }

            System.out.println("[0] Go back");
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
                    insertMoneyMenu(scanner, vendingMachine, maintenance);
                    break;
                }

                case 3:
                    // Handle the case specific to SpecialVendingMachine
                    if (vendingMachine instanceof SpecialVendingMachine) {
                        // Call the method or perform actions specific to SpecialVendingMachine
                        buyCustomItemMenu(scanner, vendingMachine, maintenance);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (userMenuChoice != 0);
    }

    /**
     * method for choosing what type of denomination to insert
     * @param scanner for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     */
    public static void insertMoneyMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        boolean isDone = false;
        boolean isValidInput = false;

        do {
            if (vendingMachine.insertMoney(scanner)) {
                do {
                    System.out.print("Do you want to continue inserting money? [Y]es or [N]o: ");
                    String choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase("Y")) {
                        isValidInput = true;
                    } else if (choice.equalsIgnoreCase("N")) {
                        System.out.println("Going to the buy item menu...");
                        isValidInput = true;
                        isDone = true;
                        buyItemMenu(scanner, vendingMachine, maintenance);
                    } else {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } while (!isValidInput);
            }
        }while(!isDone);
    }



    public static void buyItemMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance)
    {
        int choice;
        String stringChoice;
        boolean isValidInput = false;
        boolean isDone = false;
        do {
            vendingMachine.displayAvailableItems(false);
                System.out.print("Input Choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading the integer input
                if(vendingMachine.checkInputValidity(choice))
                {
                    vendingMachine.confirmTransaction(choice-1);
                    Item dispensedItem = vendingMachine.dispenseSelectedItem(choice-1, false);
                    System.out.println(dispensedItem.getType()+" <- Dispensed (1) Item");
                    maintenance.addSoldItems(vendingMachine, dispensedItem.getType());


                    do {
                        if (vendingMachine.getUserBalance() < 10) {
                            try {
                                System.out.print("Press [0] to go back: ");
                                choice = scanner.nextInt();
                                scanner.nextLine();

                                if (choice == 0) {
                                    vendingMachine.getMoneyManager().clearUserPaidMoney(); // Clear the user's paid money
                                    System.out.println("Exiting...");
                                    isValidInput = true;
                                    isDone = true;

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
                else
                    isDone=true;
        }while(!isDone);
    }
//TODO MAKE SPECIAL SLOT VERSIONS OF DISPLAY AVAILABLE/ALL ITEMS AND DISPENSE AND INVENTORY AND RECORD
    public static void buyCustomItemMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance)
    {
        ArrayList<Slot> originalSlots = maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> originalSpecialSlots = maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        ArrayList<Item> selectedItems = new ArrayList<>();
        ArrayList<Item> selectedFruits = new ArrayList<>();
        ArrayList<Item> selectedLiquids = new ArrayList<>();
        Item selectedIce = null;
        Item selectedAddOns = null;
        int maxFruits = 2;
        boolean isDone = false;
        boolean useMilk = false;
        boolean useWater = false;
        boolean isOrderCanceled = false;

        do {
        System.out.print("How many fruits would you like to select? (maximum " + maxFruits + "): ");
        int numFruits = scanner.nextInt();
        scanner.nextLine();

            if (numFruits <= 0 || numFruits > maxFruits) {
                System.out.println("Invalid number of fruits. Please select between 1 and " + maxFruits + " fruits.");
            } else {
                while (numFruits > 0) {
                    System.out.println("[Select a fruit]");
                    int itemPrice;
                    for (int i = 0; i < vendingMachine.getSlotArrayList().size(); i++) {
                        itemPrice = vendingMachine.getSelectedItem(i,false).getPrice();
                        if (vendingMachine.getSlotArrayList().get(i).getItemStock() <= 0) {
                            System.out.println("[X] " + vendingMachine.getSelectedSlot(i, false).getAssignedItemType() + " [ OUT OF STOCK ]");
                        } else {
                                System.out.println("[" + (i + 1) + "] " + vendingMachine.getSlotArrayList().get(i).getAssignedItemType() + " ₱" + itemPrice);
                        }
                    }
                    System.out.println("[0] Exit / Cancel Transaction");
                    System.out.print("Enter the index of your choice: ");
                    int fruitChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (fruitChoice == 0) {
                        System.out.println("Order canceled.");
                        vendingMachine.restoreOriginalContents(originalSlots, originalSpecialSlots);
                        return;
                    }

                    if (fruitChoice > 0 && fruitChoice <= vendingMachine.getSlotArrayList().size()) {
                        if (vendingMachine.getSelectedSlot(fruitChoice-1, false).getItemArrayList().isEmpty()) {
                            System.out.println("Chosen item is not available due to being out of stock.");
                        }
                        else {
                            if (selectedItems.contains(vendingMachine.getSelectedItem(fruitChoice - 1, false))) {
                                System.out.println("You have already selected this fruit. Please choose another one.");
                            } else {
                                Item selectedFruit = vendingMachine.dispenseSelectedItem(fruitChoice - 1, false);
                                selectedItems.add(selectedFruit);
                                selectedFruits.add(selectedFruit);
                                numFruits--;
                            }
                        }
                    } else {
                        System.out.println("Invalid choice. Please select a valid fruit.");
                    }
                }
                isDone=true;
            }
        }while(!isDone);

        if(!isOrderCanceled)
        {
            do {
                // Second Phase: User picks whether to use milk or water or both
                System.out.print("Do you want to user water [1], milk [2], or both [3]? ");
                System.out.print("([0] to cancel the order.): ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                isDone = true;
                switch (choice) {
                    case 1:
                        useWater = true;
                        break;
                    case 2:
                        useMilk = true;
                        break;
                    case 3:
                        useWater = true;
                        useMilk = true;
                        break;
                    case 0:
                        System.out.println("Order canceled.");
                        vendingMachine.restoreOriginalContents(originalSlots, originalSpecialSlots);
                        return;
                    default:
                        isDone = false;
                        break;
                }
            }while(!isDone);

            Item selectedLiquid;
            isDone=false;
            // Handle the Second Phase (A) and (B) based on user choices
            if (useWater) {
                do {
                    System.out.println("Select water type:");
                    System.out.println("[1] Regular Water |" +
                            "  Price: "+vendingMachine.getSelectedItem(0, true).getPrice() +" |" +
                            " Stock: "+vendingMachine.getSelectedSlot(0, true).getItemStock());
                    System.out.println("[2] Sugar Water |" +
                            " Price: " + vendingMachine.getSelectedItem(1, true).getPrice() + " |" +
                            " Stock: " + vendingMachine.getSelectedSlot(1, true).getItemStock());
                    System.out.println("[0] Cancel Order");
                    System.out.print("Your choice: ");

                    int waterTypeChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (waterTypeChoice) {
                        case 1:
                            selectedLiquid = vendingMachine.dispenseSelectedItem(0, true);
                            selectedItems.add(selectedLiquid);
                            selectedLiquids.add(selectedLiquid);
                            isDone = true;
                            break;
                        case 2:
                            selectedLiquid = vendingMachine.dispenseSelectedItem(1, true);
                            selectedItems.add(selectedLiquid);
                            selectedLiquids.add(selectedLiquid);
                            isDone = true;
                            break;
                        case 0:
                            System.out.println("Order canceled.");
                            vendingMachine.restoreOriginalContents(originalSlots, originalSpecialSlots);
                            return;

                        default:
                            System.out.println("Invalid choice. Please select a valid water type.");
                            break;
                    }
                }while (!isDone);
            }
            isDone = false;
            if (useMilk) {
                do {
                    System.out.println("Select milk type:");
                    System.out.println("[1] Regular Cow's Milk |" +
                            " Price: " + vendingMachine.getSelectedItem(2, true).getPrice() + " |" +
                            " Stock: " + vendingMachine.getSelectedSlot(2, true).getItemStock());
                    System.out.println("[2] Almond Milk |" +
                            " Price: " + vendingMachine.getSelectedItem(3, true).getPrice() + " |" +
                            " Stock: " + vendingMachine.getSelectedSlot(3, true).getItemStock());
                    System.out.println("[3] Soy Milk |" +
                            " Price: " + vendingMachine.getSelectedItem(4, true).getPrice() + " |" +
                            " Stock: " + vendingMachine.getSelectedSlot(4, true).getItemStock());
                    System.out.println("[4] Condensed Milk |" +
                            " Price: " + vendingMachine.getSelectedItem(5, true).getPrice() + " |" +
                            " Stock: " + vendingMachine.getSelectedSlot(5, true).getItemStock());
                    System.out.println("[5] Evaporated Milk |" +
                            " Price: " + vendingMachine.getSelectedItem(6, true).getPrice() + " |" +
                            " Stock: " + vendingMachine.getSelectedSlot(6, true).getItemStock());
                    System.out.println("[0] Cancel Order");
                    System.out.print("Your choice: ");

                    int milkTypeChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (milkTypeChoice) {
                        case 1:
                            if (vendingMachine.getSelectedSlot(2, true).getItemArrayList().isEmpty()) {
                                System.out.println("Chosen item is not available due to being out of stock.");
                            }
                            else {
                                selectedLiquid = vendingMachine.dispenseSelectedItem(2, true);
                                selectedItems.add(selectedLiquid);
                                selectedLiquids.add(selectedLiquid);
                                isDone = true;
                            }

                            break;
                        case 2:
                            if (vendingMachine.getSelectedSlot(3, true).getItemArrayList().isEmpty()) {
                                System.out.println("Chosen item is not available due to being out of stock.");
                            }
                            else {
                                selectedLiquid = vendingMachine.dispenseSelectedItem(3, true);
                                selectedItems.add(selectedLiquid);
                                selectedLiquids.add(selectedLiquid);
                                isDone = true;
                            }
                            break;
                        case 3:
                            if (vendingMachine.getSelectedSlot(4, true).getItemArrayList().isEmpty()) {
                                System.out.println("Chosen item is not available due to being out of stock.");
                            }
                            else {
                                selectedLiquid = vendingMachine.dispenseSelectedItem(4, true);
                                selectedItems.add(selectedLiquid);
                                selectedLiquids.add(selectedLiquid);
                                isDone = true;
                            }
                            break;
                        case 4:
                            if (vendingMachine.getSelectedSlot(5, true).getItemArrayList().isEmpty()) {
                                System.out.println("Chosen item is not available due to being out of stock.");
                            }
                            else {
                                selectedLiquid = vendingMachine.dispenseSelectedItem(5, true);
                                selectedItems.add(selectedLiquid);
                                selectedLiquids.add(selectedLiquid);
                                isDone = true;
                            }
                            break;
                        case 5:
                            if (vendingMachine.getSelectedSlot(6, true).getItemArrayList().isEmpty()) {
                                System.out.println("Chosen item is not available due to being out of stock.");
                            }
                            else {
                                selectedLiquid = vendingMachine.dispenseSelectedItem(6, true);
                                selectedItems.add(selectedLiquid);
                                selectedLiquids.add(selectedLiquid);
                                isDone = true;
                            }
                            break;
                        case 0:
                            System.out.println("Order canceled.");
                            vendingMachine.restoreOriginalContents(originalSlots, originalSpecialSlots);
                            return;

                        default:
                            System.out.println("Invalid choice. Please select a valid milk type.");
                            break;
                    }
                }while (!isDone);
            }


            isDone = false;
            do {
                System.out.println("Select ice type:");
                System.out.println("[1] Ice Cubes |" +
                        " Price: " + vendingMachine.getSelectedItem(7, true).getPrice() + " |" +
                        " Stock: " + vendingMachine.getSelectedSlot(7, true).getItemStock());
                System.out.println("[2] Shaved Ice |" +
                        " Price: " + vendingMachine.getSelectedItem(8, true).getPrice() + " |" +
                        " Stock: " + vendingMachine.getSelectedSlot(8, true).getItemStock());
                System.out.println("[3] None");
                System.out.println("[0] Cancel Order");
                System.out.print("Your choice: ");

                int iceTypeChoice = scanner.nextInt();
                scanner.nextLine();

                switch (iceTypeChoice) {
                    case 1:
                        if (vendingMachine.getSelectedSlot(7, true).getItemArrayList().isEmpty()) {
                            System.out.println("Chosen item is not available due to being out of stock.");
                        }
                        else {
                            selectedIce = vendingMachine.dispenseSelectedItem(7, true);
                            selectedItems.add(selectedIce);
                            isDone = true;
                        }
                        break;
                    case 2:
                        if (vendingMachine.getSelectedSlot(8, true).getItemArrayList().isEmpty()) {
                            System.out.println("Chosen item is not available due to being out of stock.");
                        }
                        else {
                            selectedIce = vendingMachine.dispenseSelectedItem(8, true);
                            selectedItems.add(selectedIce);
                            isDone = true;
                        }
                        break;
                    case 3:
                        System.out.println("None selected, moving on...");
                        isDone = true;
                        break;
                    case 0:
                        System.out.println("Order canceled.");
                        vendingMachine.restoreOriginalContents(originalSlots, originalSpecialSlots);
                        return;

                    default:
                        System.out.println("Invalid choice. Please select a valid ice type.");
                        break;
                }
            }while (!isDone);


            isDone = false;
            do {
                System.out.println("Select additional toppings:");
                System.out.println("[1] Honey |" +
                        " Price: " + vendingMachine.getSelectedItem(9, true).getPrice() + " |" +
                        " Stock: " + vendingMachine.getSelectedSlot(9, true).getItemStock());
                System.out.println("[2] Cream |" +
                        " Price: " + vendingMachine.getSelectedItem(10, true).getPrice() + " |" +
                        " Stock: " + vendingMachine.getSelectedSlot(10, true).getItemStock());
                System.out.println("[3] None");
                System.out.println("[0] Cancel Order");
                System.out.print("Your choice: ");

                int toppingsChoice = scanner.nextInt();
                scanner.nextLine();

                switch (toppingsChoice) {
                    case 1:
                        if (vendingMachine.getSelectedSlot(9, true).getItemArrayList().isEmpty()) {
                            System.out.println("Chosen item is not available due to being out of stock.");
                        }
                        else{
                            selectedAddOns = vendingMachine.dispenseSelectedItem(9, true);
                            selectedItems.add(selectedAddOns);
                            isDone = true;
                        }
                        break;
                    case 2:
                        if (vendingMachine.getSelectedSlot(10, true).getItemArrayList().isEmpty()) {
                            System.out.println("Chosen item is not available due to being out of stock.");
                        }
                        else {
                            selectedAddOns = vendingMachine.dispenseSelectedItem(10, true);
                            selectedItems.add(selectedAddOns);
                            isDone = true;
                        }
                        break;
                    case 3:
                        System.out.println("None selected, moving on...");
                        isDone = true;
                        break;
                    case 0:
                        System.out.println("Order canceled.");
                        vendingMachine.restoreOriginalContents(originalSlots, originalSpecialSlots);
                        return;

                    default:
                        System.out.println("Invalid choice. Please select a valid ice type.");
                        break;
                }
            }while (!isDone );

        }

        int totalPrice = 0;
        int totalCals = 0;
        for(Item selectedItem: selectedItems)
        {
           totalPrice+=selectedItem.getPrice();
           totalCals+=selectedItem.getCalorie();
        }
        System.out.println("Total price of the items: ₱"+totalPrice);
        System.out.println("Total calorie count of the items: "+totalCals+" cals");

        do {
            if (!vendingMachine.insertMoney(scanner)) {
                if(vendingMachine.getUserBalance() < totalPrice)
                {
                    System.out.println("Inserted money isn't enough for the order.");
                }
                    System.out.print("Do you really want to exit? [Y]es or [N]o: ");

                String stringChoice = scanner.nextLine();
                if (stringChoice.equalsIgnoreCase("Y")) {
                    System.out.println("Exiting...");
                    return; // Exit the method or return to the previous menu
                }
            }
        } while (vendingMachine.getUserBalance() < totalPrice);

        int totalUserMoney = vendingMachine.getUserBalance();
        int change = totalUserMoney - totalPrice;

        // Receipt
        System.out.println("[Transaction Successful]");
        System.out.println("\n*** Receipt ***");
        for (Item selectedItem : selectedItems) {
            System.out.println(selectedItem.getType() + " - ₱" + selectedItem.getPrice());
        }
        System.out.println("Total Price: ₱" + totalPrice);
        System.out.println("Total Calories: " + totalCals+" cals");
        System.out.println();
        System.out.println("Inserted Money: ₱"+totalUserMoney);
        System.out.println("CHANGE: ₱" + change);
        vendingMachine.getMoneyManager().depositMoney();
        vendingMachine.getMoneyManager().returnChange(change);

        System.out.println("\nPreparing your custom fruit shake...");
        System.out.print("Blending Fruits: ");
        for(Item item: selectedFruits)
        {
            System.out.print(item.getType()+" ");
        }
        System.out.println();
        System.out.print("Pouring Liquids: ");
        for(Item liquid: selectedLiquids)
        {
            System.out.print(liquid.getType()+" ");
        }
        System.out.println();
        if(selectedIce!=null)
        {
            System.out.println("Putting "+selectedIce.getType());
        }
        if(selectedAddOns!=null)
        {
            System.out.println("Topping with "+selectedAddOns.getType());
        }
        System.out.println("Order Complete! Enjoy your customized fruit shake!");
        System.out.println("\nPress [0] to go back");
        int backChoice;
        do {
            System.out.print("Enter your choice: ");
            backChoice = scanner.nextInt();
            scanner.nextLine();
        } while (backChoice != 0);
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
                    restockItemsMenu(scanner, vendingMachine, maintenance);
                    break;
                case 2:
                    stockNewItemsMenu(scanner, vendingMachine, maintenance);
                    break;
                case 3:
                    setPriceForSelectedItemTypeMenu(scanner, vendingMachine, maintenance);
                    break;
                case 4:
                    collectMachineMoneyMenu(scanner, vendingMachine, maintenance);
                    break;
                case 5:
                    replenishMachineMoneyMenu(scanner, vendingMachine, maintenance);
                    break;
                case 6:
                    printSummaryMenu(vendingMachine, maintenance);
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
        String toTryAgain=null;
        boolean isCorrect = false;

        final String DEFAULT_PASSWORD = "password"; // default admin password

        do {
            System.out.print("Input admin password: ");
            password = scanner.nextLine();

            if (DEFAULT_PASSWORD.equals(password)) {
                isCorrect = true;
                System.out.println("Password correct! Access granted.");
            } else {
                System.out.println("Wrong Password!");

                System.out.print("Do you want to try again? [Y]es or [N]o: ");
                toTryAgain = scanner.nextLine();

                if (toTryAgain.equalsIgnoreCase("N")) {
                    System.out.println("Exiting...");
                    return false; // return false if the user chooses not to try again
                }
            }
        } while (!isCorrect && toTryAgain.equalsIgnoreCase("Y"));

        return true; // return true if the password is correct or the user chooses to try again
    }


    public static void restockItemsMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        boolean isDone = false;

        if (vendingMachine instanceof SpecialVendingMachine) {
            int choice;
            do {
                System.out.println("Select which items to restock");
                System.out.println("[1] Regular Items");
                System.out.println("[2] Special Items");
                System.out.println("[0] Exit");
                System.out.print("Your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        maintenance.restockProcess(scanner, vendingMachine, false);
                        break;
                    case 2:
                        maintenance.restockProcess(scanner, vendingMachine, true);
                        break;
                    case 0:
                        isDone=true;
                        break;
                    default:
                        System.out.println("Invalid Input! Please try again!");
                        break;

                }
            } while (!isDone);
        } else
            maintenance.restockProcess(scanner, vendingMachine, false);



    }
    public static void stockNewItemsMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        boolean isDone = false;
        boolean isCorrect = false;
        ArrayList<Slot> endingInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
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
                } else if (choice.equalsIgnoreCase("N")) { //TODO MAKE THIS A FUNCTION
                    System.out.println("Going back to maintenance menu...");
                    ArrayList<Slot> startingPrevInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
                    maintenance.addAllToPrevStartingInventory(vendingMachine,startingPrevInventoryCopy);
                    maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
                    ArrayList<Slot> startingInventoryCopy = maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
                    maintenance.addAllToStartingInventory(vendingMachine,startingInventoryCopy);
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
    public static void setPriceForSelectedItemTypeMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
        boolean isDone = false;
        int choice;

        if(vendingMachine instanceof SpecialVendingMachine)
        {
            do {
                System.out.println("Select which slots to adjust the price of");
                System.out.println("[1] Regular slots");
                System.out.println("[2] Special slots");
                System.out.println("[0] Exit");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        maintenance.setPricesProcess(scanner, vendingMachine, vendingMachine.getSlotArrayList(), false);
                        break;
                    case 2:
                        maintenance.setPricesProcess(scanner, vendingMachine, vendingMachine.getSpecialSlots(), true);
                        break;
                    case 0:
                        isDone=true;
                        break;
                    default:
                        System.out.println("Invalid Input! Please try again!");
                        break;
                }
                if (!isDone) {
                    boolean isCorrect = false;
                    do {
                        System.out.print("Do you want to continue setting prices for items? [Y]es or [N]o: ");
                        String stringChoice = scanner.nextLine();

                        if (stringChoice.equalsIgnoreCase("Y")) {
                            isCorrect = true;
                        } else if (stringChoice.equalsIgnoreCase("N")) {
                            System.out.println("Going back to maintenance menu...");
                            isCorrect = true;
                            isDone = true;
                        } else {
                            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                        }
                    } while (!isCorrect);
                }
            }while(!isDone);
        }
        else
        {
            do{
                maintenance.setPricesProcess(scanner, vendingMachine, vendingMachine.getSlotArrayList(), false);
                boolean isCorrect = false;
                do {
                    System.out.print("Do you want to continue setting prices for items? [Y]es or [N]o: ");
                    String stringChoice = scanner.nextLine();

                    if (stringChoice.equalsIgnoreCase("Y")) {
                        isCorrect = true;
                    } else if (stringChoice.equalsIgnoreCase("N")) {
                        System.out.println("Going back to maintenance menu...");
                        isCorrect = true;
                        isDone = true;
                    } else {
                        System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                    }
                } while (!isCorrect);
            }while(!isDone);
        }
    }

    public static void collectMachineMoneyMenu(Scanner scanner,VendingMachine vendingMachine, Maintenance maintenance)
    {
        boolean isDone = false;
        int totalMoneyGenerated = vendingMachine.getMoneyManager().getTotalMoneyFromList(vendingMachine.getMoneyManager().getTempMoneyFromUser());
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

    public static void replenishMachineMoneyMenu(Scanner scanner, VendingMachine vendingMachine, Maintenance maintenance) {
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

    public static void printSummaryMenu(VendingMachine vendingMachine, Maintenance maintenance)
    {
        maintenance.generateSalesReport(vendingMachine);
        System.out.println();
        System.out.println("Starting Inventory since previous restocking: ");
        vendingMachine.displayAllItems(vendingMachine.getPrevStartingInventory());
        if(vendingMachine instanceof SpecialVendingMachine)
        {
            vendingMachine.displayAllItems(vendingMachine.getSpecialPrevStartingInventory());
        }

        if (vendingMachine.getEndingInventory().isEmpty() &&
                (vendingMachine instanceof SpecialVendingMachine && vendingMachine.getSpecialEndingInventory().isEmpty()))  {
            System.out.println("Ending Inventory is not available. No previous restocking recorded.");
        } else {
            System.out.println("Ending Inventory since previous restocking: ");
            vendingMachine.displayAllItems(vendingMachine.getEndingInventory());
            if(vendingMachine instanceof SpecialVendingMachine)
            {
                vendingMachine.displayAllItems(vendingMachine.getSpecialEndingInventory());
            }
        }
    }


}