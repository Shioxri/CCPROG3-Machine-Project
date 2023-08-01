package Controller;

import Model.VendingMachine;
import View.RegularMaintenance;

public class RegMaintenanceController {
    private RegularMaintenance regularMaintenance;

    public RegMaintenanceController(RegularMaintenance regularMaintenance, RegVMMenuController regVMMenuController, VendingMachine vendingMachine){
        this.regularMaintenance = regularMaintenance;

        regularMaintenance.getExitButton().addActionListener(e -> {
            regularMaintenance.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });
    }
}
