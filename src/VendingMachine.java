import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;
    private VendingMachineInitializer initializer;


    public VendingMachine() {
        slotArrayList = new ArrayList<>(8);
        initializer = new VendingMachineInitializer();
    }

    public void initializeSlotsAndItems() {
        initializer.initializeItems(this);
    }

    public ArrayList<Slot> getSlotArrayList() {
        return slotArrayList;
    }
}
