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

        regularMaintenance.getInstructionsButton().addActionListener(e ->{
            regularMaintenance.getSystemMessage().setText("<html>Instructions<br/>" +
                    "Top Left - Edit Item Slot" +
                    "<br/> Bottom Left - Add New Item(Note: make sure every text field is filled" +
                    "<br/> Right - Edit Vending Machine</html>");
        });


        regularMaintenance.getAddItem().addActionListener(e ->{

        });


        regularMaintenance.getChangePriceButton().addActionListener(e ->{
            //Item.setPrice(parse.Int(regularMaintenance.getChangePrice().getText()));
        });

        regularMaintenance.getCollectMoney().addActionListener(e ->{
            //regularMaintenance.getSystemMessage().setText("You have collected Php <Total cash>);
        });

        regularMaintenance.getReStock().addActionListener(e ->{

        });

        regularMaintenance.getSlotInfoButton().addActionListener(e -> {

        });

        regularMaintenance.getPrintSummary().addActionListener(e ->{

        });
    }
}
