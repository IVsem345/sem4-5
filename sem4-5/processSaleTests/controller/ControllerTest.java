package processSaleTests.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processSale.controller.Controller;
import processSale.controller.OperationFailedException;
import processSale.dto.DisplayInfoDTO;
import processSale.integration.Printer;
import processSale.integration.DBHandler;

class ControllerTest {
    private Controller contr;
    private Printer printer;
    private DBHandler handler;

    @BeforeEach
    public void setUp() {
        printer = new Printer();
        handler = DBHandler.getInstance();
        contr = new Controller(printer, handler);
    }

    @AfterEach
    public void tearDown() {
        printer = null;
        handler = null;
        contr = null;
    }

    @Test
    public void testStartSale() {
        try {
            contr.startSale();
        }
        catch (Exception e) {
            fail("Start sale unsuccessful");
        }
    }

    @Test
    public void testRegisterItemReturnsDisplayInfoDTO() {
        contr.startSale();
        DisplayInfoDTO displayInfoDTO;

        try {
            displayInfoDTO = contr.registerItem("Corn", 1);
        }
        catch (OperationFailedException exception) {
            fail("Unexpected exception.");
            return;
        }

        boolean expResult = true;
        boolean result = displayInfoDTO instanceof DisplayInfoDTO;

        assertEquals(expResult, result, "Wrong return value.");
    }

    @Test
    public void testRegisterItemEmptyString() {
        contr.startSale();
        String identifier = "";
        String identifierExpected = "Corn";

        try {
            identifierExpected = contr.registerItem(identifier, 1).getItemDescription();
            fail("An invalid item identifier was matched with an item.");
        }
        catch (OperationFailedException exception) {
            assertTrue(identifierExpected.equals("Corn"), "identifier does not retain initial value 'Banana'.");
        }

    }

    @Test
    public void testRegisterItemNullArgument() {
        contr.startSale();
        String identifier = null;
        String identifierExpected = "Corn";

        try {
            identifierExpected = contr.registerItem(identifier, 1).getItemDescription();
            fail("An invalid item identifier was matched with an item.");
        }
        catch (OperationFailedException exception) {
            assertTrue(identifierExpected.equals("Corn"), "identifier does not retain initial value 'Banana'.");
        }
    }

    @Test
    public void testRegisterItemInvalidQuantity() {
        contr.startSale();
        int invalidQuantity = -1;
        String identifierExpected = "Corn";

        try {
            identifierExpected = contr.registerItem("Peach", invalidQuantity).getItemDescription();
            fail("An invalid item quantity was registered.");
        }
        catch (OperationFailedException exception) {
            assertTrue(identifierExpected.equals("Corn"), "identifier does not retain initial value 'Banana'.");
        }
    }

    @Test
    public void testTransactionReturnsInteger() {
        contr.startSale();

        boolean expResult = true;
        boolean result = (Object)contr.transaction(0) instanceof Integer;

        assertEquals(expResult, result, "Return type not an integer");
    }

}