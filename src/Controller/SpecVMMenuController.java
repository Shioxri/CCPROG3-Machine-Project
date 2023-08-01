package Controller;

import Model.VendingMachine;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecVMMenuController implements ActionListener {
    private SpecialVMMenu specialVMMenu;



    public SpecVMMenuController(SpecialVMMenu specialVMMenu, MainMenuController mainMenuController, VendingMachine vendingMachine){
        this.specialVMMenu = specialVMMenu;

        specialVMMenu.getUseVMButton().addActionListener(e -> {
            specialVMMenu.getFrame().setVisible(false);
            SpecialBuy specialBuyMenu = new SpecialBuy();
            SpecialBuyController specialBuyController =
                    new SpecialBuyController(specialBuyMenu, this, vendingMachine);
            specialBuyMenu.getFrame().setVisible(true);
        });
        specialVMMenu.getMaintenanceButton().addActionListener(e -> {
            specialVMMenu.getFrame().setVisible(false);
            SpecialMaintenance specialMaintenanceMenu = new SpecialMaintenance();
            SpecMaintenanceController specMaintenanceController =
                    new SpecMaintenanceController(specialMaintenanceMenu, this, vendingMachine);
            specialMaintenanceMenu.getFrame().setVisible(true);
        });
        specialVMMenu.getBackButton().addActionListener(e -> {
            specialVMMenu.getFrame().setVisible(false);
            mainMenuController.getMainMenuGUI().getFrame().setVisible(true);
        });
    }

    public SpecialVMMenu getSpecialVMMenu() {
        return specialVMMenu;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
