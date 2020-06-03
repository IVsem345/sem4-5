package processSaleTests.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processSale.integration.DBHandler;
import processSale.integration.InvalidItemIdentifierException;
import processSale.integration.InventorySystemException;
import processSale.model.InvalidItemDTOException;
import processSale.model.InvalidQuantityException;
import processSale.model.Sale;

class SaleTest {
    Sale sale;
    DBHandler handler;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        handler = DBHandler.getInstance();
    }

    @AfterEach
    public void tearDown() {
        sale = null;
        handler = null;
    }

    @Test
    public void testAddItem() {
        try {
            sale.addItem(handler.searchInventory("Corn"), 1);
            sale.insertPayment(0);
        } catch (InvalidItemIdentifierException | InvalidQuantityException | InvalidItemDTOException exception) {
            fail("Unexpected exception of type: " + exception.getClass().toString());
        }

        boolean expResult = true;
        boolean result = sale.getSaleLog().getItems().size() == 1;

        assertEquals(expResult, result, "Item was not added.");
    }

    @Test
    public void testAddItemNullArgument() {
        try {
            sale.addItem(null, 1);
            fail("An invalid type was added to the sale.");
        }
        catch (InvalidQuantityException | InvalidItemDTOException exception) {
            sale.insertPayment(0);

            boolean expResult = true;
            boolean result = sale.getSaleLog().getItems().size() == 0;

            assertEquals(expResult, result, "An Item was wrongfully added.");
            assertTrue(exception instanceof InvalidItemDTOException, "Wrong exception type was thrown.");
        }

    }

    @Test
    public void testAddItemInvalidQuantity() {
        try {
            sale.addItem(handler.searchInventory("Corn"), -1);
            fail("An item with an invalid quantity was added to the sale.");
        }
        catch (InvalidItemIdentifierException | InventorySystemException | InvalidItemDTOException | InvalidQuantityException exception) {
            sale.insertPayment(0);

            boolean expResult = true;
            boolean result = sale.getSaleLog().getItems().size() == 0;

            assertEquals(expResult, result, "An Item was wrongfully added.");
            assertTrue(exception instanceof InvalidQuantityException, "Wrong exception type was thrown.");
        }
    }

    @Test
    public void testInsertPayment() {
        try {
            sale.addItem(handler.searchInventory("Banana"), 3);
            sale.insertPayment(40);

            boolean expResult = true;
            boolean result = sale.getSaleLog().getAmountPaid() != 0 && sale.getSaleLog().getTotalTax() != 0 && sale.getSaleLog().getChange() != 0;

            assertEquals(expResult, result, "Operation did not calculate final values correctly");

            result = sale.getSaleLog().getItems().size() == 1 && sale.getReceipt().getItems().size() == 1;

            assertEquals(expResult, result, "Wrong number of items in SaleLog and Receipt populated by insertPayment()");
        }
        catch (InvalidItemIdentifierException | InventorySystemException | InvalidItemDTOException | InvalidQuantityException exception) {
            fail("Unexpected exception of type: " + exception.getClass().toString());
        }
    }
}