package Controller;

import Model.*;
import View.SpecialBuy;

import java.util.ArrayList;

public class SpecialBuyController {
    private SpecialBuy specialBuyMenu;
    ArrayList<Slot> originalSlots;
    ArrayList<Slot> originalSpecialSlots;
    Item[] selectedItems;
    ArrayList<Item> selectedFruits;
    ArrayList<Item> selectedLiquids;


    SpecialBuyController(SpecialBuy specialBuyMenu, SpecVMMenuController specVMMenuController, SpecialVendingMachine vendingMachine){
        this.specialBuyMenu = specialBuyMenu;

        ArrayList<Slot> originalSlots = Maintenance.deepCopySlotArrayList(vendingMachine.getSlotArrayList());
        ArrayList<Slot> originalSpecialSlots = Maintenance.deepCopySlotArrayList(vendingMachine.getSpecialSlots());
        ArrayList<Item> selectedItems = new ArrayList<>();
        ArrayList<Item> selectedFruits = new ArrayList<>();
        ArrayList<Item> selectedLiquids = new ArrayList<>();

        setDropdownContents(vendingMachine);


        specialBuyMenu.getFirstFruitsDropDown().addActionListener(e ->{
            int firstFruitSelectedIndex = specialBuyMenu.getFirstFruitsDropDown().getSelectedIndex();
            Item selectedItem = vendingMachine.getSelectedItem(firstFruitSelectedIndex-1, false);
            System.out.println("Price: "+selectedItem.getPrice());
            System.out.println("Cals: "+selectedItem.getCalorie());

            specialBuyMenu.addToTotalPrice(selectedItem.getPrice(),0);
            specialBuyMenu.addToTotalCals(selectedItem.getCalorie(),0);
            updateSideLabels(specialBuyMenu, vendingMachine);

        });

        specialBuyMenu.getSecondFruitsDropDown().addActionListener(e ->{
            int secondFruitSelectedIndex = specialBuyMenu.getSecondFruitsDropDown().getSelectedIndex();
            Item selectedItem = vendingMachine.getSelectedItem(secondFruitSelectedIndex-1, false);
            specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 1);
            specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 1);
            updateSideLabels(specialBuyMenu, vendingMachine);
        });

        specialBuyMenu.getWaterType().addActionListener(e ->{
            int waterTypeSelectedIndex = specialBuyMenu.getWaterType().getSelectedIndex();
            Item selectedItem = vendingMachine.getSelectedItem(waterTypeSelectedIndex-1, true);
            specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 2);
            specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 2);
            updateSideLabels(specialBuyMenu, vendingMachine);
        });

        specialBuyMenu.getMilkType().addActionListener(e ->{
            int milkTypeSelectedIndex = specialBuyMenu.getMilkType().getSelectedIndex() + 2;
            Item selectedItem = vendingMachine.getSelectedItem(milkTypeSelectedIndex-1, true);
            specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 3);
            specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 3);
            updateSideLabels(specialBuyMenu, vendingMachine);
        });

        specialBuyMenu.getIceType().addActionListener(e ->{
            int iceTypeSelectedIndex = specialBuyMenu.getIceType().getSelectedIndex() + 7;
            Item selectedItem = vendingMachine.getSelectedItem(iceTypeSelectedIndex-1, true);
            specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 4);
            specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 4);
            updateSideLabels(specialBuyMenu, vendingMachine);
        });

        specialBuyMenu.getToppingsType().addActionListener(e ->{
            int toppingsSelectedIndex = specialBuyMenu.getToppingsType().getSelectedIndex() + 9;
            Item selectedItem = vendingMachine.getSelectedItem(toppingsSelectedIndex-1, true);
            specialBuyMenu.addToTotalPrice(selectedItem.getPrice(), 5);
            specialBuyMenu.addToTotalCals(selectedItem.getCalorie(), 5);
            updateSideLabels(specialBuyMenu, vendingMachine);

        });

        specialBuyMenu.getAddButton().addActionListener(e -> {
            vendingMachine.addTempPaidMoney((Integer) specialBuyMenu.getDenominations().getSelectedItem(), 1);
            specialBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
            specialBuyMenu.showAddedMoneyText();
            System.out.println("Added: "+vendingMachine.getMoneyManager().getTempMoneyFromUser().
                    get(vendingMachine.getMoneyManager().getTempMoneyFromUser().size()-1));
            System.out.println("User Bal: "+vendingMachine.getUserBalance());
        });

        specialBuyMenu.getCancelButton().addActionListener(e -> {
            vendingMachine.getMoneyManager().returnMoney(vendingMachine.getMoneyManager().getTempMoneyFromUser());
            vendingMachine.getMoneyManager().clearUserPaidMoney();
            specialBuyMenu.defaultBalanceText();
            specialBuyMenu.getSystemMessage().setText("<html>Cleared user balance<br/>"+
                    "Returned User Money<br/>"+
                    "Successfully cancelled the transaction </html>");
        });

        specialBuyMenu.getExitButton().addActionListener(e -> {
            specialBuyMenu.getFrame().setVisible(false);
            specVMMenuController.getSpecialVMMenu().getFrame().setVisible(true);
        });
    }
    private void setDropdownContents(SpecialVendingMachine vendingMachine)
    {
        specialBuyMenu.setFirstFruitsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
        specialBuyMenu.setSecondFruitsDropDown(getSlotTypes(vendingMachine.getSlotArrayList()));
        specialBuyMenu.setWaterDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),0,1));
        specialBuyMenu.setMilkDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),2,6));
        specialBuyMenu.setIceDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),7,8));
        specialBuyMenu.setToppingsDropDown(getSpecialSlotTypes(vendingMachine.getSpecialSlots(),9,10));
    }

    public ArrayList<Slot> getOriginalSlots() {
        return originalSlots;
    }

    public ArrayList<Slot> getOriginalSpecialSlots() {
        return originalSpecialSlots;
    }

