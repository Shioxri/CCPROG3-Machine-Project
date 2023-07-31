package Controller;

import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecVMMenuController implements ActionListener {
    private SpecialVMMenu specialVMMenu;



    public SpecVMMenuController(SpecialVMMenu specialVMMenu, MainMenuController mainMenuController){
        this.specialVMMenu = specialVMMenu;

        specialVMMenu.getUseVMButton().addActionListener(e -> {
            specialVMMenu.toggleFrame();
            SpecialBuy specialBuyMenu = new SpecialBuy();
            SpecialBuyController specialBuyController = new SpecialBuyController(specialBuyMenu, this);

        });
        specialVMMenu.getMaintenanceButton().addActionListener(e -> {
            specialVMMenu.toggleFrame();
            SpecialMaintenance specialMaintenanceMenu = new SpecialMaintenance();
            SpecMaintenanceController specMaintenanceController = new SpecMaintenanceController(specialMaintenanceMenu, this);
        });
        specialVMMenu.getBackButton().addActionListener(e -> {
            specialVMMenu.toggleFrame();
            mainMenuController.getMainMenuGUI().toggleFrame();
        });
    }

    public SpecialVMMenu getSpecialVMMenu() {
        return specialVMMenu;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
