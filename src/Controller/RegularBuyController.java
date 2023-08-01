package Controller;

import Model.VendingMachine;
import View.RegularBuy;

import java.util.concurrent.atomic.AtomicInteger;

public class RegularBuyController {
    private RegularBuy regularBuyMenu;
    private AtomicInteger updatedBalance = new AtomicInteger();
    private int selectedDenom;
    public RegularBuyController(RegularBuy regularBuyMenu, RegVMMenuController regVMMenuController, VendingMachine vendingMachine){
        this.regularBuyMenu = regularBuyMenu;


        updateGUI(vendingMachine);

        regularBuyMenu.getAddButton().addActionListener(e -> {
            vendingMachine.addTempPaidMoney((Integer) regularBuyMenu.getDenominations().getSelectedItem(), 1);
            regularBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
        });

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
