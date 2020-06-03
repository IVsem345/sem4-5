package processSale.model;

import java.time.LocalDateTime;
import java.lang.String;
import java.util.ArrayList;
import processSale.dto.SaleDTO;

/**
 * Represents a <code>Receipt</code> of a <code>Sale</code>.
 */
public class Receipt {
    private LocalDateTime saleTime;
    private String storeName;
    private String storeAddress;
    private int totalPrice;
    private int totalTax;
    private int amountPaid;
    private int change;
    private ArrayList<Item> items = new ArrayList<Item>();

    protected Receipt(SaleDTO saleInfo) {
        storeName = "Coop Kvantum";
        storeAddress = "Kistagången 150c";

        saleTime = saleInfo.getSaleTime();
        totalPrice = saleInfo.getTotalPrice();
        totalTax = saleInfo.getTotalTax();
        amountPaid = saleInfo.getAmountPaid();
        change = saleInfo.getChange();

        ArrayList<Item> saleInfoItems = saleInfo.getItems();

        for (int i = 0; i < saleInfoItems.size(); i++) {
            items.add(saleInfoItems.get(i));
        }
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
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