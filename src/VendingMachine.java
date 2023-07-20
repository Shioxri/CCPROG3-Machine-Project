import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
    private VendingMachineInitializer initializer;
    private DisplayManager displayer;
    private MoneyManager moneyManager;
    private TransactionManager transactionManager;


    public VendingMachine() {
        slotArrayList = new ArrayList<>(8);
        initializer = new VendingMachineInitializer();
        displayer = new DisplayManager();
        moneyManager = new MoneyManager();
    }

    public void initializeSlotsAndItems() {
        this.initializer.initializeItems(this);
    }
    public void initializeMoney() {
        this.initializer.initializeMoney(this.moneyManager);
    }

    public void displayAllItems() {
        this.displayer.displayAllItems(this);
    }

    public void displaySpecificItem(int index) {
        this.displayer.displaySpecificItem(this, index);
    }

    public void displayAvailableItems() {
        this.displayer.displayAvailableItems(this);
    }

    public boolean checkInputValidity(int indexChoice) { return this.transactionManager.checkInputValidity(this, indexChoice);}

    public void confirmTransaction(int indexChoice)
    {
        this.transactionManager.confirmTransaction(this, indexChoice);
    }
    public ArrayList<Slot> getSlotArrayList() {
        return slotArrayList;
    }

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }

    public int getUserBalance()
    {
        return this.getMoneyManager().getTotalTempUserMoney();
    }

    public Slot getSelectedSlot(int indexChoice)
    {
        return this.getSlotArrayList().get(indexChoice);
    }


    public Item getSelectedItem(int indexChoice)
    {
        return this.getSelectedSlot(indexChoice).getItemArrayList().get(0);
    }


}
