package Controller;

import View.RegularBuy;

public class RegularBuyController {
    private  RegularBuy regularBuy;

    public RegularBuyController(RegularBuy regularBuy, RegVMMenuController regVMMenuController){
        this.regularBuy = regularBuy;

        regularBuy.getExitButton().addActionListener(e -> {
            regularBuy.toggleFrame();
            regVMMenuController.getRegularVMMenu().toggleFrame();
        });
    }
}
