package Controller;

import Model.Item;
import Model.Maintenance;
import Model.Slot;
import Model.VendingMachine;
import View.RegularMaintenance;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The RegVMMenuController class handles the actions and navigation for the regular vending machine menu.
 */
public class RegMaintenanceController {
    private RegularMaintenance regularMaintenance;

    /**
     * Constructor for the Regular Maintenance Controller.
     *
     * @param regularMaintenance   The RegularMaintenance view.
     * @param regVMMenuController  The controller for the Regular Vending Machine menu.
     * @param vendingMachine       The VendingMachine model instance.
     */
    public RegMaintenanceController(RegularMaintenance regularMaintenance, RegVMMenuController regVMMenuController, VendingMachine vendingMachine) {
        this.regularMaintenance = regularMaintenance;

        // Initialize the dropdown and denomination label
        setDropdownContents(vendingMachine);
        updateDenomLabel(vendingMachine);

        // Exit button action listener
        regularMaintenance.getExitButton().addActionListener(e -> {
            regularMaintenance.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });

        // Instructions button action listener
        regularMaintenance.getInstructionsButton().addActionListener(e -> {
            regularMaintenance.getSystemMessage().setText("<html>Instructions<br/>" +
                    "Top Left - Edit Item Slot" +
                    "<br/> Bottom Left - Add New Item(Note: make sure every text field is filled" +
                    "<br/> Right - Edit Vending Machine</html>");
        });

        // Add item button action listener
        regularMaintenance.getAddItem().addActionListener(e -> {
            String newType = regularMaintenance.getSetName().getText();
            int newPrice = Integer.parseInt(regularMaintenance.getSetPrice().getText());
            int newCals = Integer.parseInt(regularMaintenance.getSetCalories().getText());
            updateVMInventoryAndAddStock(vendingMachine, newType, newPrice, newCals);

            regularMaintenance.getSlotsDropdown().addItem(newType);

            regularMaintenance.getSetName().setText("Enter Item Name");
            regularMaintenance.getSetPrice().setText("Enter Item Price");
            regularMaintenance.getSetCalories().setText("Enter Item Calories");
        });

        // Change price button action listener
        regularMaintenance.getChangePriceButton().addActionListener(e -> {
            int newPrice = Integer.parseInt(regularMaintenance.getChangePrice().getText());
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            if (selectedItemIndex != 0) {
                Maintenance.updateItemPrices(vendingMachine, false, selectedItemIndex - 1, newPrice);
                updateInfoLabel(selectedItemIndex, vendingMachine);
            } else {
                regularMaintenance.getSystemMessage().setText("<html>Please select an item first!</html>");
            }
        });

        // Collect money button action listener
        regularMaintenance.getCollectMoney().addActionListener(e -> {
            Maintenance.collectMoney(vendingMachine);
            updateDenomLabel(vendingMachine);
        });

        // Add button action listener
        regularMaintenance.getAddButton().addActionListener(e -> {
            int denomination = ((Integer) regularMaintenance.getDenominations().getSelectedItem());
            Maintenance.replenishMoney(vendingMachine, denomination, 1);
            updateDenomLabel(vendingMachine);
        });

        // Dropdown action listener
        regularMaintenance.getSlotsDropdown().addActionListener(e -> {
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            updateInfoLabel(selectedItemIndex, vendingMachine);
        });

        // Restock button action listener
        regularMaintenance.getRestockButton().addActionListener(e -> {
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            if (selectedItemIndex != 0) {
                if (vendingMachine.getSelectedSlot(selectedItemIndex - 1, false).getItemStock() <= 5) {
                    updateVMInventoryAndRestock(vendingMachine, selectedItemIndex);
                    updateInfoLabel(selectedItemIndex, vendingMachine);
                } else {
                    regularMaintenance.getSlotsDropdown().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,
                            new JLabel("Item Stock is more than 5!", JLabel.CENTER), "StockLeft",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Print summary button action listener
        regularMaintenance.getPrintSummary().addActionListener(e -> {
            String finalReport = Maintenance.getSalesReport(vendingMachine);
            JOptionPane.showMessageDialog(
                    null,
                    finalReport,
                    "Sales Report",
                    JOptionPane.PLAIN_MESSAGE);
        });
    }

    // Private helper method to update the denomination label with the current machine balance
    private void updateDenomLabel(VendingMachine vendingMachine) {
        int totalMachineMoney = vendingMachine.getMoneyManager().getTotalMoneyFromList(vendingMachine.getMoneyManager().getStoredMoney());
        regularMaintenance.setMachineBalanceLabel(vendingMachine.getMoneyManager().getStoredMoney(), totalMachineMoney);
    }

    // Private helper method to restock items and update the inventory
    private void updateVMInventoryAndRestock(VendingMachine vendingMachine, int selectedItemIndex) {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.restockItem(vendingMachine, selectedItemIndex - 1, false);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
    }

    // Private helper method to add new items to the inventory and update dropdown contents
    private void updateVMInventoryAndAddStock(VendingMachine vendingMachine, String newType, int newPrice, int newCals) {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.stockNewItems(vendingMachine, newType, newPrice, newCals);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
    }

    // Private helper method to set the dropdown contents with the assigned item types
    private void setDropdownContents(VendingMachine vendingMachine) {
        regularMaintenance.setSlotsDropdown(getSlotTypes(vendingMachine.getSlotArrayList()));
    }

    // Private helper method to get the assigned item types for dropdown
    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }

    /**
     * Updates the info label based on the selected item index.
     *
     * @param selectedItemIndex The index of the selected item in the dropdown.
     * @param vendingMachine    The VendingMachine model instance.
     */
    public void updateInfoLabel(int selectedItemIndex, VendingMachine vendingMachine) {
        if (selectedItemIndex != 0) {
            int chosenItem = selectedItemIndex - 1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItem, false);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItem, false);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Restocking and Repricing<br/><br/>" +
                        "Price: " + selectedItem.getPrice() +
                        " <br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                regularMaintenance.getSlotInfoLabel().setText(infoText);
                regularMaintenance.getSystemMessage().setText("Selected: " + selectedItem.getType());
            } else {
                String infoText = "<html>Restocking and Repricing<br/><br/>" +
                        " <br/> Stock: " + selectedSlot.getItemStock() +
                        "<br/> [OUT OF STOCK] </html>";
                regularMaintenance.getSlotInfoLabel().setText(infoText);
                regularMaintenance.getSystemMessage().setText("Selected: " + selectedSlot.getAssignedItemType());
            }

        } else {
            regularMaintenance.getSlotInfoLabel().setText("");
            regularMaintenance.getSystemMessage().setText("");
        }
    }
}
