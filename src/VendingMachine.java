import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
    private VendingMachineInitializer initializer;
    private DisplayManager displayer;
    private ArrayList<Integer> storedMoneyList;

    private MoneyManager moneyManager;


    public VendingMachine() {
        slotArrayList = new ArrayList<>(8);
        initializer = new VendingMachineInitializer();
        displayer = new DisplayManager();
        storedMoneyList = new ArrayList<>();
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

    public ArrayList<Integer> getStoredMoneyList() {
        return storedMoneyList;
    }

    public void setStoredMoneyList(ArrayList<Integer> storedMoneyList) {
        this.storedMoneyList = storedMoneyList;
    }

    public MoneyManager getMoneyManager() {
        return moneyManager;
    }
    public void setMoneyManager(MoneyManager moneyManager) {
        this.moneyManager = moneyManager;
    }
}
