package Controller;

import Model.*;
import View.SpecialBuy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * The SpecialBuyController class handles the user interactions and logic for the SpecialBuy view.
 */
public class SpecialBuyController {
    private SpecialBuy specialBuyMenu;

    /**
     * Constructor for the SpecialBuyController class.
     *
     * @param specialBuyMenu      The SpecialBuy view instance.
     * @param specVMMenuController The SpecVMMenuController instance.
     * @param vendingMachine      The SpecialVendingMachine model instance.
     */
    SpecialBuyController(SpecialBuy specialBuyMenu, SpecVMMenuController specVMMenuController, SpecialVendingMachine vendingMachine) {
        this.specialBuyMenu = specialBuyMenu;

        // Create copies of original slots and special slots to restore in case of cancellation
        ArrayList<Slot> originalSlots = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> originalSpecialSlots = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());

        // Set the dropdown contents based on the vending machine model
        setDropdownContents(vendingMachine);

        // First fruits dropdown action listener
        specialBuyMenu.getFirstFruitsDropDown().addActionListener(e -> {
            int firstFruitSelectedIndex = specialBuyMenu.getFirstFruitsDropDown().getSelectedIndex();
            if (firstFruitSelectedIndex == 0) {
                // If no first fruit is selected, reset the side labels and update total price and calories
                updateSideLabels(specialBuyMenu, vendingMachine);
                specialBuyMenu.addToTotalPrice(0, 0);
                specialBuyMenu.addToTotalCals(0, 0);
            } else {
                // If a first fruit is selected, update the total price and calories and update the side labels
                Item selectedItem = vendingMachine.getSelectedItem(firstFruitSelectedIndex - 1, false);
                specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 0);
                specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 0);
                updateSideLabels(specialBuyMenu, vendingMachine);
            }
        });

        // Second fruits dropdown action listener
        specialBuyMenu.getSecondFruitsDropDown().addActionListener(e -> {
            int secondFruitSelectedIndex = specialBuyMenu.getSecondFruitsDropDown().getSelectedIndex();
            if (secondFruitSelectedIndex == 0) {
                updateSideLabels(specialBuyMenu, vendingMachine);
                specialBuyMenu.addToTotalPrice(0, 1);
                specialBuyMenu.addToTotalCals(0, 1);
            } else {
                Item selectedItem = vendingMachine.getSelectedItem(secondFruitSelectedIndex - 1, false);
                specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 1);
                specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 1);
                updateSideLabels(specialBuyMenu, vendingMachine);
            }
        });

        // Water type dropdown action listener
        specialBuyMenu.getWaterType().addActionListener(e -> {
            int waterTypeSelectedIndex = specialBuyMenu.getWaterType().getSelectedIndex();
            if (waterTypeSelectedIndex == 0) {
                updateSideLabels(specialBuyMenu, vendingMachine);
                specialBuyMenu.addToTotalPrice(0, 2);
                specialBuyMenu.addToTotalCals(0, 2);
            } else {
                Item selectedItem = vendingMachine.getSelectedItem(waterTypeSelectedIndex - 1, true);
                specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 2);
                specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 2);
                updateSideLabels(specialBuyMenu, vendingMachine);
            }
        });

        // Milk type dropdown action listener
        specialBuyMenu.getMilkType().addActionListener(e -> {
            int milkTypeSelectedIndex = specialBuyMenu.getMilkType().getSelectedIndex() + 2;
            if (milkTypeSelectedIndex == 2) {
                updateSideLabels(specialBuyMenu, vendingMachine);
                specialBuyMenu.addToTotalPrice(0, 3);
                specialBuyMenu.addToTotalCals(0, 3);
            } else {
                Item selectedItem = vendingMachine.getSelectedItem(milkTypeSelectedIndex - 1, true);
                specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 3);
                specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 3);
                updateSideLabels(specialBuyMenu, vendingMachine);
            }
        });

        // Ice type dropdown action listener
        specialBuyMenu.getIceType().addActionListener(e -> {
            int iceTypeSelectedIndex = specialBuyMenu.getIceType().getSelectedIndex() + 7;
            if (iceTypeSelectedIndex == 7) {
                updateSideLabels(specialBuyMenu, vendingMachine);
                specialBuyMenu.addToTotalPrice(0, 4);
                specialBuyMenu.addToTotalCals(0, 4);
            } else {
                Item selectedItem = vendingMachine.getSelectedItem(iceTypeSelectedIndex - 1, true);
                specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 4);
                specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 4);
                updateSideLabels(specialBuyMenu, vendingMachine);
            }
        });

        // Toppings type dropdown action listener
        specialBuyMenu.getToppingsType().addActionListener(e -> {
            int toppingsSelectedIndex = specialBuyMenu.getToppingsType().getSelectedIndex() + 9;
            if (toppingsSelectedIndex == 9) {
                updateSideLabels(specialBuyMenu, vendingMachine);
                specialBuyMenu.addToTotalPrice(0, 5);
                specialBuyMenu.addToTotalCals(0, 5);
            } else {
                Item selectedItem = vendingMachine.getSelectedItem(toppingsSelectedIndex - 1, true);
                specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 5);
                specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 5);
                updateSideLabels(specialBuyMenu, vendingMachine);
            }
        });

        // Add button action listener
        specialBuyMenu.getAddButton().addActionListener(e -> {
            playButtonClickSound();
            // Add the selected denomination to the temporary paid money
            vendingMachine.addTempPaidMoney((Integer) specialBuyMenu.getDenominations().getSelectedItem(), 1);
            specialBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
            specialBuyMenu.showAddedMoneyText();
        });

        //ActionListener for the denominations dropdown
        specialBuyMenu.getDenominations().addActionListener(e -> playButtonClickSound());


        // Buy button action listener
        specialBuyMenu.getBuyButton().addActionListener(e -> {
            // Get the selected indices for each dropdown
            int firstFruitIndex = specialBuyMenu.getFirstFruitsDropDown().getSelectedIndex();
            int secondFruitIndex = specialBuyMenu.getSecondFruitsDropDown().getSelectedIndex();
            int waterTypeIndex = specialBuyMenu.getWaterType().getSelectedIndex();
            int milkTypeIndex = specialBuyMenu.getMilkType().getSelectedIndex();
            int iceTypeIndex = specialBuyMenu.getIceType().getSelectedIndex();
            int toppingsTypeIndex = specialBuyMenu.getToppingsType().getSelectedIndex();
            boolean isInvalidOrder = false;

            // Create lists to hold selected items
            ArrayList<Item> selectedItems = new ArrayList<>();
            ArrayList<Item> selectedFruits = new ArrayList<>();
            ArrayList<Item> selectedLiquids = new ArrayList<>();
            Item iceType = null;
            Item toppingsType = null;

            // Check if the user's balance is sufficient to make the purchase
            if (vendingMachine.getUserBalance() >= specialBuyMenu.getTotalPrice()) {
                // Check if change can be returned
                if (vendingMachine.getMoneyManager().canReturnChange(specialBuyMenu.getTotalPrice())) {

                    // Check if both a first and second fruit are selected, and both a water type and milk type are selected
                    if ((firstFruitIndex != 0 || secondFruitIndex != 0) && (waterTypeIndex != 0 || milkTypeIndex != 0)) {

                        // Create a StringBuilder to hold system label messages
                        StringBuilder systemLabelMessage = new StringBuilder();

                        // Check if the first and second fruits are different and are available
                        if (firstFruitIndex != secondFruitIndex) {
                            systemLabelMessage.append("<html>List of out of stock items:<br/>");
                            if (firstFruitIndex != 0) {
                                if (!vendingMachine.getSelectedSlot(firstFruitIndex - 1, false).getItemArrayList().isEmpty()) {
                                    Item firstFruit = vendingMachine.dispenseSelectedItem(firstFruitIndex - 1, false);
                                    selectedItems.add(firstFruit);
                                    selectedFruits.add(firstFruit);
                                } else {
                                    isInvalidOrder = true;
                                    systemLabelMessage.append("First Fruit is out of stock or not available<br/>");
                                }
                            }

                            if (secondFruitIndex != 0) {
                                if (!vendingMachine.getSelectedSlot(secondFruitIndex - 1, false).getItemArrayList().isEmpty()) {
                                    Item secondFruit = vendingMachine.dispenseSelectedItem(secondFruitIndex - 1, false);
                                    selectedItems.add(secondFruit);
                                    selectedFruits.add(secondFruit);
                                } else {
                                    isInvalidOrder = true;
                                    systemLabelMessage.append("Second Fruit is out of stock or not available<br/>");
                                }
                            }
                        } else {
                            isInvalidOrder = true;
                            systemLabelMessage.append("Please select different fruits to combine!");
                        }

                        // Check if the water type is available
                        if (waterTypeIndex != 0) {
                            if (!vendingMachine.getSelectedSlot(waterTypeIndex - 1, true).getItemArrayList().isEmpty()) {
                                Item waterType = vendingMachine.dispenseSelectedItem(waterTypeIndex - 1, true);
                                selectedItems.add(waterType);
                                selectedLiquids.add(waterType);
                            } else {
                                isInvalidOrder = true;
                                systemLabelMessage.append("Water Type is out of stock or not available<br/>");
                            }
                        }

                        // Check if the milk type is available
                        if (milkTypeIndex != 0) {
                            if (!vendingMachine.getSelectedSlot(milkTypeIndex + 1, true).getItemArrayList().isEmpty()) {
                                Item milkType = vendingMachine.dispenseSelectedItem(milkTypeIndex + 1, true);
                                selectedItems.add(milkType);
                                selectedLiquids.add(milkType);
                            } else {
                                isInvalidOrder = true;
                                systemLabelMessage.append("Milk Type is out of stock or not available<br/>");
                            }
                        }

                        // Check if the ice type is available
                        if (iceTypeIndex != 0) {
                            if (!vendingMachine.getSelectedSlot(iceTypeIndex + 6, true).getItemArrayList().isEmpty()) {
                                iceType = vendingMachine.dispenseSelectedItem(iceTypeIndex + 6, true);
                                selectedItems.add(iceType);
                            } else {
                                isInvalidOrder = true;
                                systemLabelMessage.append("Ice Type is out of stock or not available<br/>");
                            }
                        }

                        // Check if the toppings type is available
                        if (toppingsTypeIndex != 0) {
                            if (!vendingMachine.getSelectedSlot(toppingsTypeIndex + 8, true).getItemArrayList().isEmpty()) {
                                toppingsType = vendingMachine.dispenseSelectedItem(toppingsTypeIndex + 8, true);
                                selectedItems.add(toppingsType);
                            } else {
                                isInvalidOrder = true;
                                systemLabelMessage.append("Toppings Type is out of stock or not available<br/>");
                            }
                        }

                        // Check if the order is valid and all items are available
                        if (!isInvalidOrder) {
                            playButtonBuySound();
                            // Update side labels
                            updateSideLabels(specialBuyMenu, vendingMachine);

                            // Calculate change and deposit money
                            int totalUserMoney = vendingMachine.getUserBalance();
                            int change = totalUserMoney - specialBuyMenu.getTotalPrice();
                            for (Item item : selectedItems) {
                                Maintenance.addSoldItems(vendingMachine, item.getType());
                            }
                            vendingMachine.getMoneyManager().depositMoney();
                            vendingMachine.getMoneyManager().returnChange(change);

                            // Update user balance and reset UI elements
                            specialBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
                            specialBuyMenu.resetTotalPrice();
                            specialBuyMenu.resetTotalCals();
                            resetDropdowns();

                            // Create success message
                            StringBuilder successMessage = new StringBuilder("<html>Preparing your custom fruit shake...<br/>");
                            successMessage.append("Blended Fruits: ");
                            for (Item item : selectedFruits) {
                                successMessage.append(item.getType()).append(" ");
                            }
                            successMessage.append("<br/>Poured Liquids: ");
                            for (Item liquid : selectedLiquids) {
                                successMessage.append(liquid.getType()).append(" ");
                            }
                            successMessage.append("<br/>");
                            if (iceType != null) {
                                successMessage.append("Used ice: ").append(iceType.getType()).append("<br/>");
                            }
                            if (toppingsType != null) {
                                successMessage.append("Topped with: ").append(toppingsType.getType()).append("<br/>");
                            }
                            successMessage.append("[Transaction Complete]<br/>");
                            successMessage.append("Order Complete! Enjoy your customized fruit shake!<br/>");
                            successMessage.append("Change Returned: Php ").append(change).append("</html>");

                            specialBuyMenu.getSystemMessage().setText("<html>Order Complete!<br/>Transaction Successful!<html>");

                            // Show the success message using a JOptionPane
                            JOptionPane.showMessageDialog(null, new JLabel(successMessage.toString()));

                        } else {
                            playButtonClickSound();
                            // Show error message for an invalid order
                            JOptionPane.showMessageDialog(null,new JLabel("A selected item is out of stock!", JLabel.CENTER),"Error!", JOptionPane.ERROR_MESSAGE);
                            specialBuyMenu.getSystemMessage().setText(systemLabelMessage.toString());
                        }

                    }
                    else
                    {
                        playButtonClickSound();
                        //Error message if minimum items for an order isn't met
                        resetDropdowns();
                        JOptionPane.showMessageDialog(null,new JLabel("Order requirements not met!", JLabel.CENTER),"Error!", JOptionPane.ERROR_MESSAGE);
                        specialBuyMenu.getSystemMessage().setText("<html>Invalid Order!" +
                                "<br/>Must have at least: 1 Fruit and 1 Liquid Type (Milk/Water)" +
                                "<br/>If buying 2 fruits, they must be unique from each other"+
                                "<br/>to complete an order, please try again!<html>");

                    }
                }
                else
                {
                    playButtonClickSound();
                    //Error message if machine doesn't have enough change
                    resetDropdowns();
                    JOptionPane.showMessageDialog(null,new JLabel("Insufficient change available.", JLabel.CENTER),"Error!", JOptionPane.ERROR_MESSAGE);
                    specialBuyMenu.getSystemMessage().setText("<html>Machine does not have enough change<br/>" +
                            "Order cancelled and money returned<html>");

                }
            }
            else
            {
                playButtonClickSound();
                //Error message if user balance is not enough for the order
                resetDropdowns();
                JOptionPane.showMessageDialog(null,new JLabel("Insufficient Balance!", JLabel.CENTER),"Error!", JOptionPane.ERROR_MESSAGE);
                specialBuyMenu.getSystemMessage().setText("<html>Invalid Order!<br/>" +
                        "Not enough balance inserted!<html>");
            }

        });

        // ActionListener for the Cancel button
        specialBuyMenu.getCancelButton().addActionListener(e -> {
            playButtonClickSound();
            // Clear the user's paid money and reset the UI
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            specialBuyMenu.defaultBalanceText();
            vendingMachine.restoreOriginalContents(originalSlots, originalSpecialSlots);
            resetDropdowns();

            // Display the cancellation message
            specialBuyMenu.getSystemMessage().setText("<html>Cleared user balance<br/>" +
                    "Returned User Money<br/>" +
                    "Cleared Order<br/>" +
                    "Returned Selected Items<br/>" +
                    "Successfully canceled the transaction </html>");
        });

        // ActionListener for the Exit button
        specialBuyMenu.getExitButton().addActionListener(e -> {
            playButtonClickSound();
            // Clear the user's paid money, hide the Special Buy menu, and show the Special VM Menu
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            specialBuyMenu.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });
    }

    /**
     * Set the contents of the dropdown menus in the Special Buy UI based on the items available in the vending machine.
     *
     * @param vendingMachine The SpecialVendingMachine object representing the vending machine.
     */
    private void setDropdownContents(SpecialVendingMachine vendingMachine) {
        specialBuyMenu.setFirstFruitsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
        specialBuyMenu.setSecondFruitsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
        specialBuyMenu.setWaterDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(), 0, 1));
        specialBuyMenu.setMilkDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(), 2, 6));
        specialBuyMenu.setIceDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(), 7, 8));
        specialBuyMenu.setToppingsDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(), 9, 10));
    }

    /**
     * Reset all dropdown menus in the Special Buy UI to their default values.
     */
    private void resetDropdowns() {
        specialBuyMenu.getFirstFruitsDropDown().setSelectedIndex(0);
        specialBuyMenu.getSecondFruitsDropDown().setSelectedIndex(0);
        specialBuyMenu.getWaterType().setSelectedIndex(0);
        specialBuyMenu.getMilkType().setSelectedIndex(0);
        specialBuyMenu.getIceType().setSelectedIndex(0);
        specialBuyMenu.getToppingsType().setSelectedIndex(0);
    }

    /**
     * Get a list of assigned item types for regular slots in the vending machine.
     *
     * @param slotTypes ArrayList of Slot objects representing regular slots in the vending machine.
     * @return ArrayList of strings containing the assigned item types for regular slots.
     */
    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }

    /**
     * Get a list of assigned item types for special slots in the vending machine within the specified range.
     *
     * @param slotTypes   ArrayList of Slot objects representing special slots in the vending machine.
     * @param lowerBound  The lower bound index of the range of special slots to consider.
     * @param upperBound  The upper bound index of the range of special slots to consider.
     * @return ArrayList of strings containing the assigned item types for special slots within the specified range.
     */
    private ArrayList<String> getSpecialSlotTypes(ArrayList<Slot> slotTypes, int lowerBound, int upperBound) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (int i = lowerBound; i < upperBound + 1; i++) {
            stringSlotTypes.add(slotTypes.get(i).getAssignedItemType());
        }
        return stringSlotTypes;
    }

    /**
     * Update the side labels in the Special Buy UI with details of the selected items.
     *
     * @param specialBuyMenu The SpecialBuy object representing the Special Buy UI.
     * @param vendingMachine The SpecialVendingMachine object representing the vending machine.
     */
    public void updateSideLabels(SpecialBuy specialBuyMenu, SpecialVendingMachine vendingMachine) {
        // Get the selected indices from dropdowns
        int firstFruitIndex = specialBuyMenu.getFirstFruitsDropDown().getSelectedIndex();
        int secondFruitIndex = specialBuyMenu.getSecondFruitsDropDown().getSelectedIndex();
        int waterTypeIndex = specialBuyMenu.getWaterType().getSelectedIndex();
        int milkTypeIndex = specialBuyMenu.getMilkType().getSelectedIndex();
        int iceTypeIndex = specialBuyMenu.getIceType().getSelectedIndex();
        int toppingsTypeIndex = specialBuyMenu.getToppingsType().getSelectedIndex();

        // Initialize StringBuilder for item details and order details
        StringBuilder infoText = new StringBuilder("<html>Item Details:<br/>Price | Calories | Stock<br/>");
        StringBuilder orderText = new StringBuilder();
        orderText.append("<html>Order Details<br/>Price: Php ").append(specialBuyMenu.getTotalPrice());
        orderText.append("<br/>Calories: ").append(specialBuyMenu.getTotalCals()).append(" kCal");
        orderText.append("<br/>Selected Items:<br/>");

        // Get and display details for each selected item
        if (firstFruitIndex != 0) {
            String firstFruit = (String) specialBuyMenu.getFirstFruitsDropDown().getSelectedItem();
            int price = vendingMachine.getSelectedItem(firstFruitIndex-1, false).getPrice();
            int calories = vendingMachine.getSelectedItem(firstFruitIndex-1, false).getCalorie();
            int stock = vendingMachine.getSelectedSlot(firstFruitIndex-1, false).getItemStock();

            orderText.append("<br/>First Fruit: ").append(firstFruit);
            infoText.append("<br/>First Fruit: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/>First Fruit: ");
            orderText.append("<br/>First Fruit: ");
        }

        if (secondFruitIndex != 0) {
            String secondFruit = (String) specialBuyMenu.getSecondFruitsDropDown().getSelectedItem();
            int price = vendingMachine.getSelectedItem(secondFruitIndex-1, false).getPrice();
            int calories = vendingMachine.getSelectedItem(secondFruitIndex-1, false).getCalorie();
            int stock = vendingMachine.getSelectedSlot(secondFruitIndex-1, false).getItemStock();

            orderText.append("<br/>Second Fruit: ").append(secondFruit);
            infoText.append("<br/>Second Fruit: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Second Fruit: ");
            orderText.append("<br/>Second Fruit: ");
        }
        if (waterTypeIndex != 0) {
            String waterType = (String) specialBuyMenu.getWaterType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(waterTypeIndex-1, true).getPrice();
            int calories = vendingMachine.getSelectedItem(waterTypeIndex-1, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(waterTypeIndex-1, true).getItemStock();

            orderText.append("<br/>Water Type: ").append(waterType);
            infoText.append("<br/>Water Type: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Water Type: ");
            orderText.append("<br/>Water Type: ");
        }
        if (milkTypeIndex != 0) {
            String milkType = (String) specialBuyMenu.getMilkType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(milkTypeIndex+1, true).getPrice();
            int calories = vendingMachine.getSelectedItem(milkTypeIndex+1, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(milkTypeIndex+1, true).getItemStock();

            orderText.append("<br/>Milk Type: ").append(milkType);
            infoText.append("<br/>Milk Type: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Milk Type: ");
            orderText.append("<br/>Milk Type: ");
        }
        if (iceTypeIndex != 0) {
            String iceType = (String) specialBuyMenu.getIceType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(iceTypeIndex+6, true).getPrice();
            int calories = vendingMachine.getSelectedItem(iceTypeIndex+6, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(iceTypeIndex+6, true).getItemStock();

            orderText.append("<br/>Ice Type: ").append(iceType);
            infoText.append("<br/>Ice Type: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Ice Type: ");
            orderText.append("<br/>Ice Type: ");
        }
        if (toppingsTypeIndex != 0) {
            String toppingsType = (String) specialBuyMenu.getToppingsType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(toppingsTypeIndex+8, true).getPrice();
            int calories = vendingMachine.getSelectedItem(toppingsTypeIndex+8, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(toppingsTypeIndex+8, true).getItemStock();

            orderText.append("<br/>Toppings: ").append(toppingsType);
            infoText.append("<br/>Toppings: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Toppings: ");
            orderText.append("<br/>Toppings: ");
        }

        // Append closing HTML tags and set the text for the side labels in the Special Buy UI
        infoText.append("</html>");
        specialBuyMenu.getInfoLabel().setText(infoText.toString());
        orderText.append("</html>");
        specialBuyMenu.getOrderLabel().setText(orderText.toString());
        specialBuyMenu.getSystemMessage().setText("");
    }


    /**

     Plays a button click sound when the button is clicked.
     The sound is played from the "assets/sfx.wav" file.
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
