package processSale.view;

import processSale.controller.Controller;
import processSale.controller.OperationFailedException;
import processSale.dto.DisplayInfoDTO;

/**
 * Unit to represent the user interface (in this case hard coded).
 */
public class View {
    private Controller contr;
    private ErrorHandler errorHandler;
    private LogHandler logHandler;

    private DisplayInfoDTO infoToBeDisplayed;
    private int amountPaid;
    private int change;

    /**
     * Creates a new instance, representing user actions that dictates how the <code>Controller</code>
     * controls the program.
     * @param contr The <code>Controller</code> to control the program.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addTransactionObserver(new TotalRevenueView());
        errorHandler = new ErrorHandler();
        try { logHandler = new LogHandler();
        } catch (Exception exception) {
            System.out.println("\n*** Not meant for UI ***");
            System.out.println("Error whilst communicating to error log file.");
            System.out.println("************************\n");
        }

        for (int i = 0; i < 3; i++) {
            contr.startSale();
            System.out.println("\n---------- UI ----------");
            System.out.println("Sale started.\n");

            infoToBeDisplayed = new DisplayInfoDTO("", 0, 0);

            infoToBeDisplayed = registerItem("Corn", 3);
            printToDisplay();

            // HARDCODED: "Apple" triggers artificial database failure.
            infoToBeDisplayed = registerItem("Cherry", 1);
            printToDisplay();

            infoToBeDisplayed = registerItem("Kiwi", 5);
            printToDisplay();

            infoToBeDisplayed = registerItem("paper", 15);
            printToDisplay();

            infoToBeDisplayed = registerItem("Corn", 3);
            printToDisplay();

            infoToBeDisplayed = registerItem("Plum", 2);
            printToDisplay();

            infoToBeDisplayed = registerItem("coal", 2);
            printToDisplay();

            infoToBeDisplayed = registerItem("Apricot", 2);
            printToDisplay();

            infoToBeDisplayed = registerItem("Corn", -1);
            printToDisplay();

            // Customer id in this case is just represented by a number.
            int priceAfterDiscount = contr.discountRequest(1111);
            System.out.println("Running total (after discount(s)): " + priceAfterDiscount);

            amountPaid = 300;
            System.out.println("\nAmount paid: " + amountPaid);
            change = contr.transaction(amountPaid);
            System.out.println("Change: " + change);

            System.out.println("\nReceipt and sale log sent to database handlers.");

            System.out.println("\nSale finished");
            System.out.println("------------------------");

            System.out.println("\n-------- Display -------");
            contr.endSale();
            System.out.println("------------------------");

        }
        
    }

    private DisplayInfoDTO registerItem(String itemIdentifier, int quantity) {
        try {
            DisplayInfoDTO infoToBeReturned = contr.registerItem(itemIdentifier, quantity);
            return infoToBeReturned;
        } catch (OperationFailedException exception) {
            errorHandler.printError(exception.getMessage());
            logHandler.printError(exception);
            return infoToBeDisplayed;
        }
    }

    private void printToDisplay() {
        System.out.println("Item: " + infoToBeDisplayed.getItemDescription() + ", Price: "
                + infoToBeDisplayed.getPrice() + ", Running total: " + infoToBeDisplayed.getRunningTotal() + "\n");
    }

}