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
            updateInfoLabel(firstFruitSelectedIndex, vendingMachine, false);

        });

        specialBuyMenu.getSecondFruitsDropDown().addActionListener(e ->{
            int secondFruitSelectedIndex = specialBuyMenu.getSecondFruitsDropDown().getSelectedIndex();
            updateInfoLabel(secondFruitSelectedIndex, vendingMachine, false);
        });

        specialBuyMenu.getWaterType().addActionListener(e ->{
            int waterTypeSelectedIndex = specialBuyMenu.getWaterType().getSelectedIndex();
            updateInfoLabel(waterTypeSelectedIndex, vendingMachine, false);
        });

        specialBuyMenu.getMilkType().addActionListener(e ->{
            int milkTypeSelectedIndex = specialBuyMenu.getMilkType().getSelectedIndex() + 2;
            updateInfoLabel(milkTypeSelectedIndex, vendingMachine, false);
        });

        specialBuyMenu.getIceType().addActionListener(e ->{
            int iceTypeSelectedIndex = specialBuyMenu.getIceType().getSelectedIndex() + 7;
            updateInfoLabel(iceTypeSelectedIndex, vendingMachine, false);
        });

        specialBuyMenu.getToppingsType().addActionListener(e ->{
            int toppingsSelectedIndex = specialBuyMenu.getToppingsType().getSelectedIndex() + 9;
            updateInfoLabel(toppingsSelectedIndex, vendingMachine, false);
        });





        specialBuyMenu.getAddButton().addActionListener(e -> {
            vendingMachine.addTempPaidMoney((Integer) specialBuyMenu.getDenominations().getSelectedItem(), 1);
            specialBuyMenu.updateBalanceText(vendingMachine.getUserBalance());
            specialBuyMenu.showAddedMoneyText();
            System.out.println("Added: "+vendingMachine.getMoneyManager().getTempMoneyFromUser().
                    get(vendingMachine.getMoneyManager().getTempMoneyFromUser().size()-1));
            System.out.println("User Bal: "+vendingMachine.getUserBalance());
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
/*
    public void updateInfoLabel(int selectedItemIndex, VendingMachine vendingMachine, boolean isSpecialSlot) {
        if (selectedItemIndex != 0) {
            int chosenItemIndex = selectedItemIndex - 1;
            Item selectedItem = vendingMachine.getSelectedItem(chosenItemIndex, isSpecialSlot);
            Slot selectedSlot = vendingMachine.getSelectedSlot(chosenItemIndex, isSpecialSlot);
            if (selectedItem != null && !selectedSlot.getItemArrayList().isEmpty()) {
                String infoText = "<html>Price: " +  selectedItem.getPrice() +
                        "<br/>Calories: " +  selectedItem.getCalorie() +
                        " kCal<br/> Stock: " + selectedSlot.getItemStock() +
                        "</html>";
                specialBuyMenu.getInfoLabel().setText(infoText);
            }
            else
            {
                specialBuyMenu.getInfoLabel().setText("Item: ["+selectedSlot.getAssignedItemType()+"] IS OUT OF STOCK!");
            }

        } else {
            specialBuyMenu.getOrderLabel().setText("");
        }
    }
*/
    public void updateOrderLabel(SpecialBuy specialBuyMenu) {
        String firstFruit = (String) specialBuyMenu.getFirstFruitsDropDown().getSelectedItem();
        String secondFruit = (String) specialBuyMenu.getSecondFruitsDropDown().getSelectedItem();
        String waterType = (String) specialBuyMenu.getWaterType().getSelectedItem();
        String milkType = (String) specialBuyMenu.getMilkType().getSelectedItem();
        String iceType = (String) specialBuyMenu.getIceType().getSelectedItem();
        String toppingsType = (String) specialBuyMenu.getToppingsType().getSelectedItem();

        StringBuilder orderText = new StringBuilder();
        orderText.append("<html>Price: Php").append(specialBuyMenu.getTotalPrice());
        orderText.append("<br/>Calories: ").append(specialBuyMenu.getTotalCals()).append(" kCal");

        if (firstFruit != null && !firstFruit.equals("Choose a fruit...")) {
            orderText.append("<br/>First Fruit: ").append(firstFruit);
        }
        if (secondFruit != null && !secondFruit.equals("Choose a fruit...")) {
            orderText.append("<br/>Second Fruit: ").append(secondFruit);
        }
        if (waterType != null && !waterType.equals("Choose a type of milk...")) {
            orderText.append("<br/>Water Type: ").append(waterType);
        }
        if (milkType != null && !milkType.equals("Choose a type of water...")) {
            orderText.append("<br/>Milk Type: ").append(milkType);
        }
        if (iceType != null && !iceType.equals("Choose the type of ice...")) {
            orderText.append("<br/>Ice Type: ").append(iceType);
        }
        if (toppingsType != null && !toppingsType.equals("Choose a topping...")) {
            orderText.append("<br/>Toppings: ").append(toppingsType);
        }

        orderText.append("</html>");
        specialBuyMenu.getOrderLabel().setText(orderText.toString());
    }

}
