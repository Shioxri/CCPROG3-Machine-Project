package Controller;

import Model.Maintenance;
import Model.Slot;
import Model.SpecialVendingMachine;
import Model.VendingMachine;
import View.MainMenuGUI;
import View.RegularVMMenu;
import View.SpecialBuy;
import View.SpecialVMMenu;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuController implements ActionListener {

    private MainMenuGUI mainMenuGUI;


    public MainMenuController(MainMenuGUI mainMenuGUI)
    {
        this.mainMenuGUI = mainMenuGUI;

        mainMenuGUI.getRegularVMButton().addActionListener(e -> {
            mainMenuGUI.getFrame().setVisible(false);
            VendingMachine vendingMachine = new VendingMachine(); //instantiate from the vending machine class
            initializeVMInventory(vendingMachine);
            RegularVMMenu regularVMMenu = new RegularVMMenu();
            RegVMMenuController regVMMenuController =
                    new RegVMMenuController(regularVMMenu, this, vendingMachine);
            regularVMMenu.getFrame().setVisible(true);

        });
        mainMenuGUI.getSpecialVMButton().addActionListener(e -> {
            mainMenuGUI.getFrame().setVisible(false);
            SpecialVendingMachine vendingMachine = new SpecialVendingMachine(); //instantiate from the vending machine class
            initializeSVMInventory(vendingMachine);
            SpecialVMMenu specialVMMenu = new SpecialVMMenu();
            SpecVMMenuController specialVMMenuController =
                    new SpecVMMenuController(specialVMMenu, this, vendingMachine);
            specialVMMenu.getFrame().setVisible(true);

        });
        mainMenuGUI.getExitButton().addActionListener(e -> System.exit(0));

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void initializeVMInventory(VendingMachine vendingMachine)
    {
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> prevStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());

        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
        Maintenance.addAllToPrevStartingInventory(vendingMachine, prevStartingInventoryCopy);

    }
    
    private void initializeSVMInventory(SpecialVendingMachine vendingMachine)
    {
        ArrayList<Slot> startingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> prevStartingInventoryCopy = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());

        Maintenance.addAllToStartingInventory(vendingMachine, startingInventoryCopy);
        Maintenance.addAllToPrevStartingInventory(vendingMachine, prevStartingInventoryCopy);

            ArrayList<Slot> startingSpecialInventoryCopy = Maintenance.deepCopySlotArrayList((vendingMachine).getSpecialSlots());
            ArrayList<Slot> prevStartingSpecialInventoryCopy = Maintenance.deepCopySlotArrayList((vendingMachine).getSpecialSlots());

            Maintenance.addAllToStartingSpecialInventory(vendingMachine, startingSpecialInventoryCopy);
            Maintenance.addAllToPrevStartingSpecialInventory(vendingMachine, prevStartingSpecialInventoryCopy);

    }


    public MainMenuGUI getMainMenuGUI(){
        return mainMenuGUI;
    }
}

