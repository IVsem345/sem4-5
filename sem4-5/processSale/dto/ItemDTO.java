package processSale.dto;

import java.lang.String;

/**
 * Represents information relevant to <code>Item</code>.
 */
public class ItemDTO {
    private int price;
    private float taxRate;
    private String description;

    /**
     * Creates a new instance, representing a <code>Item</code> The operation will overflow
     * if <code>price</code> is smaller than <code>Integer.MIN_VALUE</code> or larger than
     * <code>Integer.MAX_VALUE</code> and if <code>taxRate</code> is smaller than
     * <code>Float.MIN_VALUE</code> or larger than <code>Float.MAX_VALUE</code>.
     * @param price <code>Item</code> price.
     * @param taxRate <code>Item</code> tax rate.
     * @param description <code>Item</code> description.
     */
    public ItemDTO(int price, float taxRate, String description) {
        this.price = price;
        this.taxRate = taxRate;
        this.description = description;
    }

    /**
     * @return The <code>Item</code> price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return The <code>Item</code> tax rate.
     */
    public float getTaxRate() {
        return taxRate;
    }

    /**
     * @return The <code>Item</code> description.
     */
    public String getDescription() {
        return description;
    }

}