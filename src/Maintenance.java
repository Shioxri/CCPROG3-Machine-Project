public class Maintenance {
    public void restockItem(VendingMachine vendingMachine, int indexChoice)
    {
        vendingMachine.getStockManager().restockItems(vendingMachine,indexChoice);
    }
}
