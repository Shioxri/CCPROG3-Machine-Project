import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
    private VendingMachineInitializer initializer;
    private DisplayManager displayer;


    public VendingMachine() {
        slotArrayList = new ArrayList<>(8);
        initializer = new VendingMachineInitializer();
        displayer = new DisplayManager();
    }

    public void initializeSlotsAndItems() {
        initializer.initializeItems(this);
    }

    public void displayAllItems() {
        displayer.displayAllItems(this);
    }

    public void displaySpecificItem(int index) {
        displayer.displaySpecificItem(this, index);
    }

    public ArrayList<Slot> getSlotArrayList() {
        return slotArrayList;
    }
}
