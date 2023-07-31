package Controller;

import View.SpecialBuy;
import View.SpecialMaintenance;

public class SpecialBuyController {
    private SpecialBuy specialBuy;

    SpecialBuyController(SpecialBuy specialBuy, SpecVMMenuController specVMMenuController){
        this.specialBuy = specialBuy;

        specialBuy.getExitButton().addActionListener(e -> {
            specialBuy.toggleFrame();
            specVMMenuController.getSpecialVMMenu().toggleFrame();
        });
    }
}
