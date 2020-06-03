package processSaleTests.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processSale.integration.DBHandler;
import processSale.dto.ItemDTO;
import processSale.integration.InvalidItemIdentifierException;
import processSale.integration.InventorySystemException;
import processSale.model.InvalidItemDTOException;
import processSale.model.InvalidQuantityException;
import processSale.model.Sale;
import processSale.model.SaleLog;

class DBHandlerTest {
    DBHandler handler;

    @BeforeEach
    public void setUp() {
        handler = DBHandler.getInstance();
    }

    @AfterEach
    public void tearDown() {
        handler = null;
    }

    @Test
    public void testConstructorAddsItems() {
        ItemDTO existantItemDTO;

        try {
            existantItemDTO = handler.searchInventory("Corn");

            boolean expResult = true;
            boolean result = existantItemDTO instanceof ItemDTO;

            assertEquals(expResult, result, "Incorrect object type.");
        }
        catch (InvalidItemIdentifierException | InventorySystemException exception) {
            fail("Unexpected exception.");
        }

    }

    @Test
    public void testSearchInventoryReturnsItemDTO() {
        Object item;

        try {
            item = handler.searchInventory("Corn");

            boolean expResult = true;
            boolean result = item instanceof ItemDTO;

            assertEquals(expResult, result, "Wrong return value.");
        }
        catch (InvalidItemIdentifierException | InventorySystemException exception) {
            fail("Unexpected exception.");
        }

    }

    @Test
    public void testSearchInventoryEmptyString() {
        String identifier = "";
        String identifierExpected = "Corn";

        try {
            identifierExpected = handler.searchInventory(identifier).getDescription();
            fail("An invalid item identifier was matched with an item.");
        }
        catch (InvalidItemIdentifierException | InventorySystemException exception) {
            assertTrue(identifierExpected.equals("Corn"), "identifier does not retain initial value 'Banana'.");
            assertTrue(exception instanceof InvalidItemIdentifierException, "Wrong exception type was thrown.");
        }
    }

    @Test
    public void testSearchInventoryNullArgument() {
        String identifier = null;
        String identifierExpected = "Corn";

        try {
            identifierExpected = handler.searchInventory(identifier).getDescription();
            fail("An invalid item identifier was matched with an item.");
        }
        catch (InvalidItemIdentifierException | InventorySystemException exception) {
            assertTrue(identifierExpected.equals("Corn"), "identifier does not retain initial value 'Banana'.");
            assertTrue(exception instanceof InvalidItemIdentifierException, "Wrong exception type was thrown.");
        }
    }

    @Test
    public void testDatabaseFailureException() {
        // HARDCODED: 'Apple' will trigger database failure.
        String identifier = "Cherry";

        try {
            identifier = handler.searchInventory(identifier).getDescription();
            fail("Expected database failure.");
        }
        catch (InvalidItemIdentifierException | InventorySystemException exception) {
            assertTrue(identifier.equals("Cherry"), "identifier does not retain initial value 'Apple'.");
            assertTrue(exception instanceof InventorySystemException, "Wrong exception type was thrown.");
        }
    }

    @Test
    public void testLogSale() {
        Sale sale = new Sale();

        try {
            sale.addItem(new ItemDTO(10, 0.06f, "Corn"), 1);
        }
        catch (InvalidQuantityException | InvalidItemDTOException exception) {
            fail("Unexpected exception of type: " + exception.getClass().toString());
        }

        sale.insertPayment(10);
        handler.logSale(sale.getSaleLog());
        java.util.ArrayList<SaleLog> sales = handler.getSales();

        boolean expResult = true;
        boolean result = sales.get(0) instanceof SaleLog;

        assertEquals(expResult, result, "Does not create an Object of type SaleLog");
    }

    @Test
    public void testLogSaleNullArgument() {
        handler.logSale(null);

        boolean expResult = true;
        boolean result = handler.getSales().size() == 0;

        assertEquals(expResult, result, "A Sale was wrongfully logged");
    }

}