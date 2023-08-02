package Model;

import java.util.ArrayList;
/**
 * Represents a generic vending machine that allows users to purchase items by inserting money and making selections.
 * It manages the inventory of items, handles purchase transactions, and returns change to the user.
 *
 * This class provides basic vending machine functionality and can be extended for specialized vending machines.
 *
 * Usage:
 * 1. Create an instance of the VendingMachine class.
 * 2. Set up the inventory by adding slots with items.
 * 3. Users insert money using the insertMoney() method and make selections with the makeSelection() method.
 * 4. The class handles transactions, updates the inventory, and returns change to the user.
 * 5. The vending machine can be restocked with new items using the restockItems() method.
 */
public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
    private Initializer initializer;
    private MoneyManager moneyManager;
    private TransactionManager transactionManager;
    private StockManager stockManager;
    private RecordsManager recorder;
    private ArrayList<Slot> specialSlots;

    /**
     * Constructs a new VendingMachine object.
     */
    public VendingMachine() {
        slotArrayList = new ArrayList<>();
        specialSlots = new ArrayList<>();
        initializer = new Initializer();
        moneyManager = new MoneyManager();
        transactionManager = new TransactionManager();
        stockManager = new StockManager();
        recorder = new RecordsManager();
        this.initializeSlotsAndItems();
        this.initializeMoney();
    }

    /**
     * Initializes the slots and items in the vending machine.
     */
    public void initializeSlotsAndItems() {
        this.initializer.initializeItems(this);
    }

    /**
     * Initializes the money in the vending machine.
     */
    public void initializeMoney() {
        this.initializer.initializeMoney(this);
    }

    /**
     * Checks the validity of the user's input.
     *
     * @param indexChoice   The index of the selected item.
     * @param isSpecialSlot A boolean indicating if the selected slot is a special slot.
     * @return The validity status (0 for valid, 1 for insufficient balance, 2 for out of stock).
     */
    public int checkInputValidity(int indexChoice, boolean isSpecialSlot) {
        return this.transactionManager.checkInputValidity(this, indexChoice, isSpecialSlot);
    }

    /**
     * Confirms the user's transaction and updates the stock and balance accordingly.
     *
     * @param indexChoice The index of the selected item.
     */
    public void confirmTransaction(int indexChoice) {
        this.transactionManager.confirmTransaction(this, indexChoice);
    }

    /**
     * Dispenses the selected item and removes it from the slot.
     *
     * @param indexChoice   The index of the selected item.
     * @param isSpecialSlot A boolean indicating if the selected slot is a special slot.
     * @return The selected item.
     */
    public Item dispenseSelectedItem(int indexChoice, boolean isSpecialSlot) {
        return this.stockManager.dispenseSelectedItem(this, indexChoice, isSpecialSlot);
    }

    /**
     * Adds the paid money to the temporary paid money list.
     *
     * @param denomination The denomination of the paid money.
     * @param quantity     The quantity of the paid money.
     */
    public void addTempPaidMoney(int denomination, int quantity) {
        this.moneyManager.addTempPaidMoney(denomination, quantity);
    }

    /**
     * Adds the stored money to the stored money list.
     *
     * @param denomination The denomination of the stored money.
     * @param quantity     The quantity of the stored money.
     */
    public void addStoredMoney(int denomination, int quantity) {
        this.moneyManager.addStoredMoney(denomination, quantity);
    }


    /**
     * Gets the list of regular slots in the vending machine.
     *
     * @return The list of regular slots.
     */
    public ArrayList<Slot> getSlotArrayList() {
        return slotArrayList;
    }

    /**
     * Gets the stock manager of the vending machine.
     *
     * @return The stock manager.
     */
    public StockManager getStockManager() {
        return stockManager;
    }

    /**
     * Gets the money manager of the vending machine.
     *
     * @return The money manager.
     */
    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

    /**
     * Gets the user's current balance.
     *
     * @return The user's balance.
     */
    public int getUserBalance() {
        return this.getMoneyManager().getTotalMoneyFromList(this.getMoneyManager().getTempMoneyFromUser());
    }

    /**
     * Gets the total amount of money collected by the admin.
     *
     * @return The admin's collected money.
     */
    public int getAdminCollectedMoney() {
        return this.getMoneyManager().getTotalMoneyFromList(this.getMoneyManager().getAdminMoney());
    }

    /**
     * Gets the selected slot based on the index and slot type (regular or special).
     *
     * @param indexChoice   The index of the selected slot.
     * @param isSpecialSlot A boolean indicating if the selected slot is a special slot.
     * @return The selected slot.
     */
    public Slot getSelectedSlot(int indexChoice, boolean isSpecialSlot) {
        if (!isSpecialSlot) {
            return this.getSlotArrayList().get(indexChoice);
        } else {
            return this.getSpecialSlots().get(indexChoice);
        }
    }

    /**
     * Gets the selected item based on the index and slot type (regular or special).
     *
     * @param indexChoice   The index of the selected item.
     * @param isSpecialSlot A boolean indicating if the selected slot is a special slot.
     * @return The selected item.
     */
    public Item getSelectedItem(int indexChoice, boolean isSpecialSlot) {
        Slot selectedSlot = this.getSelectedSlot(indexChoice, isSpecialSlot);
        if (!selectedSlot.getItemArrayList().isEmpty()) {
            return selectedSlot.getItemArrayList().get(0);
        } else {
            // You can return null or some other indicator to signify that the slot is empty
            return null;
        }
    }

    /**
     * Gets the records manager of the vending machine.
     *
     * @return The records manager.
     */
    public RecordsManager getRecorder() {
        return recorder;
    }

    /**
     * Gets the previous starting inventory recorded by the records manager.
     *
     * @return The previous starting inventory.
     */
    public ArrayList<Slot> getPrevStartingInventory() {
        return this.getRecorder().getPrevStartingInventory();
    }

    /**
     * Gets the current starting inventory recorded by the records manager.
     *
     * @return The current starting inventory.
     */
    public ArrayList<Slot> getStartingInventory() {
        return this.getRecorder().getStartingInventory();
    }

    /**
     * Gets the current ending inventory recorded by the records manager.
     *
     * @return The current ending inventory.
     */
    public ArrayList<Slot> getEndingInventory() {
        return this.getRecorder().getEndingInventory();
    }

    /**
     * Gets the special ending inventory recorded by the records manager.
     *
     * @return The special ending inventory.
     */
    public ArrayList<Slot> getSpecialEndingInventory() {
        return this.getRecorder().getEndingInventory();
    }

    /**
     * Gets the list of special slots in the vending machine.
     *
     * @return The list of special slots.
     */
    public ArrayList<Slot> getSpecialSlots() {
        return specialSlots;
    }

    /**
     * Restores the original contents of the vending machine with the provided original slots and special slots.
     *
     * @param originalSlots        The original regular slots.
     * @param originalSpecialSlots The original special slots.
     */
    public void restoreOriginalContents(ArrayList<Slot> originalSlots, ArrayList<Slot> originalSpecialSlots) {
        this.getStockManager().restoreOriginalSlotContents(this, originalSlots, originalSpecialSlots);
    }


}
