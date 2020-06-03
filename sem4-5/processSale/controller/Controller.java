package processSale.controller;

import processSale.integration.*;
import processSale.dto.DisplayInfoDTO;
import processSale.model.InvalidItemDTOException;
import processSale.model.InvalidQuantityException;
import processSale.model.Sale;
import processSale.model.TransactionObserver;

import java.util.ArrayList;

/**
 * Handles operations between the user interface and external systems. Operates logic.
 */
public class Controller {
    private ArrayList<TransactionObserver> transactionObservers = new ArrayList<TransactionObserver>();

    Printer printer;
    DBHandler handler;
    Sale sale;

    private int amountInRegister;

    /**
     * Creates a new instance, creates external system handlers
     * @param printer External system to be controlled
     * @param handler External system to be controlled
     */
    public Controller(Printer printer, DBHandler handler) {
        this.printer = printer;
        this.handler = handler;

        amountInRegister = 1500;
    }

    /**
     * Initiates a new sale instance.
     */
    public void startSale() {
        sale = new Sale();
        sale.addTransactionObservers(transactionObservers);
    }

    /**
     * Registers <code>Item</code> to the sale by initiating a search in an external system
     * through <code>handler</code>, catches exception on invalid identifier
     * or failed communication with database. The operation will overflow if <code>quantity</code>
     * is smaller than <code>Integer.MIN_VALUE</code> or larger than <code>Integer.MAX_VALUE</code>.
     * @param itemIdentifier Identifier that identifies an item from an external system.
     * @param quantity Represents the number of <code>Item</code> to be registered.
     * @throws OperationFailedException If an item is not found or communication fails with database.
     * @return Information to be displayed to the cashier
     */
    public DisplayInfoDTO registerItem(String itemIdentifier, int quantity) throws OperationFailedException {
        try {
            sale.addItem(handler.searchInventory(itemIdentifier), quantity);
            return sale.getDisplayInfoDTO();
        }
        catch (InvalidItemIdentifierException | InventorySystemException | InvalidQuantityException | InvalidItemDTOException exception) {
            throw new OperationFailedException(exception.getMessage(), exception);
        }

    }

    /**
     * Handles the payment for the sale and sends a <code>SaleLog</code> to an external
     * system as well as prompts the printer to initiate the operation of printing a 
     * <code>Receipt</code>. Updates amount of money present in the virtual register as well.
     * The case of <code>amountPaid</code> not being sufficient is not handled and thus negative
     * values of change are possible. The operation will overflow if the <code>amountPaid</code> or result
     * is smaller than <code>Integer.MIN_VALUE</code> or larger than <code>Integer.MAX_VALUE</code>.
     * @param amountPaid The amount of money that was received for the <code>Sale</code>.
     * @return The amount of change.
     */
    public int transaction(int amountPaid) {
        sale.insertPayment(amountPaid);

        handler.logSale(sale.getSaleLog());
        printer.printReceipt(sale.getReceipt());

        updateAmountInRegister(sale.getTotalPrice());


        return sale.getChange();
    }

    /**
     * Starts a sequence that will calculate the total price of the <code>sale</code> after a discount,
     * if the request for a discount is valid which is decided by the <code>customerId</code>.
     * @param customerId Id to decide eligibility for discount.
     */
    public int discountRequest(int customerId) {
        if (handler.checkIdValidity(customerId)) {
            sale.addDiscount(new ItemSpecialsDiscountFinder().findDiscount(sale.getSaleInfo()) + new SalePriceDiscountFinder().findDiscount(sale.getSaleInfo()));
        }
        return sale.getTotalPrice();
    }

    /**
     * Officially ends the sale.
     */
    public void endSale() {
        sale.endSale();
    }

    /**
     * Adds an observer of type <code>TransactionObserver</code> to the list <code>transactionObservers</code>,
     * it will be notified whenever a transaction is completed.
     */
    public void addTransactionObserver(TransactionObserver observer) {
        transactionObservers.add(observer);
    }

    private void updateAmountInRegister(int amountReceived) {
        amountInRegister += amountReceived;
    }

}