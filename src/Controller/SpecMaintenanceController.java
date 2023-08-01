package Controller;

import Model.VendingMachine;
import View.SpecialMaintenance;
import View.SpecialVMMenu;

public class SpecMaintenanceController {
    private SpecialMaintenance specialMaintenance;

    SpecMaintenanceController(SpecialMaintenance specialMaintenance, SpecVMMenuController specVMMenuController, VendingMachine vendingMachine){
        this.specialMaintenance = specialMaintenance;

        specialMaintenance.getExitButton().addActionListener(e -> {
            specialMaintenance.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });
    }
}
