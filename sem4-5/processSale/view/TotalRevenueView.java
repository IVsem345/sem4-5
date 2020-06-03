package processSale.view;

import processSale.model.TransactionObserver;

/**
 * Class representing a display that shows the total revenue of all sales since the program's start.
 * Is an observer, implementing from <code>TransactionObserver</code>.
 */
public class TotalRevenueView implements TransactionObserver {
    private int totalIncome;

    /**
     * Creates an instance and initializes starting value.
     */
    public TotalRevenueView() {
        totalIncome = 0;
    }

    /**
     * Adds revenue from a sale.
     * @param income The revenue gained.
     */
    @Override
    public void newTransaction(int income) {
        totalIncome += income;
    }

    /**
     * Prints out the total revenue.
     */
    @Override
    public void endOfSale() {
        System.out.println("-  Total revenue: " + totalIncome + "  -");
    }
}
