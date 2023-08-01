package Controller;

import Model.Item;
import Model.Maintenance;
import Model.Slot;
import Model.VendingMachine;
import View.RegularBuy;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RegularBuyController {
    private RegularBuy regularBuyMenu;

    public RegularBuyController(RegularBuy regularBuyMenu, RegVMMenuController regVMMenuController, VendingMachine vendingMachine){
        this.regularBuyMenu = regularBuyMenu;



        updateGUI(vendingMachine);

        regularBuyMenu.getAddButton().addActionListener(e -> {
            vendingMachine.addTempPaidMoney((Integer) regularBuyMenu.getDenominations().getSelectedItem(), 1);
            regularBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
            System.out.println("Added: "+vendingMachine.getMoneyManager().getTempMoneyFromUser().
                    get(vendingMachine.getMoneyManager().getTempMoneyFromUser().size()-1));
            System.out.println("User Bal: "+vendingMachine.getUserBalance());
        });

        regularBuyMenu.getExitButton().addActionListener(e -> {
            regularBuyMenu.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });
    }
    private void updateGUI(VendingMachine vendingMachine)
    {
        regularBuyMenu.setCashBalance(vendingMachine.getUserBalance());
        regularBuyMenu.setRegularItems(getSlotTypes(vendingMachine.getSlotArrayList()));
    }

    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }

    public void updateInfoLabel(int selectedItemIndex, VendingMachine vendingMachine) {
        if (selectedItemIndex != 0) {
            int itemIndex = selectedItemIndex - 1;
            int slotIndex = selectedItemIndex - 1;
            String infoText = "<html>Price: " + vendingMachine.getSelectedItem(itemIndex, false).getPrice() +
                    "<br/>Calories: " + vendingMachine.getSelectedItem(itemIndex, false).getCalorie() +
                    " kCal<br/> Stock: " + vendingMachine.getSelectedSlot(slotIndex, false).getItemStock() +
                    "</html>";
            regularBuyMenu.getInfoLabel().setText(infoText);
        } else {
            regularBuyMenu.getInfoLabel().setText("");
        }
    }

}
