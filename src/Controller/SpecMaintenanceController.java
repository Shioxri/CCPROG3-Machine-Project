package Controller;

import Model.Maintenance;
import Model.SpecialVendingMachine;
import Model.VendingMachine;
import View.SpecialBuy;
import View.SpecialMaintenance;
import View.SpecialVMMenu;

public class SpecMaintenanceController {
    private SpecialMaintenance specialMaintenance;

    SpecMaintenanceController(SpecialMaintenance specialMaintenance, SpecVMMenuController specVMMenuController, SpecialVendingMachine vendingMachine){
        this.specialMaintenance = specialMaintenance;

        updateDenomLabel(vendingMachine);

        specialMaintenance.getAddButton().addActionListener(e ->{
            int denomination = ((Integer) specialMaintenance.getDenominations().getSelectedItem());
            System.out.println("Denomination: "+denomination);
            Maintenance.replenishMoney(vendingMachine, denomination, 1 );
            updateDenomLabel(vendingMachine);
        });



        specialMaintenance.getExitButton().addActionListener(e -> {
            specialMaintenance.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });

    }

    private void updateDenomLabel(VendingMachine vendingMachine)
    {
        int totalMachineMoney = vendingMachine.getMoneyManager().getTotalMoneyFromList(vendingMachine.getMoneyManager().getStoredMoney());
        specialMaintenance.setMachineBalanceLabel(vendingMachine.getMoneyManager().getStoredMoney(), totalMachineMoney);
    }



}
