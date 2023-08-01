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
            vendingMachine.initializeInventory();
            RegularVMMenu regularVMMenu = new RegularVMMenu();
            RegVMMenuController regVMMenuController =
                    new RegVMMenuController(regularVMMenu, this, vendingMachine);
            regularVMMenu.getFrame().setVisible(true);

        });
        mainMenuGUI.getSpecialVMButton().addActionListener(e -> {
            mainMenuGUI.getFrame().setVisible(false);
            SpecialVendingMachine vendingMachine = new SpecialVendingMachine(); //instantiate from the vending machine class
            vendingMachine.initializeInventory();
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
}

