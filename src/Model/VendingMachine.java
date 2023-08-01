package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;

    public Initializer getInitializer() {
        return initializer;
    }

    private Initializer initializer;
    private DisplayManager displayer;
    private MoneyManager moneyManager;
    private TransactionManager transactionManager;
    private StockManager stockManager;
    private RecordsManager recorder;
    private ArrayList<Slot> specialSlots;

    public VendingMachine() {
        slotArrayList = new ArrayList<>();
        specialSlots = new ArrayList<>();
        initializer = new Initializer();
        displayer = new DisplayManager();
        moneyManager = new MoneyManager();
        transactionManager = new TransactionManager();
        stockManager = new StockManager();
        recorder = new RecordsManager();
        this.initializeSlotsAndItems();
        this.initializeMoney();
    }

    public void initializeSlotsAndItems() {
        this.initializer.initializeItems(this);
    }
    public void initializeMoney() {
        this.initializer.initializeMoney(this);
    }

    public void displayAllItems(ArrayList<Slot> slots) {
        this.displayer.displayAllItems(slots);
    }

    public void displaySpecificItem(int index) {
        this.displayer.displaySpecificItem(this, index);
    }

    public void displayAvailableItems(boolean isSpecialSlot) {
        this.displayer.displayAvailableItems(this, isSpecialSlot);
    }

    public int checkInputValidity(int indexChoice) { return this.transactionManager.checkInputValidity(this, indexChoice);}

    public void confirmTransaction(int indexChoice) { this.transactionManager.confirmTransaction(this, indexChoice); }

    public Item dispenseSelectedItem(int indexChoice, boolean isSpecialSlot) { return this.stockManager.dispenseSelectedItem(this,indexChoice, isSpecialSlot); }

    /**
     * Adds the paid money to the user paid money list.
     * @param denomination The denomination of the paid money.
     * @param quantity     The quantity of the paid money.
     */
    public void addTempPaidMoney(int denomination, int quantity) {
        this.moneyManager.addTempPaidMoney(denomination, quantity);
    }

    /**
     * Adds the stored money to the stored money list.
     * @param denomination The denomination of the stored money.
     * @param quantity     The quantity of the stored money.
     */
    public void addStoredMoney(int denomination, int quantity) {
        this.moneyManager.addStoredMoney(denomination, quantity);
    }

    /**
     * Adds the admin money to the admin money list.
     * @param denomination The denomination of the admin money.
     * @param quantity     The quantity of the admin money.
     */
    public void addAdminMoney(int denomination, int quantity) {
        this.moneyManager.addAdminMoney(denomination, quantity);
    }

    public ArrayList<Slot> getSlotArrayList() {
        return slotArrayList;
    }

    public StockManager getStockManager() {
        return stockManager;
    }

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

    public int getUserBalance()
    {
        return this.getMoneyManager().getTotalMoneyFromList(this.getMoneyManager().getTempMoneyFromUser());
    }
    public int getAdminCollectedMoney()
    {
        return this.getMoneyManager().getTotalMoneyFromList(this.getMoneyManager().getAdminMoney());
    }

    public Slot getSelectedSlot(int indexChoice, boolean isSpecialSlot)
    {
        if(!isSpecialSlot)
        {
            return this.getSlotArrayList().get(indexChoice);
        }
        else
            return this.getSpecialSlots().get(indexChoice);
    }


    public Item getSelectedItem(int indexChoice, boolean isSpecialSlot)
    {
        return this.getSelectedSlot(indexChoice, isSpecialSlot).getItemArrayList().get(0);
    }

    public RecordsManager getRecorder() {
        return recorder;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public boolean insertMoney(Scanner scanner)
    {
        return this.getTransactionManager().insertMoneyProcess(scanner, this);
    }
    public ArrayList<Slot> getPrevStartingInventory()
    {
        return this.getRecorder().getPrevStartingInventory();
    }

    public ArrayList<Slot> getStartingInventory()
    {
        return this.getRecorder().getStartingInventory();
    }

    public ArrayList<Slot> getEndingInventory()
    {
        return this.getRecorder().getEndingInventory();
    }

    public ArrayList<Slot> getSpecialPrevStartingInventory()
    {
        return this.getRecorder().getSpecialPrevStartingInventory();
    }

    public ArrayList<Slot> getSpecialStartingInventory()
    {
        return this.getRecorder().getStartingInventory();
    }

    public ArrayList<Slot> getSpecialEndingInventory()
    {
        return this.getRecorder().getEndingInventory();
    }

    public ArrayList<Slot> getSpecialSlots() {
        return specialSlots;
    }

    public Item getSpecialItemByItemType(String itemType) {
        for (Slot slot : this.specialSlots) {
            for (Item item : slot.getItemArrayList()) {
                if (item.getType().equalsIgnoreCase(itemType)) {
                    return item;
                }
            }
        }
        return null; // Return null if the item with the specified itemType is not found
    }

    public void restoreOriginalContents(ArrayList<Slot> originalSlots, ArrayList<Slot> originalSpecialSlots) {
        this.getStockManager().restoreOriginalSlotContents(this,originalSlots, originalSpecialSlots);

    }

    public void initializeInventory()
    {
        this.getInitializer().initializeInventory(this);
    }

}
