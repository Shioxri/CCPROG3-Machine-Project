import java.util.ArrayList;

public class VendingMachine {

    private ArrayList<Slot> slotArrayList;


    public VendingMachine() {
        slotArrayList = new ArrayList<>(8);
    }

    public ArrayList<Slot> getSlotArrayList() {
        return slotArrayList;
    }



}
