package processSale.integration;

/**
 * Exception to be thrown if something fails whilst communicating with
 * the <code>InventorySystem</code>.
 */
public class InventorySystemException extends RuntimeException {
    /**
     * Creates an instance that represents what led to an error.
     * @param errorMessage The message representing what led to the error.
     */
    public InventorySystemException(String errorMessage) {
        super(errorMessage);
    }

}
