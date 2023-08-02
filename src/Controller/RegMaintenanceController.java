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
        updateDenomLabel(vendingMachine);

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
            String newType = regularMaintenance.getSetName().getText();
            int newPrice =Integer.parseInt( regularMaintenance.getSetPrice().getText());
            int newCals =Integer.parseInt( regularMaintenance.getSetCalories().getText());
            updateVMInventoryAndAddStock(vendingMachine, newType, newPrice, newCals);

            regularMaintenance.getSlotsDropdown().addItem(newType);

            regularMaintenance.getSetName().setText("Enter Item Name");
            regularMaintenance.getSetPrice().setText("Enter Item Price");
            regularMaintenance.getSetCalories().setText("Enter Item Calories");
        });


        regularMaintenance.getChangePriceButton().addActionListener(e ->{
            int newPrice = Integer.parseInt(regularMaintenance.getChangePrice().getText());
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            if(selectedItemIndex!=0)
            {
                Maintenance.updateItemPrices(vendingMachine, false ,selectedItemIndex-1, newPrice);
                updateInfoLabel(selectedItemIndex, vendingMachine);
            }
            else
            {
                regularMaintenance.getSystemMessage().setText("<html>Please select and item first!<html>");
            }

        });

        regularMaintenance.getCollectMoney().addActionListener(e ->{
            Maintenance.collectMoney(vendingMachine);
            updateDenomLabel(vendingMachine);
        });

        regularMaintenance.getAddButton().addActionListener(e ->{
            int denomination = ((Integer) regularMaintenance.getDenominations().getSelectedItem());
            System.out.println("Denomination: "+denomination);
            Maintenance.replenishMoney(vendingMachine, denomination, 1 );
            updateDenomLabel(vendingMachine);
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
                    updateVMInventoryAndRestock(vendingMachine, selectedItemIndex);
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



        regularMaintenance.getPrintSummary().addActionListener(e ->{
            String finalReport = Maintenance.getSalesReport(vendingMachine);
            JOptionPane.showMessageDialog(
                    null,
                    "<html><body><p style='width: 200px;'>"+finalReport+"</p></body></html>",
                    "Sales Report",
                    JOptionPane.PLAIN_MESSAGE);
        });
    }

    private void updateDenomLabel(VendingMachine vendingMachine)
    {
        int totalMachineMoney = vendingMachine.getMoneyManager().getTotalMoneyFromList(vendingMachine.getMoneyManager().getStoredMoney());
        regularMaintenance.setMachineBalanceLabel(vendingMachine.getMoneyManager().getStoredMoney(), totalMachineMoney);
    }

    private void updateVMInventoryAndRestock(VendingMachine vendingMachine, int selectedItemIndex)
    {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.restockItem(vendingMachine, selectedItemIndex-1, false);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
    }


    private void updateVMInventoryAndAddStock(VendingMachine vendingMachine, String newType, int newPrice, int newCals)
    {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.stockNewItems(vendingMachine, newType, newPrice, newCals);
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
            System.out.println(chosenItem);
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
