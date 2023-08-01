package Controller;

import Model.VendingMachine;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegVMMenuController implements ActionListener {
    private RegularVMMenu regularVMMenu;

    public RegVMMenuController(RegularVMMenu regularVMMenu, MainMenuController mainMenuController, VendingMachine vendingMachine)
    {
        this.regularVMMenu = regularVMMenu;

        regularVMMenu.getUseVMButton().addActionListener(e -> {
            regularVMMenu.getFrame().setVisible(false);
            RegularBuy regularBuyMenu = new RegularBuy();
            RegularBuyController regularBuyController =
                    new RegularBuyController(regularBuyMenu, this, vendingMachine);
            regularBuyMenu.getFrame().setVisible(true);
        });
        regularVMMenu.getMaintenanceButton().addActionListener(e -> {
            regularVMMenu.getFrame().setVisible(false);
            RegularMaintenance regularMaintenanceMenu = new RegularMaintenance();
            RegMaintenanceController regMaintenanceController =
                    new RegMaintenanceController(regularMaintenanceMenu, this, vendingMachine);
            regularMaintenanceMenu.getFrame().setVisible(true);
        });
        regularVMMenu.getBackButton().addActionListener(e -> {
            regularVMMenu.getFrame().setVisible(false);
            mainMenuController.getMainMenuGUI().getFrame().setVisible(true);
        });

    }

    public RegularVMMenu getRegularVMMenu(){
        return regularVMMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
