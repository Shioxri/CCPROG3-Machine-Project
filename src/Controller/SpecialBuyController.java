package Controller;

import Model.VendingMachine;
import View.SpecialBuy;
import View.SpecialMaintenance;

public class SpecialBuyController {
    private SpecialBuy specialBuy;

    SpecialBuyController(SpecialBuy specialBuy, SpecVMMenuController specVMMenuController, VendingMachine vendingMachine){
        this.specialBuy = specialBuy;

        specialBuy.getExitButton().addActionListener(e -> {
            specialBuy.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });
    }
}
