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



        setDropdownContents(vendingMachine);

        regularBuyMenu.getAddButton().addActionListener(e -> {
            vendingMachine.addTempPaidMoney((Integer) regularBuyMenu.getDenominations().getSelectedItem(), 1);
            regularBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
            regularBuyMenu.showAddedMoneyText();
            System.out.println("Added: "+vendingMachine.getMoneyManager().getTempMoneyFromUser().
                    get(vendingMachine.getMoneyManager().getTempMoneyFromUser().size()-1));
            System.out.println("User Bal: "+vendingMachine.getUserBalance());
        });

        regularBuyMenu.getExitButton().addActionListener(e -> {
            regularBuyMenu.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });

        regularBuyMenu.getRegularItems().addActionListener(e ->{
            int selectedItemIndex = regularBuyMenu.getRegularItems().getSelectedIndex();
            updateInfoLabel(selectedItemIndex, vendingMachine);
        });

        regularBuyMenu.getBuyButton().addActionListener(e -> {
            vendingMachine.displayAllItems(vendingMachine.getSlotArrayList());
            int selectedItemIndex = regularBuyMenu.getRegularItems().getSelectedIndex();
            int errorType = vendingMachine.checkInputValidity(selectedItemIndex, false);
            if(errorType==0)
            {

                updateInfoLabel(selectedItemIndex, vendingMachine);
                Item dispensedItem = vendingMachine.dispenseSelectedItem(selectedItemIndex-1, false);
                int change = vendingMachine.getUserBalance()-dispensedItem.getPrice();
                vendingMachine.confirmTransaction(selectedItemIndex-1);


                regularBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
                System.out.println(dispensedItem.getType() + " <- Dispensed (1) Item");

                regularBuyMenu.getSystemMessage().setText("<html>[Transaction Successful!]<br/>"+
                        "Dispensed "+dispensedItem.getType()+"<br/>"+
                        "Return Change: "+change+"</html>");

            }
            else {
               regularBuyMenu.setTextForInvalid(errorType);
            }
        });

        regularBuyMenu.getCancelButton().addActionListener(e -> {
            vendingMachine.getMoneyManager().returnMoney(vendingMachine.getMoneyManager().getTempMoneyFromUser());
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            regularBuyMenu.defaultBalanceText();
            regularBuyMenu.getSystemMessage().setText("<html>Cleared user balance<br/>"+
                    "Returned User Money<br/>"+
                    "Successfully cancelled the transaction </html>");
        });
    }
    private void setDropdownContents(VendingMachine vendingMachine)
    {
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
            int chosenItemIndex = selectedItemIndex - 1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItemIndex, false);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItemIndex, false);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Price: " +  selectedItem.getPrice() +
                        "<br/>Calories: " +  selectedItem.getCalorie() +
                        " kCal<br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                regularBuyMenu.getInfoLabel().setText(infoText);
            }
            else
            {
                regularBuyMenu.getInfoLabel().setText("Item: ["+selectedSlot.getAssignedItemType()+"] IS OUT OF STOCK!");
            }

        } else {
            regularBuyMenu.getInfoLabel().setText("");
        }
    }



}
