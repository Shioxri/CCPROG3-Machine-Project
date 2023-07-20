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
                    vendingMachine.initializeSlotsAndItems();
                    isDone = createRegularVendMachineMenu(scanner, vendingMachine);
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
    public static boolean createRegularVendMachineMenu(Scanner scanner, VendingMachine vendingMachine) {
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
                        createMaintenanceMenu(scanner, vendingMachine);
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
    private static void insertMoney(Scanner scanner, VendingMachine vendingMachine) {
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
                    vendingMachine.getMoneyManager().addTempPaidMoney(100, quantity);
                    break;
                case 2:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.getMoneyManager().addTempPaidMoney(50, quantity);
                    break;
                case 3:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.getMoneyManager().addTempPaidMoney(20, quantity);
                    break;
                case 4:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.getMoneyManager().addTempPaidMoney(10, quantity);
                    break;
                case 5:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.getMoneyManager().addTempPaidMoney(5, quantity);
                    break;
                case 6:
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer input
                    vendingMachine.getMoneyManager().addTempPaidMoney(1, quantity);
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
        boolean isDone = false;
        do {

            vendingMachine.displayAvailableItems();
            try{
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading the integer input
                if(vendingMachine.checkInputValidity(choice))
                {
                    vendingMachine.confirmTransaction(choice-1);
                    do {
                        try { // check if user input is valid
                            System.out.print("Press [0] to go back: ");
                            choice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.nextLine(); // Clear the input buffer
                        }
                    } while (choice != 0);
                    isDone=true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the input buffer
            }

        }while(!isDone);
    }



    /**
     * Menu for Maintenance/Admin
     *
     * @param scanner        for scanning user input
     * @param vendingMachine the vending machine object to be used/tested
     */
    public static void createMaintenanceMenu(Scanner scanner, VendingMachine vendingMachine) {

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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
                    System.out.println("Do you want to try again? [Y]es or [N]o");
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

}