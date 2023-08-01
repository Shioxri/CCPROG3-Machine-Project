package Controller;

import Model.VendingMachine;
import View.RegularBuy;

public class RegularBuyController {
    private RegularBuy regularBuyMenu;

    public RegularBuyController(RegularBuy regularBuyMenu, RegVMMenuController regVMMenuController, VendingMachine vendingMachine){
        this.regularBuyMenu = regularBuyMenu;


        regularBuyMenu.getExitButton().addActionListener(e -> {
            regularBuyMenu.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });
    }
    private void updateGUI()
    {

    }

}
