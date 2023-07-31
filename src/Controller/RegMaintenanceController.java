package Controller;

import View.RegularMaintenance;

public class RegMaintenanceController {
    private RegularMaintenance regularMaintenance;

    public RegMaintenanceController(RegularMaintenance regularMaintenance, RegVMMenuController regVMMenuController){
        this.regularMaintenance = regularMaintenance;

        regularMaintenance.getExitButton().addActionListener(e -> {
            regularMaintenance.toggleFrame();
            regVMMenuController.getRegularVMMenu().toggleFrame();
        });
    }
}
