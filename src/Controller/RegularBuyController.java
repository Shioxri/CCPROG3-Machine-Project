package Controller;

import Model.VendingMachine;
import View.RegularBuy;

public class RegularBuyController {
    private  RegularBuy regularBuy;

    public RegularBuyController(RegularBuy regularBuy, RegVMMenuController regVMMenuController, VendingMachine vendingMachine){
        this.regularBuy = regularBuy;


        regularBuy.getExitButton().addActionListener(e -> {
            regularBuy.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });
    }
    private void updateGUI()
    {

    }

}
