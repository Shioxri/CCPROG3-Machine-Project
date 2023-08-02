package Controller;

import Model.SpecialVendingMachine;
import Model.VendingMachine;
import View.SpecialBuy;
import View.SpecialMaintenance;
import View.SpecialVMMenu;

public class SpecMaintenanceController {
    private SpecialMaintenance specialMaintenance;

    SpecMaintenanceController(SpecialMaintenance specialMaintenance, SpecVMMenuController specVMMenuController, SpecialVendingMachine vendingMachine){
        this.specialMaintenance = specialMaintenance;

        specialMaintenance.getExitButton().addActionListener(e -> {
            specialMaintenance.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });

        specialMaintenance.getswitchButton().addActionListener(e ->{
            System.exit(0);
        });
    }
}
