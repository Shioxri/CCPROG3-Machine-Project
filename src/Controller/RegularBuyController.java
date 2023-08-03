package Controller;

import Model.Item;
import Model.Maintenance;
import Model.Slot;
import Model.VendingMachine;
import View.RegularBuy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * The RegularBuyController class handles the actions and logic for the regular buy menu.
 */
public class RegularBuyController {
    private RegularBuy regularBuyMenu;

    /**
     * Constructs the RegularBuyController with the specified RegularBuy menu, RegVMMenuController, and VendingMachine.
     *
     * @param regularBuyMenu      The RegularBuy menu to associate with the controller.
     * @param regVMMenuController The RegVMMenuController for main menu navigation.
     * @param vendingMachine      The VendingMachine representing the regular vending machine.
     */
    public RegularBuyController(RegularBuy regularBuyMenu, RegVMMenuController regVMMenuController, VendingMachine vendingMachine) {
        this.regularBuyMenu = regularBuyMenu;

        setDropdownContents(vendingMachine);
        // ActionListener for the "Add" button
        regularBuyMenu.getAddButton().addActionListener(e -> {
            playButtonClickSound();
            vendingMachine.addTempPaidMoney((Integer) regularBuyMenu.getDenominations().getSelectedItem(), 1);
            regularBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
            regularBuyMenu.showAddedMoneyText();
        });

        // ActionListener for the "Exit" button
        regularBuyMenu.getExitButton().addActionListener(e -> {
            playButtonClickSound();
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            regularBuyMenu.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });

        // ActionListener for the item selection dropdown
        regularBuyMenu.getRegularItems().addActionListener(e -> {
            playButtonClickSound();
            int selectedItemIndex = regularBuyMenu.getRegularItems().getSelectedIndex();
            updateInfoLabel(selectedItemIndex, vendingMachine);
        });

        //ActionListener for the denominations dropdown
        regularBuyMenu.getDenominations().addActionListener(e -> playButtonClickSound());

        // ActionListener for the "Buy" button
        regularBuyMenu.getBuyButton().addActionListener(e -> {
            int selectedItemIndex = regularBuyMenu.getRegularItems().getSelectedIndex();
            int errorType = vendingMachine.checkInputValidity(selectedItemIndex, false);
            if (errorType == 0) {
                playButtonBuySound();
                Item dispensedItem = vendingMachine.dispenseSelectedItem(selectedItemIndex - 1, false);
                updateInfoLabel(selectedItemIndex, vendingMachine);
                int change = vendingMachine.getUserBalance() - dispensedItem.getPrice();
                vendingMachine.confirmTransaction(selectedItemIndex - 1);

                regularBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
                Maintenance.addSoldItems(vendingMachine, dispensedItem.getType());
                regularBuyMenu.getSystemMessage().setText("<html>[Transaction Successful!]<br/>" +
                        "Dispensed " + dispensedItem.getType() + "<br/>" +
                        "Return Change: " + change + "</html>");

                JOptionPane.showMessageDialog(null,
                        new JLabel("Successfully Bought " + dispensedItem.getType() + "!", JLabel.CENTER), "Successful Transaction"
                        , JOptionPane.PLAIN_MESSAGE);
            } else {
                playButtonClickSound();
                regularBuyMenu.setTextForInvalid(errorType);
            }
        });

        // ActionListener for the "Cancel" button
        regularBuyMenu.getCancelButton().addActionListener(e -> {
            playButtonClickSound();
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            regularBuyMenu.defaultBalanceText();
            regularBuyMenu.getRegularItems().setSelectedIndex(0);
            regularBuyMenu.getSystemMessage().setText("<html>Cleared user balance<br/>" +
                    "Returned User Money<br/>" +
                    "Cleared item selection<br/>" +
                    "Successfully canceled the transaction </html>");
            JOptionPane.showMessageDialog(null, new JLabel("Transaction Canceled!", JLabel.CENTER), "Canceled Transaction", JOptionPane.PLAIN_MESSAGE);
        });
    }

    // Private helper method to set the dropdown contents based on the vending machine's slot types
    private void setDropdownContents(VendingMachine vendingMachine) {
        regularBuyMenu.setRegularItems(getSlotTypes(vendingMachine.getSlotArrayList()));
    }

    // Private helper method to extract the slot types as a list of strings
    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }

    // Private helper method to update the info label based on the selected item index
    public void updateInfoLabel(int selectedItemIndex, VendingMachine vendingMachine) {
        if (selectedItemIndex != 0) {
            int chosenItem = selectedItemIndex - 1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItem, false);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItem, false);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Price: " + selectedItem.getPrice() +
                        "<br/>Calories: " + selectedItem.getCalorie() +
                        " kCal<br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                regularBuyMenu.getInfoLabel().setText(infoText);
                regularBuyMenu.getSystemMessage().setText("Selected: " + selectedItem.getType());
            } else {
                int price = vendingMachine.getSelectedSlot(chosenItem, false).getAssignedItemPrice();
                System.out.println(price);
                String infoText = "<html>Price: " + price + "<br/>Item: [" + selectedSlot.getAssignedItemType() + "] IS OUT OF STOCK!</html>";
                regularBuyMenu.getInfoLabel().setText(infoText);
                regularBuyMenu.getSystemMessage().setText("Selected: " + selectedSlot.getAssignedItemType());
            }

        } else {
            regularBuyMenu.getInfoLabel().setText("");
            regularBuyMenu.getSystemMessage().setText("");
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
     * Plays a sound when the buy button is clicked during a purchase action.
     */
    private void playButtonBuySound() {
        try {
            File soundFile = new File("assets/buysfx.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
