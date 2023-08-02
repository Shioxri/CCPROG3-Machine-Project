package Controller;

import Model.*;
import View.SpecialMaintenance;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpecMaintenanceController {
    private SpecialMaintenance specialMaintenance;

    SpecMaintenanceController(SpecialMaintenance specialMaintenance, SpecVMMenuController specVMMenuController, SpecialVendingMachine vendingMachine){
        this.specialMaintenance = specialMaintenance;


        setRegularDropdownContents(vendingMachine);
        setSpecialDropdownContents(vendingMachine);
        updateDenomLabel(vendingMachine);

        specialMaintenance.getAddButton().addActionListener(e ->{
            int denomination = ((Integer) specialMaintenance.getDenominations().getSelectedItem());
            Maintenance.replenishMoney(vendingMachine, denomination, 1 );
            updateDenomLabel(vendingMachine);
        });


        specialMaintenance.getInstructionsButton().addActionListener(e -> {
            specialMaintenance.getSystemMessage().setText("<html>Instructions<br/>" +
                    "Top Left - Edit Item Slot" +
                    "<br/> Bottom Left - Add New Item(Note: make sure every text field is filled" +
                    "<br/> Right - Edit Vending Machine</html>");
        });



        specialMaintenance.getRegularSlotsDropDown().addActionListener(e -> {
            int selectedItemIndex = specialMaintenance.getRegularSlotsDropDown().getSelectedIndex();
            updateRegularInfoLabel(selectedItemIndex,vendingMachine);
        });

        specialMaintenance.getSpecialSlotsDropDown().addActionListener(e -> {
            int selectedItemIndex = specialMaintenance.getSpecialSlotsDropDown().getSelectedIndex();
            updateSpecialInfoLabel(selectedItemIndex,vendingMachine);
        });


        specialMaintenance.getChangeRegularPriceButton().addActionListener(e ->{
            int newPrice = Integer.parseInt(specialMaintenance.getChangePriceRegular().getText());
            int selectedItemIndex = specialMaintenance.getRegularSlotsDropDown().getSelectedIndex();
            if(selectedItemIndex!=0)
            {
                Maintenance.updateItemPrices(vendingMachine, false ,selectedItemIndex-1, newPrice);
                updateRegularInfoLabel(selectedItemIndex, vendingMachine);
            }
            else
            {
                specialMaintenance.getSystemMessage().setText("<html>Please select and item first!<html>");
            }
        });

        specialMaintenance.getChangeSpecialPriceButton().addActionListener(e ->{
            int newPrice = Integer.parseInt(specialMaintenance.getChangePriceSpecial().getText());
            int selectedItemIndex = specialMaintenance.getSpecialSlotsDropDown().getSelectedIndex();
            if(selectedItemIndex!=0)
            {
                Maintenance.updateItemPrices(vendingMachine, true ,selectedItemIndex-1, newPrice);
                updateSpecialInfoLabel(selectedItemIndex, vendingMachine);
            }
            else
            {
                specialMaintenance.getSystemMessage().setText("<html>Please select and item first!<html>");
            }
        });


        specialMaintenance.getRestockRegular().addActionListener(e ->{
            int selectedItemIndex = specialMaintenance.getRegularSlotsDropDown().getSelectedIndex();
            if(selectedItemIndex!=0)
            {
                if(vendingMachine.getSelectedSlot(selectedItemIndex-1, false).getItemStock() <= 5)
                {
                    updateVMInventoryAndRestock(vendingMachine, selectedItemIndex, false);
                    updateRegularInfoLabel(selectedItemIndex,vendingMachine);
                }
                else
                {
                    specialMaintenance.getRegularSlotsDropDown().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,
                            new JLabel("Item Stock is more than 5!", JLabel.CENTER),"StockLeft",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        specialMaintenance.getRestockSpecial().addActionListener(e ->{
            int selectedItemIndex = specialMaintenance.getSpecialSlotsDropDown().getSelectedIndex();
            if(selectedItemIndex!=0)
            {
                if(vendingMachine.getSelectedSlot(selectedItemIndex-1, true).getItemStock() <= 5)
                {
                    updateVMInventoryAndRestock(vendingMachine, selectedItemIndex, true);
                    updateSpecialInfoLabel(selectedItemIndex,vendingMachine);
                }
                else
                {
                    specialMaintenance.getSpecialSlotsDropDown().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,
                            new JLabel("Item Stock is more than 5!", JLabel.CENTER),"StockLeft",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });



        specialMaintenance.getAddItem().addActionListener(e ->{
            String newType = specialMaintenance.getSetName().getText();
            int newPrice =Integer.parseInt( specialMaintenance.getSetPrice().getText());
            int newCals =Integer.parseInt( specialMaintenance.getSetCalories().getText());
            updateVMInventoryAndAddStock(vendingMachine, newType, newPrice, newCals);
            specialMaintenance.getRegularSlotsDropDown().addItem(newType);
            specialMaintenance.getSetName().setText("Enter Item Name");
            specialMaintenance.getSetPrice().setText("Enter Item Price");
            specialMaintenance.getSetCalories().setText("Enter Item Calories");
        });

        specialMaintenance.getCollectMoney().addActionListener(e ->{
            Maintenance.collectMoney(vendingMachine);
            updateDenomLabel(vendingMachine);
        });

        specialMaintenance.getExitButton().addActionListener(e -> {
            specialMaintenance.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });

        specialMaintenance.getPrintSummary().addActionListener(e ->{
            String finalReport = Maintenance.getSalesReport(vendingMachine);
            JTextArea textArea = new JTextArea(finalReport);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
            JOptionPane.showMessageDialog(null, scrollPane, "dialog test with textarea",
                    JOptionPane.YES_NO_OPTION);
        });

    }

    private void updateDenomLabel(VendingMachine vendingMachine)
    {
        int totalMachineMoney = vendingMachine.getMoneyManager().getTotalMoneyFromList(vendingMachine.getMoneyManager().getStoredMoney());
        specialMaintenance.setMachineBalanceLabel(vendingMachine.getMoneyManager().getStoredMoney(), totalMachineMoney);
    }


    private void updateVMInventoryAndRestock(VendingMachine vendingMachine, int selectedItemIndex, boolean isSpecialSlot)
    {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot>  specialEndingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        Maintenance.restockItem(vendingMachine, selectedItemIndex-1, isSpecialSlot);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);

        ArrayList<Slot> specialPrevStartingInventory = Maintenance.deepCopySlotArrayList(vendingMachine.getPrevStartingInventory());
        Maintenance.addAllToPrevStartingSpecialInventory (vendingMachine, specialPrevStartingInventory);
        Maintenance.addAllToEndingSpecialInventory (vendingMachine, specialEndingInventoryCopy);
        ArrayList<Slot> specialStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToStartingSpecialInventory(vendingMachine, specialStartingInventoryCopy);
    }


    private void updateVMInventoryAndAddStock(VendingMachine vendingMachine, String newType, int newPrice, int newCals)
    {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot>  specialEndingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        Maintenance.stockNewItems(vendingMachine, newType, newPrice, newCals);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
            ArrayList<Slot> specialPrevStartingInventory = Maintenance.deepCopySlotArrayList(vendingMachine.getPrevStartingInventory());
            Maintenance.addAllToPrevStartingSpecialInventory (vendingMachine, specialPrevStartingInventory);
            Maintenance.addAllToEndingSpecialInventory (vendingMachine, specialEndingInventoryCopy);
            ArrayList<Slot> specialStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
            Maintenance.addAllToStartingSpecialInventory(vendingMachine, specialStartingInventoryCopy);
    }

    private void setRegularDropdownContents(VendingMachine vendingMachine)
    {
        specialMaintenance.setRegularSlotsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
    }

    private void setSpecialDropdownContents(VendingMachine vendingMachine)
    {
        specialMaintenance.setSpecialSlotsDropDown(getSlotTypes(vendingMachine.getSpecialSlots()));
    }

    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }



    public void updateRegularInfoLabel(int selectedItemIndex, SpecialVendingMachine vendingMachine) {
        if (selectedItemIndex != 0) {
            int chosenItem = selectedItemIndex-1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItem, false);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItem, false);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Price: " +  selectedItem.getPrice() +
                        "<br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                specialMaintenance.getRegularSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: "+selectedItem.getType());
            }
            else
            {
                int price = vendingMachine.getSelectedSlot(chosenItem, false).getAssignedItemPrice();
                String infoText = "<html>Price: " + price +
                        "<br/>Item: ["+selectedSlot.getAssignedItemType()+"] IS OUT OF STOCK!</html>";
                specialMaintenance.getRegularSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: "+selectedSlot.getAssignedItemType());
            }

        } else {
            specialMaintenance.getRegularSlotLabel().setText("");
            specialMaintenance.getSystemMessage().setText("");
        }
    }

    public void updateSpecialInfoLabel(int selectedItemIndex, SpecialVendingMachine vendingMachine) {
        if (selectedItemIndex != 0) {
            int chosenItem = selectedItemIndex-1;

            Item selectedItem = vendingMachine.getSelectedItem(chosenItem, true);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItem, true);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Price: " +  selectedItem.getPrice() +
                        "<br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                specialMaintenance.getSpecialSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: "+selectedItem.getType());
            }
            else
            {
                int price = vendingMachine.getSelectedSlot(chosenItem, true).getAssignedItemPrice();
                String infoText = "<html>Price: " + price +
                        "<br/>Item: ["+selectedSlot.getAssignedItemType()+"] IS OUT OF STOCK!</html>";
                specialMaintenance.getSpecialSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: "+selectedSlot.getAssignedItemType());
            }
        } else {
            specialMaintenance.getSpecialSlotLabel().setText("");
            specialMaintenance.getSystemMessage().setText("");
        }
    }

}