/*
    private void getAllSelectedItems(SpecialVendingMachine vendingMachine)
    {
        //Make a string that will cat everytime you select

        int firstFruitSelectedIndex = specialBuyMenu.getFirstFruitsDropDown().getSelectedIndex();
        if(vendingMachine.checkInputValidity(firstFruitSelectedIndex, false)==0)
        {
            Item dispensedItem = (vendingMachine.dispenseSelectedItem(firstFruitSelectedIndex-1, false));
            selectedItems[0] = dispensedItem;
            specialBuyMenu.addToTotalPrice(dispensedItem.getPrice());
            specialBuyMenu.addToTotalCals(dispensedItem.getCalorie());

        }

        int secondFruitSelectedIndex = specialBuyMenu.getSecondFruitsDropDown().getSelectedIndex();
        if(vendingMachine.checkInputValidity(secondFruitSelectedIndex, false)==0)
        {
            Item dispensedItem = (vendingMachine.dispenseSelectedItem(secondFruitSelectedIndex-1, false));
            selectedItems[1] = dispensedItem;
            specialBuyMenu.addToTotalPrice(dispensedItem.getPrice());
            specialBuyMenu.addToTotalCals(dispensedItem.getCalorie());

        }

        int waterTypeSelectedIndex = specialBuyMenu.getWaterType().getSelectedIndex();
        if(vendingMachine.checkInputValidity(waterTypeSelectedIndex, true)==0)
        {
            Item dispensedItem = (vendingMachine.dispenseSelectedItem(waterTypeSelectedIndex-1, true));
            selectedItems[2] = dispensedItem;
            specialBuyMenu.addToTotalPrice(dispensedItem.getPrice());
            specialBuyMenu.addToTotalCals(dispensedItem.getCalorie());
        }

        int milkTypeSelectedIndex = specialBuyMenu.getMilkType().getSelectedIndex() + 2;
        if(vendingMachine.checkInputValidity(milkTypeSelectedIndex, true)==0)
        {
            Item dispensedItem = (vendingMachine.dispenseSelectedItem(milkTypeSelectedIndex-1, true));
            selectedItems[3] = dispensedItem;
            specialBuyMenu.addToTotalPrice(dispensedItem.getPrice());
            specialBuyMenu.addToTotalCals(dispensedItem.getCalorie());
        }

        int iceTypeSelectedIndex = specialBuyMenu.getIceType().getSelectedIndex() + 7;
        if(vendingMachine.checkInputValidity(iceTypeSelectedIndex, true)==0)
        {
            Item dispensedItem = (vendingMachine.dispenseSelectedItem(iceTypeSelectedIndex-1, true));
            selectedItems[4] = dispensedItem;
            specialBuyMenu.addToTotalPrice(dispensedItem.getPrice());
            specialBuyMenu.addToTotalCals(dispensedItem.getCalorie());
        }

        int toppingsSelectedIndex = specialBuyMenu.getToppingsType().getSelectedIndex() + 9;
        if(vendingMachine.checkInputValidity(toppingsSelectedIndex, true)==0)
        {
            Item dispensedItem = (vendingMachine.dispenseSelectedItem(toppingsSelectedIndex-1, true));
            selectedItems[5] = dispensedItem;
            specialBuyMenu.addToTotalPrice(dispensedItem.getPrice());
            specialBuyMenu.addToTotalCals(dispensedItem.getCalorie());
        }


    }

*/
    private ArrayList<String> getSlotTypes(ArrayList<Slot> slotTypes) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (Slot slot : slotTypes) {
            stringSlotTypes.add(slot.getAssignedItemType());
        }
        return stringSlotTypes;
    }

    private ArrayList<String> getSpecialSlotTypes(ArrayList<Slot> slotTypes, int lowerBound, int upperBound) {
        ArrayList<String> stringSlotTypes = new ArrayList<>();
        for (int i=lowerBound; i<upperBound+1;i++) {
            stringSlotTypes.add(slotTypes.get(i).getAssignedItemType());
        }
        return stringSlotTypes;
    }

    public void updateSideLabels(SpecialBuy specialBuyMenu, SpecialVendingMachine vendingMachine) {
        int firstFruitIndex = specialBuyMenu.getFirstFruitsDropDown().getSelectedIndex();
        int secondFruitIndex = specialBuyMenu.getSecondFruitsDropDown().getSelectedIndex();
        int waterTypeIndex = specialBuyMenu.getWaterType().getSelectedIndex();
        int milkTypeIndex = specialBuyMenu.getMilkType().getSelectedIndex();
        int iceTypeIndex = specialBuyMenu.getIceType().getSelectedIndex();
        int toppingsTypeIndex = specialBuyMenu.getToppingsType().getSelectedIndex();

        StringBuilder infoText = new StringBuilder("<html>Item Details:<br/>Price | Calories | Stock<br/>");
        StringBuilder orderText = new StringBuilder();
        orderText.append("<html>Order Details<br/>Price: Php ").append(specialBuyMenu.getTotalPrice());
        orderText.append("<br/>Calories: ").append(specialBuyMenu.getTotalCals()).append(" kCal");
        orderText.append("<br/>Selected Items:<br/>");

        if (firstFruitIndex != 0) {
            String firstFruit = (String) specialBuyMenu.getFirstFruitsDropDown().getSelectedItem();
            int price = vendingMachine.getSelectedItem(firstFruitIndex-1, false).getPrice();
            int calories = vendingMachine.getSelectedItem(firstFruitIndex-1, false).getCalorie();
            int stock = vendingMachine.getSelectedSlot(firstFruitIndex-1, false).getItemStock();

            orderText.append("<br/>First Fruit: ").append(firstFruit);
            infoText.append("<br/>First Fruit: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/>First Fruit: ");
            orderText.append("<br/>First Fruit: ");
        }

        if (secondFruitIndex != 0) {
            String secondFruit = (String) specialBuyMenu.getSecondFruitsDropDown().getSelectedItem();
            int price = vendingMachine.getSelectedItem(secondFruitIndex-1, false).getPrice();
            int calories = vendingMachine.getSelectedItem(secondFruitIndex-1, false).getCalorie();
            int stock = vendingMachine.getSelectedSlot(secondFruitIndex-1, false).getItemStock();

            orderText.append("<br/>Second Fruit: ").append(secondFruit);
            infoText.append("<br/>Second Fruit: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Second Fruit: ");
            orderText.append("<br/>Second Fruit: ");
        }
        if (waterTypeIndex != 0) {
            String waterType = (String) specialBuyMenu.getWaterType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(waterTypeIndex-1, true).getPrice();
            int calories = vendingMachine.getSelectedItem(waterTypeIndex-1, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(waterTypeIndex-1, true).getItemStock();

            orderText.append("<br/>Water Type: ").append(waterType);
            infoText.append("<br/>Water Type: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Water Type: ");
            orderText.append("<br/>Water Type: ");
        }
        if (milkTypeIndex != 0) {
            String milkType = (String) specialBuyMenu.getMilkType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(milkTypeIndex+1, true).getPrice();
            int calories = vendingMachine.getSelectedItem(milkTypeIndex+1, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(milkTypeIndex+1, true).getItemStock();

            orderText.append("<br/>Milk Type: ").append(milkType);
            infoText.append("<br/>Milk Type: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Milk Type: ");
            orderText.append("<br/>Milk Type: ");
        }
        if (iceTypeIndex != 0) {
            String iceType = (String) specialBuyMenu.getIceType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(iceTypeIndex+6, true).getPrice();
            int calories = vendingMachine.getSelectedItem(iceTypeIndex+6, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(iceTypeIndex+6, true).getItemStock();

            orderText.append("<br/>Ice Type: ").append(iceType);
            infoText.append("<br/>Ice Type: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Ice Type: ");
            orderText.append("<br/>Ice Type: ");
        }
        if (toppingsTypeIndex != 0) {
            String toppingsType = (String) specialBuyMenu.getToppingsType().getSelectedItem();
            int price = vendingMachine.getSelectedItem(toppingsTypeIndex+8, true).getPrice();
            int calories = vendingMachine.getSelectedItem(toppingsTypeIndex+8, true).getCalorie();
            int stock = vendingMachine.getSelectedSlot(toppingsTypeIndex+8, true).getItemStock();

            orderText.append("<br/>Toppings: ").append(toppingsType);
            infoText.append("<br/>Toppings: ").append(price).append(" | ").append(calories).append(" | ").append(stock);
        } else {
            infoText.append("<br/> Toppings: ");
            orderText.append("<br/>Toppings: ");
        }

        infoText.append("</html>");
        specialBuyMenu.getInfoLabel().setText(infoText.toString());
        orderText.append("</html>");
        specialBuyMenu.getOrderLabel().setText(orderText.toString());
    }








}
