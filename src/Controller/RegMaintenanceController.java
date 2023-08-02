package Controller;

import Model.Item;
import Model.Maintenance;
import Model.Slot;
import Model.VendingMachine;
import View.RegularMaintenance;

import javax.swing.*;
import java.util.ArrayList;

public class RegMaintenanceController {
    private RegularMaintenance regularMaintenance;

    public RegMaintenanceController(RegularMaintenance regularMaintenance, RegVMMenuController regVMMenuController, VendingMachine vendingMachine){
        this.regularMaintenance = regularMaintenance;


        setDropdownContents(vendingMachine);
        int totalMachineMoney = vendingMachine.getMoneyManager().getTotalMoneyFromList(vendingMachine.getMoneyManager().getStoredMoney());
        regularMaintenance.setMachineBalanceLabel(vendingMachine.getMoneyManager().getStoredMoney(), totalMachineMoney);

        regularMaintenance.getExitButton().addActionListener(e -> {
            regularMaintenance.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });

        regularMaintenance.getInstructionsButton().addActionListener(e -> {
            regularMaintenance.getSystemMessage().setText("<html>Instructions<br/>" +
                    "Top Left - Edit Item Slot" +
                    "<br/> Bottom Left - Add New Item(Note: make sure every text field is filled" +
                    "<br/> Right - Edit Vending Machine</html>");
        });


        regularMaintenance.getAddItem().addActionListener(e ->{

        });

        regularMaintenance.getChangePrice().addActionListener(e ->{


        });

        regularMaintenance.getChangePriceButton().addActionListener(e ->{
            int newPrice = Integer.parseInt(regularMaintenance.getChangePrice().getText());
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            Maintenance.updateItemPrices(vendingMachine, false ,selectedItemIndex-1, newPrice);
            updateInfoLabel(selectedItemIndex, vendingMachine);
        });

        regularMaintenance.getCollectMoney().addActionListener(e ->{
            //regularMaintenance.getSystemMessage().setText("You have collected Php <Total cash>);
            // AllDenominationsVM.set(0) or AllDenominationsVM = 0;
        });

        regularMaintenance.getSlotsDropdown().addActionListener(e -> {
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            updateInfoLabel(selectedItemIndex,vendingMachine);
        });
        regularMaintenance.getRestockButton().addActionListener(e ->{
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            if(selectedItemIndex!=0)
            {
                if(vendingMachine.getSelectedSlot(selectedItemIndex-1, false).getItemStock() <= 5)
                {
                    updateVMInventory(vendingMachine, selectedItemIndex);
                    updateInfoLabel(selectedItemIndex,vendingMachine);
                }
                else
                {
                    regularMaintenance.getSlotsDropdown().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,
                            new JLabel("Item Stock is more than 5!", JLabel.CENTER),"StockLeft",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });


        regularMaintenance.getSlotInfoButton().addActionListener(e -> {
            //String system info = regularMaintenance.getStocks().getSelectedItem;
            // regularMaintenance.getSystemMessage.setText(system info);
        });

        regularMaintenance.getPrintSummary().addActionListener(e ->{

        });
    }


    private void updateVMInventory(VendingMachine vendingMachine, int selectedItemIndex)
    {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.restockItem(vendingMachine, selectedItemIndex-1, false);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
    }

    private void setDropdownContents(VendingMachine vendingMachine)
    {
        regularMaintenance.setSlotsDropdown(getSlotTypes(vendingMachine.getSlotArrayList()));
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
            int chosenItem = selectedItemIndex-1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItem, false);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItem, false);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Restocking and Repricing<br/><br/>" +
                        "Price: " +  selectedItem.getPrice() +
                        " <br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                regularMaintenance.getSlotInfoLabel().setText(infoText);
                regularMaintenance.getSystemMessage().setText("Selected: "+selectedItem.getType());
            }
            else
            {
                String infoText = "<html>Restocking and Repricing<br/><br/>" +
                        " <br/> Stock: " + selectedSlot.getItemStock() +
                        "<br/> [OUT OF STOCK] </html>";
                regularMaintenance.getSlotInfoLabel().setText(infoText);
                regularMaintenance.getSystemMessage().setText("Selected: "+selectedSlot.getAssignedItemType());
            }

        } else {
            regularMaintenance.getSlotInfoLabel().setText("");
            regularMaintenance.getSystemMessage().setText("");
        }
    }
}
