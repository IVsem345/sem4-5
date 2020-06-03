package processSale.integration;

import processSale.dto.SaleDTO;

/**
 * Defines the ability to match a customer id with discounts a customer is eligible for.
 * Implemented by classes that find discounts.
 */
public interface DiscountFinder {
    /**
     * Using sale information calculates an amount of money to be reduced from <code>sale</code> price.
     * @param saleDTO The sale information to be used.
     * @return Total amount to be reduced from <code>sale</code>.
     */
    int findDiscount(SaleDTO saleDTO);
}
