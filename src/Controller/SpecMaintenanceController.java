package Controller;

import Model.*;
import View.SpecialMaintenance;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * The SpecMaintenanceController class handles the actions and logic for the special maintenance menu.
 */
public class SpecMaintenanceController {
    private SpecialMaintenance specialMaintenance;

    /**
     * Constructs the SpecMaintenanceController with the specified SpecialMaintenance menu, SpecVMMenuController, and SpecialVendingMachine.
     *
     * @param specialMaintenance   The SpecialMaintenance menu to associate with the controller.
     * @param specVMMenuController The SpecVMMenuController for main menu navigation.
     * @param vendingMachine      The SpecialVendingMachine representing the special vending machine.
     */
    SpecMaintenanceController(SpecialMaintenance specialMaintenance, SpecVMMenuController specVMMenuController, SpecialVendingMachine vendingMachine) {
        this.specialMaintenance = specialMaintenance;

        // Set the initial contents of dropdowns
        setRegularDropdownContents(vendingMachine);
        setSpecialDropdownContents(vendingMachine);
        updateDenomLabel(vendingMachine);

        // ActionListener for the "Add" button to replenish money
        specialMaintenance.getAddButton().addActionListener(e -> {
            playButtonClickSound();
            int denomination = ((Integer) specialMaintenance.getDenominations().getSelectedItem());
            Maintenance.replenishMoney(vendingMachine, denomination, 1);
            updateDenomLabel(vendingMachine);
        });

        // ActionListener for the "Instructions" button
        specialMaintenance.getInstructionsButton().addActionListener(e -> {
            playButtonClickSound();
            specialMaintenance.getSystemMessage().setText("<html>Instructions<br/>" +
                    "Top Left - Edit Item Slot" +
                    "<br/> Bottom Left - Add New Item(Note: make sure every text field is filled" +
                    "<br/> Right - Edit Vending Machine</html>");
        });

        // ActionListener for the dropdown selection of regular slots
        specialMaintenance.getRegularSlotsDropDown().addActionListener(e -> {
            playButtonClickSound();
            int selectedItemIndex = specialMaintenance.getRegularSlotsDropDown().getSelectedIndex();
            updateRegularInfoLabel(selectedItemIndex, vendingMachine);
        });

        // ActionListener for the dropdown selection of special slots
        specialMaintenance.getSpecialSlotsDropDown().addActionListener(e -> {
            playButtonClickSound();
            int selectedItemIndex = specialMaintenance.getSpecialSlotsDropDown().getSelectedIndex();
            updateSpecialInfoLabel(selectedItemIndex, vendingMachine);
        });

        // ActionListener for the "Change Regular Price" button
        specialMaintenance.getChangeRegularPriceButton().addActionListener(e -> {
            playButtonClickSound();
            if(!specialMaintenance.getChangePriceRegular().getText().isBlank() &&
                    !specialMaintenance.getChangePriceRegular().getText().equals("Enter New Price"))
            {
                int newPrice = Integer.parseInt(specialMaintenance.getChangePriceRegular().getText());
                int selectedItemIndex = specialMaintenance.getRegularSlotsDropDown().getSelectedIndex();
                if (selectedItemIndex != 0) {
                    Maintenance.updateItemPrices(vendingMachine, false, selectedItemIndex - 1, newPrice);
                    updateRegularInfoLabel(selectedItemIndex, vendingMachine);
                } else {
                    specialMaintenance.getSystemMessage().setText("<html>Please select an item first!</html>");
                }
            }
            else
            {
                specialMaintenance.getSystemMessage().setText("<html>Please enter a value!</html>");
            }

        });

        // ActionListener for the "Change Special Price" button
        specialMaintenance.getChangeSpecialPriceButton().addActionListener(e -> {
            playButtonClickSound();
            if(!specialMaintenance.getChangePriceSpecial().getText().isBlank() &&
                    !specialMaintenance.getChangePriceSpecial().getText().equals("Enter New Price"))
            {
                int newPrice = Integer.parseInt(specialMaintenance.getChangePriceSpecial().getText());
                int selectedItemIndex = specialMaintenance.getSpecialSlotsDropDown().getSelectedIndex();
                if (selectedItemIndex != 0) {
                    Maintenance.updateItemPrices(vendingMachine, true, selectedItemIndex - 1, newPrice);
                    updateSpecialInfoLabel(selectedItemIndex, vendingMachine);
                } else {
                    specialMaintenance.getSystemMessage().setText("<html>Please select an item first!</html>");
                }
            }
            else
            {
                specialMaintenance.getSystemMessage().setText("<html>Please enter a value!</html>");
            }

        });

        // ActionListener for the "Restock Regular" button
        specialMaintenance.getRestockRegular().addActionListener(e -> {
            playButtonClickSound();
            int selectedItemIndex = specialMaintenance.getRegularSlotsDropDown().getSelectedIndex();
            if (selectedItemIndex != 0) {
                if (vendingMachine.getSelectedSlot(selectedItemIndex - 1, false).getItemStock() <= 5) {
                    updateVMInventoryAndRestock(vendingMachine, selectedItemIndex, false);
                    updateRegularInfoLabel(selectedItemIndex, vendingMachine);
                } else {
                    specialMaintenance.getRegularSlotsDropDown().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,
                            new JLabel("Item Stock is more than 5!", JLabel.CENTER), "StockLeft",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for the "Restock Special" button
        specialMaintenance.getRestockSpecial().addActionListener(e -> {
            playButtonClickSound();
            int selectedItemIndex = specialMaintenance.getSpecialSlotsDropDown().getSelectedIndex();
            if (selectedItemIndex != 0) {
                if (vendingMachine.getSelectedSlot(selectedItemIndex - 1, true).getItemStock() <= 5) {
                    updateVMInventoryAndRestock(vendingMachine, selectedItemIndex, true);
                    updateSpecialInfoLabel(selectedItemIndex, vendingMachine);
                } else {
                    specialMaintenance.getSpecialSlotsDropDown().setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null,
                            new JLabel("Item Stock is more than 5!", JLabel.CENTER), "StockLeft",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for the "Add Item" button
        specialMaintenance.getAddItem().addActionListener(e -> {
            playButtonClickSound();
            String name = specialMaintenance.getSetName().getText().trim();
            String priceText = specialMaintenance.getSetPrice().getText().trim();
            String caloriesText = specialMaintenance.getSetCalories().getText().trim();

            if (name.isEmpty() || priceText.isEmpty() || caloriesText.isEmpty()) {
                specialMaintenance.getSystemMessage().setText("Please correctly input all the necessary details for an item!");
            } else {
                try {
                    int price = Integer.parseInt(priceText);
                    int calories = Integer.parseInt(caloriesText);
                    updateVMInventoryAndAddStock(vendingMachine, name, price, calories);
                    specialMaintenance.getRegularSlotsDropDown().addItem(name);
                    specialMaintenance.getSystemMessage().setText("Successfully added "+name+"!");
                    specialMaintenance.getSetName().setText("Enter Item Name");
                    specialMaintenance.getSetPrice().setText("Enter Item Price");
                    specialMaintenance.getSetCalories().setText("Enter Item Calories");
                } catch (NumberFormatException n) {
                    specialMaintenance.getSystemMessage().setText("Please enter valid integers for the price and calories!");
                }
            }


        });

        //ActionListener for the denominations dropdown
        specialMaintenance.getDenominations().addActionListener(e -> playButtonClickSound());

        // ActionListener for the "Collect Money" button
        specialMaintenance.getCollectMoney().addActionListener(e -> {

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

        // ActionListener for the "Exit" button (Go back prev menu)
        specialMaintenance.getExitButton().addActionListener(e -> {
            playButtonClickSound();
            specialMaintenance.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });

        //ActionListener for the "Quit" button (Exit program)
        specialMaintenance.getQuitButton().addActionListener(e -> {
            playButtonClickSound();
            System.exit(0);
        });

        // ActionListener for the "Print Summary" button
        specialMaintenance.getPrintSummary().addActionListener(e -> {
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
        specialMaintenance.setMachineBalanceLabel(vendingMachine.getMoneyManager().getStoredMoney(), totalMachineMoney);
    }

    // Private helper method to restock items and update the inventory
    private void updateVMInventoryAndRestock(VendingMachine vendingMachine, int selectedItemIndex, boolean isSpecialSlot) {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> specialEndingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        Maintenance.restockItem(vendingMachine, selectedItemIndex - 1, isSpecialSlot);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);

        ArrayList<Slot> specialPrevStartingInventory = Maintenance.deepCopySlotArrayList(vendingMachine.getPrevStartingInventory());
        Maintenance.addAllToPrevStartingSpecialInventory(vendingMachine, specialPrevStartingInventory);
        Maintenance.addAllToEndingSpecialInventory(vendingMachine, specialEndingInventoryCopy);
        ArrayList<Slot> specialStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToStartingSpecialInventory(vendingMachine, specialStartingInventoryCopy);
    }

    // Private helper method to add new items to the inventory and update dropdown contents
    private void updateVMInventoryAndAddStock(VendingMachine vendingMachine, String newType, int newPrice, int newCals) {
        ArrayList<Slot> endingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> specialEndingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        Maintenance.stockNewItems(vendingMachine, newType, newPrice, newCals);
        ArrayList<Slot> startingPrevInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToPrevStartingInventory(vendingMachine, startingPrevInventoryCopy);
        Maintenance.addAllToEndingInventory(vendingMachine, endingInventoryCopy);
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);

        ArrayList<Slot> specialPrevStartingInventory = Maintenance.deepCopySlotArrayList(vendingMachine.getPrevStartingInventory());
        Maintenance.addAllToPrevStartingSpecialInventory(vendingMachine, specialPrevStartingInventory);
        Maintenance.addAllToEndingSpecialInventory(vendingMachine, specialEndingInventoryCopy);
        ArrayList<Slot> specialStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getStartingInventory());
        Maintenance.addAllToStartingSpecialInventory(vendingMachine, specialStartingInventoryCopy);
    }

    // Private helper method to set the dropdown contents for regular slots
    private void setRegularDropdownContents(VendingMachine vendingMachine) {
        specialMaintenance.setRegularSlotsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
    }

    // Private helper method to set the dropdown contents for special slots
    private void setSpecialDropdownContents(VendingMachine vendingMachine) {
        specialMaintenance.setSpecialSlotsDropDown(getSlotTypes(vendingMachine.getSpecialSlots()));
    }

    // Private helper method to extract the slot types as a list of strings
    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }

    /**
     * Updates the information label for the selected regular item slot.
     *
     * @param selectedItemIndex The index of the selected item in the dropdown.
     * @param vendingMachine    The SpecialVendingMachine containing the items and slots.
     */
    public void updateRegularInfoLabel(int selectedItemIndex, SpecialVendingMachine vendingMachine) {
        if (selectedItemIndex != 0) {
            int chosenItem = selectedItemIndex - 1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItem, false);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItem, false);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Price: " + selectedItem.getPrice() +
                        "<br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                specialMaintenance.getRegularSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: " + selectedItem.getType());
            } else {
                int price = vendingMachine.getSelectedSlot(chosenItem, false).getAssignedItemPrice();
                String infoText = "<html>Price: " + price +
                        "<br/>Item: [" + selectedSlot.getAssignedItemType() + "] IS OUT OF STOCK!</html>";
                specialMaintenance.getRegularSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: " + selectedSlot.getAssignedItemType());
            }
        } else {
            specialMaintenance.getRegularSlotLabel().setText("");
            specialMaintenance.getSystemMessage().setText("");
        }
    }

    /**
     * Updates the information label for the selected special item slot.
     *
     * @param selectedItemIndex The index of the selected item in the dropdown.
     * @param vendingMachine    The SpecialVendingMachine containing the items and slots.
     */
    public void updateSpecialInfoLabel(int selectedItemIndex, SpecialVendingMachine vendingMachine) {
        if (selectedItemIndex != 0) {
            int chosenItem = selectedItemIndex - 1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItem, true);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItem, true);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Price: " + selectedItem.getPrice() +
                        "<br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                specialMaintenance.getSpecialSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: " + selectedItem.getType());
            } else {
                int price = vendingMachine.getSelectedSlot(chosenItem, true).getAssignedItemPrice();
                String infoText = "<html>Price: " + price +
                        "<br/>Item: [" + selectedSlot.getAssignedItemType() + "] IS OUT OF STOCK!</html>";
                specialMaintenance.getSpecialSlotLabel().setText(infoText);
                specialMaintenance.getSystemMessage().setText("Selected: " + selectedSlot.getAssignedItemType());
            }
        } else {
            specialMaintenance.getSpecialSlotLabel().setText("");
            specialMaintenance.getSystemMessage().setText("");
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
