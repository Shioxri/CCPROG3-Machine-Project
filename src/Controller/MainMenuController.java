package Controller;

import Model.Maintenance;
import Model.Slot;
import Model.SpecialVendingMachine;
import Model.VendingMachine;
import View.MainMenuGUI;
import View.RegularVMMenu;
import View.SpecialVMMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The MainMenuController class handles the main menu actions and navigation.
 */
public class MainMenuController implements ActionListener {

    private MainMenuGUI mainMenuGUI;

    /**
     * Constructs the MainMenuController with the specified MainMenuGUI.
     *
     * @param mainMenuGUI The MainMenuGUI to associate with the controller.
     */
    public MainMenuController(MainMenuGUI mainMenuGUI) {
        this.mainMenuGUI = mainMenuGUI;

        // ActionListener for the "Regular Vending Machine" button
        mainMenuGUI.getRegularVMButton().addActionListener(e -> {
            mainMenuGUI.getFrame().setVisible(false);

            // Instantiate a new VendingMachine and initialize its inventory
            VendingMachine vendingMachine = new VendingMachine();
            initializeVMInventory(vendingMachine);

            // Show the Regular Vending Machine menu
            RegularVMMenu regularVMMenu = new RegularVMMenu();
            RegVMMenuController regVMMenuController =
                    new RegVMMenuController(regularVMMenu, this, vendingMachine);
            regularVMMenu.getFrame().setVisible(true);
        });

        // ActionListener for the "Special Vending Machine" button
        mainMenuGUI.getSpecialVMButton().addActionListener(e -> {
            mainMenuGUI.getFrame().setVisible(false);

            // Instantiate a new SpecialVendingMachine and initialize its inventory
            SpecialVendingMachine vendingMachine = new SpecialVendingMachine();
            initializeSVMInventory(vendingMachine);

            // Show the Special Vending Machine menu
            SpecialVMMenu specialVMMenu = new SpecialVMMenu();
            SpecVMMenuController specialVMMenuController =
                    new SpecVMMenuController(specialVMMenu, this, vendingMachine);
            specialVMMenu.getFrame().setVisible(true);
        });

        // ActionListener for the "Exit" button
        mainMenuGUI.getExitButton().addActionListener(e -> System.exit(0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // No action required for the main menu
    }

    /**
     * Initializes the regular vending machine inventory by copying and adding slots to the vending machine.
     *
     * @param vendingMachine The VendingMachine to initialize.
     */
    private void initializeVMInventory(VendingMachine vendingMachine) {
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> prevStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());

        // Set the starting and previous starting inventory
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
        Maintenance.addAllToPrevStartingInventory(vendingMachine, prevStartingInventoryCopy);
    }

    /**
     * Initializes the special vending machine inventory by copying and adding slots to the vending machine.
     *
     * @param vendingMachine The SpecialVendingMachine to initialize.
     */
    private void initializeSVMInventory(SpecialVendingMachine vendingMachine) {
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> prevStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());

        // Set the starting and previous starting inventory for regular slots
        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
        Maintenance.addAllToPrevStartingInventory(vendingMachine, prevStartingInventoryCopy);

        ArrayList<Slot> startingSpecialInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        ArrayList<Slot> prevStartingSpecialInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());

        // Set the starting and previous starting inventory for special slots
        Maintenance.addAllToStartingSpecialInventory(vendingMachine, startingSpecialInventoryCopy);
        Maintenance.addAllToPrevStartingSpecialInventory(vendingMachine, prevStartingSpecialInventoryCopy);
    }

    /**
     * Returns the MainMenuGUI associated with this controller.
     *
     * @return The MainMenuGUI.
     */
    public MainMenuGUI getMainMenuGUI() {
        return mainMenuGUI;
    }
}
