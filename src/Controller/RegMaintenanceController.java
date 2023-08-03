package Controller;

import Model.Item;
import Model.Maintenance;
import Model.Slot;
import Model.VendingMachine;
import View.RegularMaintenance;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
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

        // Exit button action listener (Go back prev menu)
        regularMaintenance.getExitButton().addActionListener(e -> {
            playButtonClickSound();
            regularMaintenance.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });

        //Quit button action listener (Exit program)
        regularMaintenance.getQuitButton().addActionListener(e -> {
            playButtonClickSound();
            System.exit(0);
        });

        //ActionListener for the denominations dropdown
        regularMaintenance.getDenominations().addActionListener(e -> playButtonClickSound());

        // Instructions button action listener
        regularMaintenance.getInstructionsButton().addActionListener(e -> {
            playButtonClickSound();
            regularMaintenance.getSystemMessage().setText("<html>Instructions<br/>" +
                    "Top Left - Edit Item Slot" +
                    "<br/> Bottom Left - Add New Item(Note: make sure every text field is filled" +
                    "<br/> Right - Edit Vending Machine</html>");
        });

        // Add item button action listener
        regularMaintenance.getAddItem().addActionListener(e -> {
            playButtonClickSound();
            String name = regularMaintenance.getSetName().getText().trim();
            String priceText = regularMaintenance.getSetPrice().getText().trim();
            String caloriesText = regularMaintenance.getSetCalories().getText().trim();

            if (name.isEmpty() || priceText.isEmpty() || caloriesText.isEmpty()) {
                regularMaintenance.getSystemMessage().setText("Please correctly input all the necessary details for an item!");
            } else {
                try {
                    int price = Integer.parseInt(priceText);
                    int calories = Integer.parseInt(caloriesText);
                    updateVMInventoryAndAddStock(vendingMachine, name, price, calories);
                    regularMaintenance.getSlotsDropdown().addItem(name);
                    regularMaintenance.getSystemMessage().setText("Successfully added "+name+"!");
                    regularMaintenance.getSetName().setText("Enter Item Name");
                    regularMaintenance.getSetPrice().setText("Enter Item Price");
                    regularMaintenance.getSetCalories().setText("Enter Item Calories");
                } catch (NumberFormatException n) {
                    regularMaintenance.getSystemMessage().setText("Please enter valid integers for the price and calories!");
                }
            }


        });

        // Change price button action listener
        regularMaintenance.getChangePriceButton().addActionListener(e -> {
            playButtonClickSound();
            if(!regularMaintenance.getChangePrice().getText().isBlank() &&
                    !regularMaintenance.getChangePrice().getText().equals("Enter New Price"))
            {
                int newPrice = Integer.parseInt(regularMaintenance.getChangePrice().getText());
                int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
                if (selectedItemIndex != 0) {
                    Maintenance.updateItemPrices(vendingMachine, false, selectedItemIndex - 1, newPrice);
                    updateInfoLabel(selectedItemIndex, vendingMachine);
                } else {
                    regularMaintenance.getSystemMessage().setText("<html>Please select an item first!</html>");
                }
            }
            else
            {
                regularMaintenance.getSystemMessage().setText("<html>Please select an item first!</html>");
            }

        });

        // Collect money button action listener
        regularMaintenance.getCollectMoney().addActionListener(e -> {

            int total = vendingMachine.getMoneyManager().getTotalMoneyFromList(vendingMachine.getMoneyManager().getStoredMoney());
            if(total>0)
            {
                playButtonCollectSound();
                Maintenance.collectMoney(vendingMachine);
                JOptionPane.showMessageDialog(null, "Collected: Php [ "+total+" ] !", "Collect Money",
                        JOptionPane.INFORMATION_MESSAGE);
                updateDenomLabel(vendingMachine);
            }
            else
            {
                playButtonClickSound();
                JOptionPane.showMessageDialog(null, "No money to be collected!", "Collect Money",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        });

        // Add button action listener
        regularMaintenance.getAddButton().addActionListener(e -> {
            playButtonClickSound();
            int denomination = ((Integer) regularMaintenance.getDenominations().getSelectedItem());
            Maintenance.replenishMoney(vendingMachine, denomination, 1);
            updateDenomLabel(vendingMachine);
        });

        // Dropdown action listener
        regularMaintenance.getSlotsDropdown().addActionListener(e -> {
            playButtonClickSound();
            int selectedItemIndex = regularMaintenance.getSlotsDropdown().getSelectedIndex();
            updateInfoLabel(selectedItemIndex, vendingMachine);
        });

        // Restock button action listener
        regularMaintenance.getRestockButton().addActionListener(e -> {
            playButtonClickSound();
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
            playButtonClickSound();
            String finalReport = Maintenance.getSalesReport(vendingMachine);
            JTextArea textArea = new JTextArea(finalReport);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setFont(new Font("Century Gothic", Font.BOLD, 15));
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "Print Sales Summary",
                    JOptionPane.INFORMATION_MESSAGE);
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
                        "Price: " + selectedSlot.getAssignedItemPrice() +
                        "<br/>Item: [" + selectedSlot.getAssignedItemType() + "] IS OUT OF STOCK!</html>";
                regularMaintenance.getSlotInfoLabel().setText(infoText);
                regularMaintenance.getSystemMessage().setText("Selected: " + selectedSlot.getAssignedItemType());
            }

        } else {
            regularMaintenance.getSlotInfoLabel().setText("");
            regularMaintenance.getSystemMessage().setText("");
        }
    }

    /**

     Plays a button click sound when the button is clicked.
     The sound is played from the "assets/sfx.wav" file.
     If an error occurs while playing the sound, the exception is printed to the standard error stream.

     */
    private void playButtonClickSound() {
        try {
            File soundFile = new File("assets/sfx.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**

     Plays a "ka-ching!" sound when the collect money button is clicked.
     The sound is played from the "assets/sfx.wav" file.
     If an error occurs while playing the sound, the exception is printed to the standard error stream.

     */
    private void playButtonCollectSound() {
        try {
            File soundFile = new File("assets/moneysfx.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
