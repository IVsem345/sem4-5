package processSale.model;

import java.util.ArrayList;
import processSale.dto.SaleDTO;

/**
 * Represents information about a <code>Sale</code> that shall be saved.
 */
public class SaleLog {
    private int totalPrice;
    private int totalTax;
    private int amountPaid;
    private int change;
    private ArrayList<Item> items = new ArrayList<Item>();

    protected SaleLog(SaleDTO saleInfo) {
        totalPrice = saleInfo.getTotalPrice();
        totalTax = saleInfo.getTotalTax();
        amountPaid = saleInfo.getAmountPaid();
        change = saleInfo.getChange();

        ArrayList<Item> saleInfoItems = saleInfo.getItems();

        for (int i = 0; i < saleInfoItems.size(); i++) {
            items.add(saleInfoItems.get(i));
        }
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getTotalTax() {
        return totalTax;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getChange() {
        return change;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}