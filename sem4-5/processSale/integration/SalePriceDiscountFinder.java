package processSale.integration;

import processSale.dto.SaleDTO;

import java.util.ArrayList;

/**
 * Finds discounts by searching database for price ranges that give discounts.
 */
public class SalePriceDiscountFinder implements DiscountFinder {
    int discountAmount;

    /**
     * Finds discounts in database based on the <code>sale</code> total price.
     * @param saleDTO The sale information to be used.
     * @return An amount representing amount of money discounted.
     */
    @Override
    public int findDiscount(SaleDTO saleDTO) {
        DBHandler handler = DBHandler.getInstance();
        ArrayList<Discount> discounts = handler.getDiscounts();

        discountAmount = 0;
        int bestDealIndex = -1;

        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getIsTotalPriceSale() && saleDTO.getTotalPrice() >= discounts.get(i).getTotalPriceSaleThreshold()) {
                if (bestDealIndex == -1) {
                    bestDealIndex = i;
                }
                else if (discounts.get(i).getPercentDiscount() > discounts.get(bestDealIndex).getPercentDiscount()) {
                    bestDealIndex = i;
                }
            }
        }
        if (bestDealIndex != -1) {
            discountAmount += saleDTO.getTotalPrice() * discounts.get(bestDealIndex).getPercentDiscount();
        }

        return discountAmount;
    }
}
