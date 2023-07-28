package Model;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
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

    public void displayAvailableItems() {
        this.displayer.displayAvailableItems(this);
    }

    public boolean checkInputValidity(int indexChoice) { return this.transactionManager.checkInputValidity(this, indexChoice);}

    public void confirmTransaction(int indexChoice) { this.transactionManager.confirmTransaction(this, indexChoice); }

    public Item dispenseSelectedItem(int indexChoice) { return this.stockManager.dispenseSelectedItem(this,indexChoice); }

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

    public Slot getSelectedSlot(int indexChoice)
    {
        return this.getSlotArrayList().get(indexChoice);
    }


    public Item getSelectedItem(int indexChoice)
    {
        return this.getSelectedSlot(indexChoice).getItemArrayList().get(0);
    }

    public RecordsManager getRecorder() {
        return recorder;
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

    public void restoreOriginalContents(VendingMachine vendingMachine, ArrayList<Slot> originalSlots, ArrayList<Slot> originalSpecialSlots) {
        // Restore the original contents of regular slots
        for (int i = 0; i < originalSlots.size(); i++) {
            Slot originalSlot = originalSlots.get(i);
            Slot currentSlot = vendingMachine.getSlotArrayList().get(i);
            currentSlot.setItemArrayList(originalSlot.getItemArrayList());
            currentSlot.setItemStock(originalSlot.getItemArrayList().size());
        }

        // Restore the original contents of special slots
        for (int i = 0; i < originalSpecialSlots.size(); i++) {
            Slot originalSlot = originalSpecialSlots.get(i);
            Slot currentSlot = vendingMachine.getSpecialSlots().get(i);
            currentSlot.setItemArrayList(originalSlot.getItemArrayList());
            currentSlot.setItemStock(originalSlot.getItemArrayList().size());
        }
    }

}
