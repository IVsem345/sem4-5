package processSale.integration;

import processSale.dto.ItemDTO;

/**
 * Represents a discount.
 */
public class Discount {
    private ItemDTO itemOnSale;
    private int totalPriceSaleThreshold;
    private boolean isTotalPriceSale;
    private float percentDiscount;

    /**
     * Creates an instance representing a discount which can either be an <code>item</code> on sale or the whole sale
     * being discounted depending on a price threshold, determined by <code>isTotalPriceSale</code>.
     * @param itemOnSale <code>item</code> that is on sale.
     * @param totalPriceSaleThreshold Threshold to get discount.
     * @param isTotalPriceSale Determines whether or not there is a discount on the total price.
     * @param percentDiscount The discount in percent, whether it be on an item or the entire sale.
     */
    public Discount(ItemDTO itemOnSale, int totalPriceSaleThreshold, boolean isTotalPriceSale, float percentDiscount) {
        this.itemOnSale = itemOnSale;
        this.totalPriceSaleThreshold = totalPriceSaleThreshold;
        this.isTotalPriceSale = isTotalPriceSale;
        this.percentDiscount = percentDiscount;
    }

    /**
     * @return <code>ItemDTO</code> on sale.
     */
    public ItemDTO getItemOnSale() {
        return itemOnSale;
    }

    /**
     * @return Price threshold on sale.
     */
    public int getTotalPriceSaleThreshold() {
        return totalPriceSaleThreshold;
    }

    /**
     * @return True if entire sale discounted, false otherwise.
     */
    public boolean getIsTotalPriceSale() {
        return isTotalPriceSale;
    }

    /**
     * @return How big the discount is in percent.
     */
    public float getPercentDiscount() {
        return percentDiscount;
    }
}
