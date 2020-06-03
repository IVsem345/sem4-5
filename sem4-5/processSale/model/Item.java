package processSale.model;

import java.lang.String;
import processSale.dto.ItemDTO;

/**
 * Represents an <code>Item</code> in a <code>Sale</code>.
 */
public class Item {
    private int price;
    private float taxRate;
    private String description;
    private int quantity;

    public Item(ItemDTO itemInfo, int quantity) {
        price = itemInfo.getPrice();
        taxRate = itemInfo.getTaxRate();
        description = itemInfo.getDescription();
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;   
    }

    /**
     * Increases amount of <code>Item</code>, quantity values of less than 1 are not handled.
     * The operation will overflow if the <code>amount</code> is smaller than <code>Integer.MIN_VALUE</code>
     * or larger than <code>Integer.MAX_VALUE</code>.
     * @param amount Amount to increase with.
     */
    public void increaseQuantity(int amount) {
        quantity += amount;
    }

}