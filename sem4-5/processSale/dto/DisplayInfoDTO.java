package processSale.dto;

import java.lang.String;

/**
 * Contains relevant information about the current <code>Item</code> in the sale.
 */
public class DisplayInfoDTO {
    private String itemDescription;
    private int price;
    private int runningTotal;

    /**
     * Creates a new instance, representing the collection of relevant information
     * The operation will overflow if <code>price</code> or <code>runningTotal</code>
     * is smaller than <code>Integer.MIN_VALUE</code> or larger than <code>Integer.MAX_VALUE</code>.
     * @param itemDescription Information about the <code>Item</code>.
     * @param price The price of the <code>Item</code>.
     * @param runningTotal The current total price for the <code>Sale</code>.
     */
    public DisplayInfoDTO(String itemDescription, int price, int runningTotal) {
        this.itemDescription = itemDescription;
        this.price = price;
        this.runningTotal = runningTotal;
    }

    /**
     * @return The <code>Item</code> description.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @return The <code>Item</code>} price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return The total price of the sale for all current <code>Item</code>.
     */
    public int getRunningTotal() {
        return runningTotal;
    }

}