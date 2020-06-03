package processSaleTests.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processSale.dto.ItemDTO;
import processSale.integration.DBHandler;
import processSale.model.InvalidItemDTOException;
import processSale.model.InvalidQuantityException;
import processSale.model.Sale;
import processSale.model.SaleLog;

class ItemTest {
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
    public void testIncreaseQuantity() {
        Sale sale = new Sale();

        ItemDTO itemToAddTwice = new ItemDTO(10, 0.06f, "Corn");

        try {
            sale.addItem(itemToAddTwice, 1);
            sale.addItem(itemToAddTwice, 1);
        }
        catch (InvalidQuantityException | InvalidItemDTOException exception) {
            fail("Unexpected exception of type: " + exception.getClass().toString());
        }

        sale.insertPayment(20);
        handler.logSale(sale.getSaleLog());

        java.util.ArrayList<SaleLog> sales = handler.getSales();

        boolean expResult = true;
        boolean result = sales.size() == 1;

        assertEquals(expResult, result, "Item was added instead of quantity increasing.");
    }
}