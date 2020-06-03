package processSale.startup;

import processSale.integration.Printer;
import processSale.integration.DBHandler;
import processSale.controller.Controller;
import processSale.view.View;

/**
 * A unit to initiate different layers of the program.
 */
public class Startup {
    /**
     * Initializes different objects that control different layers of the program.
     * @param args Arguments to initialize program by.
     */
    public static void main(String[] args) {
        Printer printer = new Printer();
        DBHandler handler = DBHandler.getInstance();
        Controller contr = new Controller(printer, handler);
        View view = new View(contr);
    }
}

