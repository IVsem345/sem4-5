package processSale.model;

/**
 * Listener to be implemented by classes seeking to be notified about
 * transactions.
 */
public interface TransactionObserver {
    /**
     * Invoked whenever a sale is payed for.
     * @param income The amount of money gained.
     */
    void newTransaction(int income);

    /**
     * Invoked whenever a sale is deliberately ended.
     */
    void endOfSale();
}
