package processSale.integration;

import processSale.model.Receipt;

/**
 * Unit to handle communication with an external system, a printer.
 */
public class Printer {
    
    /**
     * Creates a new instance, representing a printer.
     */
    public Printer() {
    }

    /**
     * Initiates the printout of a <code>Receipt</code> from an external printer.
     * @param receipt The <code>Receipt</code> to be printed.
     */
    public void printReceipt(Receipt receipt) {
        /* operation to contact external system */
    }

}