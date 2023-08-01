package Controller;

import Model.Slot;
import Model.SpecialVendingMachine;
import Model.VendingMachine;
import View.SpecialBuy;
import View.SpecialMaintenance;

import java.util.ArrayList;

public class SpecialBuyController {
    private SpecialBuy specialBuyMenu;

    SpecialBuyController(SpecialBuy specialBuyMenu, SpecVMMenuController specVMMenuController, SpecialVendingMachine vendingMachine){
        this.specialBuyMenu = specialBuyMenu;

        setDropdownContents(vendingMachine);

        specialBuyMenu.getAddButton().addActionListener(e -> {
            vendingMachine.addTempPaidMoney((Integer) specialBuyMenu.getDenominations().getSelectedItem(), 1);
            specialBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
            specialBuyMenu.showAddedMoneyText();
            System.out.println("Added: "+vendingMachine.getMoneyManager().getTempMoneyFromUser().
                    get(vendingMachine.getMoneyManager().getTempMoneyFromUser().size()-1));
            System.out.println("User Bal: "+vendingMachine.getUserBalance());
        });

        specialBuyMenu.getExitButton().addActionListener(e -> {
            specialBuyMenu.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });
    }
    private void setDropdownContents(SpecialVendingMachine vendingMachine)
    {
        specialBuyMenu.setFirstFruitsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
        specialBuyMenu.setSecondFruitsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
        specialBuyMenu.setWaterDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),0,1));
        specialBuyMenu.setMilkDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),2,6));
        specialBuyMenu.setIceDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),7,8));
        specialBuyMenu.setToppingsDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),9,10));
    }

    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }

    private ArrayList<String> getSpecialSlotTypes(ArrayList<Slot> slotTypes, int lowerBound, int upperBound) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (int i=lowerBound; i<upperBound+1;i++) {
            stringSlotTypes.add(slotTypes.get(i).getAssignedItemType());
        }
        return stringSlotTypes;
    }
}
