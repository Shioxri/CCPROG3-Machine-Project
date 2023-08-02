package Model;

import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
    private Initializer initializer;
    private MoneyManager moneyManager;
    private TransactionManager transactionManager;
    private StockManager stockManager;
    private RecordsManager recorder;
    private ArrayList<Slot> specialSlots;

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

    public void initializeSlotsAndItems() {
        this.initializer.initializeItems(this);
    }
    public void initializeMoney() {
        this.initializer.initializeMoney(this);
    }


    public int checkInputValidity(int indexChoice, boolean isSpecialSlot) { return this.transactionManager.checkInputValidity(this, indexChoice, isSpecialSlot);}

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


    public Item getSelectedItem(int indexChoice, boolean isSpecialSlot) {
        Slot selectedSlot = this.getSelectedSlot(indexChoice, isSpecialSlot);
        if (!selectedSlot.getItemArrayList().isEmpty()) {
            return selectedSlot.getItemArrayList().get(0);
        } else {
            // You can return null or some other indicator to signify that the slot is empty
            return null;
        }
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

    public ArrayList<Slot> getSpecialEndingInventory()
    {
        return this.getRecorder().getEndingInventory();
    }

    public ArrayList<Slot> getSpecialSlots() {
        return specialSlots;
    }


    public void restoreOriginalContents(ArrayList<Slot> originalSlots, ArrayList<Slot> originalSpecialSlots) {
        this.getStockManager().restoreOriginalSlotContents(this,originalSlots, originalSpecialSlots);

    }


}
