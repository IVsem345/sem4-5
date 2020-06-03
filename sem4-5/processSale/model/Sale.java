package processSale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.Math;
import processSale.dto.SaleDTO;
import processSale.dto.ItemDTO;
import processSale.dto.DisplayInfoDTO;

/**
 * Represents a <code>Sale</code> of one or more <code>Item</code>.
 */
public class Sale {
    private ArrayList<TransactionObserver> transactionObservers = new ArrayList<TransactionObserver>();

    private LocalDateTime saleTime;
    private int totalPrice;
    private int totalTax;
    private int amountPaid;
    private int change;
    private ArrayList<Item> items = new ArrayList<Item>();
    private DisplayInfoDTO displayInfoDTO;
    private Receipt receipt;
    private SaleLog saleLog;

    /**
     * Creates a new instance and records initialization time and date.
     */
    public Sale() {
        saleTime = LocalDateTime.now();
        totalPrice = 0;
        totalTax = 0;
    }

    /**
     * Adds an <code>Item</code> to the current <code>Sale</code>, if <code>itemDTO</code>
     * is null or <code>quantity</code> is invalid it is not added. The operation will overflow
     * if result is smaller than <code>Integer.MIN_VALUE</code> or larger than <code>Integer.MAX_VALUE</code>.
     * @param itemDTO Item information to create and add an <code>Item</code> from.
     * @param quantity The number of <code>Item</code> to be added.
     */
    public void addItem(ItemDTO itemDTO, int quantity) throws InvalidItemDTOException, InvalidQuantityException {
        if (itemDTO == null) { throw new InvalidItemDTOException(); }
        if (quantity < 1) { throw new InvalidQuantityException(itemDTO.getDescription(), quantity); }

        Item item = new Item(itemDTO, quantity);

        int index = indexOfDuplicate(item);

        if (index >= 0) {
            increaseQuantityOfItem(index, item);
        } else {
            items.add(item);
        }

        increasePrice(item);
        updateDisplayInfo(item);
    }

    private void increasePrice(Item item) {
        totalPrice += item.getPrice() * item.getQuantity();
    }

    private int indexOfDuplicate(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (item.getDescription().equals(items.get(i).getDescription())) {
                return i;
            }
        }
        return -1;
    }

    private void increaseQuantityOfItem(int index, Item item) {
        items.get(index).increaseQuantity(item.getQuantity());
    }

    /**
     * Sets the amount of money paid. The operation will overflow if <code>amount</code>
     * is smaller than <code>Integer.MIN_VALUE</code> or larger than <code>Integer.MAX_VALUE</code>.
     * @param amount The amount paid.
     */
    public void setAmountPaid(int amount) {
        amountPaid = amount;
    }

    private void updateDisplayInfo(Item item) {
        displayInfoDTO = new DisplayInfoDTO(item.getDescription(), item.getPrice(), totalPrice);
    }

    /**
     * @return Information to be displayed to cashier.
     */
    public DisplayInfoDTO getDisplayInfoDTO() {
        return displayInfoDTO;
    }

    private void calculateTotalTax() {
        for (int i = 0; i < items.size(); i++) {
            totalTax += Math.round(((float)items.get(i).getPrice() * (float)items.get(i).getQuantity() * items.get(i).getTaxRate()));
        }
    }

    private void calculateChange() {
        change = amountPaid - totalPrice;
    }

    /**
     * Inserts payment and calculates final values, <code>change</code> and <code>totalTax</code>
     * and initiates operation <code>sendSaleInfo</code> to send <code>saleInfo</code> to external system
     * handlers. The operation does not handle invalid <code>amountPaid</code>. The operation will overflow
     * if <code>amountPaid</code> is smaller than <code>Integer.MIN_VALUE</code> or larger than
     * <code>Integer.MAX_VALUE</code>.
     * @param amountPaid Amount of money paid for the <code>Sale</code>.
     */
    public void insertPayment(int amountPaid) {
        setAmountPaid(amountPaid);
        calculateTotalTax();
        calculateChange();

        sendSaleInfo();

        notifyObservers();
    }

    /**
     * Notifies observers of <code>Sale</code>.
     */
    public void notifyObservers() {
        for (TransactionObserver observer : transactionObservers) {
            observer.newTransaction(totalPrice);
        }
    }

    /**
     * Adds an observer of type <code>TransactionObserver</code> to the list <code>transactionObservers</code>.
     * @param observer Observer to add to the list.
     */
    public void addTransactionObserver(TransactionObserver observer) {
        transactionObservers.add(observer);
    }

    /**
     * Removes an observer of type <code>TransactionObserver</code> from the list <code>transactionObservers</code>.
     * @param observer Observer to remove from list.
     */
    public void removeTransactionObserver(TransactionObserver observer) {
        transactionObservers.remove(observer);
    }

    /**
     * Adds all observers of type <code>TransactionObserver</code> to the list <code>transactionObservers</code>.
     * @param observers The observers to add.
     */
    public void addTransactionObservers(ArrayList<TransactionObserver> observers) {
        transactionObservers.addAll(observers);
    }

    private void sendSaleInfo() {
        SaleDTO saleInfo = getSaleInfo();

        receipt = new Receipt(saleInfo);
        saleLog = new SaleLog(saleInfo);
    }

    /**
     * Updates <code>totalPrice</code> with a discounted price.
     * @param discountAmount amount to update with.
     */
    public void addDiscount(int discountAmount) {
        totalPrice -= discountAmount;
    }

    /**
     * Prompts all observers to initiate any sequence reliant on the end of a sale.
     */
    public void endSale() {
        for (TransactionObserver observer : transactionObservers) {
            observer.endOfSale();
        }
    }

    /**
     * @return Retrieves information about <code>Sale</code>.
     */
    public SaleDTO getSaleInfo() {
        return new SaleDTO(saleTime, items, totalPrice, totalTax, amountPaid, change);
    }

    /**
     * @return Retrieves the <code>Receipt</code>.
     */
    public Receipt getReceipt() {
        return receipt;
    }

    /**
     * @return Retrieves the <code>SaleLog</code>.
     */
    public SaleLog getSaleLog() {
        return saleLog;
    }

    /**
     * @return Retrieves the <code>totalPrice</code> of the <code>Sale</code>.
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * @return Retrieves the total <code>change</code> of the <code>Sale</code>.
     */
    public int getChange() {
        return change;
    }

}