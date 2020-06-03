package processSale.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import processSale.model.Item;

/**
 * Represents relevant information regarding a <code>Sale</code>.
 */
public class SaleDTO {
    private LocalDateTime saleTime;
    private int totalPrice;
    private int totalTax;
    private int amountPaid;
    private int change;
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Creates a new instance, representing a <code>Sale</code>. The operation will overflow
     * if <code>totalPrice</code>, <code>totalTax</code>, <code>amountPaid</code> or <code>change</code>
     * is smaller than <code>Integer.MIN_VALUE</code> or larger than <code>Integer.MAX_VALUE</code>.
     * @param saleTime The time when the <code>Sale</code> was initiated.
     * @param items The amount of <code>Item</code> in the <code>Sale</code>.
     * @param totalPrice The price for the <code>Sale</code>.
     * @param totalTax The total tax in money for the <code>Sale</code>.
     * @param amountPaid The total amount of money received for the <code>Sale</code>.
     * @param change The change calculated from <code>Sale</code>.
     */
    public SaleDTO(LocalDateTime saleTime, ArrayList<Item> items, int totalPrice, int totalTax, int amountPaid, int change) {
        this.saleTime = saleTime;
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.amountPaid = amountPaid;
        this.change = change;

        for (int i = 0; i < items.size(); i++) {
            this.items.add(items.get(i));
        }
    }

    /**
     * @return The time and date at the beginning of the <code>Sale</code>.
     */
    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    /**
     * @return The price for the <code>Sale</code>.
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * @return The total tax in money.
     */
    public int getTotalTax() {
        return totalTax;
    }

    /**
     * @return The amount of money received for <code>Sale</code>.
     */
    public int getAmountPaid() {
        return amountPaid;
    }

    /**
     * @return The amount of money in excess after payment.
     */
    public int getChange() {
        return change;
    }

    /**
     * @return The list of <code>Item</code> in <code>Sale</code>.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

}