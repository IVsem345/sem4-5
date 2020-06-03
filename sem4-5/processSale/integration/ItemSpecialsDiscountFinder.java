package processSale.integration;

import processSale.dto.SaleDTO;

import java.util.ArrayList;

/**
 * Finds discounts by searching database for <code>item</code> that have special offers.
 */
public class ItemSpecialsDiscountFinder implements DiscountFinder {
    int discountAmount;

    /**
     * Creates an instance representing an algorithm to find <code>discount</code> based on <code>item</code>
     * specials.
     */
    public ItemSpecialsDiscountFinder() {}

    /**
     * Finds discounts in database based on <code>item</code> that have special offers, goes through
     * all items in the <code>sale</code> and sees if there are any discounts with matching <code>item</code>
     * on sale.
     * @param saleDTO The sale information to be used.
     * @return An amount representing amount of money discounted.
     */
    @Override
    public int findDiscount(SaleDTO saleDTO) {
        DBHandler handler = DBHandler.getInstance();
        ArrayList<Discount> discounts = handler.getDiscounts();

        discountAmount = 0;

        for (int i = 0; i < saleDTO.getItems().size(); i++) {
            for (int j = 0; j < discounts.size(); j++) {
                if (discounts.get(j).getItemOnSale() != (null)) {
                    if (saleDTO.getItems().get(i).getDescription().equals(discounts.get(j).getItemOnSale().getDescription())) {
                        discountAmount += saleDTO.getItems().get(i).getPrice() * saleDTO.getItems().get(i).getQuantity() * discounts.get(j).getPercentDiscount();
                    }
                }
            }
        }

        return discountAmount;
    }

}
