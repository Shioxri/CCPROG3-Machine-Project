package Controller;

import Model.VendingMachine;
import View.RegularBuy;

import java.util.concurrent.atomic.AtomicInteger;

public class RegularBuyController {
    private RegularBuy regularBuyMenu;
    
    private AtomicInteger cash;

    public RegularBuyController(RegularBuy regularBuyMenu, RegVMMenuController regVMMenuController, VendingMachine vendingMachine){
        this.regularBuyMenu = regularBuyMenu;

        updateGUI(vendingMachine);

        regularBuyMenu.getExitButton().addActionListener(e -> {
            regularBuyMenu.getFrame().setVisible(false);
            regVMMenuController.getRegularVMMenu().getFrame().setVisible(true);
        });
    }
    private void updateGUI(VendingMachine vendingMachine)
    {
        regularBuyMenu.setCashBalance(vendingMachine.getUserBalance());
    }


}
