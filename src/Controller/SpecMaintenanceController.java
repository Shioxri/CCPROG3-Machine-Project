package Controller;

import View.SpecialMaintenance;
import View.SpecialVMMenu;

public class SpecMaintenanceController {
    private SpecialMaintenance specialMaintenance;

    SpecMaintenanceController(SpecialMaintenance specialMaintenance, SpecVMMenuController specVMMenuController){
        this.specialMaintenance = specialMaintenance;

        specialMaintenance.getExitButton().addActionListener(e -> {
            specialMaintenance.toggleFrame();
            specVMMenuController.getSpecialVMMenu().toggleFrame();
        });
    }
}
