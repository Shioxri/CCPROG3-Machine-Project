import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
    private VendingMachineInitializer initializer;
    private DisplayManager displayer;
    private MoneyManager moneyManager;


    public VendingMachine() {
        slotArrayList = new ArrayList<>(8);
        initializer = new VendingMachineInitializer();
        displayer = new DisplayManager();
        moneyManager = new MoneyManager();
    }

    public void initializeSlotsAndItems() {
        initializer.initializeItems(this);
    }
    public void initializeMoney() {
        initializer.initializeMoney(this.moneyManager);
    }

    public void displayAllItems() {
        displayer.displayAllItems(this);
    }

    public void displaySpecificItem(int index) {
        displayer.displaySpecificItem(this, index);
    }

    public void displayAvailableItems() {
        displayer.displayAvailableItems(this);
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
